package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.WorkOrderMaterialRequestDTO;
import com.servicore.web.dtos.response.WorkOrderMaterialResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Material;
import com.servicore.web.models.WorkOrder;
import com.servicore.web.models.WorkOrderMaterial;
import com.servicore.web.repositories.MaterialRepository;
import com.servicore.web.repositories.WorkOrderMaterialRepository;
import com.servicore.web.repositories.WorkOrderRepository;

@Service 
public class WorkOrderMaterialService {
    
    private WorkOrderMaterialRepository workOrderMaterialRepository;
    private WorkOrderRepository workOrderRepository;
    private MaterialRepository materialRepository;

    public WorkOrderMaterialService(
        WorkOrderMaterialRepository workOrderMaterialRepository,
        WorkOrderRepository workOrderRepository,
        MaterialRepository materialRepository
    ) {
        this.workOrderMaterialRepository = workOrderMaterialRepository;
        this.workOrderRepository = workOrderRepository;
        this.materialRepository = materialRepository;
    }

    @Transactional(readOnly = true)
    public List<WorkOrderMaterialResponseDTO> getAllWorkOrderMaterialByCompanyId(Long companyId) {

        return workOrderMaterialRepository
                .findByMaterial_Company_Id(companyId)
                .stream()
                .map(this::toResponseDTO)
                .toList();

    }

    @Transactional 
    public WorkOrderMaterialResponseDTO createWorkOrderMaterial(WorkOrderMaterialRequestDTO dto) {

        WorkOrder workOrder = workOrderRepository
            .findById(dto.getWorkOrderId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Orden no encontrada")
            );

        Material material = materialRepository
            .findById(dto.getMaterialId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Material no encontrado.")
            );

        if (material.getStock().compareTo(dto.getQuantity()) < 0) {
            throw new IllegalArgumentException("Stock insuficiente para la cantidad solicitada.");
        }

        WorkOrderMaterial workOrderMaterial = new WorkOrderMaterial(workOrder, material, dto.getQuantity());

        WorkOrderMaterial saved = workOrderMaterialRepository.save(workOrderMaterial);

        return toResponseDTO(saved);
    }

    public WorkOrderMaterialResponseDTO toResponseDTO(WorkOrderMaterial dto) {
        return new WorkOrderMaterialResponseDTO(
            dto.getId(), 
            dto.getWorkOder().getId(), 
            dto.getMaterial().getId(), 
            dto.getQuantity()
        );
    }

}
