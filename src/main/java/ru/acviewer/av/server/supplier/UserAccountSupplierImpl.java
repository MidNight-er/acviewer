package ru.acviewer.av.server.supplier;

import java.util.Date;
import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.persist.Transactional;

import ru.acviewer.av.server.controller.sing.UserSession;
import ru.acviewer.av.server.domain.SummaryRange;
import ru.acviewer.av.server.domain.UserAccount;
import ru.acviewer.av.server.exception.UserIsNotAuthenticated;
import ru.acviewer.av.server.interceptor.AuthenticationRequired;

public class UserAccountSupplierImpl implements UserAccountSupplier {
	
	private Logger log;
	private Provider<EntityManager> entityManagerProvider;
	private Provider<UserSession> userSessionProvider;
	
	@Inject
	public UserAccountSupplierImpl(Logger log, 
			Provider<EntityManager> entityManagerProvider,
			Provider<UserSession> userSessionProvider) {
		this.log = log;
		this.entityManagerProvider = entityManagerProvider;
		this.userSessionProvider = userSessionProvider;
	}
	
	@Override
	public UserAccount findById(String id) {
		return entityManagerProvider.get().find(UserAccount.class, id);
	}
	
	@Transactional
	@Override
	public void saveOrUpdate(UserAccount userAccount) {
		entityManagerProvider.get().merge(userAccount);
	}

	@Override
	public UserAccount signIn(String name, String password) throws UserIsNotAuthenticated {
		UserAccount userAccount = null, ua = findById(name);
		if (ua != null && BCrypt.checkpw(password, ua.getHash())) {
			userAccount = ua;
		} else {
			throw new UserIsNotAuthenticated("User authentication error");
		}
		return userAccount;
	}
	
	@Override
	public void signOut() {
		UserSession userSession = userSessionProvider.get();
		userSession.setUserAccount(null);
	}
	
	@AuthenticationRequired
	@Override
	public void updatePassword(String password, String newPassword, 
			String retypePassword) throws UserIsNotAuthenticated {
		UserSession userSession = userSessionProvider.get();
		// get current user id
		String id = userSession.getUserAccount().getId();
		UserAccount userAccount = findById(id);
		if (BCrypt.checkpw(password, userAccount.getHash())) {
			String hash = BCrypt.hashpw(newPassword, BCrypt.gensalt());
			userAccount.setHash(hash);
			saveOrUpdate(userAccount);
		} else {
			throw new UserIsNotAuthenticated("Update password error");
		}
	}
	
	@AuthenticationRequired
	@Transactional
	@Override
	public void updateSummaryRange(Date start, Date end) {
		UserSession userSession = userSessionProvider.get();
		// get current user id
		String id = userSession.getUserAccount().getId();
		UserAccount userAccount = findById(id);
		SummaryRange summaryRange = userAccount.getSummaryRange();
		summaryRange.setCdrStart(start);
		summaryRange.setCdrEnd(end);
		userAccount.setSummaryRange(summaryRange);
		entityManagerProvider.get().merge(userAccount);
	}

}
