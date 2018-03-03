package ru.acviewer.av.client.cell;

import ru.acviewer.av.client.bean.AudioPlayer;
import ru.acviewer.av.client.bean.PlayerControl;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.dom.client.Element;
import com.google.gwt.safehtml.shared.SafeHtml;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlUtils;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.gwt.user.client.DOM;

public class AudioTimerCell extends AbstractCell<PlayerControl> {

	interface AudioTimerCellUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String elementId, String timer);
	}

	private static final AudioTimerCellUiRenderer renderer = GWT
			.create(AudioTimerCellUiRenderer.class);

	public AudioTimerCell() {

	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			PlayerControl value, SafeHtmlBuilder sb) {
		if (value != null) {
			String elementId = AudioPlayer.TIMER_ELEMENT_ID + value.getId();
			renderer.render(sb, elementId, "0:0");
		}
	}

}
