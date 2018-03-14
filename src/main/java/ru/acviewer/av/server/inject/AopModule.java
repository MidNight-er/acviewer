package ru.acviewer.av.server.inject;

import ru.acviewer.av.server.interceptor.AuthenticationInterceptor;
import ru.acviewer.av.server.interceptor.AuthenticationRequired;

import com.google.inject.AbstractModule;
import com.google.inject.matcher.Matchers;

public class AopModule extends AbstractModule {

	@Override
	protected void configure() {
		AuthenticationInterceptor authentication = new AuthenticationInterceptor();
		requestInjection(authentication);
		bindInterceptor(Matchers.any(), Matchers.annotatedWith(AuthenticationRequired.class),
				authentication);
	}

}
