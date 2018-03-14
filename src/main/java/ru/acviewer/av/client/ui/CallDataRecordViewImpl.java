package ru.acviewer.av.client.ui;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import ru.acviewer.av.client.bean.AudioPlayer;
import ru.acviewer.av.client.bean.PlayerControl;
import ru.acviewer.av.client.cell.AudioPlayButtonCell;
import ru.acviewer.av.client.cell.AudioSeekCell;
import ru.acviewer.av.client.cell.AudioTimerCell;
import ru.acviewer.av.client.cell.AudioDownloadCell;
import ru.acviewer.av.client.cell.HeaderCell;
import ru.acviewer.av.client.i18n.messages.AppMessages;
import ru.acviewer.av.client.view.CallDataRecordView;
import ru.acviewer.av.client.widget.SearchEditor;
import ru.acviewer.av.shared.CallDataRecordProxy;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.github.gwtbootstrap.client.ui.Label;
import com.github.gwtbootstrap.client.ui.constants.IconType;
import com.github.gwtbootstrap.datetimepicker.client.ui.DateTimeBox;
import com.github.gwtbootstrap.datetimepicker.client.ui.base.PickerPosition;
import com.google.gwt.cell.client.Cell;
import com.google.gwt.cell.client.CompositeCell;
import com.google.gwt.cell.client.DateCell;
import com.google.gwt.cell.client.FieldUpdater;
import com.google.gwt.cell.client.HasCell;
import com.google.gwt.cell.client.TextCell;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.dom.client.MediaElement;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.KeyCodes;
import com.google.gwt.event.dom.client.KeyDownEvent;
import com.google.gwt.i18n.client.DateTimeFormat;
import com.google.gwt.i18n.client.DateTimeFormat.PredefinedFormat;
import com.google.gwt.i18n.client.LocaleInfo;
import com.google.gwt.media.client.Audio;
import com.google.gwt.resources.client.CssResource;
import com.google.gwt.uibinder.client.UiBinder;
import com.google.gwt.uibinder.client.UiField;
import com.google.gwt.uibinder.client.UiHandler;
import com.google.gwt.user.cellview.client.Column;
import com.google.gwt.user.cellview.client.Header;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasValue;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.Widget;
import com.google.gwt.view.client.NoSelectionModel;
import com.google.gwt.view.client.Range;
import com.google.gwt.view.client.RangeChangeEvent;

public class CallDataRecordViewImpl extends Composite implements CallDataRecordView {

	interface AppBinder extends UiBinder<Widget, CallDataRecordViewImpl> {
	}

	private static AppBinder appBinder = GWT.create(AppBinder.class);
	
	private NoSelectionModel<CallDataRecordProxy> selectionModel;
	
	interface AppResource extends CssResource {
		String emptyTableWidget();
	}
	
	// private final String SERVICE_LEVEL = "Обслуживание";
	private Presenter presenter;
	
	private AppMessages msg = GWT.create(AppMessages.class);

	private AudioPlayer audioPlayer;
	
	public CallDataRecordViewImpl() {
		initWidget(appBinder.createAndBindUi(this));
		setUpAudioPlayer();
		buildTable();
		setUpListener();
	}
	
	@Override
	public void setPresenter(Presenter presenter) {
		this.presenter = presenter;
		presenter.onLengthDate();
	}
	
	@UiField
	AppResource style;
	
	@UiField
	SearchEditor searchEditor;
	
	@Override
	public SearchEditor getSearchEditor() {
		return searchEditor;
	}

/*	@UiHandler("searchField")
	void clickEvent(KeyDownEvent event) {
		if (event.getNativeKeyCode() == KeyCodes.KEY_ENTER) {
			GWT.log("key down");
			searchCalls(null);
		}
	}
*/
	// search
	@UiHandler(value = "searchButton")
	void searchCalls(ClickEvent event) {
		final Range range = new Range(0, callDataRecords.getPageSize());
		callDataRecords.setVisibleRangeAndClearData(range, true);
	}

	@UiField
	CellTable<CallDataRecordProxy> callDataRecords;
	
	@Override
	public CellTable<CallDataRecordProxy> getCallDataRecords() {
		return callDataRecords;
	}

	// create and attache 'Audio' element to DOM
	private void setUpAudioPlayer() {
		Audio audio = Audio.createIfSupported();
		if (audio != null) {
			audio.setControls(false);
			audio.setPreload(MediaElement.PRELOAD_NONE);
			RootPanel.get().add(audio);
			audioPlayer = new AudioPlayer(audio);
		} else {
			GWT.log("Sorry, HTML5 items are not supportet in this browser");
		}
	}
	
	/*
	 * table for render 'CallDataRecord' object
	 */
	private void buildTable() {
		selectionModel = new NoSelectionModel<CallDataRecordProxy>();
		callDataRecords.setSelectionModel(selectionModel);
		Cell<Date> dateCell = new DateCell(DateTimeFormat.getFormat(PredefinedFormat.DATE_MEDIUM));
		Cell<Date> timeCell = new DateCell(DateTimeFormat.getFormat(PredefinedFormat.TIME_SHORT));
		Cell<String> textCell = new TextCell();

		// date columns
		Column<CallDataRecordProxy, Date> date = new Column<CallDataRecordProxy, Date>(dateCell) {
			@Override
			public Date getValue(CallDataRecordProxy object) {
				return object.getStart();
			}
		};
		callDataRecords.setColumnWidth(date, "10%");
		callDataRecords.addColumn(date, msg.callDate());
		
		// clid column
		Column<CallDataRecordProxy, String> clid = new Column<CallDataRecordProxy, String>(textCell) {
			@Override
			public String getValue(CallDataRecordProxy object) {
				return object.getClid().getName();
			}
		};
		callDataRecords.setColumnWidth(clid, "20%");
		callDataRecords.addColumn(clid, msg.callClid());

		// src column
		Column<CallDataRecordProxy, String> src = new Column<CallDataRecordProxy, String>(textCell) {
			@Override
			public String getValue(CallDataRecordProxy object) {
				return object.getSrc();
			}
		};
		callDataRecords.setColumnWidth(src, "11%");
		callDataRecords.addColumn(src, msg.callSrc());

		// dst column
		Column<CallDataRecordProxy, String> dst = new Column<CallDataRecordProxy, String>(textCell) {
			@Override
			public String getValue(CallDataRecordProxy object) {
				return object.getDst();
			}
		};
		callDataRecords.setColumnWidth(dst, "11%");
		callDataRecords.addColumn(dst, msg.callDst());

		// disposition column
		Column<CallDataRecordProxy, String> disposition = new Column<CallDataRecordProxy, String>(
				textCell) {
			@Override
			public String getValue(CallDataRecordProxy object) {
				String disp = null;
				switch (object.getDisposition()) {
				case "ANSWERED":
					disp = msg.dispAnswered();
					break;
				case "BUSY":
					disp = msg.dispBusy();
					break;
				case "FAILED":
					disp = msg.dispFailed();
					break;
				case "NO ANSWER":
					disp = msg.dispNoAnswer();
					break;
				case "CONGESTION":
					disp = msg.dispCongestion();
					break;
				}
				return disp;
			}
		};
		callDataRecords.setColumnWidth(disposition, "12%");
		callDataRecords.addColumn(disposition, msg.callDisposition());

		// date column
		Column<CallDataRecordProxy, Date> start = new Column<CallDataRecordProxy, Date>(timeCell) {
			@Override
			public Date getValue(CallDataRecordProxy object) {
				return object.getStart();
			}
		};
		callDataRecords.addColumn(start, msg.callStart());

		// answer column
		Column<CallDataRecordProxy, Date> answer = new Column<CallDataRecordProxy, Date>(timeCell) {
			@Override
			public Date getValue(CallDataRecordProxy object) {
				return object.getAnswer();
			}
		};
		callDataRecords.addColumn(answer, msg.callAnswer());

		// end column
		Column<CallDataRecordProxy, Date> end = new Column<CallDataRecordProxy, Date>(timeCell) {
			@Override
			public Date getValue(CallDataRecordProxy object) {
				return object.getEnd();
			}
		};
		callDataRecords.addColumn(end, msg.callEnd());

		// play button column
		AudioPlayButtonCell playButtonCell = new AudioPlayButtonCell();
		Column<CallDataRecordProxy, Long> playButton = new Column<CallDataRecordProxy, Long>(playButtonCell) {
			@Override
			public Long getValue(CallDataRecordProxy object) {
				Long id = null;
				if (object.getRecord()) {
					id = object.getId();
				} return id;
			}
		};
		playButton.setFieldUpdater(new FieldUpdater<CallDataRecordProxy, Long>() {
			@Override
			public void update(int index, CallDataRecordProxy object, Long value) {
				if (value != null) {
					audioPlayer.play(value);
				}
			}
		});
		Header<String> playHeader = columnHeader(IconType.HEADPHONES);
		callDataRecords.addColumn(playButton, playHeader);		
		
		// audio composite cell column
		CompositeCell<PlayerControl> compositeCell = buildAudioCompositeCell();
		Column<CallDataRecordProxy, PlayerControl> composit = new Column<CallDataRecordProxy, PlayerControl>(compositeCell) {
			@Override
			public PlayerControl getValue(CallDataRecordProxy object) {
				PlayerControl playerControl = null;
				if (object.getRecord()) {
					playerControl = new PlayerControl(object.getId());
				} return playerControl;
			}
		};
		callDataRecords.setColumnWidth(composit, "12%");
		Header<String> compositPlayerHeader = columnHeader(IconType.FORWARD);
		callDataRecords.addColumn(composit, compositPlayerHeader);
		
		// miscellaneous
		callDataRecords.setLoadingIndicator(null);
		Label label = new Label(msg.checkSearchParameters());
		label.addStyleName(style.emptyTableWidget());
		callDataRecords.setEmptyTableWidget(label);
	}
	
	/*
	 * table header
	 */
	private Header<String> columnHeader(final IconType iconType) {
		Cell<String> headerCell = new HeaderCell();
		Header<String> header = new Header<String>(headerCell) {
			@Override
			public String getValue() {
				return iconType.get();
			}
		};
		//header.setHeaderStyleNames(Style.TextAlign.CENTER.getCssName());
		return header;
	}
	
	/*
	 *  composite cell for 'AudioSeekCell', 'AudioTimerCell', 'AudioDownloadCell'
	 */
	private CompositeCell<PlayerControl> buildAudioCompositeCell() {
		List<HasCell<PlayerControl, ?>> cells = new LinkedList<HasCell<PlayerControl, ?>>();
		
		// seek cell
		AudioSeekCell audioSeekCell = new AudioSeekCell();
		cells.add(new SeekHasCell(audioSeekCell));
		// timer cell
		AudioTimerCell audioTimerCell = new AudioTimerCell();
		cells.add(new TimerHasCell(audioTimerCell));
		// download cell
		AudioDownloadCell downloadCell = new AudioDownloadCell();
		cells.add(new DownloadHasCell(downloadCell));
		
		CompositeCell<PlayerControl> cell = new CompositeCell<PlayerControl>(cells);
		return cell;
	}
	
	private class SeekHasCell implements HasCell<PlayerControl, PlayerControl> {
		
		private AudioSeekCell audioSeekCell;
		
		public SeekHasCell(AudioSeekCell audioSeekCell) {
			this.audioSeekCell = audioSeekCell;
		}

		@Override
		public Cell<PlayerControl> getCell() {
			return audioSeekCell;
		}

		@Override
		public FieldUpdater<PlayerControl, PlayerControl> getFieldUpdater() {
			FieldUpdater<PlayerControl, PlayerControl> seekUpdater = new FieldUpdater<PlayerControl, PlayerControl>() {
				@Override
				public void update(int index, PlayerControl object, PlayerControl value) {
					Long id = value.getId();
					double percentMouse = object.getPercentMouse();
					audioPlayer.play(id, percentMouse);
				}
			};
			return seekUpdater;
		}

		@Override
		public PlayerControl getValue(PlayerControl object) {
			return object;
		}
	}
	
	private class TimerHasCell implements HasCell<PlayerControl, PlayerControl> {

		private AudioTimerCell audioTimerCell;
		
		public TimerHasCell(AudioTimerCell audioTimerCell) {
			this.audioTimerCell = audioTimerCell;
		}

		@Override
		public Cell<PlayerControl> getCell() {
			return audioTimerCell;
		}

		@Override
		public FieldUpdater<PlayerControl, PlayerControl> getFieldUpdater() {
			return null;
		}

		@Override
		public PlayerControl getValue(PlayerControl object) {
			return object;
		}
	}
	
	private class DownloadHasCell implements HasCell<PlayerControl, PlayerControl> {
		
		private AudioDownloadCell downloadCell;
		
		public DownloadHasCell(AudioDownloadCell downloadCell) {
			this.downloadCell = downloadCell;
		}

		@Override
		public Cell<PlayerControl> getCell() {
			return downloadCell;
		}

		@Override
		public FieldUpdater<PlayerControl, PlayerControl> getFieldUpdater() {
			return null;
		}

		@Override
		public PlayerControl getValue(PlayerControl object) {
			return object;
		}
	}

	/*
	 * listener
	 */
	private void setUpListener() {
		// stop audio on change page
		RangeChangeEvent.Handler rangeChangeHandler = new RangeChangeEvent.Handler() {
			@Override
			public void onRangeChange(RangeChangeEvent event) {
				audioPlayer.stop();
			}
		};
		callDataRecords.addRangeChangeHandler(rangeChangeHandler);
	}
}
