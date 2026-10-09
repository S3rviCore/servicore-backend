package com.servicore.web.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Country;

public interface CountryRepository extends JpaRepository<Country, Long> {
    
    Optional<Country> findById(Long id);

}
