package com.servicore.web.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.servicore.web.dtos.request.UserRequestDTO;
import com.servicore.web.dtos.response.UserResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.Role;
import com.servicore.web.models.User;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.RoleRepository;
import com.servicore.web.repositories.UserRepository;

@Service 
public class UserService {
    
    private final UserRepository userRepostory;
    private final RoleRepository roleRepository;
    private final CompanyRepository companyRepository;
    private final PasswordEncoder passwordEncoder;
    private final TemporaryPasswordGenerator passwordGenerator;

    public UserService(
        UserRepository userRepostory,
        RoleRepository roleRepository,
        CompanyRepository companyRepository,
        PasswordEncoder passwordEncoder,
        TemporaryPasswordGenerator passwordGenerator
    ) {
        this.userRepostory = userRepostory;
        this.roleRepository = roleRepository;
        this.companyRepository = companyRepository;
        this.passwordEncoder = passwordEncoder;
        this.passwordGenerator = passwordGenerator;
    }

    @Transactional(readOnly = true)
    public List<UserResponseDTO> getAllUsers(Long companyId) {
        
        return userRepostory.findAllByCompany_Id(companyId)
            .stream()
            .map(user -> new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().getId(),
                user.getRole().getName()
            ))
            .toList();

    }

    @Transactional 
    public UserResponseDTO updateUser(
        Long id,
        Long companyId,
        UserRequestDTO dto 
    ) {

        User user = userRepostory
                .findByIdAndCompany_Id(id, companyId)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Usuario no encontrado en esta compania.")
                );

        user.setName(dto.getName());
        user.setLastName(dto.getLastName());
        user.setEmail(dto.getEmail());

        Role role = roleRepository
                .findByIdAndCompany_Id(dto.getRoleId(), companyId)
                .orElseThrow(() -> 
                    new ResourceNotFoundException("Rol no encontrado en esta compania.")
                );

        user.setRole(role);

        return new UserResponseDTO(
            user.getId(), 
            user.getName(), 
            user.getLastName(), 
            user.getEmail(), 
            user.getRole().getId(), 
            user.getRole().getName()
        );
    }

    @Transactional 
    public UserResponseDTO createUser(
        Long companyId,
        UserRequestDTO dto
    ) {

        if (userRepostory.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "El email ya esta registrado.");
        }

        Company company = companyRepository.findById(companyId)
                    .orElseThrow(() -> new ResourceNotFoundException("No se encontro esa compania"));

        Role role = roleRepository.findByIdAndCompany_Id(dto.getRoleId(), companyId)
        .orElseThrow(() -> new ResourceNotFoundException("Rol no encontrado."));

        String temporaryPassword = passwordGenerator.generate();
        String encodedPassword = passwordEncoder.encode(temporaryPassword);

        User user = new User(
            dto.getName(),
            dto.getLastName(),
            dto.getEmail(),
            encodedPassword,
            role,
            company
        );

        user.setMustChangePassword(true);

        User savedUser = userRepostory.save(user);
        return toResponseDTO(savedUser);
    }

    private UserResponseDTO toResponseDTO(User user) {

        return new UserResponseDTO(
                user.getId(),
                user.getName(),
                user.getLastName(),
                user.getEmail(),
                user.getRole().getId(),
                user.getRole().getName());
    }

}
