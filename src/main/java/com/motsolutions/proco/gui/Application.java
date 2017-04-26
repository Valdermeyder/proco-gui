/*
 * Copyright 2017 Motorola Solutions, Inc.
 * All Rights Reserved.
 * Motorola Solutions Confidential Restricted
 */

package com.motsolutions.proco.gui;

import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;
import com.motsolutions.proco.gui.problem.Consumer;
import com.motsolutions.proco.gui.problem.Magazine;
import com.motsolutions.proco.gui.problem.ProducerConsumerFarm;
import com.motsolutions.proco.gui.problem.Producer;
import java.util.List;
import javax.swing.SwingUtilities;

/*
 * RULES:
 *
 * 1. You must not add any extra locks to the application,
 * 2. All classes must be annotated with @Immutable, @ThreadSafe, @NotThreadSafe
 * 3. Swing code must not be accessed from other threads.
 */
public class Application {
	public static void main(String args[]) {
		EventBus eventBus = new EventBus();
		MainWindow view = new MainWindow();

		Magazine magazine = new Magazine(20, eventBus);

		List<Producer> producers = ImmutableList.of(
			new Producer(magazine, "A", 1300, eventBus),
			new Producer(magazine, "B", 2000, eventBus),
				new Producer(magazine, "C", 1500, eventBus),
			new Producer(magazine, "D", 1700, eventBus),
				new Producer(magazine, "E", 700, eventBus)
		);
		List<Consumer> consumers = ImmutableList.of(new Consumer(magazine, 800),
			new Consumer(magazine, 1100),
			new Consumer(magazine, 900)
		);
		ProducerConsumerFarm farm = new ProducerConsumerFarm(producers, consumers);

		SwingUtilities.invokeLater(() -> {
			MainController controller = new MainController(eventBus, view, farm);
			eventBus.register(controller);
			view.setVisible(true);
		});
	}
}
