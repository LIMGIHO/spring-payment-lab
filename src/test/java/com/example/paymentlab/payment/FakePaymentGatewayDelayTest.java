package com.example.paymentlab.payment;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;

class FakePaymentGatewayDelayTest {

	private static final PaymentRequest REQUEST = new PaymentRequest("order-001", 15000);

	@Test
	void waitsForConfiguredDelayAndApproves() {
		RecordingSleeper sleeper = new RecordingSleeper();
		FakePaymentGateway gateway = new FakePaymentGateway(Duration.ofSeconds(2), sleeper);

		assertThat(gateway.approve(REQUEST)).isEqualTo(PaymentStatus.APPROVED);
		assertThat(sleeper.requested).containsExactly(Duration.ofSeconds(2));
	}

	@Test
	void doesNotSleepWhenDelayIsZero() {
		RecordingSleeper sleeper = new RecordingSleeper();
		FakePaymentGateway gateway = new FakePaymentGateway(Duration.ZERO, sleeper);

		assertThat(gateway.approve(REQUEST)).isEqualTo(PaymentStatus.APPROVED);
		assertThat(sleeper.requested).isEmpty();
	}

	static class RecordingSleeper implements Sleeper {
		final List<Duration> requested = new ArrayList<>();

		@Override
		public void sleep(Duration duration) {
			requested.add(duration);
		}
	}
}
