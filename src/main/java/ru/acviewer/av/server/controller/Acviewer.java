package ru.acviewer.av.server.controller;

import java.util.logging.Logger;

import ru.acviewer.av.server.controller.sing.UserSession;
import ru.acviewer.av.server.domain.UserAccount;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.http.Get;

public class Acviewer {
	
	private Logger log;
	
	private Provider<UserSession> userSessionProvider;
	
	private UserAccount userAccount;
	
	@Inject
	public Acviewer(Logger log, Provider<UserSession> userSessionProvider) {
		this.log = log;
		this.userSessionProvider = userSessionProvider;
	}
	
	@Get
	public void get() {
		UserSession userSession = userSessionProvider.get();
		userAccount = userSession.getUserAccount();
	}
	
	public UserAccount getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
	
}
