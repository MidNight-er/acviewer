package ru.acviewer.av.client.bean;

import java.util.Date;

import ru.acviewer.av.client.widget.Disposition;

public class SearchRequest {
	
	private Date dateTimeStart;

	private Date dateTimeLength;

	private String clid;

	private String src;

	private String dst;

	private Disposition disposition;
	
	public Date getDateTimeStart() {
		return dateTimeStart;
	}

	public void setDateTimeStart(Date dateTimeStart) {
		this.dateTimeStart = dateTimeStart;
	}

	public Date getDateTimeLength() {
		return dateTimeLength;
	}

	public void setDateTimeLength(Date dateTimeLength) {
		this.dateTimeLength = dateTimeLength;
	}
	
	public String getClid() {
		return clid;
	}

	public void setClid(String clid) {
		this.clid = clid;
	}

	public String getSrc() {
		return src;
	}

	public void setSrc(String src) {
		this.src = src;
	}

	public String getDst() {
		return dst;
	}

	public void setDst(String dst) {
		this.dst = dst;
	}

	public Disposition getDisposition() {
		return disposition;
	}

	public void setDisposition(Disposition disposition) {
		this.disposition = disposition;
	}

}
