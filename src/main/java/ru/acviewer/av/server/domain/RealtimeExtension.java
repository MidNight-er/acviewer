package ru.acviewer.av.server.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rt_extension")
public class RealtimeExtension extends Realtime {

	@Column(name = "context", nullable = false)
	private String context;
	
	@Column(name = "exten", nullable = false)
	private String exten;
	
	@Column(name = "priority", nullable = false)
	private Integer priority;
	
	@Column(name = "app")
	private String app;
	
	@Column(name = "appdata")
	private String appData;
	
	public RealtimeExtension() {
		
	}
	
	public String getContext() {
		return context;
	}

	public void setContext(String context) {
		this.context = context;
	}

	public String getExten() {
		return exten;
	}

	public void setExten(String exten) {
		this.exten = exten;
	}

	public Integer getPriority() {
		return priority;
	}

	public void setPriority(Integer priority) {
		this.priority = priority;
	}

	public String getApp() {
		return app;
	}

	public void setApp(String app) {
		this.app = app;
	}

	public String getAppData() {
		return appData;
	}

	public void setAppData(String appData) {
		this.appData = appData;
	}

	
}

/*
`id` int(11) NOT NULL AUTO_INCREMENT,
`context` varchar(20) NOT NULL DEFAULT '',
`exten` varchar(20) NOT NULL DEFAULT '',
`priority` tinyint(4) NOT NULL DEFAULT '0',
`app` varchar(20) DEFAULT NULL,
`appdata` varchar(128) DEFAULT NULL,
*/