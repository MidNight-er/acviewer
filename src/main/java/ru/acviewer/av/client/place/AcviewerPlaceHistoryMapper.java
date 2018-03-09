package ru.acviewer.av.client.place;

import com.google.gwt.place.shared.PlaceHistoryMapper;
import com.google.gwt.place.shared.WithTokenizers;

@WithTokenizers({ CdrPlace.Tokenizer.class,
		CdrSummaryPlace.Tokenizer.class })
public interface AcviewerPlaceHistoryMapper extends PlaceHistoryMapper {

}
