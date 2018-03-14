package ru.acviewer.av.client;

import ru.acviewer.av.client.gin.GinInjector;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.user.client.ui.RootLayoutPanel;

public class Acviewer implements EntryPoint {
	
	private final GinInjector injector = GWT.create(GinInjector.class);

	@Override
	public void onModuleLoad() {
		RootLayoutPanel.get().add(injector.getMainPanel());
		
		injector.getHistoryHandler().handleCurrentHistory();
	}

}
