package ru.acviewer.av.client.event.dom;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasPlayHandlers extends HasHandlers {
	
	HandlerRegistration addPlayHandler(PlayHandler handler);
}
