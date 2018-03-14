package ru.acviewer.av.client.service;

import ru.acviewer.av.server.requestfactory.InjectingServiceLocator;
import ru.acviewer.av.server.supplier.UserAccountSupplierImpl;

import com.google.web.bindery.requestfactory.shared.Request;
import com.google.web.bindery.requestfactory.shared.RequestContext;
import com.google.web.bindery.requestfactory.shared.RequestFactory;
import com.google.web.bindery.requestfactory.shared.Service;

public interface UserRequestFactory extends RequestFactory {

	@Service(value = UserAccountSupplierImpl.class, locator = InjectingServiceLocator.class)
	public interface UserRequestContext extends RequestContext {

		Request<Void> updatePassword(String password, String newPassword,
				String retypePassword);
	}

	UserRequestContext createRequestContext();
}
