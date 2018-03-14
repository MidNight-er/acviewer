package ru.acviewer.av.client.cell;

import ru.acviewer.av.client.bean.PlayerControl;

import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.cell.client.AbstractCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.safehtml.shared.SafeHtmlBuilder;
import com.google.gwt.safehtml.shared.SafeUri;
import com.google.gwt.safehtml.shared.UriUtils;
import com.google.gwt.uibinder.client.UiRenderer;
import com.google.gwt.user.client.Window;

public class AudioDownloadCell extends AbstractCell<PlayerControl> {

	private final String DOWNLOAD_BUTTON = "btn btn-mini";
	
	interface AudioDownloadCellUiRenderer extends UiRenderer {
		void render(SafeHtmlBuilder sb, String downloadButtonClass, 
				String downloadIconClass, SafeUri download);
	}
	
	private static final AudioDownloadCellUiRenderer renderer = 
			GWT.create(AudioDownloadCellUiRenderer.class);
	
	public AudioDownloadCell() {
		
	}

	@Override
	public void render(com.google.gwt.cell.client.Cell.Context context,
			PlayerControl value, SafeHtmlBuilder sb) {
		if (value != null) {
			String download = buildUrl(value.getId());
			renderer.render(sb, DOWNLOAD_BUTTON, IconType.SAVE.get(), 
					UriUtils.fromString(download));
		}
	}
	
	private String buildUrl(Long id) {
		UrlBuilder urlBuilder = new UrlBuilder();
		urlBuilder.setProtocol(Window.Location.getProtocol());
		urlBuilder.setHost(Window.Location.getHost());
		urlBuilder.setPath(Window.Location.getPath() + "downloadService");
		urlBuilder.setParameter("id", String.valueOf(id));
		String url = urlBuilder.buildString();
		return url; 
	}
	
}
