package ru.acviewer.av.server.controller.sing;

import java.util.logging.Logger;

import ru.acviewer.av.server.domain.UserAccount;
import ru.acviewer.av.server.exception.UserIsNotAuthenticated;
import ru.acviewer.av.server.supplier.UserAccountSupplier;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Post;

public class SignInService {
	
	private Logger log;
	
	private Provider<UserSession> userSessionProvider;
	
	private String name;
	
	private String password;
	
	@Inject
	private UserAccountSupplier userAccountSupplier;
	
	@Inject
	public SignInService(Logger log, Provider<UserSession> userSessionProvider) {
		this.log = log;
		this.userSessionProvider = userSessionProvider;
	}
	
	@Post
	public Reply<Object> signIn() {
		try {
			UserAccount userAccount = userAccountSupplier.signIn(name, password);
			if (userAccount != null) {
				UserSession userSession = userSessionProvider.get();
				userSession.setUserAccount(userAccount);
			}
		} catch (UserIsNotAuthenticated e) {
			log.warning(e.getMessage());
		}
		return Reply.saying().redirect("/");
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
