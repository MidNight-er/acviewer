package ru.acviewer.av.shared;

import ru.acviewer.av.server.domain.CdrSummary;

import com.google.web.bindery.requestfactory.shared.ProxyFor;
import com.google.web.bindery.requestfactory.shared.ValueProxy;

@ProxyFor(CdrSummary.class)
public interface CdrSummaryProxy extends ValueProxy {

	String getId();
	Double getAverage();
	Double getTotal();
	Long getCount();
}
