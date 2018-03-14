package ru.acviewer.av.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class CallDataRecordPlace extends Place {

	public CallDataRecordPlace() {

	}

	@Prefix("cdr")
	public static class Tokenizer implements PlaceTokenizer<CallDataRecordPlace> {
		@Override
		public CallDataRecordPlace getPlace(String token) {
			return new CallDataRecordPlace();
		}

		@Override
		public String getToken(CallDataRecordPlace place) {
			return "";
		}
	}
}
