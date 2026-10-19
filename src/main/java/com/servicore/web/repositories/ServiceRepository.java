package com.servicore.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.ServiceModel;

public interface ServiceRepository extends JpaRepository<ServiceModel, Long> {
    
    List<ServiceModel> findByCompany_Id(Long companyId);

}