package com.servicore.web.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.servicore.web.models.PaymentStatus;

public interface PaymentStatusRepository extends JpaRepository<PaymentStatus, Long> {
    List<PaymentStatus> findByCompany_Id(Long companyId);
}
