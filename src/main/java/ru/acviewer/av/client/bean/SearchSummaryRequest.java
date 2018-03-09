package ru.acviewer.av.client.bean;

import java.util.Date;

public class SearchSummaryRequest {
	
	private Date dateTimeStart;

	private Date dateTimeEnd;

	public Date getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(Date dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public Date getDateTimeEnd() {
		return dateTimeEnd;
	}

	public void setDateTimeEnd(Date dateTimeEnd) {
		this.dateTimeEnd = dateTimeEnd;
	}
	
}
