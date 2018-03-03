package ru.acviewer.av.client.event.dom;

import com.google.gwt.event.dom.client.DomEvent;

public class PauseEvent extends DomEvent<PauseHandler> {

	private static final Type<PauseHandler> TYPE = new Type<PauseHandler>(
			MediaBrowserEvents.PAUSE, new PauseEvent());
	
	protected PauseEvent() {
		
	}

	public static Type<PauseHandler> getType() {
		return TYPE;
	}
	
	@Override
	public Type<PauseHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PauseHandler handler) {
		handler.onPause(this);
	}
}
