package ru.acviewer.av.client.gin;

import ru.acviewer.av.client.MainPanel;
import ru.acviewer.av.client.activity.AcviewerActivityMapper;
import ru.acviewer.av.client.activity.CallDataRecordActivity;
import ru.acviewer.av.client.activity.SignOnActivity;
import ru.acviewer.av.client.place.AppPlaceHistoryMapper;
import ru.acviewer.av.client.place.CallDataRecordPlace;
import ru.acviewer.av.client.service.CallDataRecordRequestFactory;
import ru.acviewer.av.client.service.UserRequestFactory;
import ru.acviewer.av.client.ui.CallDataRecordViewImpl;
import ru.acviewer.av.client.ui.SignOnViewImpl;
import ru.acviewer.av.client.view.CallDataRecordView;
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
		bind(CallDataRecordView.class).to(CallDataRecordViewImpl.class).in(Singleton.class);
		bind(CallDataRecordView.Presenter.class).to(CallDataRecordActivity.class);

		bind(EventBus.class).to(SimpleEventBus.class).in(Singleton.class);
		bind(PlaceHistoryMapper.class).to(AppPlaceHistoryMapper.class).in(Singleton.class);
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
	public CallDataRecordRequestFactory getCallRequestFactory(
			com.google.web.bindery.event.shared.SimpleEventBus eventBus) {
		CallDataRecordRequestFactory factory = GWT.create(CallDataRecordRequestFactory.class);
		factory.initialize(eventBus);
		return factory;
	}
	
	@Provides
	@Singleton
	public UserRequestFactory getUserRequestFactory(
			com.google.web.bindery.event.shared.SimpleEventBus eventBus) {
		UserRequestFactory factory = GWT.create(UserRequestFactory.class);
		factory.initialize(eventBus);
		return factory;
	}
	
	private Place getDefaultPlace() {
		return new CallDataRecordPlace();
	}
}
