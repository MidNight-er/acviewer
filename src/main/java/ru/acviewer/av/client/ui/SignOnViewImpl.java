package ru.acviewer.av.client.ui;

import ru.acviewer.av.client.event.PasswordUpdateEvent;
import ru.acviewer.av.client.view.SignOnView;

import com.github.gwtbootstrap.client.ui.Badge;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.Modal;
import com.github.gwtbootstrap.client.ui.PasswordTextBox;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.SimplePanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class SignOnViewImpl extends Composite implements SignOnView {
	
	private Presenter presenter;

	private static SignOutViewImplUiBinder uiBinder = GWT
			.create(SignOutViewImplUiBinder.class);
	
	interface SignOutViewImplUiBinder extends UiBinder<Widget, SignOnViewImpl> {
	}
	
	public SignOnViewImpl() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
	}
	
	@UiField	// WorkFlow PANEL
	SimplePanel workFlowPanel;
	@Override
	public SimplePanel getWorkFlowPanel() {
		return workFlowPanel;
	}

	@UiField
	PasswordTextBox currentPasswordField, newPasswordField, retypePasswordField;
	@Override
	public HasValue<String> getCurrentPassword() {
		return currentPasswordField;
	}
	@Override
	public HasValue<String> getNewPassword() {
		return newPasswordField;
	}
	@Override
	public HasValue<String> getRetypePassword() {
		return retypePasswordField;
	}
	
	@UiField	// UserAccount Panel
	Modal userAccountModalField;
	@UiHandler("userAccountShowField")
	void userAccountShow(ClickEvent event) {
		userAccountModalField.show();
	}
	@UiHandler("userAccountHideButton")
	void userAccountHide(ClickEvent event) {
		userAccountModalField.hide();
		passwordNotificationField.setText("");
	}
	@UiHandler("userAccountUpdateButton")
	void userAccountUpdate(ClickEvent event) {
		if (presenter != null) {
			presenter.onUpdatePassword();
		}
	}
	
	@UiField	// About Panel
	Modal aboutModalField;
	@UiHandler("aboutProgrammField")
	void aboutProgrammShow(ClickEvent event) {
		aboutModalField.show();
	}
	@UiHandler("aboutModalHideButton")
	void aboutProgrammHide(ClickEvent event) {
		aboutModalField.hide();
	}

	@UiField
	Label passwordNotificationField;
	
	@Inject
	private void addHandlers(EventBus eventBus) {
		eventBus.addHandler(PasswordUpdateEvent.getType(), 
				new PasswordUpdateEvent.AppHandler() {
			@Override
			public void onSetPassword(PasswordUpdateEvent event) {
				passwordNotificationField.setText(event.getMessage());
			}
		});
	}

}
