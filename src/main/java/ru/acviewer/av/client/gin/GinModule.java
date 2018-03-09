package ru.acviewer.av.client.gin;

import ru.acviewer.av.client.MainPanel;
import ru.acviewer.av.client.activity.AcviewerActivityMapper;
import ru.acviewer.av.client.activity.CdrActivity;
import ru.acviewer.av.client.activity.CdrSummaryActivity;
import ru.acviewer.av.client.activity.SignOnActivity;
import ru.acviewer.av.client.place.AcviewerPlaceHistoryMapper;
import ru.acviewer.av.client.place.CdrPlace;
import ru.acviewer.av.client.place.CdrSummaryPlace;
import ru.acviewer.av.client.service.CdrRequestFactory;
import ru.acviewer.av.client.service.UserAccountRequestFactory;
import ru.acviewer.av.client.ui.CdrSummaryViewImpl;
import ru.acviewer.av.client.ui.CdrViewImpl;
import ru.acviewer.av.client.ui.SignOnViewImpl;
import ru.acviewer.av.client.view.CdrSummaryView;
import ru.acviewer.av.client.view.CdrView;
import ru.acviewer.av.client.view.SignOnView;

import com.google.gwt.core.shared.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.event.shared.SimpleEventBus;
import com.google.gwt.inject.client.AbstractGinModule;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.place.shared.PlaceHistoryHandler;
import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.inject.Provides;
import com.google.inject.Singleton;

public class GinModule extends AbstractGinModule {
	
	@Override
	protected void configure() {
		bind(MainPanel.class).in(Singleton.class);
		bind(SignOnView.class).to(SignOnViewImpl.class).in(Singleton.class);
		bind(SignOnView.Presenter.class).to(SignOnActivity.class);
		bind(CdrView.class).to(CdrViewImpl.class).in(Singleton.class);
		bind(CdrView.Presenter.class).to(CdrActivity.class);
		bind(CdrSummaryView.class).to(CdrSummaryViewImpl.class).in(Singleton.class);
		bind(CdrSummaryView.Presenter.class).to(CdrSummaryActivity.class).in(Singleton.class);

		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceHistoryMapper.class).to(AcviewerPlaceHistoryMapper.class).in(Singleton.class);
		bind(AcviewerActivityMapper.class);
	}

	@Provides
	@Singleton
	public PlaceController getPlaceController(EventBus eventBus) {
		return new PlaceController(eventBus);
	}

	@Provides
	@Singleton
	public PlaceHistoryHandler getHistoryHandler(
			PlaceController placeController, PlaceHistoryMapper historyMapper,
			EventBus eventBus) {
		PlaceHistoryHandler historyHandler = new PlaceHistoryHandler(historyMapper);
		historyHandler.register(placeController, eventBus, getDefaultPlace());
		return historyHandler;
	}

	@Provides
	@Singleton
	public CdrRequestFactory getCallRequestFactory(
			com.google.web.bindery.event.shared.SimpleEventBus eventBus) {
		CdrRequestFactory factory = GWT.create(CdrRequestFactory.class);
		factory.initialize(eventBus);
		return factory;
	}
	
	@Provides
	@Singleton
	public UserAccountRequestFactory getUserRequestFactory(
			com.google.web.bindery.event.shared.SimpleEventBus eventBus) {
		UserAccountRequestFactory factory = GWT.create(UserAccountRequestFactory.class);
		factory.initialize(eventBus);
		return factory;
	}
	
	private Place getDefaultPlace() {
		return new CdrPlace();
	}
}
