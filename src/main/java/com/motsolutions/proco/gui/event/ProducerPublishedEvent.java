/*
 * Copyright 2017 Motorola Solutions, Inc.
 * All Rights Reserved.
 * Motorola Solutions Confidential Restricted
 */

package com.motsolutions.proco.gui.event;

import com.google.common.collect.ImmutableList;
import net.jcip.annotations.Immutable;

import java.util.List;

@Immutable
public class ProducerPublishedEvent {
	private final ProducerSnapshot producerProperties;

	public ProducerPublishedEvent(ProducerSnapshot producerProperties) {
		this.producerProperties = producerProperties;
	}

	public ProducerSnapshot getContent() {
		return producerProperties;
	}
}
