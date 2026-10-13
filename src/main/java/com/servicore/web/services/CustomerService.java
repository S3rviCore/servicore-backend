package com.servicore.web.services;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.servicore.web.dtos.request.CustomerRequestDTO;
import com.servicore.web.dtos.response.CustomerResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.Country;
import com.servicore.web.models.Customer;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.CountryRepository;
import com.servicore.web.repositories.CustomerRepository;

@Service 
public class CustomerService {
    
    private final CustomerRepository customerRepository;
    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;

    public CustomerService(
        CustomerRepository customerRepository,
        CompanyRepository companyRepository,
        CountryRepository countryRepository
    ) {
        this.customerRepository = customerRepository;
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getAllCustomers() {

        return customerRepository
                .findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();

    }

    @Transactional(readOnly = true)
    public List<CustomerResponseDTO> getAllCustomersByCompanyId(Long companyId) {

        return customerRepository
                .findByCompany_Id(companyId)
                .stream()
                .map(this::toResponseDTO)
                .toList();

    }

    @Transactional 
    public CustomerResponseDTO createCustomer(CustomerRequestDTO dto) {

        Company company = companyRepository
            .findById(dto.getCompanyId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Compania no encontrada.")
            );
    
        Country country = countryRepository
            .findById(dto.getCountryId())
            .orElseThrow(() ->
                new ResourceNotFoundException("Pais no encontrado.")
            );

        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "El email ya esta registrado."
            );
        }

        Customer customer = new Customer(
            company, 
            dto.getName(), 
            dto.getLastName(), 
            dto.getPhone(), 
            dto.getEmail(), 
            country, 
            dto.getAddress(), 
            dto.getStatus()
        );

        Customer saved = customerRepository.save(customer);
        
        return toResponseDTO(saved);
    }

    @Transactional 
    public CustomerResponseDTO updateCustomer(
        Long customerId,
        CustomerRequestDTO dto
    ) {

        Customer customer = customerRepository
            .findById(customerId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Cliente no encontrado")
            );
    
        Country country = countryRepository
            .findById(dto.getCountryId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Pais no encontrado")
            );
        
        if (customerRepository.existsByEmail(dto.getEmail())) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT, "El email ya esta registrado."
            );
        }

        customer.setName(dto.getName());
        customer.setLastName(dto.getLastName());
        customer.setPhone(dto.getPhone());
        customer.setEmail(dto.getEmail());
        customer.setCountry(country);
        customer.setAddress(dto.getAddress());
        customer.setStatus(dto.getStatus());

        return toResponseDTO(customer);
    }

    public CustomerResponseDTO toResponseDTO(Customer customer) {
        return new CustomerResponseDTO(
            customer.getCompany().getId(), 
            customer.getCompany().getName(), 
            customer.getId(), 
            customer.getName(), 
            customer.getLastName(), 
            customer.getPhone(), 
            customer.getEmail(), 
            customer.getCountry().getName(), 
            customer.getAddress(), 
            customer.getStatus()
        );
    }

}
