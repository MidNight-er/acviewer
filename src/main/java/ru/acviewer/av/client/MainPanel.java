package ru.acviewer.av.client;

import ru.acviewer.av.client.activity.AcviewerActivityMapper;
import ru.acviewer.av.client.activity.CdrActivityMapper;
import ru.acviewer.av.client.view.SignOnView;

import com.google.gwt.activity.shared.ActivityManager;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.inject.Inject;

public class MainPanel extends Composite {
	
	private static MainPanelUiBinder uiBinder = GWT.create(MainPanelUiBinder.class);

	interface MainPanelUiBinder extends UiBinder<Widget, MainPanel> {
	}

	@Inject
	private EventBus eventBus;
	
	public MainPanel() {
		initWidget(uiBinder.createAndBindUi(this));
	}
	
	@UiField
	ScrollPanel mainPanel;
	
	@Inject
	private void setUpActivityMapper(AcviewerActivityMapper appActivityMapper, 
			CdrActivityMapper cdrActivityMapper, SignOnView signOnView) {
		
		// AppActivityMapper
		ActivityManager appActivityManager = new ActivityManager(
				appActivityMapper, eventBus);
		appActivityManager.setDisplay(mainPanel);
		// CdrActivityMapper
		ActivityManager cdrActivityManager = new ActivityManager(
				cdrActivityMapper, eventBus);
		cdrActivityManager.setDisplay(signOnView.getWorkFlowPanel());
				
	}

}
