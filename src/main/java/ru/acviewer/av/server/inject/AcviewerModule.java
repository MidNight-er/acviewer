package ru.acviewer.av.server.inject;

import ru.acviewer.av.server.supplier.AudioSupplier;
import ru.acviewer.av.server.supplier.AudioSupplierImpl;
import ru.acviewer.av.server.supplier.CdrSupplier;
import ru.acviewer.av.server.supplier.CdrSupplierImpl;
import ru.acviewer.av.server.supplier.RealtimeSupplier;
import ru.acviewer.av.server.supplier.RealtimeSupplierImpl;
import ru.acviewer.av.server.supplier.UserAccountSupplier;
import ru.acviewer.av.server.supplier.UserAccountSupplierImpl;

import com.google.common.eventbus.EventBus;
import com.google.inject.AbstractModule;
import com.google.inject.Singleton;
import com.google.inject.TypeLiteral;
import com.google.inject.matcher.Matchers;
import com.google.inject.name.Names;
import com.google.inject.spi.InjectionListener;
import com.google.inject.spi.TypeEncounter;
import com.google.inject.spi.TypeListener;

public class AcviewerModule extends AbstractModule {
	private final EventBus eventBus = new EventBus("Default EventBus");

	@Override
	protected void configure() {
		bind(CdrSupplier.class).to(CdrSupplierImpl.class).in(Singleton.class);
		bind(RealtimeSupplier.class).to(RealtimeSupplierImpl.class).in(Singleton.class);
		bind(UserAccountSupplier.class).to(UserAccountSupplierImpl.class).in(Singleton.class);
		bind(AudioSupplier.class).to(AudioSupplierImpl.class).in(Singleton.class);
		
		bind(String.class).annotatedWith(Names.named("FILE_PATH")).toInstance(
				"/var/spool/asterisk/monitor/");
		
		bind(EventBus.class).toInstance(eventBus);
		bindListener(Matchers.any(), new TypeListener() {
			@Override
			public <I> void hear(TypeLiteral<I> type, TypeEncounter<I> encounter) {
				encounter.register(new InjectionListener<I>() {
					@Override
					public void afterInjection(I injectee) {
						eventBus.register(injectee);
					}
				});
			}
		});
	}
}
