package ru.acviewer.av.client.view;

import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.user.client.ui.SimplePanel;

public interface SignOnView extends IsWidget {

	public SimplePanel getWorkFlowPanel();
	
	public HasValue<String> getCurrentPassword();
	public HasValue<String> getNewPassword();
	public HasValue<String> getRetypePassword();
	
	public void setPresenter(Presenter presenter);
	
	public interface Presenter {
		void onUpdatePassword();
	}
}
