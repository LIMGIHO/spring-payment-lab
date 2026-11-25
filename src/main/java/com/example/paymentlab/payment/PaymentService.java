package com.example.paymentlab.payment;

import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	private final PaymentGateway paymentGateway;

	public PaymentService(PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public PaymentResponse pay(PaymentRequest request) {
		return new PaymentResponse(request.orderId(), paymentGateway.approve(request));
	}
}
