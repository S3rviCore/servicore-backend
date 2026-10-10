package com.servicore.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.RolePermission;
import com.servicore.web.models.RolePermissionId;

public interface RolePermissionRepository
        extends JpaRepository<RolePermission, RolePermissionId> {

    List<RolePermission> findByRole_Id(Long roleId);

    boolean existsByRole_IdAndPermission_Id(
        Long roleId,
        Long permissionId
    );
}