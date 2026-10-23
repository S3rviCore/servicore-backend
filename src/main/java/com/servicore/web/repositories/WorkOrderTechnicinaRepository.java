package com.servicore.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.WorkOrderTechnician;

public interface WorkOrderTechnicinaRepository extends JpaRepository<WorkOrderTechnician, Long> {
    List<WorkOrderTechnician> findByWorkOrder_Id(Long workOrderId);
}
