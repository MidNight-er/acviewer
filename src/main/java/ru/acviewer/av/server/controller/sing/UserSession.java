package ru.acviewer.av.server.controller.sing;

import java.io.Serializable;

import ru.acviewer.av.server.domain.UserAccount;

import com.google.inject.servlet.SessionScoped;

@SessionScoped
public class UserSession implements Serializable {
	private static final long serialVersionUID = 1L;

	private UserAccount userAccount;
	
	public UserSession() {

	}

	public synchronized UserAccount getUserAccount() {
		return userAccount;
	}

	public synchronized void setUserAccount(UserAccount userAccount) {
		this.userAccount = userAccount;
	}
}
