package ru.acviewer.av.client.event.dom;

import com.google.gwt.event.dom.client.DomEvent;

public class CanPlayEvent extends DomEvent<CanPlayHandler> {

	public static final Type<CanPlayHandler> TYPE = 
			new Type<CanPlayHandler>(MediaBrowserEvents.CANPLAY, new CanPlayEvent());
	
	protected CanPlayEvent() {
		
	}
	
	public static Type<CanPlayHandler> getType() {
		return TYPE;
	}
	
	@Override
	public Type<CanPlayHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(CanPlayHandler handler) {
		handler.onCanPlay(this);
	}

}
