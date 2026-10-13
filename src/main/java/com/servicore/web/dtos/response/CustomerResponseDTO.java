package com.servicore.web.dtos.response;

public class CustomerResponseDTO {
    
    private final Long companyId;
    private final String companyName;
    private final Long customerId;
    private final String customerName;
    private final String customerLastName;
    private final String phone;
    private final String email;
    private final String country;
    private final String address;
    private final boolean status;
    
    public CustomerResponseDTO(
        Long companyId,
        String companyName,
        Long customerId,
        String customerName,
        String customerLastName,
        String phone,
        String email,
        String country,
        String address,
        boolean status
    ) {
        this.companyId = companyId;
        this.companyName = companyName;
        this.customerId = customerId;
        this.customerName = customerName;
        this.customerLastName = customerLastName;
        this.phone = phone;
        this.email = email;
        this.country = country;
        this.address = address;
        this.status = status;
    }

    public Long getCompanyId() {
        return companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public String getCustomerName() {
        return customerName;
    }
    
    public String getCustomerLastName() {
        return customerLastName;
    }
 
    public String getPhone() {
        return phone;
    }

    public String getEmail() {
        return email;
    }

    public String getCountry() {
        return country;
    }

    public String getAddress() {
        return address;
    }

    public boolean getStatus() {
        return status;
    }

}
