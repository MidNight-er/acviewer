package ru.acviewer.av.client.view;

import ru.acviewer.av.client.provider.CdrSummaryDataProvider;
import ru.acviewer.av.client.widget.SearchSummaryEditor;
import ru.acviewer.av.shared.CdrSummaryProxy;

import com.github.gwtbootstrap.client.ui.CellTable;
import com.google.gwt.user.client.ui.IsWidget;

public interface CdrSummaryView extends IsWidget {

	public interface Presenter {

		CdrSummaryDataProvider getCdrSummaryDataProvider();

	}
	
	void setPresenter(Presenter presenter);

	SearchSummaryEditor getSearchSummaryEditor();

}
