package ru.acviewer.av.server.locator;

import ru.acviewer.av.server.domain.Cdr;
import ru.acviewer.av.server.supplier.CdrSupplier;

import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Locator;

public class CdrLocator extends Locator<Cdr, Long> {
	
	private CdrSupplier supplier;

	@Inject
	private void setUpCallSupplier(CdrSupplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public Cdr create(Class<? extends Cdr> clazz) {
		return new Cdr();
	}

	@Override
	public Cdr find(Class<? extends Cdr> clazz, Long id) {
		return supplier.findById(id);
	}

	@Override
	public Class<Cdr> getDomainType() {
		return Cdr.class;
	}

	@Override
	public Long getId(Cdr domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<Long> getIdType() {
		return Long.class;
	}

	@Override
	public Object getVersion(Cdr domainObject) {
		return domainObject.getVersion();
	}

}
