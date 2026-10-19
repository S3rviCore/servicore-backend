package com.servicore.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Technician;

public interface TechnicianRepository extends JpaRepository <Technician, Long> {
    
    List<Technician> findByUser_Company_Id(Long companyId);
    Optional<Technician> findByIdAndUser_Company_Id(Long technicianId, Long companyId);
    boolean existsByUser_Id(Long userId);

}
