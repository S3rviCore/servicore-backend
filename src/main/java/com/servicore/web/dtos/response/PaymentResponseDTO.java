package com.servicore.web.dtos.response;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class PaymentResponseDTO {
    
    private final Long workOrderId;
    private final Long paymentMethodId;
    private final String paymentMethodName;
    private final Long paymentStatusId;
    private final String paymentStatusName;
    private final BigDecimal amount;
    private final String transactionReference;
    private final LocalDateTime paidAt;
    private final String note;

    public PaymentResponseDTO(
        Long workOrderId,
        Long paymentMethodId,
        String paymentMethodName,
        Long paymentStatusId,
        String paymentStatusName,
        BigDecimal amount,
        String transactionReference,
        LocalDateTime paidAt,
        String note
    ) {
        this.workOrderId = workOrderId;
        this.paymentMethodId = paymentMethodId;
        this.paymentMethodName = paymentMethodName;
        this.paymentStatusId = paymentStatusId;
        this.paymentStatusName = paymentStatusName;
        this.amount = amount;
        this.transactionReference = transactionReference;
        this.paidAt = paidAt;
        this.note = note;
    }

    public Long getWorkOrderId() {
        return workOrderId;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public String getPaymentMethodName() {
        return paymentMethodName;
    }

    public Long getPaymentStatusId() {
        return paymentStatusId;
    }

    public String getPaymentStatusName() {
        return paymentStatusName;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public LocalDateTime getPaidAt() {
        return paidAt;
    }

    public String getNote() {
        return note;
    }

}
