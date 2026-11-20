package com.servicore.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Departament;

public interface DepartamentRepository extends JpaRepository<Departament, Long> {
     List<Departament> findByCountryIdOrderByNameAsc(Long countryId);
     Optional<Departament> findByIdAndCountryId(Long id, Long countryId);
}
