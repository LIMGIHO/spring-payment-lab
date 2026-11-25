package com.example.paymentlab.payment;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@SpringBootTest
@AutoConfigureMockMvc
class PaymentApiTest {

	@Autowired
	MockMvc mockMvc;

	@Test
	void approvesPayment() throws Exception {
		mockMvc.perform(post("/payments")
						.contentType(MediaType.APPLICATION_JSON)
						.content("{\"orderId\":\"order-001\",\"amount\":15000}"))
				.andExpect(status().isOk())
				.andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
				.andExpect(jsonPath("$.orderId").value("order-001"))
				.andExpect(jsonPath("$.status").value("APPROVED"));
	}
}
