package com.servicore.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.WorkOrder;

public interface WorkOrderRepository extends JpaRepository<WorkOrder, Long> {
    
    List<WorkOrder> findByCompany_Id(Long companyId);

    Optional<WorkOrder> findTopByCompany_IdAndOrderCodeStartingWithOrderByOrderCodeDesc(
        Long companyId,
        String prefix
    );

}
