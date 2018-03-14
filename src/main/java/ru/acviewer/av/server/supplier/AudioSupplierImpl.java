package ru.acviewer.av.server.supplier;

import java.io.File;

import ru.acviewer.av.server.exception.UserIsNotAuthenticated;
import ru.acviewer.av.server.interceptor.AuthenticationRequired;

public class AudioSupplierImpl implements AudioSupplier {

	@AuthenticationRequired
	@Override
	public File getAudioFile(String fileName) throws UserIsNotAuthenticated {
		return new File(fileName);
	}

}
