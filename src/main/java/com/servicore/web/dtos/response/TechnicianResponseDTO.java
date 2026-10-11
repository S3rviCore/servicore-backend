package com.servicore.web.dtos.response;

public class TechnicianResponseDTO {
    
    private final Long id;
    private final Long userId;
    private final String userName;
    private final String phone;
    private final Boolean status;

    public TechnicianResponseDTO(
        Long id,
        Long userId,
        String userName,
        String phone,
        Boolean status
    ) {
        this.id = id;
        this.userId = userId;
        this.userName = userName;
        this.phone = phone;
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public Long getUserId() {
        return userId;
    }

    public String getUserName() {
        return userName;
    }

    public String getPhone() {
        return phone;
    }

    public Boolean getStatus() {
        return status;
    }

}
