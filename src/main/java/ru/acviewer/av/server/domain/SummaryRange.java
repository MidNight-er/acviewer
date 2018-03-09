package ru.acviewer.av.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "summary_range")
public class SummaryRange implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(name = "cdr_start")
	private Date cdrStart;
	
	@Column(name = "cdr_end")
	private Date cdrEnd;

	public SummaryRange() {
		
	}
	
	public Long getId() {
		return id;
	}

	public Date getCdrStart() {
		return cdrStart;
	}

	public void setCdrStart(Date cdrStart) {
		this.cdrStart = cdrStart;
	}

	public Date getCdrEnd() {
		return cdrEnd;
	}

	public void setCdrEnd(Date cdrEnd) {
		this.cdrEnd = cdrEnd;
	}

}
