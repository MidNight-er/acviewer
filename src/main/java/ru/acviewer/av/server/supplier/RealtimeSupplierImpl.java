package ru.acviewer.av.server.supplier;

import java.util.logging.Logger;

import javax.persistence.EntityManager;

import com.google.inject.Inject;
import com.google.inject.Provider;

import ru.acviewer.av.server.domain.Realtime;
import ru.acviewer.av.server.domain.RealtimeSipBuddy;

public class RealtimeSupplierImpl implements RealtimeSupplier {
	
	private Logger log;
	
	private Provider<EntityManager> entityManagerProvider;
	
	@Inject
	public RealtimeSupplierImpl(Logger log, Provider<EntityManager> entityManagerProvider) {
		this.log = log;
		this.entityManagerProvider = entityManagerProvider;
	}



}
