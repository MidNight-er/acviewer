package ru.acviewer.av.client.service;

import ru.acviewer.av.server.requestfactory.InjectingServiceLocator;
import ru.acviewer.av.server.supplier.RealtimeSupplierImpl;

import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

public interface RealtimeAccountRequestFactory extends RequestFactory {

	@Service(value = RealtimeSupplierImpl.class, locator = InjectingServiceLocator.class)
	public interface CustomerAccountRequestContext extends RequestContext {
		

	}
	
	CustomerAccountRequestContext createRequestContext();
}
