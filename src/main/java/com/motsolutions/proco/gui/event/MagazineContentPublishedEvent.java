/*
 * Copyright 2017 Motorola Solutions, Inc.
 * All Rights Reserved.
 * Motorola Solutions Confidential Restricted
 */

package com.motsolutions.proco.gui.event;

import com.google.common.collect.ImmutableList;
import java.util.List;
import net.jcip.annotations.Immutable;

@Immutable
public class MagazineContentPublishedEvent {
	private final List<String> magazineContent;

	public MagazineContentPublishedEvent(List<String> magazineContent) {
		this.magazineContent = ImmutableList.copyOf(magazineContent);
	}

	public List<String> getContent() {
		return magazineContent;
	}
}
