package ru.acviewer.av.shared;

import java.util.Date;

import ru.acviewer.av.server.domain.Cdr;
import ru.acviewer.av.server.locator.CdrLocator;

import com.google.web.bindery.requestfactory.shared.EntityProxy;
import com.google.web.bindery.requestfactory.shared.ProxyFor;

@ProxyFor(value = Cdr.class, locator = CdrLocator.class)
public interface CdrProxy extends EntityProxy {
	
	Long getId();
	ClidProxy getClid();
	String getSrc();
	String getDst();
	Double getDuration();
	Double getBillsec();
	String getDisposition();
	String getUniqueId();
	Date getStart();
	Date getAnswer();
	Date getEnd();
	
	boolean getRecord();
}
