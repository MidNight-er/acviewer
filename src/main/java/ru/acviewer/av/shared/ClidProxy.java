package ru.acviewer.av.shared;

import ru.acviewer.av.server.domain.Clid;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

@ProxyFor(Clid.class)
public interface ClidProxy extends ValueProxy {
	
	String getName();
}
