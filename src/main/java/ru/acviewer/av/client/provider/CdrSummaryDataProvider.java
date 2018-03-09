package ru.acviewer.av.client.provider;

import java.util.Date;
import java.util.List;

import ru.acviewer.av.client.activity.CdrSummaryActivity.Driver;
import ru.acviewer.av.client.bean.SearchSummaryRequest;
import ru.acviewer.av.client.service.CdrRequestFactory;
import ru.acviewer.av.client.service.CdrRequestFactory.CallRequestContext;
import ru.acviewer.av.client.service.UserAccountRequestFactory;
import ru.acviewer.av.client.service.UserAccountRequestFactory.UserAccountContext;
import ru.acviewer.av.shared.CdrSummaryProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class CdrSummaryDataProvider extends AsyncDataProvider<CdrSummaryProxy> {

	private CdrRequestFactory cdrRequestFactory;
	private UserAccountRequestFactory userAccountRequestFactory;
	private Driver driver;
	private SearchSummaryRequest searchSummaryRequest;
	
	@Inject
	private EventBus eventBus;

	public CdrSummaryDataProvider(CdrRequestFactory requestFactory, 
			UserAccountRequestFactory userAccountRequestFactory, Driver driver,
			SearchSummaryRequest searchSummaryRequest) {
		this.cdrRequestFactory = requestFactory;
		this.userAccountRequestFactory = userAccountRequestFactory;
		this.driver = driver;
		this.searchSummaryRequest = searchSummaryRequest;
	}

	@Override
	protected void onRangeChanged(HasData<CdrSummaryProxy> display) {
		final Range range = display.getVisibleRange();
		driver.flush();
		//Date dateStart = new Date(new Date().getTime() - 1000L * 60 * 60 * 24 * 730);
		Date dateTimeStart = searchSummaryRequest.getDateTimeStart();
		Date dateTimeEnd = searchSummaryRequest.getDateTimeEnd();
		final UserAccountContext uaCtx = userAccountRequestFactory.createRequestContext();
		uaCtx.updateSummaryRange(dateTimeStart, dateTimeEnd).fire(new Receiver<Void>() {
			@Override
			public void onSuccess(Void response) {
				
				CallRequestContext ctx = cdrRequestFactory.createRequestContext();
				int start = range.getStart();
				int length = start + range.getLength();
				ctx.findAllSummaries(start, length).fire(
						new Receiver<List<CdrSummaryProxy>>() {
							@Override
							public void onSuccess(List<CdrSummaryProxy> response) {
								if (response != null) {
									updateRowData(range.getStart(), response);
									updateRowCount(response.size(), true);
								}
							}

							@Override
							public void onFailure(ServerFailure failure) {
								GWT.log("cdr summary data error: " + failure.getMessage());
							}
						});
				}				
		});
		
	}
		

}
