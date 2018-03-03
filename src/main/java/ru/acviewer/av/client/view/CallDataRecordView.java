package ru.acviewer.av.client.view;

import ru.acviewer.av.client.provider.CallDataProvider;
import ru.acviewer.av.client.widget.SearchEditor;
import ru.acviewer.av.shared.CallDataRecordProxy;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.user.client.ui.IsWidget;
import com.google.gwt.view.client.HasData;

public interface CallDataRecordView extends IsWidget {

	void setPresenter(Presenter presenter);
	
	public interface Presenter {
		void onLengthDate();
	}

	SearchEditor getSearchEditor();

	CellTable<CallDataRecordProxy> getCallDataRecords();
}
