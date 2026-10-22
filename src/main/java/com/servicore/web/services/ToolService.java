package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.ToolRequestDTO;
import com.servicore.web.dtos.response.ToolResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.Tool;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.ToolRepository;

@Service 
public class ToolService {
    
    private ToolRepository toolRepository;
    private CompanyRepository companyRepository;

    public ToolService(
        ToolRepository toolRepository,
        CompanyRepository companyRepository
    ) {
        this.toolRepository = toolRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional(readOnly = true)
    public List<ToolResponseDTO> getToolsByCompanyId(Long companyId) {
        return toolRepository
            .findByCompany_Id(companyId)
            .stream()
            .map(this::toResponseDTO)
            .toList();
    }

    @Transactional
    public ToolResponseDTO createTool(ToolRequestDTO dto) {

        Company company = companyRepository
            .findById(dto.getCompanyId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Compania no encontrada.")
            );

        Tool tool = new Tool(
            dto.getName(), 
            dto.getSerialNumber(), 
            dto.getStatus(),
            company
        );

        Tool saved = toolRepository.save(tool);
        return toResponseDTO(saved);

    }

    @Transactional 
    public ToolResponseDTO updateTool(Long toolId, ToolRequestDTO dto) {
        
        Tool tool = toolRepository
            .findById(toolId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Herramienta no encontrada.")
            );

        tool.setName(dto.getName());
        tool.setSerialNumber(dto.getSerialNumber());
        tool.setStatus(dto.getStatus());

        return toResponseDTO(tool);
    }

    public ToolResponseDTO toResponseDTO(Tool tool) {
        return new ToolResponseDTO(
            tool.getId(), 
            tool.getName(), 
            tool.getSerialNumber(), 
            tool.getStatus(), 
            tool.getCompany().getId(), 
            tool.getCompany().getName()
        );
    }

}
