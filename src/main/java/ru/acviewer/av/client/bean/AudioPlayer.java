package ru.acviewer.av.client.bean;

import ru.acviewer.av.client.event.dom.CanPlayEvent;
import ru.acviewer.av.client.event.dom.CanPlayHandler;
import ru.acviewer.av.client.event.dom.PauseEvent;
import ru.acviewer.av.client.event.dom.PauseHandler;
import ru.acviewer.av.client.event.dom.PlayEvent;
import ru.acviewer.av.client.event.dom.PlayHandler;
import ru.acviewer.av.client.event.dom.TimeUpdateEvent;
import ru.acviewer.av.client.event.dom.TimeUpdateHandler;

import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.AudioElement;
import com.google.gwt.dom.client.Element;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.EndedEvent;
import com.google.gwt.event.dom.client.EndedHandler;
import com.google.gwt.event.dom.client.ErrorEvent;
import com.google.gwt.event.dom.client.ErrorHandler;
import com.google.gwt.event.dom.client.LoadedMetadataEvent;
import com.google.gwt.event.dom.client.LoadedMetadataHandler;
import com.google.gwt.http.client.UrlBuilder;
import com.google.gwt.i18n.client.NumberFormat;
import com.google.gwt.media.client.Audio;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Window;

public class AudioPlayer {
	
	// different cell must have different name id
	public static final String PLAY_ELEMENT_ID = "play-";
	public static final String SEEK_ELEMENT_ID = "seek-";
	public static final String TIMER_ELEMENT_ID = "timer-";
	
	private Audio audio;
	
	private Element play;
	
	private Element seek;
	
	private Element timer;
	
	public AudioPlayer(Audio audio) {
		this.audio = audio;
		setUpAudioListener();
	}
	
	public void play(Long id) {
		String srcUrl = buildUrl(id);
		defaulState(srcUrl);
		play = DOM.getElementById(PLAY_ELEMENT_ID + id);
		seek = DOM.getElementById(SEEK_ELEMENT_ID + id);
		timer = DOM.getElementById(TIMER_ELEMENT_ID + id);
		playOrPause(id, srcUrl);
	}
	
	public void play(Long id, double percentMouse) {
		String srcUrl = buildUrl(id);
		if (!audio.isPaused() && audio.getSrc().equals(srcUrl)) {
			updatePosition(percentMouse, audio.getDuration());
		}
	}
	
	public void stop() {
		audio.pause();
		audio.setCurrentTime(0);
	}
	
	private void defaulState(String srcUrl) {
		if (play != null && seek != null && timer != null 
				&& !audio.getSrc().equals(srcUrl)) {
			// default state 'play' element
			Element child = play.getFirstChildElement();
			child.setClassName(IconType.PLAY.get());
			// default state 'timer' and 'seek' element
			updateTime(0);
			updateSeek(0, 0);
		}
	}
	
	private void playOrPause(Long id, String srcUrl) {
		// play on first time
		if (!audio.getSrc().equals(srcUrl)) {
			checkAudioFormatSupportAndPlay(srcUrl);
		} else if (audio.getSrc().equals(srcUrl)) {
			if (audio.isPaused()) {
				audio.play();
			} else {
				audio.pause();
			}
		} else {
			// play on next file
			audio.setSrc(srcUrl);
			audio.play();
		}		
	}
		
	private void setUpAudioListener() {
		// This events is generated when meta data for the specified audio/video has been loaded.
		audio.addBitlessDomHandler(new LoadedMetadataHandler() {
			@Override
			public void onLoadedMetadata(LoadedMetadataEvent event) {
				//GWT.log("duration: " + audio.getDuration());
			}
		}, LoadedMetadataEvent.getType());
		
		// This event is generated when enough data is available that the media can be played.
		audio.addBitlessDomHandler(new CanPlayHandler() {
			@Override
			public void onCanPlay(CanPlayEvent event) {
				
			}
		}, CanPlayEvent.getType());
		
		// This event is generated when playback starts or resumes.
		audio.addBitlessDomHandler(new PlayHandler() {
			@Override
			public void onPlay(PlayEvent event) {
				Element child = play.getFirstChildElement();
				child.setClassName(IconType.PAUSE.get());
			}
		}, PlayEvent.getType());

		// This event is generated when playback is paused.
		audio.addBitlessDomHandler(new PauseHandler() {
			@Override
			public void onPause(PauseEvent event) {
				Element child = play.getFirstChildElement();
				child.setClassName(IconType.PLAY.get());
			}
		}, PauseEvent.getType());

		// This event is generated when playback completes.
		audio.addBitlessDomHandler(new EndedHandler() {
			@Override
			public void onEnded(EndedEvent event) {
				updateTime(0);
				updateSeek(0, 0);
			}
		}, EndedEvent.getType());

		// This event is update played time
		audio.addBitlessDomHandler(new TimeUpdateHandler() {
			@Override
			public void onTimeUpdate(TimeUpdateEvent event) {
				double currentTime = audio.getCurrentTime();
				updateTime(currentTime);
				double duration = audio.getDuration();
				updateSeek(currentTime, duration);
			}
		}, TimeUpdateEvent.getType());
		
		// This event is generated when an error occurs.
		audio.addBitlessDomHandler(new ErrorHandler() {
			@Override
			public void onError(ErrorEvent event) {
				Window.Location.assign("/");
			}
		}, ErrorEvent.getType());
	}
	
	private String buildUrl(Long id) {
		UrlBuilder urlBuilder = new UrlBuilder();
		urlBuilder.setProtocol(Window.Location.getProtocol());
		urlBuilder.setHost(Window.Location.getHost());
		urlBuilder.setPath(Window.Location.getPath() + "audioService");
		urlBuilder.setParameter("id", String.valueOf(id));
		String url = urlBuilder.buildString();
		return url; 
	}
	
	private void checkAudioFormatSupportAndPlay(String srcUrl) {
		audio.setSrc(srcUrl);
		if (MediaElement.CAN_PLAY_PROBABLY.equals(
			audio.canPlayType(AudioElement.TYPE_WAV))) {
			audio.play();
		} else if (MediaElement.CAN_PLAY_MAYBE.equals(
			audio.canPlayType(AudioElement.TYPE_WAV))) {
			audio.play();
		} else  {
			GWT.log("wav audio not supported");
		}
	}
	
	private void updateTime(double currentTime) {
		// minutes format
		double m = Math.floor((currentTime / 60) % 60);
		String mf = m < 10 ? NumberFormat.getFormat("0").format(m) :
			NumberFormat.getFormat("00").format(m);
		// seconds format
		double s = Math.floor(currentTime % 60);
		String sf = s < 10 ? NumberFormat.getFormat("0").format(s) :
			NumberFormat.getFormat("00").format(s);
		timer.setInnerText(mf + ":" + sf);		
	}
	
	private void updateSeek(double currentTime, double duration) {
		double min = 0;
		double max = duration;
		double value = currentTime;
		double percent = (max != min) ? (value - min) / (max - min ) * 100 : 0;
		// <div>
		Element firstChild = seek.getFirstChildElement();
		firstChild.getStyle().setWidth(percent, Unit.PCT);
		// <a>
		Element firstSibling = firstChild.getNextSiblingElement();
		firstSibling.getStyle().setLeft(percent, Unit.PCT);
	}
	
	public void updatePosition(double percentMouse, double duration) {
		double min = 0;
		double max = duration;
		double total = max - min;
		double currentTime = min + percentMouse * total;
		audio.setCurrentTime(currentTime);
	}

}

/*
abort	This event is generated when playback is aborted.
loadeddata	This event is generated when the first frame of the media has finished loading.
loadstart	This event is generated when loading of the media begins.
progress	This event is generated periodically to inform the progress of the downloading the media.
ratechange	This event is generated when the playback speed changes.
seeked	This event is generated when a seek operation completes.
seeking	This event is generated when a seek operation begins.
suspend	This event is generated when loading of the media is suspended.
volumechange	This event is generated when the audio volume changes.
waiting	This event is generated when the requested operation (such as playback) is delayed pending the completion of another operation (such as a seek).
*/

