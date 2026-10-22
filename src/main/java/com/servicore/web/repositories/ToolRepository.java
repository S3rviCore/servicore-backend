package com.servicore.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Tool;

public interface ToolRepository extends JpaRepository<Tool, Long> {
    
    List<Tool> findByCompany_Id(Long companyId);

}
