package ru.acviewer.av.server.supplier;

import javax.persistence.EntityManager;

import com.google.inject.Guice;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Provider;
import com.google.inject.persist.PersistService;
import com.google.inject.persist.Transactional;
import com.google.inject.persist.jpa.JpaPersistModule;

import ru.acviewer.av.server.domain.UserAccount;

public class AppMisc {

	// default user and pass
	private final String NAME = "admin";
	private final String PASS = "admin";

	@Inject
	private Provider<EntityManager> entityManagerProvider;
	
	@Inject
	private PersistService persistService;

	@Transactional	// transactional annotation going to be set only on public method
	public void createUserAccount() {
		if (entityManagerProvider.get().find(UserAccount.class, NAME) == null) {
			UserAccount account = new UserAccount(NAME);
			String hash = BCrypt.hashpw(PASS, BCrypt.gensalt());
			account.setHash(hash);
			entityManagerProvider.get().persist(account);
		}
	}
	
	private void startService() {
		if (persistService != null) {
			persistService.start();
		}
	}
	
	private void stopService() {
		if (persistService != null) {
			persistService.stop();
		}
	}
	
	public static void populateDataStoreAtOnce(JpaPersistModule persistModule) {
		Injector injector = Guice.createInjector(persistModule);
		AppMisc appMisc = injector.getInstance(AppMisc.class);
		appMisc.startService();
		// create single user, "session per transaction" strategy
		appMisc.createUserAccount();
		appMisc.stopService();
	}
}
