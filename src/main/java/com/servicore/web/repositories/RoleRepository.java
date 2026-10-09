package com.servicore.web.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Role;

public interface RoleRepository extends JpaRepository<Role, Long> {
    
    Optional<Role> findByIdAndCompany_Id(Long id, Long companyId);

}
