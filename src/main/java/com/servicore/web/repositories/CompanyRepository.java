package com.servicore.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Company;

public interface CompanyRepository extends JpaRepository<Company, Long> {
    
}
