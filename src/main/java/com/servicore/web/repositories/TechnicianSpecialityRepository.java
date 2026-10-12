package com.servicore.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.TechnicianSpecialities;
import com.servicore.web.models.TechnicianSpecialityId;

public interface TechnicianSpecialityRepository extends JpaRepository<TechnicianSpecialities, TechnicianSpecialityId> {
    
    List<TechnicianSpecialities> findByTechnician_User_Company_Id(Long companyId);
    
    Optional<TechnicianSpecialities> findByTechnician_IdAndSpeciality_IdAndTechnician_User_Company_Id(Long technicianId, Long specialityId, Long companyId);

    boolean existsByTechnician_IdAndSpeciality_Id(
        Long technicianId,
        Long specialityId
    );

}
