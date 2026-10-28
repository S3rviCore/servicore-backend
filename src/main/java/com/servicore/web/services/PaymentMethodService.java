package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.PaymentMethodRequestDTO;
import com.servicore.web.dtos.response.PaymentMethodResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.PaymentMethod;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.PaymentMethodRepository;

@Service 
public class PaymentMethodService {
    
    private PaymentMethodRepository paymentMethodRepository;
    private CompanyRepository companyRepository;

    public PaymentMethodService(
        PaymentMethodRepository paymentMethodRepository,
        CompanyRepository companyRepository
    ) {
        this.paymentMethodRepository = paymentMethodRepository;
        this.companyRepository = companyRepository;
    }

    @Transactional(readOnly = true)
    public List<PaymentMethodResponseDTO> getAllPaymentMethodsByCompanyId(Long companyId) {

        return paymentMethodRepository
            .findByCompany_Id(companyId)
            .stream()
            .map(this::toResponseDTO)
            .toList();

    }

    @Transactional 
    public PaymentMethodResponseDTO createPaymentMethod(
        PaymentMethodRequestDTO dto
    ) {

        Company company = companyRepository
            .findById(dto.getCompanyId())
            .orElseThrow(() ->
                new ResourceNotFoundException("")
            );

        PaymentMethod paymentMethod = new PaymentMethod(
            company,
            dto.getName(), 
            dto.getDescription()
        );

        PaymentMethod saved = paymentMethodRepository.save(paymentMethod);

        return toResponseDTO(saved);

    }

    @Transactional 
    public PaymentMethodResponseDTO updatePaymentMethod(
        Long companyId,
        Long paymentMethodId,
        PaymentMethodRequestDTO dto
    ) {

        if (!companyRepository.existsById(companyId)) {
            throw new ResourceNotFoundException("Compania no encontrada.");
        }

        PaymentMethod paymentMethod = paymentMethodRepository
            .findById(paymentMethodId)
            .orElseThrow(() -> 
                new ResourceNotFoundException("Metodo de pago no encontrado.")
            );

        paymentMethod.setName(dto.getName());
        paymentMethod.setDescription(dto.getDescription());

        return toResponseDTO(paymentMethod);

    }

    public PaymentMethodResponseDTO toResponseDTO(PaymentMethod paymentMethod) {
        return new PaymentMethodResponseDTO(
            paymentMethod.getCompany().getId(), 
            paymentMethod.getCompany().getName(), 
            paymentMethod.getName(), 
            paymentMethod.getDescription()
        );
    }

}
