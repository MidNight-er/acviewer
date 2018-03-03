package ru.acviewer.av.client.event.dom;

import com.google.gwt.event.shared.EventHandler;

public interface PlayHandler extends EventHandler {
	
	void onPlay(PlayEvent event);
}
