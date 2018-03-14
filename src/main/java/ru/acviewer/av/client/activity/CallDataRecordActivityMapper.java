package ru.acviewer.av.client.activity;

import ru.acviewer.av.client.place.CallDataRecordPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CallDataRecordActivityMapper implements ActivityMapper {

	private Provider<CallDataRecordActivity> callActivityProvider;
	
	@Inject
	public CallDataRecordActivityMapper(Provider<CallDataRecordActivity> callActivityProvider) {
		super();
		this.callActivityProvider = callActivityProvider;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof CallDataRecordPlace) {
			return callActivityProvider.get().withPlace((CallDataRecordPlace) place);
		} else {
			return null;
		}
	}
}
