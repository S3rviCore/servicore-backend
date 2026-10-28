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

import com.servicore.web.dtos.request.WorkOrderMaterialRequestDTO;
import com.servicore.web.dtos.response.WorkOrderMaterialResponseDTO;
import com.servicore.web.services.WorkOrderMaterialService;

@RestController 
@RequestMapping("/api/work-order-materials")
@CrossOrigin 
public class WorkOrderMaterialController {
    
    private WorkOrderMaterialService workOrderMaterialService;

    private WorkOrderMaterialController(WorkOrderMaterialService workOrderMaterialService) {
        this.workOrderMaterialService = workOrderMaterialService;
    }

    @GetMapping("/{companyId}")
    public List<WorkOrderMaterialResponseDTO> getAllWorkOrderMaterialByCompanyId(
        @PathVariable Long companyId
    ) {
        return workOrderMaterialService.getAllWorkOrderMaterialByCompanyId(companyId);
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public WorkOrderMaterialResponseDTO createWorkOrderMaterial(
        @RequestBody WorkOrderMaterialRequestDTO dto
    ) {
        return workOrderMaterialService.createWorkOrderMaterial(dto);
    }

}
