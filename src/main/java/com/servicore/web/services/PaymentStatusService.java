package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.PaymentStatusRequestDTO;
import com.servicore.web.dtos.response.PaymentStatusResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.PaymentStatus;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.PaymentStatusRepository;

@Service 
public class PaymentStatusService {
    
    private PaymentStatusRepository paymentStatusRepository;
    private CompanyRepository companyRepository;

    public PaymentStatusService(
        PaymentStatusRepository paymentStatusRepository,
        CompanyRepository companyRepository
    ) {
        this.paymentStatusRepository = paymentStatusRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional(readOnly = true)
    public List<PaymentStatusResponseDTO> getAllPaymentStatusByCompanyId(
        Long companyId
    ) {

        return paymentStatusRepository
            .findByCompany_Id(companyId)
            .stream()
            .map(this::toResponseDTO)
            .toList();

    }

    @Transactional 
    public PaymentStatusResponseDTO createPaymentStatus(
        Long companyId,
        PaymentStatusRequestDTO dto
    ) {

        Company company = companyRepository
            .findById(companyId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Compania no encontrada.")
            );

        PaymentStatus paymentStatus = new PaymentStatus(
            company,
            dto.getName(), 
            dto.getDescription()
        );

        PaymentStatus saved = paymentStatusRepository.save(paymentStatus);

        return toResponseDTO(saved);

    }

    @Transactional 
    public PaymentStatusResponseDTO updatePaymentStatus(
        Long companyId,
        Long paymentStatusId,
        PaymentStatusRequestDTO dto
    ) {

        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Compania no encontrado.");
        }

        PaymentStatus paymentStatus = paymentStatusRepository
            .findById(paymentStatusId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("El estado no fue encontrado.")
            );

        paymentStatus.setName(dto.getName());
        paymentStatus.setDescription(dto.getDescription());
        
        return toResponseDTO(paymentStatus);

    }   

    public PaymentStatusResponseDTO toResponseDTO(PaymentStatus paymentStatus) {
        return new PaymentStatusResponseDTO(paymentStatus.getName(), paymentStatus.getDescription());
    }

}
