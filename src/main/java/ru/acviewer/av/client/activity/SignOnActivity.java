package ru.acviewer.av.client.activity;

import ru.acviewer.av.client.event.PasswordUpdateEvent;
import ru.acviewer.av.client.i18n.messages.AppMessages;
import ru.acviewer.av.client.service.UserAccountRequestFactory;
import ru.acviewer.av.client.service.UserAccountRequestFactory.UserAccountContext;
import ru.acviewer.av.client.view.SignOnView;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Receiver;
import com.google.web.bindery.requestfactory.shared.ServerFailure;

public class SignOnActivity extends AbstractActivity implements
		SignOnView.Presenter {

	private SignOnView signOnView;
	private PlaceController placeController;
	private UserAccountRequestFactory requestFactory;

	@Inject
	private EventBus eventBus;
	@Inject 
	private AppMessages messages;

	@Inject
	public SignOnActivity(SignOnView signOnView, PlaceController placeController) {
		this.signOnView = signOnView;
		this.placeController = placeController;
	}

	@Inject
	private void setUpRequestFactory(UserAccountRequestFactory requestFactory) {
		this.requestFactory = requestFactory;
	}

	public SignOnActivity withPlace(Place place) {
		return this;
	}

	@Override
	public void start(AcceptsOneWidget panel, EventBus eventBus) {
		signOnView.setPresenter(this);
		panel.setWidget(signOnView.asWidget());
	}
	
	@Override
	public void onUpdatePassword() {
		String password = signOnView.getCurrentPassword().getValue();
		String newPassword = signOnView.getNewPassword().getValue();
		String retypePassword = signOnView.getRetypePassword().getValue();

		UserAccountContext ctx = requestFactory.createRequestContext();
			ctx.updatePassword(password, newPassword, retypePassword).fire(
					new Receiver<Void>() {
					@Override
				public void onSuccess(Void response) {
					eventBus.fireEvent(new PasswordUpdateEvent(
							messages.passwowdUpdateSuccess()));
				}
			
				@Override
				public void onFailure(ServerFailure failure) {
					eventBus.fireEvent(new PasswordUpdateEvent(
							messages.passwordUpdateError()));
				}
			});
	}

}
