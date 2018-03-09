package ru.acviewer.av.client.ui;

import ru.acviewer.av.client.AcviewerUtils;
import ru.acviewer.av.client.i18n.messages.AppMessages;
import ru.acviewer.av.client.provider.CdrSummaryDataProvider;
import ru.acviewer.av.client.ui.CdrViewImpl.AppResource;
import ru.acviewer.av.client.view.CdrSummaryView;
import ru.acviewer.av.client.widget.SearchEditor;
import ru.acviewer.av.client.widget.SearchSummaryEditor;
import ru.acviewer.av.shared.CdrProxy;
import ru.acviewer.av.shared.CdrSummaryProxy;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.NumberCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.Range;

public class CdrSummaryViewImpl extends Composite implements CdrSummaryView {

	private static CdrSummaryViewImplUiBinder uiBinder = GWT
			.create(CdrSummaryViewImplUiBinder.class);

	interface CdrSummaryViewImplUiBinder extends
			UiBinder<Widget, CdrSummaryViewImpl> {
	}
	
	private Presenter presenter;
	
	private AppMessages msg = GWT.create(AppMessages.class);
	
	public CdrSummaryViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
		buildTable();
	}

	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		CdrSummaryDataProvider dataProvider = presenter.getCdrSummaryDataProvider();
		dataProvider.addDataDisplay(cdrSummaries);
	}
	
	@UiField
	AppResource style;
	
	@UiField
	SearchSummaryEditor searchSummaryEditor;
	
	@Override
	public SearchSummaryEditor getSearchSummaryEditor() {
		return searchSummaryEditor;
	}
	
	// search
	@UiHandler(value = "searchButton")
	void searchCalls(ClickEvent event) {
		final Range range = new Range(0, cdrSummaries.getPageSize());
		cdrSummaries.setVisibleRangeAndClearData(range, true);
	}

	@UiField
	CellTable<CdrSummaryProxy> cdrSummaries;
	
	private void buildTable() {
		Cell<String> textCell = new TextCell();
		Cell<Number> numberCell = new NumberCell();
		
		// id column
		Column<CdrSummaryProxy, String> id = new Column<CdrSummaryProxy, String>(textCell) {
			@Override
			public String getValue(CdrSummaryProxy object) {
				return object.getId();
			}
		};
		cdrSummaries.setColumnWidth(id, "20%");
		cdrSummaries.addColumn(id, "Номер абонента");
		
		// average column
		Column<CdrSummaryProxy, String> average = new Column<CdrSummaryProxy, String>(textCell) {
			@Override
			public String getValue(CdrSummaryProxy object) {
				String mf = AcviewerUtils.minuteFormat(object.getAverage());
				String sf = AcviewerUtils.secondFormat(object.getAverage());
				return mf + ":" + sf;
			}
		};
		cdrSummaries.setColumnWidth(average, "20%");
		cdrSummaries.addColumn(average, "Средняя продолжительность");

		// total column
		Column<CdrSummaryProxy, String> total = new Column<CdrSummaryProxy, String>(textCell) {
			@Override
			public String getValue(CdrSummaryProxy object) {
				String hf = AcviewerUtils.hourFormat(object.getTotal());
				String mf = AcviewerUtils.minuteFormat(object.getTotal());
				String sf = AcviewerUtils.secondFormat(object.getTotal());
				return hf + ":" + mf + ":" + sf;
			}
		};
		cdrSummaries.setColumnWidth(total, "20%");
		cdrSummaries.addColumn(total, "Общая продолжительность");
		
		// average column
		Column<CdrSummaryProxy, Number> count = new Column<CdrSummaryProxy, Number>(numberCell) {
			@Override
			public Number getValue(CdrSummaryProxy object) {
				return object.getCount();
			}
		};
		cdrSummaries.setColumnWidth(count, "20%");
		cdrSummaries.addColumn(count, "Количество соединений");


	}
}
