package ru.acviewer.av.client.event.dom;

import com.google.gwt.event.shared.EventHandler;

public interface CanPlayHandler extends EventHandler {
	
	void onCanPlay(CanPlayEvent event);
}
