package ru.acviewer.av.shared;

import java.util.Date;

import ru.acviewer.av.server.domain.CallDataRecord;
import ru.acviewer.av.server.locator.CallLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = CallDataRecord.class, locator = CallLocator.class)
public interface CallDataRecordProxy extends EntityProxy {
	
	Long getId();
	ClidProxy getClid();
	String getSrc();
	String getDst();
	String getDisposition();
	String getUniqueId();
	Date getStart();
	Date getAnswer();
	Date getEnd();
	
	boolean getRecord();
}
