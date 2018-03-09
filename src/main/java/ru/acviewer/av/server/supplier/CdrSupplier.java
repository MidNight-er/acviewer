package ru.acviewer.av.server.supplier;

import java.util.Date;
import java.util.List;

import ru.acviewer.av.server.domain.Cdr;
import ru.acviewer.av.server.domain.CdrSummary;

public interface CdrSupplier {

	Cdr findById(Long id);
	Date lengthDate();
	List<Cdr> findAll(int start, int length, Date dateStart,
                      Date dateEnd, String clid, String src, String dst, String disposition);
	Integer count(Date dateStart, Date dateLength, String clid,
                  String src, String dst, String disposition);
	List<CdrSummary> findAllSummaries(int start, int length);
}
