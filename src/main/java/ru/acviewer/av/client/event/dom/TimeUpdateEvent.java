package ru.acviewer.av.client.event.dom;

import com.google.gwt.event.dom.client.DomEvent;

public class TimeUpdateEvent extends DomEvent<TimeUpdateHandler> {
	
	private static final Type<TimeUpdateHandler> TYPE = 
			new Type<TimeUpdateHandler>(MediaBrowserEvents.TIMEUPDATE, 
					new TimeUpdateEvent());
	
	protected TimeUpdateEvent() {
		
	}
	
	public static Type<TimeUpdateHandler> getType() {
		return TYPE;
	}

	@Override
	public Type<TimeUpdateHandler> getAssociatedType() {
		return TYPE;
	}

	@Override
	protected void dispatch(TimeUpdateHandler handler) {
		handler.onTimeUpdate(this);
	}
}
