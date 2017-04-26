/*
 * Copyright 2017 Motorola Solutions, Inc.
 * All Rights Reserved.
 * Motorola Solutions Confidential Restricted
 */

package com.motsolutions.proco.gui;

import java.util.ArrayList;
import java.util.List;
import javax.swing.AbstractListModel;
import javax.swing.SwingUtilities;

/**
 * Helper for Swing JList. Use this model to display items in the lists. The model supports
 * updating existing items, if the <tt>equals()</tt> method is correctly implemented.
 */
public class StaticListModel<T> extends AbstractListModel<T> {
	private final List<T> items;

	public StaticListModel(List<T> items) {
		this.items = new ArrayList<>(items.size());
		this.items.addAll(items);
	}

	public void publishUpdate(T item) {
		if (!SwingUtilities.isEventDispatchThread()) {
			throw new IllegalStateException("Must be in Swing event dispatch thread!");
		}
		for (int i = 0; i < items.size(); i++) {
			T original = items.get(i);
			if (original.equals(item)) {
				items.set(i, item);
				this.fireContentsChanged(this, i, i);
				return;
			}
		}
		int insertIndex = items.size();
		items.add(item);
		this.fireIntervalAdded(this, insertIndex, insertIndex);
	}

	@Override
	public int getSize() {
		return items.size();
	}

	@Override
	public T getElementAt(int index) {
		return items.get(index);
	}
}
