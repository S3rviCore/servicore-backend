package com.servicore.web.dtos.request;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;

public class PaymentRequestDTO {
    
    @NotNull(message = "El ID de la orden es requerida.")
    @Positive(message = "El ID de la orden debe ser positiva.")
    private Long workOrderId;

    @NotNull(message = "El ID del metodo de pago es requerido.")
    @Positive(message = "El ID del metodo de pago debe ser positiva.")
    private Long paymentMethodId;

    @NotNull(message = "El ID del estado del metodo de pago es requerido.")
    @Positive(message = "El ID del estado del metodo de pago debe ser positivo.")
    private Long paymentStatusId;

    @NotNull(message = "El monto del pago es requerido.")
    @Positive(message = "El monto del pago debe ser positivo.")
    private BigDecimal amount;

    private String transactionReference;

    @NotNull(message = "La fecha de pago es requerida.")
    private LocalDateTime paidAt;

    @Size(max = 255, message = "La nota no debe superar los 255 caracteres.")
    private String note;

    public PaymentRequestDTO() {

    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public void setWorkOrderId(Long workOrderId) {
        this.workOrderId = workOrderId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Long getPaymentStatusId() {
        return paymentStatusId;
    }
    
    public void setPaymentStatusId(Long paymentStatusId) {
        this.paymentStatusId = paymentStatusId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public void setTransactionReference(String transactionReference) {
        this.transactionReference = transactionReference;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public void setPaidAt(LocalDateTime paidAt) {
        this.paidAt = paidAt;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

}
