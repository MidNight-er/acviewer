package ru.acviewer.av.client.widget;

public enum Disposition {

	ANSWERED("ANSWERED"),
	BUSY("BUSY"),
	FAILED("FAILED"),
	NO_ANSWER("NO ANSWER"),
	CONGESTION("CONGESTION");
	
	private String text;
	
	Disposition(final String text) {
		this.text = text;
	}
	
	public String toString() {
		return text;
	}
}
