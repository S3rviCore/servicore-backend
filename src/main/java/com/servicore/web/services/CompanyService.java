package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.CompanyRequestDTO;
import com.servicore.web.dtos.response.CompanyResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.Country;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.CountryRepository;

@Service 
public class CompanyService {
    
    private final CompanyRepository companyRepository;
    private final CountryRepository countryRepository;

    public CompanyService(
        CompanyRepository companyRepository,
        CountryRepository countryRepository
    ) {
        this.companyRepository = companyRepository;
        this.countryRepository = countryRepository;
    }

    @Transactional(readOnly = true)
    public List<CompanyResponseDTO> getAllCompanies() {
        return companyRepository.findAll()
                .stream()
                .map(company -> new CompanyResponseDTO(
                    company.getId(), 
                    company.getLogoUrl(), 
                    company.getName(), 
                    company.getEmail(), 
                    company.getDescription(), 
                    company.getCountry().getId(), 
                    company.getCountry().getName()
                ))
                .toList();
    }

    @Transactional 
    public CompanyResponseDTO updateCompany(
        Long id,
        CompanyRequestDTO dto
    ) {
        
        Company company = companyRepository
                    .findById(id)
                    .orElseThrow(() -> 
                        new ResourceNotFoundException("Compania no encontrada.")
                    );

        Country country = countryRepository.findById(dto.getCountryId())
                    .orElseThrow(() -> 
                        new ResourceNotFoundException("Pais no encontrado")
                    );

        company.setLogoUrl(dto.getLogoUrl());
        company.setName(dto.getName());
        company.setEmail(dto.getEmail());
        company.setDescription(dto.getDescription());
        company.setCountry(country);

        return toResponseDTO(company);
    }

    @Transactional 
    public CompanyResponseDTO createCompany(
        CompanyRequestDTO dto
    ) {
        
        Country country = countryRepository.findById(dto.getCountryId())
            .orElseThrow(() ->
                new ResourceNotFoundException("El pais no fue encontrado.")
            );

        Company company = new Company(
            dto.getLogoUrl(), 
            dto.getName(), 
            dto.getEmail(), 
            dto.getDescription(), 
            country
        );

        Company companySaved = companyRepository.save(company);

        return toResponseDTO(companySaved);
    }

    private CompanyResponseDTO toResponseDTO(Company company) {
        return new CompanyResponseDTO(
            company.getId(),
            company.getLogoUrl(),
            company.getName(),
            company.getEmail(),
            company.getDescription(),
            company.getCountry().getId(),
            company.getCountry().getName()
        );
    }

}
