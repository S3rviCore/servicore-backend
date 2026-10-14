package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.PriorityRequestDTO;
import com.servicore.web.dtos.response.PriorityResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Priority;
import com.servicore.web.repositories.PriorityRepository;

@Service 
public class PriorityService {
    
    private PriorityRepository priorityRepository;

    public PriorityService(PriorityRepository priorityRepository) {
        this.priorityRepository = priorityRepository;
    }

    @Transactional(readOnly = true)
    public List<PriorityResponseDTO> getAllPriorities() {
        return priorityRepository
            .findAll()
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    @Transactional 
    public PriorityResponseDTO createPriority(PriorityRequestDTO dto) {
        Priority priority = new Priority(dto.getName());
        Priority saved = priorityRepository.save(priority);
        return toResponseDTO(saved);
    }

    @Transactional 
    public PriorityResponseDTO updatePriority(Long priorityId, PriorityRequestDTO dto) {

        Priority priority = priorityRepository
            .findById(priorityId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Prioridad no encontrada.")
            );

        priority.setName(dto.getName());

        return toResponseDTO(priority);

    }

    public PriorityResponseDTO toResponseDTO(Priority priority) {
        return new PriorityResponseDTO(priority.getName());
    }

}
