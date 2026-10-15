package com.servicore.web.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.WorkOrderStatusRequestDTO;
import com.servicore.web.dtos.response.WorkOrderStatusResponseDTO;
import com.servicore.web.services.WorkOrderStatusService;

@RestController 
@RequestMapping("/api/work-order-statuses")
@CrossOrigin 
public class WorkOrderStatusController {
    
    private WorkOrderStatusService workOrderStatusService;

    public WorkOrderStatusController(WorkOrderStatusService workOrderStatusService) {
        this.workOrderStatusService = workOrderStatusService;
    }

    @GetMapping 
    public List<WorkOrderStatusResponseDTO> getAllWorkOrderStatuses() {
        return workOrderStatusService.getAllWorkOrderStatuses();
    }

    @PostMapping 
    public WorkOrderStatusResponseDTO createWorkOrderStatus(WorkOrderStatusRequestDTO dto) {
        return workOrderStatusService.createWorkOrderStatus(dto);
    }

    @PutMapping("/{workOrderStatusId}")
    public WorkOrderStatusResponseDTO updateWorkOrderStatus(
        Long workOrderStatusId,
        WorkOrderStatusRequestDTO dto
    ) {
        return workOrderStatusService.updateWorkOrderStatus(workOrderStatusId, dto);
    }

}
