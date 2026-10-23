package com.servicore.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.WorkOrderTechnicianTool;

public interface WorkOrderTechnicianToolRepository extends JpaRepository<WorkOrderTechnicianTool, Long> {
    List<WorkOrderTechnicianTool> findByWorkOrderTechnician_Id(
        Long workOrderTechnicianId
    );   
}
