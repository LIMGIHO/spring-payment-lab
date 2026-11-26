package com.example.paymentlab.payment;

import java.time.Duration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class FakePaymentGateway implements PaymentGateway {

	private final Duration delay;
	private final Sleeper sleeper;

	public FakePaymentGateway(@Value("${payment.gateway.delay:0ms}") Duration delay, Sleeper sleeper) {
		this.delay = delay;
		this.sleeper = sleeper;
	}

	@Override
	public PaymentStatus approve(PaymentRequest request) {
		if (!delay.isZero() && !delay.isNegative()) {
			try {
				sleeper.sleep(delay);
			}
			catch (InterruptedException e) {
				Thread.currentThread().interrupt();
				throw new IllegalStateException("Interrupted while waiting for fake PG", e);
			}
		}
		return PaymentStatus.APPROVED;
	}
}
