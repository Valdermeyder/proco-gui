/*
 * Copyright 2017 Motorola Solutions, Inc.
 * All Rights Reserved.
 * Motorola Solutions Confidential Restricted
 */

package com.motsolutions.proco.gui.problem;

import com.google.common.collect.ImmutableList;
import java.util.ArrayList;
import java.util.List;

/**
 * Note: after stopping the producers and consumers, it shall be possible to
 * RESUME their operation by clicking 'Start all' again.
 */
public class ProducerConsumerFarm {
	private final List<Producer> producers;
	private final List<Consumer> consumers;

	private volatile List<Thread> threads = null;

	public ProducerConsumerFarm(List<Producer> producers, List<Consumer> consumers) {
		this.producers = ImmutableList.copyOf(producers);
		this.consumers = ImmutableList.copyOf(consumers);
	}

	public synchronized void startAll() {
		threads = new ArrayList<>(producers.size() + consumers.size());
		createThreadsFor(consumers);
		createThreadsFor(producers);
	}

	public synchronized void stopAll() throws InterruptedException {
		// impleent your stopping here
	}

	public boolean isStarted() {
		return null != threads;
	}

	private void createThreadsFor(List<? extends Runnable> items) {
		for (Runnable it: items) {
			Thread thr = new Thread(it);
			thr.start();
			threads.add(thr);
		}
	}
}
