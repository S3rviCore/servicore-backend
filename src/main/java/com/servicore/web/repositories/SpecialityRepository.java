package com.servicore.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Speciality;

public interface SpecialityRepository extends JpaRepository <Speciality, Long> {
    
    List<Speciality> findByCompany_Id(Long companyId);

}