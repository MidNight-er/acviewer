package ru.acviewer.av.server.supplier;

import java.util.Date;

import ru.acviewer.av.server.domain.UserAccount;
import ru.acviewer.av.server.exception.UserIsNotAuthenticated;

public interface UserAccountSupplier {

	UserAccount findById(String id);
	void saveOrUpdate(UserAccount userAccount);
	
	UserAccount signIn(String name, String password) throws UserIsNotAuthenticated;
	void signOut();
	void updatePassword(String password, String newPassword,
                        String retypePassword) throws UserIsNotAuthenticated;
	void updateSummaryRange(Date start, Date end);

}
