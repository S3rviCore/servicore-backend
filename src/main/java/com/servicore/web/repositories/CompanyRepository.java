package com.servicore.web.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
    Optional<Company> findById(Long id);

}
