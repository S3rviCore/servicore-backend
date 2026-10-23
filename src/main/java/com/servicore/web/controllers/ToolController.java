package com.servicore.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.ToolRequestDTO;
import com.servicore.web.dtos.response.ToolResponseDTO;
import com.servicore.web.services.ToolService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/tools")
@CrossOrigin 
public class ToolController {
    
    private final ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @GetMapping("/{companyId}")
    public List<ToolResponseDTO> getToolsByCompanyId(@PathVariable Long companyId) {
        return toolService.getToolsByCompanyId(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ToolResponseDTO createTool(@Valid @RequestBody ToolRequestDTO dto) {
        return toolService.createTool(dto);
    }

    @PutMapping("/{toolId}")
    public ToolResponseDTO updateTool(
        @PathVariable Long toolId, 
        @Valid @RequestBody ToolRequestDTO dto
    ) {
        return toolService.updateTool(toolId, dto);
    }

}
