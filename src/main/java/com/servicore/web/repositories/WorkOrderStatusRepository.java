package com.servicore.web.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.WorkOrderStatus;

public interface WorkOrderStatusRepository extends JpaRepository<WorkOrderStatus, Long> {
    
}
