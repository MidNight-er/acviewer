package ru.acviewer.av.server.controller;

import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.URLConnection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

import ru.acviewer.av.server.domain.CallDataRecord;
import ru.acviewer.av.server.exception.UserIsNotAuthenticated;
import ru.acviewer.av.server.supplier.AudioSupplier;
import ru.acviewer.av.server.supplier.CallDataRecordSupplier;

import com.google.inject.Inject;
import com.google.inject.name.Named;
import com.google.sitebricks.client.transport.Raw;
import com.google.sitebricks.headless.Reply;
import com.google.sitebricks.headless.Request;
import com.google.sitebricks.http.Get;

public class AudioService {

	private Logger log;

	private Long id;

	@Inject
	@Named("FILE_PATH")
	private String filePath;

	private String fileExtension = "wav";

	@Inject
	private AudioSupplier audioSupplier;

	@Inject
	private CallDataRecordSupplier callDataRecordSupplier;

	@Inject
	public AudioService(Logger log) {
		this.log = log;
	}

	@Get
	public Reply<?> streamAudio(Request<String> request) {
		String rangeValue = request.header("range").trim().substring("bytes=".length());
		try {
			CallDataRecord cdr = callDataRecordSupplier.findById(id);
			if (cdr != null && !cdr.getUniqueId().isEmpty()) {
				File file = audioSupplier.getAudioFile(filePath
						+ cdr.getUniqueId() + "." + fileExtension);
				// 206 partial content and range request
				long fileLength = file.length();
				long start, end;
				if (rangeValue.startsWith("-")) {
					end = fileLength - 1;
					start = fileLength - 1 - Long.parseLong(rangeValue
							.substring("-".length()));
				} else {
					String[] range = rangeValue.split("-");
					start = Long.parseLong(range[0]);
					end = range.length > 1 ? Long.parseLong(range[1]) : fileLength -1;
				}
				if (end > fileLength -1) {
					end = fileLength - 1;
				}

				// headers
				Map<String, String> headers = new HashMap<String, String>();
				if (start <= end) {
					long contentLength = end - start + 1;
					headers.put("Content-Length", contentLength + "");
					headers.put("Content-Range", "bytes " + start + "-" 
							+ end + "/" + fileLength);
					String mimeType = URLConnection.guessContentTypeFromName(file.getName());
					headers.put("Content-Type", mimeType);
					headers.put("Cache-Control", "private, no-store, no-cache, must-revalidate");
					// byte array
					RandomAccessFile raf = new RandomAccessFile(file, "r");
					raf.seek(start);
					byte[] bytes = new byte[(int) raf.length()];
					raf.read(bytes);
					raf.close();
					return Reply.with(bytes).as(Raw.class).headers(headers).status(206);
				}
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
