package ru.acviewer.av.server.supplier;

import java.io.File;

import ru.acviewer.av.server.exception.UserIsNotAuthenticated;

public interface AudioSupplier {

	File getAudioFile(String fileName) throws UserIsNotAuthenticated;
}
