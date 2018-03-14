package ru.acviewer.av.client.event.dom;

import com.google.gwt.event.shared.HandlerRegistration;
import com.google.gwt.event.shared.HasHandlers;

public interface HasTimeUpdateHandlers extends HasHandlers {

	HandlerRegistration addTimeUpdateHandler(TimeUpdateHandler handler);
}
