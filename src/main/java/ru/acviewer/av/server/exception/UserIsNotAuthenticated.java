package ru.acviewer.av.server.exception;

public class UserIsNotAuthenticated extends Exception {
	private static final long serialVersionUID = 1L;

	public UserIsNotAuthenticated(String message) {
		super(message);
	}
}
