package ru.acviewer.av.client.service;

import java.util.Date;
import java.util.List;

import ru.acviewer.av.server.requestfactory.InjectingServiceLocator;
import ru.acviewer.av.server.supplier.CdrSupplierImpl;
import ru.acviewer.av.shared.CdrProxy;
import ru.acviewer.av.shared.CdrSummaryProxy;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

public interface CdrRequestFactory extends RequestFactory {

	@Service(value = CdrSupplierImpl.class, locator = InjectingServiceLocator.class)
	public interface CallRequestContext extends RequestContext {

		Request<CdrProxy> findById(Long id);
		Request<Date> lengthDate();
		Request<List<CdrProxy>> findAll(int start, int length, Date dateStart,
                                        Date dateLength, String cid, String src, String dst, String disposition);
		Request<Integer> count(Date dateStart, Date dateLength, String cid,
                               String src, String dst, String disposition);
		
		Request<List<CdrSummaryProxy>> findAllSummaries(int start, int length);
	}
	
	public CallRequestContext createRequestContext();
}
