package com.servicore.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.PriorityRequestDTO;
import com.servicore.web.dtos.response.PriorityResponseDTO;
import com.servicore.web.services.PriorityService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/priorities")
public class PriorityController {
    
    private PriorityService priorityService;
    
    public PriorityController(PriorityService priorityService) {
        this.priorityService = priorityService;
    }

    @GetMapping 
    public List<PriorityResponseDTO> getAllPriorities() {
        return priorityService.getAllPriorities();
    }

    @PostMapping 
    @ResponseStatus(HttpStatus.CREATED)
    public PriorityResponseDTO createPriority(@Valid @RequestBody PriorityRequestDTO dto) {
        return priorityService.createPriority(dto);
    }

    @PutMapping("/{priorityId}")
    public PriorityResponseDTO updatePriority(
        @PathVariable Long priorityId, 
        @RequestBody PriorityRequestDTO dto
    ) {
        return priorityService.updatePriority(priorityId, dto);
    }

}
