package ru.acviewer.av.client.event.dom;

import com.google.gwt.event.shared.EventHandler;

public interface TimeUpdateHandler extends EventHandler {

	void onTimeUpdate(TimeUpdateEvent event);
}
