package ru.acviewer.av.client.event;

import java.util.List;

import ru.acviewer.av.shared.CdrProxy;

import com.google.gwt.event.shared.EventHandler;
import com.google.gwt.event.shared.GwtEvent;
import com.google.gwt.view.client.Range;

public class PopulateDataEvent extends GwtEvent<PopulateDataEvent.AppHandler> {
	
	public interface AppHandler extends EventHandler {
		void onPopulate(PopulateDataEvent event);
	}
	
	public static final Type<AppHandler> TYPE = new Type<AppHandler>();

	private List<CdrProxy> calls;

	private Range range; 
	
	public PopulateDataEvent(Range range, List<CdrProxy> calls) {
		this.range = range;
		this.calls = calls;
	}
	
	public List<CdrProxy> getCalls() {
		return calls;
	}

	@Override
	public Type<AppHandler> getAssociatedType() {
		return TYPE;
	}
	
	public static Type<AppHandler> getType() {
		return TYPE;
	}

	@Override
	protected void dispatch(AppHandler handler) {
		handler.onPopulate(this);
	}

	public Range getRange() {
		return range;
	}
}
