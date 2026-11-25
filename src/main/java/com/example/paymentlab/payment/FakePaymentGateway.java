package com.example.paymentlab.payment;

import org.springframework.stereotype.Component;

@Component
public class FakePaymentGateway implements PaymentGateway {

	@Override
	public PaymentStatus approve(PaymentRequest request) {
		return PaymentStatus.APPROVED;
	}
}
