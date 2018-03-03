package ru.acviewer.av.server.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class Clid implements Serializable {
	/**
	 * Caller ID has caller name and caller number
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(name = "clid", nullable = false, length = 80)
	private String clid;
	
	public Clid() {

	}

	public String getName() {
		String name = null;
		try {
			name = clid.substring(clid.indexOf('"'), clid.lastIndexOf('"') + 1);
		} catch (IndexOutOfBoundsException e) {
			name = clid;
		}
		return name;
	}
}
