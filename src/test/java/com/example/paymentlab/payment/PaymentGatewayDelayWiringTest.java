package com.example.paymentlab.payment;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.Duration;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest(properties = "payment.gateway.delay=1500ms")
@AutoConfigureMockMvc
class PaymentGatewayDelayWiringTest {

	@TestConfiguration
	static class Config {
		@Bean
		@Primary
		FakePaymentGatewayDelayTest.RecordingSleeper recordingSleeper() {
			return new FakePaymentGatewayDelayTest.RecordingSleeper();
		}
	}

	@Autowired
	MockMvc mockMvc;

	@Autowired
	FakePaymentGatewayDelayTest.RecordingSleeper sleeper;

	@Test
	void propertyReachesGatewayAndApiStillApproves() throws Exception {
		mockMvc.perform(post("/payments")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"orderId\":\"order-001\",\"amount\":15000}"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.status").value("APPROVED"));

		assertThat(sleeper.requested).containsExactly(Duration.ofMillis(1500));
	}
}
