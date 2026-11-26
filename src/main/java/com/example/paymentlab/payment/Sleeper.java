package com.example.paymentlab.payment;

import java.time.Duration;

public interface Sleeper {

	void sleep(Duration duration) throws InterruptedException;
}
