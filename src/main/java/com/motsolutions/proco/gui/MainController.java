/*
 * Copyright 2017 Motorola Solutions, Inc.
 * All Rights Reserved.
 * Motorola Solutions Confidential Restricted
 */

package com.motsolutions.proco.gui;

import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;
import com.motsolutions.proco.gui.event.MagazineContentPublishedEvent;
import com.motsolutions.proco.gui.event.ProducerPublishedEvent;
import com.motsolutions.proco.gui.event.ProducerSnapshot;
import com.motsolutions.proco.gui.problem.ProducerConsumerFarm;

import javax.swing.*;
import java.awt.event.ActionEvent;

/**
 * Here you have the controller code, where you should make your changes.
 * The interaction with Swing was reduced to the absolute minimum.
 */
public class MainController {
	private final EventBus eventBus;
	private final MainWindow view;
	private final ProducerConsumerFarm farm;

	private final StaticListModel<ProducerSnapshot> producerModel = new StaticListModel<>(ImmutableList.of());

	public MainController(EventBus eventBus, MainWindow view, ProducerConsumerFarm farm) {
		this.eventBus = Preconditions.checkNotNull(eventBus);
		this.view = Preconditions.checkNotNull(view);
		this.farm = Preconditions.checkNotNull(farm);

		installView(view);
	}

	private void installView(MainWindow view) {
		view.initializeButtonStateListeners(farm.isStarted());
		view.setStartAllAction((evt) -> onStartAll(evt));
		view.setStopAllAction((evt) -> onStopAll(evt));

		view.getProducerList().setModel(producerModel);
	}

	public void onStartAll(ActionEvent event) {
		farm.startAll();
	}

	public void onStopAll(ActionEvent event) {
		// handle stopping here...
	}

	/**
	 * You can receive more events with Event Bus by creating immutable event class, adding
	 * a handler method and placing Subscribe annotation on that method.
	 *
	 * @param event
	 */
	@Subscribe
	public void onMagazineContentPublished(MagazineContentPublishedEvent event) {
		view.getMagazineList().setModel(new StaticListModel<>(ImmutableList.copyOf(event.getContent())));
		System.out.println("Content" + event.getContent());
	}

	@Subscribe
	public void onProducerPublished(ProducerPublishedEvent producerPublishedEvent) {
		SwingUtilities.invokeLater(() -> producerModel.publishUpdate(producerPublishedEvent.getContent()));
	}
}
