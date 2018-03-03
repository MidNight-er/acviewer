package ru.acviewer.av.client.event;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;

public class PasswordUpdateEvent extends GwtEvent<PasswordUpdateEvent.AppHandler> {
	
	public interface AppHandler extends EventHandler {
		void onSetPassword(PasswordUpdateEvent event);
	}
	
	private static final Type<AppHandler> TYPE = new Type<AppHandler>();
	
	private String message;
	
	public PasswordUpdateEvent(String message) {
		this.message = message;
	}

	public String getMessage() {
		return message;
	}

	@Override
	public com.google.gwt.event.shared.GwtEvent.Type<AppHandler> getAssociatedType() {
		return TYPE;
	}
	
	public static com.google.gwt.event.shared.GwtEvent.Type<AppHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AppHandler handler) {
		handler.onSetPassword(this);
	}
}
