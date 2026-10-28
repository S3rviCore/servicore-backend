package com.servicore.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.PaymentStatusRequestDTO;
import com.servicore.web.dtos.response.PaymentStatusResponseDTO;
import com.servicore.web.services.PaymentStatusService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/payment-statuses")
@CrossOrigin 
public class PaymentStatusController {
    
    private PaymentStatusService paymentStatusService;

    public PaymentStatusController(
        PaymentStatusService paymentStatusService
    ) {
        this.paymentStatusService = paymentStatusService;
    }

    @GetMapping("/{companyId}")
    public List<PaymentStatusResponseDTO> getAllPaymentStatusByCompanyId(
        @PathVariable Long companyId
    ) {
        return paymentStatusService.getAllPaymentStatusByCompanyId(companyId);
    }

    @PostMapping("/{companyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentStatusResponseDTO createPaymentStatus(
        @PathVariable Long companyId,
        @Valid @RequestBody PaymentStatusRequestDTO dto
    ) {
        return paymentStatusService.createPaymentStatus(companyId, dto);
    }

    @PutMapping("/{companyId}/{paymentStatusId}")
    public PaymentStatusResponseDTO updatePaymentStatus(
        @PathVariable Long companyId,
        @PathVariable Long paymentStatusId,
        @Valid @RequestBody PaymentStatusRequestDTO dto
    ) {
        return paymentStatusService.updatePaymentStatus(companyId, paymentStatusId, dto);
    }

}
