package ru.acviewer.av.server.inject;

import ru.acviewer.av.server.controller.Acviewer;
import ru.acviewer.av.server.controller.AudioService;
import ru.acviewer.av.server.controller.DownloadService;
import ru.acviewer.av.server.controller.sing.SignInService;
import ru.acviewer.av.server.controller.sing.SignOutService;
import ru.acviewer.av.server.requestfactory.InjectedRequestFactoryModule;
import ru.acviewer.av.server.requestfactory.InjectedRequestFactoryServlet;
import ru.acviewer.av.server.supplier.AppMisc;

import com.google.inject.Guice;
import com.google.inject.Injector;
import com.google.inject.persist.PersistFilter;
import com.google.inject.persist.jpa.JpaPersistModule;
import com.google.inject.servlet.GuiceServletContextListener;
import com.google.inject.servlet.ServletModule;
import com.google.sitebricks.SitebricksModule;
import com.google.sitebricks.SitebricksServletModule;

public class WebServletContextListener extends GuiceServletContextListener {

	@Override
	protected Injector getInjector() {
		return Guice.createInjector(new ServletModule() {
			@Override
			protected void configureServlets() {
				install(new AppModule());
				// JPA
				JpaPersistModule persistModule = new JpaPersistModule("resource-local");
				install(persistModule);
				install(new SitebricksModule() {
					@Override
					protected void configureSitebricks() {
						at("/").show(Acviewer.class);
						at("/signIn").serve(SignInService.class);
						at("/signOut").serve(SignOutService.class);
						at("/audioService").serve(AudioService.class);
						at("/downloadService").serve(DownloadService.class);
					}
					
					@Override
					protected SitebricksServletModule servletModule() {
						return new SitebricksServletModule() {
							@Override
							protected void configurePreFilters() {
								filter("/*").through(PersistFilter.class);
							}
						};
					}
				});
				
				// GWT injected RequestFactory
				install(new InjectedRequestFactoryModule());
				serve("/gwtRequest").with(InjectedRequestFactoryServlet.class);
				
				// AOP
				install(new AopModule());
				
				AppMisc.populateDataStoreAtOnce(persistModule);
			}
		});
	}

}
