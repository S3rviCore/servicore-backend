package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.WorkOrderStatusRequestDTO;
import com.servicore.web.dtos.response.WorkOrderStatusResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.WorkOrderStatus;
import com.servicore.web.repositories.WorkOrderStatusRepository;

@Service 
public class WorkOrderStatusService {
    
    private WorkOrderStatusRepository workOrderStatusRepository;

    public WorkOrderStatusService(WorkOrderStatusRepository workOrderStatusRepository) {
        this.workOrderStatusRepository = workOrderStatusRepository;
    }

    @Transactional(readOnly = true)
    public List<WorkOrderStatusResponseDTO> getAllWorkOrderStatuses() {
        
        return workOrderStatusRepository
                .findAll()
                .stream()
                .map(this::toResponseDTO)
                .toList();

    }

    @Transactional 
    public WorkOrderStatusResponseDTO createWorkOrderStatus(WorkOrderStatusRequestDTO dto) {

        WorkOrderStatus workOrderStatus = new WorkOrderStatus(dto.getName());
        WorkOrderStatus saved = workOrderStatusRepository.save(workOrderStatus);
        return toResponseDTO(saved);

    }

    @Transactional 
    public WorkOrderStatusResponseDTO updateWorkOrderStatus(
        Long workOrderStatusId,
        WorkOrderStatusRequestDTO dto
    ) {
        WorkOrderStatus workOrderStatus = workOrderStatusRepository
            .findById(workOrderStatusId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Estado de orden no encontrado.")
            );

        workOrderStatus.setName(dto.getName());

        return toResponseDTO(workOrderStatus);
    }

    public WorkOrderStatusResponseDTO toResponseDTO(WorkOrderStatus workOrderStatus) {
        return new WorkOrderStatusResponseDTO(workOrderStatus.getName());
    }

}
