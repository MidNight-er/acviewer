package ru.acviewer.av.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class CdrPlace extends Place {

	public CdrPlace() {

	}

	@Prefix("cdr")
	public static class Tokenizer implements PlaceTokenizer<CdrPlace> {
		@Override
		public CdrPlace getPlace(String token) {
			return new CdrPlace();
		}

		@Override
		public String getToken(CdrPlace place) {
			return "";
		}
	}
}
