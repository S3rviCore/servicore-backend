package com.servicore.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.WorkOrderRequestDTO;
import com.servicore.web.dtos.response.WorkOrderResponseDTO;
import com.servicore.web.services.WorkOrderService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/work-orders")
@CrossOrigin 
public class WorkOrderController {
    
    private final WorkOrderService workOrderService;

    public WorkOrderController(WorkOrderService workOrderService) {
        this.workOrderService = workOrderService;
    }

    @GetMapping("/{companyId}") 
    public List<WorkOrderResponseDTO> getWorkOrdersByCompanyId(
        @PathVariable Long companyId
    ) {
        return workOrderService.getWorkOrdersByCompanyId(companyId);
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public WorkOrderResponseDTO createWorkOrder(
        @Valid @RequestBody WorkOrderRequestDTO dto
    ) {
        return workOrderService.createWorkOrder(dto);
    }

}
