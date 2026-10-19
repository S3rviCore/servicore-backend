package com.servicore.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Speciality;

public interface SpecialityRepository extends JpaRepository <Speciality, Long> {
    
    List<Speciality> findByCompany_Id(Long companyId);
    Optional<Speciality> findByIdAndCompany_Id(Long specialityId, Long companyId);

}