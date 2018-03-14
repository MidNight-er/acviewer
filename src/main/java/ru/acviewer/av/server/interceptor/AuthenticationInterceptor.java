package ru.acviewer.av.server.interceptor;

import java.util.logging.Logger;

import org.aopalliance.intercept.MethodInterceptor;
import org.aopalliance.intercept.MethodInvocation;

import ru.acviewer.av.server.controller.sing.UserSession;
import ru.acviewer.av.server.exception.UserIsNotAuthenticated;

import com.google.inject.Inject;
import com.google.inject.Provider;

public class AuthenticationInterceptor implements MethodInterceptor {
	private static final Logger LOG = Logger.getLogger(AuthenticationInterceptor.class.getName());

	@Inject
	private Provider<UserSession> userSessionProvider;
	
	@Override
	public Object invoke(MethodInvocation invocation) throws Throwable {
		UserSession userSession = userSessionProvider.get();
		if (userSession.getUserAccount() != null) {
			return invocation.proceed();
		} else {
			throw new UserIsNotAuthenticated("User is not authenticated.");
		}
	}
	
}
