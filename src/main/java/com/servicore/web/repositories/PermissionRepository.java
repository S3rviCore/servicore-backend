package com.servicore.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Long> {
    
}
