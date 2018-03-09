package ru.acviewer.av.client.activity;

import ru.acviewer.av.client.place.CdrPlace;
import ru.acviewer.av.client.place.CdrSummaryPlace;

import com.google.gwt.activity.shared.Activity;
import com.google.gwt.activity.shared.ActivityMapper;
import com.google.gwt.place.shared.Place;
import com.google.inject.Inject;
import com.google.inject.Provider;

public class AcviewerActivityMapper implements ActivityMapper {

	private Provider<SignOnActivity> signOnActivityProvider;

	@Inject
	public AcviewerActivityMapper(Provider<SignOnActivity> signOnActivityProvider) {
		super();
		this.signOnActivityProvider = signOnActivityProvider;
	}

	// SignOnActivity mapped CallPlace and any other place (e.g VoIPUserAccountPlace)
	@Override
	public Activity getActivity(Place place) {
		if (place instanceof CdrPlace) {
			return signOnActivityProvider.get().withPlace((CdrPlace) place);
		} if (place instanceof CdrSummaryPlace) {
			return signOnActivityProvider.get().withPlace((CdrSummaryPlace) place);
		} else {
			return null;
		}
	}

}
