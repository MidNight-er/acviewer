package ru.acviewer.av.server.supplier;

import java.io.File;
import java.util.Date;
import java.util.List;
import java.util.logging.Logger;

import javax.persistence.EntityManager;
import javax.persistence.NamedQuery;
import javax.persistence.Query;
import javax.persistence.TypedQuery;

import com.google.inject.Inject;
import com.google.inject.Provider;
import com.google.inject.name.Named;

import ru.acviewer.av.server.domain.Cdr;
import ru.acviewer.av.server.domain.CdrSummary;
import ru.acviewer.av.server.interceptor.AuthenticationRequired;

public class CdrSupplierImpl implements CdrSupplier {

	private Logger log;
	
	private Provider<EntityManager> entityManagerProvider;

	@Inject
	@Named("FILE_PATH")
	private String filePath;
	
	@Inject
	public CdrSupplierImpl(Logger log, Provider<EntityManager> entityManagerProvider) {
		this.log = log;
		this.entityManagerProvider = entityManagerProvider;
	}

	@Override
	public Cdr findById(Long id) {
		return entityManagerProvider.get().find(Cdr.class, id);
	}
	
	@Override
	public Date lengthDate() {
		return new Date();
	}

	@AuthenticationRequired
	@Override
	public List<Cdr> findAll(int start, int length, Date dateStart, 
			Date dateLength, String clid, String src, String dst, String disposition) {
		List<Cdr> callList = null;
		TypedQuery<Cdr> query = entityManagerProvider.get().createNamedQuery(
				"Cdr.findAll", Cdr.class);
		query.setFirstResult(start);
		query.setMaxResults(length);

		query.setParameter("start", dateStart);
		query.setParameter("end", dateLength);
		String clidParam = makeClidParam(clid);
		query.setParameter("clid", clidParam);
		src = (src.isEmpty()) ? "%" : "%" + src + "%";
		query.setParameter("src", src);
		dst = (dst.isEmpty()) ? "%" : "%" + dst + "%";
		query.setParameter("dst", dst);
		disposition = (disposition.isEmpty()) ? "%" : "%" + disposition + "%";
		query.setParameter("disposition", disposition);
		
		try {
			callList = query.getResultList();
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
		for (Cdr call : callList) {
			// check file system and SELinux context permission
			File file = new File(filePath, call.getUniqueId() + ".wav");
			call.setRecord(file.exists());
		}
		return callList;
	}

	@AuthenticationRequired
	@Override
	public Integer count(Date dateStart, Date dateLength,
			String clid, String src, String dst, String disposition) {
		int count = 0;
		TypedQuery<Number> query = entityManagerProvider.get()
				.createNamedQuery("Cdr.count", Number.class);
		query.setParameter("start", dateStart);
		query.setParameter("end", dateLength);
		String clidParam = makeClidParam(clid);
		query.setParameter("clid", clidParam);
		src = (src.isEmpty()) ? "%" : "%" + src + "%";
		query.setParameter("src", src);
		dst = (dst.isEmpty()) ? "%" : "%" + dst + "%";
		query.setParameter("dst", dst);
		disposition = (disposition.isEmpty()) ? "%" : "%" + disposition + "%";
		query.setParameter("disposition", disposition);
		
		try {
			count = query.getSingleResult().intValue();
		} catch (Exception e) {
			log.warning(e.getMessage());
		}
		return count; 
	}
	
	private String makeClidParam(String clid) {
		return (clid.isEmpty()) ? "%" : "%\"%" + clid + "%\"%"; // get name only
	}
	
	@Override
	public List<CdrSummary> findAllSummaries(int start, int length) {
		List<CdrSummary> summaries = null;
		TypedQuery<CdrSummary> query = entityManagerProvider.get()
				.createNamedQuery("CdrSummary.findAll", CdrSummary.class);
		query.setFirstResult(start);
		query.setMaxResults(length);
		
		try {
			summaries = query.getResultList();
		} catch (Exception e) {
			log.warning(e.getMessage());
		}		
		return summaries;
	}
}
