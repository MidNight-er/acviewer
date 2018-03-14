package ru.acviewer.av.client.cell;

import ru.acviewer.av.client.bean.AudioPlayer;

import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.cell.client.Cell.Context;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class AudioPlayButtonCell extends AbstractCell<Long> {
	
	private final String PLAY_BUTTON = "btn btn-mini";
	private final String PLAY_BUTTON_DISABLED = "btn btn-mini disabled";

	interface AudioPlayCellUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String elementId, String playButtonClass, 
				String playIconClass);
		void onBrowserEvent(AudioPlayButtonCell cell, NativeEvent event,
				Element parent, Long value, Context context,
				ValueUpdater<Long> vUp);
	}
	
	private static final AudioPlayCellUiRenderer renderer = 
			GWT.create(AudioPlayCellUiRenderer.class);
	
	public AudioPlayButtonCell() {
		super(BrowserEvents.CLICK);
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			Long value, SafeHtmlBuilder sb) {
		String elementId = AudioPlayer.PLAY_ELEMENT_ID + value;
		if (value != null) {
			renderer.render(sb, elementId, PLAY_BUTTON, IconType.PLAY.get());
		} else {
			renderer.render(sb, "", PLAY_BUTTON_DISABLED, IconType.BAN_CIRCLE.get());
		}
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, Long value,
			NativeEvent event, ValueUpdater<Long> valueUpdater) {
		
		renderer.onBrowserEvent(this, event, parent, value, context, valueUpdater);
		if (BrowserEvents.CLICK.equals(event.getType())) {
			valueUpdater.update(value);
		}
	}

}
