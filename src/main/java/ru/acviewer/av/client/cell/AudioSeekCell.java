package ru.acviewer.av.client.cell;

import ru.acviewer.av.client.bean.AudioPlayer;
import ru.acviewer.av.client.bean.PlayerControl;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.cell.client.ValueUpdater;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.BrowserEvents;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.NativeEvent;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;

public class AudioSeekCell extends AbstractCell<PlayerControl> {

	interface AudioSeekCellUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String elementId);
		void onBrowserEvent(AudioSeekCell cell, NativeEvent event,
                            Element parent, PlayerControl value, Context context, ValueUpdater<PlayerControl> vUp);
	}
	
	private static final AudioSeekCellUiRenderer renderer = 
			GWT.create(AudioSeekCellUiRenderer.class);
	
	public AudioSeekCell() {
		super(BrowserEvents.CLICK);
	}

	@Override
	public void render(Context context,
			PlayerControl value, SafeHtmlBuilder sb) {
		if (value != null) {
			String elementId = AudioPlayer.SEEK_ELEMENT_ID + value.getId();
			renderer.render(sb, elementId);
		}
	}
	
	@Override
	public void onBrowserEvent(Context context, Element parent, PlayerControl value,
			NativeEvent event, ValueUpdater<PlayerControl> vUp) {
		renderer.onBrowserEvent(this, event, parent, value, context, vUp);
		
		if (BrowserEvents.CLICK.equals(event.getType()) && vUp != null) {
			Element seek = parent.getFirstChildElement();
			// convert mouse pointer to element pixels
 			double pixelMouse = event.getClientX() - seek.getAbsoluteLeft();
			double pixelTotal = seek.getClientWidth();
			double percentMouse = pixelMouse / pixelTotal;
			percentMouse = percentMouse > 1 ? 1 : percentMouse;
			percentMouse = percentMouse < 0 ? 0 : percentMouse;
			
			value.setPercentMouse(percentMouse);
			vUp.update(value);
		}
	}
}
