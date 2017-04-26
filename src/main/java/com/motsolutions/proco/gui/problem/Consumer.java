/*
 * Copyright 2017 Motorola Solutions, Inc.
 * All Rights Reserved.
 * Motorola Solutions Confidential Restricted
 */

package com.motsolutions.proco.gui.problem;

import java.util.Objects;

public class Consumer implements Runnable {
	private final Magazine magazine;
	private final int consumptionTime;

	public Consumer(Magazine magazine, int consumptionTime) {
		this.magazine = Objects.requireNonNull(magazine);
		this.consumptionTime = consumptionTime;
	}

	@Override
	public void run() {
		try {
			while(true) {
				System.out.println("Consumed: " + magazine.fetch());
				Thread.sleep(consumptionTime);
			}
		} catch (InterruptedException exception) {
			System.out.println("Consumer interrupted, finishing...");
		}
	}
}
