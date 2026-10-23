package com.servicore.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Material;

public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findByCompany_Id(Long companyId); 
}
