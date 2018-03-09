package ru.acviewer.av.server.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.persistence.Transient;
import javax.persistence.Version;

@Entity
@NamedQueries({
		@NamedQuery(name = "Cdr.findAll", query = "SELECT c FROM Cdr c"
				+ " WHERE (c.start > :start) AND (c.end < :end)" 
				+ " AND (c.clid.clid LIKE :clid) AND (c.src LIKE :src)"
				+ " AND (c.dst LIKE :dst)"
				+ " AND (c.disposition LIKE :disposition)"
				+ " ORDER BY c.start DESC"),
		@NamedQuery(name = "Cdr.count", query = "SELECT COUNT(c) FROM Cdr c"
				+ " WHERE (c.start > :start) AND (c.end < :end)"
				+ " AND (c.clid.clid LIKE :clid) AND (c.src LIKE :src)"
				+ " AND (c.dst LIKE :dst)"
				+ " AND (c.disposition LIKE :disposition)")
})
@Table(name = "cdr")
public class Cdr implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private Long id;

	@Version
	@Transient
	private Long version = 0L;
	
	// file
	@Transient
	private boolean record;

	@Embedded
	private Clid clid;

	@Column(name = "src", nullable = false, length = 80)
	private String src;

	@Column(name = "dst", nullable = false, length = 80)
	private String dst;

	@Column(name = "dcontext", nullable = false, length = 80)
	private String dcontext;

	@Column(name = "lastapp", nullable = false, length = 200)
	private String lastapp;

	@Column(name = "lastdata", nullable = false, length = 200)
	private String lastdata;

	@Column(name = "duration", nullable = true)
	private Double duration;

	@Column(name = "billsec", nullable = true)
	private Double billsec;

	@Column(name = "disposition", nullable = true)
	private String disposition;

	@Column(name = "channel", nullable = true, length = 50)
	private String channel;

	@Column(name = "dstchannel", nullable = true, length = 50)
	private String dstchannel;

	@Column(name = "amaflags", nullable = true, length = 50)
	private String amaflags;

	@Column(name = "accountcode", nullable = true, length = 20)
	private String accountcode;

	@Column(name = "uniqueId", nullable = false, length = 32)
	private String uniqueId;

	@Column(name = "userfield", nullable = true)
	private Double userfield;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "start", nullable = false)
	private Date start;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "answer", nullable = false)
	private Date answer;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "end", nullable = false)
	private Date end;

	public Cdr() {

	}

	public Long getId() {
		return id;
	}

	public Long getVersion() {
		return version;
	}
	
	public Clid getClid() {
		return clid;
	}

	public String getSrc() {
		return src;
	}

	public String getDst() {
		return dst;
	}
	
	public Double getDuration() {
		return duration;
	}

	public Double getBillsec() {
		return billsec;
	}

	public String getDisposition() {
		return disposition;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public Date getStart() {
		return start;
	}

	public Date getAnswer() {
		return answer;
	}

	public Date getEnd() {
		return end;
	}

	public boolean getRecord() {
		return record;
	}

	public void setRecord(boolean record) {
		this.record = record;
	}
}
