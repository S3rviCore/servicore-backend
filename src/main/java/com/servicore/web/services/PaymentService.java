package com.servicore.web.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.servicore.web.dtos.request.PaymentRequestDTO;
import com.servicore.web.dtos.response.PaymentResponseDTO;
import com.servicore.web.exceptions.ResourceNotFoundException;
import com.servicore.web.models.Company;
import com.servicore.web.models.Payment;
import com.servicore.web.models.PaymentMethod;
import com.servicore.web.models.PaymentStatus;
import com.servicore.web.models.WorkOrder;
import com.servicore.web.repositories.CompanyRepository;
import com.servicore.web.repositories.PaymentMethodRepository;
import com.servicore.web.repositories.PaymentRepository;
import com.servicore.web.repositories.PaymentStatusRepository;
import com.servicore.web.repositories.WorkOrderRepository;

@Service 
public class PaymentService {
    
    private PaymentRepository paymentRepository;
    private CompanyRepository companyRepository;
    private WorkOrderRepository workOrderRepository;
    private PaymentMethodRepository paymentMethodRepository;
    private PaymentStatusRepository paymentStatusRepository;

    public PaymentService(
        PaymentRepository paymentRepository,
        CompanyRepository companyRepository,
        WorkOrderRepository workOrderRepository,
        PaymentMethodRepository paymentMethodRepository,
        PaymentStatusRepository paymentStatusRepository
    ) {
        this.paymentRepository = paymentRepository;
        this.companyRepository = companyRepository;
        this.workOrderRepository = workOrderRepository;
        this.paymentMethodRepository = paymentMethodRepository;
        this.paymentStatusRepository = paymentStatusRepository;
    }

    @Transactional(readOnly = true)
    public List<PaymentResponseDTO> getAllPaymentsByCompanyId(
        Long companyId
    ) {
        
        return paymentRepository
            .findByCompany_Id(companyId)
            .stream()
            .map(this::toResponseDTO)
            .toList();

    }

    @Transactional
    public PaymentResponseDTO createPayment(
        Long companyId,
        PaymentRequestDTO dto
    ) {

        Company company = companyRepository
            .findById(companyId)
            .orElseThrow(() ->
                new ResourceNotFoundException("Compania no encontrada.")
            );

        WorkOrder workOrder = workOrderRepository
            .findById(dto.getWorkOrderId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Orden no encontrada.")
            );

        PaymentMethod paymentMethod = paymentMethodRepository
            .findById(dto.getPaymentMethodId())
            .orElseThrow(() -> 
                new ResourceNotFoundException("Metodo de pago no encontrado.")
            );

        PaymentStatus paymentStatus = paymentStatusRepository
            .findById(dto.getPaymentStatusId())
            .orElseThrow(() ->
                new ResourceNotFoundException("El estado del pago no fue encontrado.")
            );

        Payment payment = new Payment(
            company, 
            workOrder, 
            paymentMethod, 
            paymentStatus, 
            dto.getAmount(), 
            dto.getTransactionReference(), 
            dto.getPaidAt(), 
            dto.getNote()
        );

        Payment saved = paymentRepository.save(payment);

        return toResponseDTO(saved);
    }

    public PaymentResponseDTO toResponseDTO(Payment payment) {
        return new PaymentResponseDTO(
            payment.getWorkOrder().getId(), 
            payment.getPaymentMethod().getId(), 
            payment.getPaymentMethod().getName(), 
            payment.getPaymentStatus().getId(), 
            payment.getPaymentStatus().getName(), 
            payment.getAmount(), 
            payment.getTransactionReference(), 
            payment.getPaidAt(), 
            payment.getNote()
        );
    }

}
