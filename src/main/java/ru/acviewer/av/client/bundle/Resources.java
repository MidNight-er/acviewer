package ru.acviewer.av.client.bundle;

import com.google.gwt.resources.client.ClientBundle;
import com.google.gwt.resources.client.DataResource;
import com.google.gwt.resources.client.DataResource.DoNotEmbed;
import com.google.gwt.resources.client.ImageResource;
import com.google.gwt.resources.client.TextResource;

public interface Resources extends ClientBundle {
	
	@Source("image/Original_512.png")
	ImageResource logo();
	
	@Source("image/reader-16x16.png")
	ImageResource acrobatReader();
	
	@Source("text/AboutProgram.txt")
	TextResource aboutProgram();
	
	@DoNotEmbed
	@Source("text/license.pdf")
	DataResource license();
	
}
