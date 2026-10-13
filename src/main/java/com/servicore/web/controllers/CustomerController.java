package com.servicore.web.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.servicore.web.dtos.request.CustomerRequestDTO;
import com.servicore.web.dtos.response.CustomerResponseDTO;
import com.servicore.web.services.CustomerService;

import jakarta.validation.Valid;

@RestController 
@RequestMapping("/api/customers")
@CrossOrigin 
public class CustomerController {
    
    private CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping 
    public List<CustomerResponseDTO> getAllCustomers() {
        return customerService.getAllCustomers();
    }

    @GetMapping("/{companyId}")
    public List<CustomerResponseDTO> getAllCustomersByCompanyId(@PathVariable Long companyId) {
        return customerService.getAllCustomersByCompanyId(companyId);
    }

    @PostMapping 
    public CustomerResponseDTO createCustomer(@Valid @RequestBody CustomerRequestDTO dto) {
        return customerService.createCustomer(dto);
    }

    @PutMapping("/{customerId}") 
    public CustomerResponseDTO updateCustomer(
        @PathVariable Long customerId,
        @Valid @RequestBody CustomerRequestDTO dto
    ) {
        return customerService.updateCustomer(customerId, dto);
    }

}
