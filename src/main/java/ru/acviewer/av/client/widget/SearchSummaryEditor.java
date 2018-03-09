package ru.acviewer.av.client.widget;

import ru.acviewer.av.client.bean.SearchSummaryRequest;

import com.github.gwtbootstrap.datetimepicker.client.ui.DateTimeBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.editor.client.Editor;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;

public class SearchSummaryEditor extends Composite implements Editor<SearchSummaryRequest> {

	private static SearchSummaryEditorUiBinder uiBinder = GWT
			.create(SearchSummaryEditorUiBinder.class);

	interface SearchSummaryEditorUiBinder extends
			UiBinder<Widget, SearchSummaryEditor> {
	}

	public SearchSummaryEditor() {
		initWidget(uiBinder.createAndBindUi(this));
		setUpFields();
	}

	@UiField
	DateTimeBox dateTimeStart;

	@UiField
	DateTimeBox dateTimeEnd;
	
	private void setUpFields() {
		String locale = LocaleInfo.getCurrentLocale().getLocaleName();
		dateTimeStart.setLanguage(locale);
		dateTimeEnd.setLanguage(locale);
	}
}
