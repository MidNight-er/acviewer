package ru.acviewer.av.client.service;

import java.util.Date;

import ru.acviewer.av.server.requestfactory.InjectingServiceLocator;
import ru.acviewer.av.server.supplier.UserAccountSupplierImpl;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

public interface UserAccountRequestFactory extends RequestFactory {

	@Service(value = UserAccountSupplierImpl.class, locator = InjectingServiceLocator.class)
	public interface UserAccountContext extends RequestContext {

		Request<Void> updatePassword(String password, String newPassword,
                                     String retypePassword);
		Request<Void> updateSummaryRange(Date start, Date end);
	}

	UserAccountContext createRequestContext();
}
