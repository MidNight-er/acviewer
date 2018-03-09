package ru.acviewer.av.client.cell;

import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.uibinder.client.UiRenderer;

public class HeaderCell extends AbstractCell<String> {
	
	interface HeaderCellUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String icon);
	}
	
	private static final HeaderCellUiRenderer renderer = 
			GWT.create(HeaderCellUiRenderer.class);

	public HeaderCell() {
		
	}
	
	@Override
	public void render(Context context,
			String value, SafeHtmlBuilder sb) {
		if (value != null) {
			renderer.render(sb, value);
		}
	}

}
