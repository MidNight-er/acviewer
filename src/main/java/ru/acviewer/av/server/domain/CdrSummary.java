package ru.acviewer.av.server.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@NamedQuery(name = "CdrSummary.findAll", query = "SELECT s FROM CdrSummary s")
@Table(name = "cdr_summary")
public class CdrSummary implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "id")
	private String id;
	
	@Column(name = "average")
	private Double average;
	
	@Column(name = "total")
	private Double total;
	
	@Column(name = "count")
	private Long count;
	
	public CdrSummary() {
		
	}
	
	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}

	public Double getAverage() {
		return average;
	}
	
	public void setAverage(Double average) {
		this.average = average;
	}

	public Double getTotal() {
		return total;
	}
	
	public void setTotal(Double total) {
		this.total = total;
	}

	public Long getCount() {
		return count;
	}
	
	public void setCount(Long count) {
		this.count = count;
	}

}
