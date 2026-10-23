package com.servicore.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Unit;

public interface UnitRepository extends JpaRepository<Unit, Long> {
    
}
