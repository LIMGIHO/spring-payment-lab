package com.example.paymentlab.payment;

public record PaymentResponse(String orderId, PaymentStatus status) {
}
