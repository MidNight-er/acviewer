package ru.acviewer.av.client.activity;

import java.util.Date;

import ru.acviewer.av.client.bean.SearchRequest;
import ru.acviewer.av.client.provider.CdrDataProvider;
import ru.acviewer.av.client.service.CdrRequestFactory;
import ru.acviewer.av.client.service.CdrRequestFactory.CallRequestContext;
import ru.acviewer.av.client.view.CdrView;
import ru.acviewer.av.client.widget.SearchEditor;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;

public class CdrActivity extends AbstractActivity implements
		CdrView.Presenter {
	private final long DATE_START = 1000L * 60 * 60 * 24 * 14; // two weeks
	//private final long DATE_START = 1000L * 60 * 60 * 24 * 400; // 400 days

	private CdrView callDataRecordView;
	private PlaceController placeController;
	private CdrRequestFactory cdrRequestFactory;
	
	@Inject
	private EventBus eventBus;
	
	@Inject
	public CdrActivity(CdrView callDataRecordView, 
			PlaceController placeController) {
		this.callDataRecordView = callDataRecordView;
		this.placeController = placeController;
	}
	
	public interface Driver extends SimpleBeanEditorDriver<SearchRequest, SearchEditor> {
		
	}
	
	private static final Driver driver = GWT.create(Driver.class);
	
	@Inject
	private void setUpReqestFactory(CdrRequestFactory cdrRequestFactory) {
		this.cdrRequestFactory = cdrRequestFactory;
	}
	
	public CdrActivity withPlace(Place place) {
		return this;
	}
	
	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		callDataRecordView.setPresenter(this);
		panel.setWidget(callDataRecordView.asWidget());
	}
	
	@Override
	public void onLengthDate() {
		CallRequestContext ctx = cdrRequestFactory.createRequestContext();
		ctx.lengthDate().fire(new Receiver<Date>() {
			@Override
			public void onSuccess(Date response) {
				SearchRequest searchRequest = new SearchRequest();
				searchRequest.setDateTimeStart(new Date(response.getTime() - DATE_START));
				searchRequest.setDateTimeLength(response);
				driver.initialize(callDataRecordView.getSearchEditor());
				driver.edit(searchRequest);
				
				CdrDataProvider callDataProvider = new CdrDataProvider(cdrRequestFactory,
						driver, searchRequest);
				callDataProvider.addDataDisplay(callDataRecordView.getCallDataRecords());
			}
		});

	}

}
