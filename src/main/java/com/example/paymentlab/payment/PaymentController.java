package com.example.paymentlab.payment;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PaymentController {

	private final PaymentService paymentService;

	public PaymentController(PaymentService paymentService) {
		this.paymentService = paymentService;
	}

	@PostMapping("/payments")
	public PaymentResponse pay(@RequestBody PaymentRequest request) {
		return paymentService.pay(request);
	}
}
