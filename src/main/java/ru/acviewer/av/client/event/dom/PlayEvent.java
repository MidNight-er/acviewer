package ru.acviewer.av.client.event.dom;

import com.google.gwt.event.dom.client.DomEvent;

public class PlayEvent extends DomEvent<PlayHandler> {

	public static final Type<PlayHandler> TYPE = new Type<PlayHandler>(
			MediaBrowserEvents.PLAY, new PlayEvent());
	
	protected PlayEvent() {
		
	}
	
	public static Type<PlayHandler> getType() {
		return TYPE;
	}

	@Override
	public Type<PlayHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(PlayHandler handler) {
		handler.onPlay(this);
	}
}
