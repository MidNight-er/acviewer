package ru.acviewer.av.client.service;

import java.util.Date;
import java.util.List;

import ru.acviewer.av.server.requestfactory.InjectingServiceLocator;
import ru.acviewer.av.server.supplier.CallDataRecordSupplierImpl;
import ru.acviewer.av.shared.CallDataRecordProxy;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

public interface CallDataRecordRequestFactory extends RequestFactory {

	@Service(value = CallDataRecordSupplierImpl.class, locator = InjectingServiceLocator.class)
	public interface CallRequestContext extends RequestContext {

		Request<CallDataRecordProxy> findById(Long id);
		Request<Date> lengthDate();
		Request<List<CallDataRecordProxy>> findAll(int start, int length, Date dateStart,
				Date dateLength, String cid, String src, String dst, String disposition);
		Request<Integer> count(Date dateStart, Date dateLength, String cid,
				String src, String dst, String disposition);
	}
	
	public CallRequestContext createRequestContext();
}
