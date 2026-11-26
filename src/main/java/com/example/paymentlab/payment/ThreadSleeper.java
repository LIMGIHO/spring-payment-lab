package com.example.paymentlab.payment;

import java.time.Duration;

import org.springframework.stereotype.Component;

@Component
public class ThreadSleeper implements Sleeper {

	@Override
	public void sleep(Duration duration) throws InterruptedException {
		Thread.sleep(duration.toMillis());
	}
}
