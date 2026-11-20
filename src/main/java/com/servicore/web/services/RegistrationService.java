package com.servicore.web.services;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.servicore.web.dtos.request.RegisterRequestDTO;
import com.servicore.web.dtos.response.RegisterResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.Country;
import com.servicore.web.models.Departament;
import com.servicore.web.models.Municipality;
import com.servicore.web.models.Role;
import com.servicore.web.models.User;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.CountryRepository;
import com.servicore.web.repositories.DepartamentRepository;
import com.servicore.web.repositories.MunicipalityRepository;
import com.servicore.web.repositories.RoleRepository;
import com.servicore.web.repositories.UserRepository;

@Service 
public class RegistrationService {
    
    private final UserRepository userRepository;
    private final CompanyRepository companyRepository;
    private final RoleRepository roleRepository;
    private final CountryRepository countryRepository;
    private final DepartamentRepository departamentRepository;
    private final MunicipalityRepository municipalityRepository;
    private final PasswordEncoder passwordEncoder;

    public RegistrationService(
        UserRepository userRepository,
        CompanyRepository companyRepository,
        RoleRepository roleRepository,
        CountryRepository countryRepository,
        DepartamentRepository departamentRepository,
        MunicipalityRepository municipalityRepository,
        PasswordEncoder passwordEncoder
    ) {
        this.userRepository = userRepository;
        this.companyRepository = companyRepository;
        this.roleRepository = roleRepository;
        this.countryRepository = countryRepository;
        this.departamentRepository = departamentRepository;
        this.municipalityRepository = municipalityRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional 
    public RegisterResponseDTO register(RegisterRequestDTO dto) {

        String email = dto.getEmail().trim().toLowerCase();

        if (userRepository.existsByEmail(email)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "El email ya esta registrado.");
        }

        Country country = countryRepository.findById(dto.getCountryId())
            .orElseThrow(() -> new ResourceNotFoundException("El pais no fue encontrado."));

        Departament departament = departamentRepository
            .findByIdAndCountryId(dto.getDepartamentId(), country.getId())
            .orElseThrow(() -> new ResourceNotFoundException("El departamento no pertenece al pais seleccionado."));

        Municipality municipality = municipalityRepository
            .findByIdAndDepartamentId(dto.getMunicipalityId(), departament.getId())
            .orElseThrow(() -> new ResourceNotFoundException("El municipio no pertenece al departamento seleccionado."));

        Company company = companyRepository.save(new Company(
            null,
            dto.getCompanyName().trim(),
            dto.getCompanyEmail().trim().toLowerCase(),
            dto.getCompanyDescription() == null ? null : dto.getCompanyDescription().trim(),
            country,
            departament,
            municipality
        ));

        Role adminRole = roleRepository.save(new Role("Administrador", "Administrador de la compania", company));

        User user = new User(
            dto.getName().trim(),
            dto.getLastName().trim(),
            email,
            passwordEncoder.encode(dto.getPassword()),
            adminRole,
            company
        );
        user.setMustChangePassword(false);

        User savedUser = userRepository.save(user);

        return new RegisterResponseDTO(
            savedUser.getId(),
            savedUser.getName(),
            savedUser.getLastName(),
            savedUser.getEmail(),
            company.getId(),
            company.getName(),
            company.getDescription(),
            company.getEmail(),
            country.getName(),
            departament.getName(),
            municipality.getName(),
            adminRole.getName(),
            null
        );

    }

}
