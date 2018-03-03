package ru.acviewer.av.client.widget;

import java.util.Arrays;

import ru.acviewer.av.client.bean.SearchRequest;
import ru.acviewer.av.client.i18n.messages.AppMessages;

import com.github.gwtbootstrap.client.ui.TextBox;
import com.github.gwtbootstrap.client.ui.ValueListBox;
import com.github.gwtbootstrap.datetimepicker.client.ui.DateTimeBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.text.shared.AbstractRenderer;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SearchEditor extends Composite implements Editor<SearchRequest> {

	private static SearchWidgetUiBinder uiBinder = GWT
			.create(SearchWidgetUiBinder.class);

	interface SearchWidgetUiBinder extends UiBinder<Widget, SearchEditor> {
	}
	
	private AppMessages messages = GWT.create(AppMessages.class);

	public SearchEditor() {
		disposition = new ValueListBox<Disposition>(new AbstractRenderer<Disposition>() {
			@Override
			public String render(Disposition object) {
				return object != null ? disposition(object) : messages.dispAll();
			}
		});
		
		initWidget(uiBinder.createAndBindUi(this));
		disposition.setAcceptableValues(Arrays.asList(Disposition.values()));
		setUpFields();
	}
	
	@UiField
	DateTimeBox dateTimeStart;

	@UiField
	DateTimeBox dateTimeLength;

	@UiField
	TextBox clid;

	@UiField
	TextBox src;

	@UiField
	TextBox dst;
	
	@UiField(provided = true)
	ValueListBox<Disposition> disposition;

	private String disposition(Disposition disposition) {
		String value = null;
		switch (disposition) {
			case ANSWERED:
				value = messages.dispAnswered();
				break;
			case BUSY:
				value = messages.dispBusy();
				break;
			case CONGESTION:
				value = messages.dispCongestion();
				break;
			case FAILED:
				value = messages.dispFailed();
				break;
			case NO_ANSWER:
				value = messages.dispNoAnswer();
				break;
		}
		return value;
	}
	
	private void setUpFields() {
		// firefox has not locale info in header
		String locale = LocaleInfo.getCurrentLocale().getLocaleName();
		dateTimeStart.setLanguage(locale);
		dateTimeLength.setLanguage(locale);
	}

}
