package ru.acviewer.av.client.provider;

import java.util.Date;
import java.util.List;

import ru.acviewer.av.client.activity.CdrActivity.Driver;
import ru.acviewer.av.client.bean.SearchRequest;
import ru.acviewer.av.client.service.CdrRequestFactory;
import ru.acviewer.av.client.service.CdrRequestFactory.CallRequestContext;
import ru.acviewer.av.shared.CdrProxy;

import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.view.client.AsyncDataProvider;
import com.google.gwt.view.client.HasData;
import com.google.gwt.view.client.Range;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class CdrDataProvider extends AsyncDataProvider<CdrProxy> {

	private CdrRequestFactory requestFactory;
	private SearchRequest searchRequest;
	private Driver driver;
	
	@Inject
	private EventBus eventBus;

	public CdrDataProvider(CdrRequestFactory requestFactory, 
			Driver driver, SearchRequest searchRequest) {
		this.requestFactory = requestFactory;
		this.driver = driver;
		this.searchRequest = searchRequest;
	}

	@Override
	protected void onRangeChanged(HasData<CdrProxy> display) {
		driver.flush();
		Date dateStart = searchRequest.getDateTimeStart();
		Date dateLength = searchRequest.getDateTimeLength();
		String clid = searchRequest.getClid() != null ? searchRequest.getClid() : "";
		String src = searchRequest.getSrc() != null ? searchRequest.getSrc() : "";
		String dst = searchRequest.getDst() != null ? searchRequest.getDst() : "";
		String disposition = searchRequest.getDisposition() != null 
				? searchRequest.getDisposition().toString() : "";
		
		CallRequestContext countCtx = requestFactory.createRequestContext();
		rowCount(countCtx, dateStart, dateLength, clid, src, dst, disposition);
		CallRequestContext dataCtx = requestFactory.createRequestContext();
		rowData(dataCtx, display, dateStart, dateLength, clid, src, dst, disposition);
	}

	private void rowCount(CallRequestContext countCtx, Date dateStart, Date dateLength, 
			String clid, String src, String dst, String disposition) {
		countCtx.count(dateStart, dateLength, clid, src, dst, disposition)
				.with("clid").fire(new Receiver<Integer>() {
			@Override
			public void onSuccess(Integer response) {
				if (response != null) {
					updateRowCount(response, true);
				}
			}
			
			@Override
			public void onFailure(ServerFailure failure) {
				GWT.log("call row count error: " + failure.getMessage());
			}
		});
	}

	private void rowData(CallRequestContext context, HasData<CdrProxy> display,
			Date dateStart, Date dateLength, String clid, String src, String dst, 
			String disposition) {
		final Range range = display.getVisibleRange();
		int start = range.getStart();
		int length = start + range.getLength();
		
		context.findAll(start, length,	dateStart, dateLength, clid, src, dst, disposition)
				.with("clid").fire(new Receiver<List<CdrProxy>>() {
			@Override
			public void onSuccess(List<CdrProxy> response) {
				if (response != null) {
					updateRowData(range.getStart(), response);
				}
			}

			@Override
			public void onFailure(ServerFailure failure) {
				//GWT.getHostPageBaseURL()
//				GWT.log("exception type: " + failure.getExceptionType());
				GWT.log("call row data error: " + failure.getMessage());
			}
		});
	}

}
