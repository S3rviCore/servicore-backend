package com.servicore.web.dtos.request;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class WorkOrderRequestDTO {
    
    @NotNull(message = "El ID de la compania es requerido.")
    @Positive(message = "El ID de la campania debe ser posigtivo.")
    private Long companyId;

    @NotNull(message = "El ID del cliente es requerido.")
    @Positive(message = "El ID del cliente debe ser positivo.")
    private Long customerId;

    @NotNull(message = "El ID del servicio es requerido.")
    @Positive(message = "El ID del servicio debe ser positivo.")
    private Long serviceId;

    @NotNull(message = "La hora programada es requerida.")
    private LocalDateTime programmedDate;

    @NotBlank(message = "La direccion a la que se realizara el servicio es requerida.")
    @Size(max = 255, message = "La direccion no debe superar los 255 caracteres.")
    private String serviceAddress;

    @NotBlank(message = "La descripcion del problema es requerida.")
    private String problemDescription;

    @NotNull(message = "El ID de la prioridad es requerida.")
    @Positive(message = "El ID de la prioridad debe ser positivo.")
    private Long priorityId;

    @NotNull(message = "El ID del estado de la orden es requerido.")
    @Positive(message = "El ID del estado de la orden debe ser positivo.")
    private Long workOrderStatusId;

    @NotEmpty(message = "Los tecnicos son requeridos.")
    @Valid 
    private List<WorkOrderTechnicianRequestDTO> technicians; 

    public WorkOrderRequestDTO() {

    }

    public Long getCompanyId() {
        return companyId;
    }

    public void setCompanyId(Long companyId) {
        this.companyId = companyId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
    }

    public Long getServiceId() {
        return serviceId;
    }

    public void setServiceId(Long serviceId) {
        this.serviceId = serviceId;
    }

    public LocalDateTime getProgrammedDate() {
        return programmedDate;
    }

    public void setProgrammedDate(LocalDateTime programmedDate) {
        this.programmedDate = programmedDate;
    }

    public String getServiceAddress() {
        return serviceAddress;
    }

    public void setServiceAddress(String serviceAddress) {
        this.serviceAddress = serviceAddress;
    }

    public String getProblemDescription() {
        return problemDescription;
    }

    public void setProblemDescription(String problemDescription) {
        this.problemDescription = problemDescription;
    }

    public Long getPriorityId() {
        return priorityId;
    }

    public void setPriorityId(Long priorityId) {
        this.priorityId = priorityId;
    }

    public List<WorkOrderTechnicianRequestDTO> getTechnicians() {
        return technicians;
    }

    public void setTechnicians(List<WorkOrderTechnicianRequestDTO> technicians) {
        this.technicians = technicians;
    }

    public Long getWorkOrderStatusId() {
        return workOrderStatusId;
    }

    public void setWorkOrderStatusId(Long workOrderStatusId) {
        this.workOrderStatusId = workOrderStatusId;
    }

}