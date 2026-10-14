package com.servicore.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Priority;

public interface PriorityRepository extends JpaRepository<Priority, Long> {
    
}
