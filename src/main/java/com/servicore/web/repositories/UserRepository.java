package com.servicore.web.repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.User;

public interface UserRepository extends JpaRepository<User, Long> {
    
    List<User> findAllByCompany_Id(Long companyId);
    Optional<User> findByIdAndCompany_Id(Long id, Long companyId);
    boolean existsByEmail(String email);

}
