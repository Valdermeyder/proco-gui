/*
 * Copyright 2017 Motorola Solutions, Inc.
 * All Rights Reserved.
 * Motorola Solutions Confidential Restricted
 */

package com.motsolutions.proco.gui.problem;

import com.google.common.eventbus.EventBus;
import com.motsolutions.proco.gui.event.MagazineContentPublishedEvent;

import java.util.LinkedList;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Magazine {

	private final int capacity;

	private final EventBus eventBus;
	private final LinkedList<String> items = new LinkedList<>();

	private final Lock lock = new ReentrantLock();

	private final Condition notEmpty = lock.newCondition();

	private final Condition notFull = lock.newCondition();


	public Magazine (int capacity, EventBus eventBus) {
		this.capacity = capacity;
		this.eventBus = eventBus;
	}

	public void insert(String item) throws InterruptedException {
		lock.lock();
		try {
			while (items.size() >= capacity) {
				notFull.await();
			}
			items.add(item);
			eventBus.post(new MagazineContentPublishedEvent(items));
			notEmpty.signal();
		} finally {
			lock.unlock();
		}
	}

	public String fetch() throws InterruptedException {
		lock.lock();
		try {
			while (items.isEmpty()) {
				notEmpty.await();
			}
			String value = items.poll();
			notFull.signal();
			return value;
		} finally {
			lock.unlock();
		}
	}
}
