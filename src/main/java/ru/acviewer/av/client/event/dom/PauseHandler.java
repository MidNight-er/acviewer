package ru.acviewer.av.client.event.dom;

import com.google.gwt.event.shared.EventHandler;

public interface PauseHandler extends EventHandler {
	
	void onPause(PauseEvent event);
}
