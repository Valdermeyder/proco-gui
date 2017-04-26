/*
 * Copyright 2017 Motorola Solutions, Inc.
 * All Rights Reserved.
 * Motorola Solutions Confidential Restricted
 */
package com.motsolutions.proco.gui.problem;

import com.google.common.eventbus.EventBus;
import com.motsolutions.proco.gui.event.ProducerPublishedEvent;
import com.motsolutions.proco.gui.event.ProducerSnapshot;

import java.util.Date;
import java.util.Objects;

public class Producer implements Runnable {
	private final Magazine magazine;
	private final String producedItem;
	private final int productionTime;
	private final EventBus eventBus;
	private int counter;

	public Producer(Magazine magazine, String producedItem, int productionTime, EventBus eventBus) {
		this.magazine = Objects.requireNonNull(magazine);
		this.producedItem = producedItem;
		this.productionTime = productionTime;
		this.eventBus = eventBus;
	}

	@Override
	public void run() {
		try {
			while(true) {
				magazine.insert(producedItem + counter);
				counter++;
				eventBus.post(new ProducerPublishedEvent(new ProducerSnapshot(producedItem, counter, new Date().getTime())));
				Thread.sleep(productionTime);
			}
		} catch (InterruptedException exception) {
			System.out.println("Producer for '"+producedItem+"' interrupted, finishing...");
		}
	}
}
