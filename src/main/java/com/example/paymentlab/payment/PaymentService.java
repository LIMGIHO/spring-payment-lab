package com.example.paymentlab.payment;

import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

	private static final Logger log = LoggerFactory.getLogger(PaymentService.class);

	private final PaymentGateway paymentGateway;

	public PaymentService(PaymentGateway paymentGateway) {
		this.paymentGateway = paymentGateway;
	}

	public PaymentResponse pay(PaymentRequest request) {
		log.info("PG call start orderId={}", request.orderId());
		long startNanos = System.nanoTime();
		PaymentStatus status = paymentGateway.approve(request);
		long elapsedMs = TimeUnit.NANOSECONDS.toMillis(System.nanoTime() - startNanos);
		log.info("PG call end orderId={} status={} elapsedMs={}", request.orderId(), status, elapsedMs);
		return new PaymentResponse(request.orderId(), status);
	}
}
