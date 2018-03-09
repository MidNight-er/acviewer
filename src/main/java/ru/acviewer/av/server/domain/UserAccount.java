package ru.acviewer.av.server.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name = "user_account")
public class UserAccount implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id	// default strategy is "assigned"
	private String id;
	
	@Version
	private Long version;
	
	@Column(name = "hash", nullable = false)
	private String hash;	// BCrypt HASH of user password
	
	@OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private SummaryRange summaryRange;
	
	public UserAccount() {
		
	}
	
	public UserAccount(String id) {
		this.id = id;
	}
	
	public String getId() {
		return id;
	}
	
	public Long getVersion() {
		return version;
	}
	
	public String getHash() {
		return hash;
	}
	
	public void setHash(String hash) {
		this.hash = hash;
	}

	public SummaryRange getSummaryRange() {
		return summaryRange;
	}

	public void setSummaryRange(SummaryRange summaryRange) {
		this.summaryRange = summaryRange;
	}

}
