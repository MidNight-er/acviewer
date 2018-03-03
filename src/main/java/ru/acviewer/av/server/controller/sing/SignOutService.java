package ru.acviewer.av.server.controller.sing;

import java.util.logging.Logger;

import ru.acviewer.av.server.supplier.UserAccountSupplier;

import com.google.inject.Inject;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Get;

public class SignOutService {

	private Logger log;
	
	@Inject
	private UserAccountSupplier userAccountSupplier;
	
	@Inject
	public SignOutService(Logger log) {
		this.log = log;
	}
	
	@Get
	public Reply<Object> signOut() {
		userAccountSupplier.signOut();
		return Reply.saying().redirect("/");
	}
}
