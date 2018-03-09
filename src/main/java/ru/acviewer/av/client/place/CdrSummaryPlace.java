package ru.acviewer.av.client.place;

import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceTokenizer;
import com.google.gwt.place.shared.Prefix;

public class CdrSummaryPlace extends Place {

	public CdrSummaryPlace() {
		
	}
	
	@Prefix("cdr-summary")
	public static class Tokenizer implements PlaceTokenizer<CdrSummaryPlace> {

		@Override
		public CdrSummaryPlace getPlace(String token) {
			return new CdrSummaryPlace();
		}

		@Override
		public String getToken(CdrSummaryPlace place) {
			return "";
		}
		
	}
}
