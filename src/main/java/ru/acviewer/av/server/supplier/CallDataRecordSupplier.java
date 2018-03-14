package ru.acviewer.av.server.supplier;

import java.util.Date;
import java.util.List;

import ru.acviewer.av.server.domain.CallDataRecord;

public interface CallDataRecordSupplier {

	CallDataRecord findById(Long id);
	Date lengthDate();
	List<CallDataRecord> findAll(int start, int length, Date dateStart,
			Date dateLength, String clid, String src, String dst, String disposition);
	Integer count(Date dateStart, Date dateLength, String clid,
			String src, String dst, String disposition);
	
}
