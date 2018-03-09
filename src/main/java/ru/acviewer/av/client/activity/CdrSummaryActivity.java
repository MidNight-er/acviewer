package ru.acviewer.av.client.activity;

import com.google.gwt.activity.shared.AbstractActivity;
import com.google.gwt.core.shared.GWT;
import com.google.gwt.editor.client.SimpleBeanEditorDriver;
import com.google.gwt.event.shared.EventBus;
import com.google.gwt.place.shared.Place;
import com.google.gwt.place.shared.PlaceController;
import com.google.gwt.user.client.ui.AcceptsOneWidget;
import com.google.inject.Inject;
import ru.acviewer.av.client.bean.SearchSummaryRequest;
import ru.acviewer.av.client.provider.CdrSummaryDataProvider;
import ru.acviewer.av.client.service.CdrRequestFactory;
import ru.acviewer.av.client.service.UserAccountRequestFactory;
import ru.acviewer.av.client.view.CdrSummaryView;
import ru.acviewer.av.client.widget.SearchSummaryEditor;

import java.util.Date;

public class CdrSummaryActivity extends AbstractActivity implements
        CdrSummaryView.Presenter {

    private CdrSummaryView cdrSummaryView;
    private PlaceController placeController;
    private CdrRequestFactory cdrRequestFactory;
    private UserAccountRequestFactory userAccountRequestFactory;

    @Inject
    public CdrSummaryActivity(CdrSummaryView cdrSummaryView, PlaceController placeController) {
        this.cdrSummaryView = cdrSummaryView;
        this.placeController = placeController;
    }

    public CdrSummaryActivity withPlace(Place place) {
        return this;
    }

    @Override
    public void start(AcceptsOneWidget panel, EventBus eventBus) {
        cdrSummaryView.setPresenter(this);
        panel.setWidget(cdrSummaryView.asWidget());
    }

    public interface Driver extends SimpleBeanEditorDriver<SearchSummaryRequest, SearchSummaryEditor> {

    }

    private static final Driver driver = GWT.create(Driver.class);

    @Inject
    private void setUpReqestFactory(CdrRequestFactory cdrRequestFactory,
                                    UserAccountRequestFactory userAccountRequestFactory) {
        this.cdrRequestFactory = cdrRequestFactory;
        this.userAccountRequestFactory = userAccountRequestFactory;
    }

    @Override
    public CdrSummaryDataProvider getCdrSummaryDataProvider() {
        SearchSummaryRequest searchSummaryRequest = new SearchSummaryRequest();
        searchSummaryRequest.setDateTimeStart(new Date(new Date().getTime() - 1000L * 60 * 60 * 12));
        searchSummaryRequest.setDateTimeEnd(new Date());
        driver.initialize(cdrSummaryView.getSearchSummaryEditor());
        driver.edit(searchSummaryRequest);
        CdrSummaryDataProvider cdrSummaryProvider = new CdrSummaryDataProvider(
                cdrRequestFactory, userAccountRequestFactory, driver, searchSummaryRequest);
        return cdrSummaryProvider;
    }
}
