package ru.acviewer.av.server.locator;

import ru.acviewer.av.server.domain.CallDataRecord;
import ru.acviewer.av.server.supplier.CallDataRecordSupplier;

import com.google.inject.Inject;
import com.google.web.bindery.requestfactory.shared.Locator;

public class CallLocator extends Locator<CallDataRecord, Long> {
	
	private CallDataRecordSupplier supplier;

	@Inject
	private void setUpCallSupplier(CallDataRecordSupplier supplier) {
		this.supplier = supplier;
	}

	@Override
	public CallDataRecord create(Class<? extends CallDataRecord> clazz) {
		return new CallDataRecord();
	}

	@Override
	public CallDataRecord find(Class<? extends CallDataRecord> clazz, Long id) {
		return supplier.findById(id);
	}

	@Override
	public Class<CallDataRecord> getDomainType() {
		return CallDataRecord.class;
	}

	@Override
	public Long getId(CallDataRecord domainObject) {
		return domainObject.getId();
	}

	@Override
	public Class<Long> getIdType() {
		return Long.class;
	}

	@Override
	public Object getVersion(CallDataRecord domainObject) {
		return domainObject.getVersion();
	}

}
