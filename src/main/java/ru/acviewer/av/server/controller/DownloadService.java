package ru.acviewer.av.server.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import ru.acviewer.av.server.domain.Cdr;
import ru.acviewer.av.server.exception.UserIsNotAuthenticated;
import ru.acviewer.av.server.supplier.AudioSupplier;
import ru.acviewer.av.server.supplier.CdrSupplier;

import com.google.common.io.ByteStreams;
import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.sitebricks.client.transport.Raw;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.http.Get;

public class DownloadService {

	private Logger log;
	
	private Long id;
	
	@Inject
	@Named("FILE_PATH")
	private String filePath;
	
	private String fileExtension = "wav";
	
	@Inject
	private AudioSupplier audioSupplier;
	
	@Inject
	private CdrSupplier callDataRecordSupplier;
	
	@Inject
	public DownloadService(Logger log) {
		this.log = log;
	}

	@Get
	public Reply<?> downloadFile() {
		try {
			Cdr cdr = callDataRecordSupplier.findById(id);
			if (cdr != null && !cdr.getUniqueId().isEmpty()) {
				File file = audioSupplier.getAudioFile(filePath 
						+ cdr.getUniqueId() + "." + fileExtension);
				// date format
				SimpleDateFormat sdf = new SimpleDateFormat(
						"dd-MM-yyyy_HH-mm");
				String date = sdf.format(cdr.getStart());
				// rename file
				String fileName = cdr.getSrc() + "_" + cdr.getDst() 
						+ "_" + date + "." + fileExtension; 
				FileInputStream inputStream = new FileInputStream(file);
//				byte[] bytes = ByteStreams.toByteArray(inputStream);
				// set headers
				Map<String, String> headers = new HashMap<String, String>();
				headers.put("Content-Type", "application/octet-stream");
				headers.put("Content-disposition", "attachment; filename=" + fileName);
				return Reply.with(inputStream).as(Raw.class).headers(headers);
			} else {
				log.warning("call data record: \"" + id 
						+ "\" or uniqueId field not found");
			}
		} catch (IOException e) {
			log.warning(e.getMessage());
		} catch (UserIsNotAuthenticated e) {
			log.warning(e.getMessage());
			return Reply.saying().unauthorized();
		}
		return Reply.saying().ok();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
