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

import com.servicore.web.dtos.request.PaymentMethodRequestDTO;
import com.servicore.web.dtos.response.PaymentMethodResponseDTO;
import com.servicore.web.services.PaymentMethodService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/payment-methods")
@CrossOrigin 
public class PaymentMethodController {
    
    private PaymentMethodService paymentMethodService;

    public PaymentMethodController(PaymentMethodService paymentMethodService) {
        this.paymentMethodService = paymentMethodService;
    }

    @GetMapping("/{companyId}")
    public List<PaymentMethodResponseDTO> getAllPaymentMethodsByCompanyId(@PathVariable Long companyId) {
        return paymentMethodService.getAllPaymentMethodsByCompanyId(companyId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public PaymentMethodResponseDTO createPaymentMethod(
        @Valid @RequestBody PaymentMethodRequestDTO dto
    ) {
        return paymentMethodService.createPaymentMethod(dto);
    }

    @PutMapping("/{companyId}/{paymentMethodId}")
    public PaymentMethodResponseDTO updatePaymentMethod(
        @PathVariable Long companyId,
        @PathVariable Long paymentMethodId,
        @Valid @RequestBody PaymentMethodRequestDTO dto
    ) {
        return paymentMethodService.updatePaymentMethod(companyId, paymentMethodId, dto);
    }

}
