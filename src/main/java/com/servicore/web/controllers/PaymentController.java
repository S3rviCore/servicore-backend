package com.servicore.web.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.PaymentRequestDTO;
import com.servicore.web.dtos.response.PaymentResponseDTO;
import com.servicore.web.services.PaymentService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/payments")
@CrossOrigin 
public class PaymentController {
    
    private PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/{companyId}")
    public List<PaymentResponseDTO> getAllPaymentsByCompanyId(
        @PathVariable Long companyId
    ) {
        return paymentService.getAllPaymentsByCompanyId(companyId);
    }

    @PostMapping("/{companyId}")
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentResponseDTO createPayment(
        @PathVariable Long companyId,
        @Valid @RequestBody PaymentRequestDTO dto
    ) {
        return paymentService.createPayment(companyId, dto);
    }

}
