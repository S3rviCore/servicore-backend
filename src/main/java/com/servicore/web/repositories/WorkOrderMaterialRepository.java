package com.servicore.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.WorkOrderMaterial;

public interface WorkOrderMaterialRepository extends JpaRepository<WorkOrderMaterial, Long> {
    
    List<WorkOrderMaterial> findByMaterial_Company_Id(Long companyId);

}
