package ru.acviewer.av.client.gin;

import ru.acviewer.av.client.MainPanel;
import ru.acviewer.av.client.view.SignOnView;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.inject.client.GinModules;
import com.google.gwt.inject.client.Ginjector;
import com.google.gwt.place.shared.PlaceHistoryHandler;

@GinModules({ GinModule.class })
public interface GinInjector extends Ginjector {
	
	MainPanel getMainPanel();
	SignOnView getSignOnView();
	EventBus getEventBus();
	PlaceHistoryHandler getHistoryHandler();
}
