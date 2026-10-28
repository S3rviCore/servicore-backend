package com.servicore.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.Payment;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    
    List<Payment> findByCompany_Id(Long companyId);

}
