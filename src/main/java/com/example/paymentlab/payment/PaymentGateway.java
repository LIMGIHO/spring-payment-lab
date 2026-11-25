package com.example.paymentlab.payment;

public interface PaymentGateway {

	PaymentStatus approve(PaymentRequest request);
}
