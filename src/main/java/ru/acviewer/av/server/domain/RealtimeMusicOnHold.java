package ru.acviewer.av.server.domain;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "rt_moh")
public class RealtimeMusicOnHold extends Realtime {

	public RealtimeMusicOnHold() {
		
	}
}

/*
`name` char(80) NOT NULL,
`mode` char(80) DEFAULT NULL,
`directory` char(255) DEFAULT NULL,
`application` char(255) DEFAULT NULL,
`digit` char(1) DEFAULT NULL,
`sort` char(10) DEFAULT NULL,
`format` char(10) DEFAULT NULL,
`stamp` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
PRIMARY KEY (`name`)
*/