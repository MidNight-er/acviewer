package ru.acviewer.av.client;

import com.google.gwt.i18n.client.NumberFormat;

public class AcviewerUtils {

	public static String hourFormat(double currentTime) {
		double h = Math.floor((currentTime / 60 / 60) % 60);
		String hf = h < 10 ? NumberFormat.getFormat("0").format(h) :
			NumberFormat.getFormat("00").format(h);
		return hf;
	}
	
	public static String minuteFormat(double currentTime) {
		double m = Math.floor((currentTime / 60) % 60);
		String mf = m < 10 ? NumberFormat.getFormat("0").format(m) :
			NumberFormat.getFormat("00").format(m);
		return mf;
	}
	
	public static String secondFormat(double currentTime) {
		double s = Math.floor(currentTime % 60);
		String sf = s < 10 ? NumberFormat.getFormat("0").format(s) :
			NumberFormat.getFormat("00").format(s);
		return sf;
	}
}
