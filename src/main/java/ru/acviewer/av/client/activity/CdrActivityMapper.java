package ru.acviewer.av.client.activity;

import ru.acviewer.av.client.place.CdrPlace;
import ru.acviewer.av.client.place.CdrSummaryPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class CdrActivityMapper implements ActivityMapper {

	private Provider<CdrActivity> cdrActivityProvider;
	private Provider<CdrSummaryActivity> cdrSummaryActivityProvider;
	
	@Inject
	public CdrActivityMapper(Provider<CdrActivity> cdrActivityProvider,
			Provider<CdrSummaryActivity> cdrSummaryActivityProvider) {
		super();
		this.cdrActivityProvider = cdrActivityProvider;
		this.cdrSummaryActivityProvider = cdrSummaryActivityProvider;
	}

	@Override
	public Activity getActivity(Place place) {
		if (place instanceof CdrPlace) {
			return cdrActivityProvider.get().withPlace((CdrPlace) place);
		} else if (place instanceof CdrSummaryPlace) {
			return cdrSummaryActivityProvider.get().withPlace((CdrSummaryPlace) place);
		} else {
			return null;
		}
	}
}
