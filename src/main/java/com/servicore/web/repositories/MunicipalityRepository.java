package com.servicore.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Municipality;

public interface MunicipalityRepository extends JpaRepository<Municipality, Long> {
    
    List<Municipality> findByDepartamentIdOrderByNameAsc(Long departamentId);
    Optional<Municipality> findByIdAndDepartamentId(Long id, Long departamentId);

}
