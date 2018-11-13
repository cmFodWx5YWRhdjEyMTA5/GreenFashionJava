package com.zeowls.store.greenfashion.ui.search;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.MainCats;
import com.zeowls.domain.entity.Search;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.domain.interactor.useCases.SearchCase;
import com.zeowls.domain.scope.ViewScope;

import java.util.List;

import javax.inject.Inject;


@ViewScope
public class SearchPresenter implements SearchContract.Presenter {

    private SingleInteractor<MainCats> interactor;
    private SearchCase<List<Search>> searchCase;
    private SearchContract.View view;

    @Inject
    public SearchPresenter(SingleInteractor<MainCats> useCase, SearchCase<List<Search>> searchCase) {
        this.interactor = useCase;
        this.searchCase = searchCase;
    }

    @Override
    public void attachView(@NonNull SearchContract.View view) {
        this.view = view;
    }

    @Override
    public void dispose() {
        interactor.dispose();
        searchCase.dispose();
    }

    @Override
    public void getData() {
        interactor.execute(this::catchCats, this::catchError, 1);
    }

    @Override
    public void getMore(int page) {
        interactor.execute(this::catchMore, this::catchError, page);
    }

    @Override
    public void getRecent() {
        searchCase.recent(this::catchRecent, this::catchError);
    }

    @Override
    public void getSuggestion() {
        searchCase.suggestions(this::catchSuggestion, this::catchError);
    }

    @Override
    public void deleteRecent(int id) {
        searchCase.deleteRecent(id).subscribe();
    }

    @Override
    public void deleteAllRecent() {
        searchCase.deleteAllRecent().subscribe();
    }

    @Override
    public void addRecent(String name) {
        searchCase.insert(name).subscribe();
    }

    private void catchCats(MainCats data) {
        if (!data.isError()) {
            view.setCats(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }

    private void catchRecent(List<Search> data) {
        if (!data.isEmpty()) {
            view.setRecent(data);
        } else {
            view.showEmptyMessage();
        }
    }

    private void catchSuggestion(List<Search> data) {
        if (!data.isEmpty()) {
            view.setSuggestion(data);
        } else {
            view.showErrorMessage("ERROR");
        }
    }

    private void catchMore(MainCats data) {
        if (!data.isError()) {
            view.appendMore(data);
        } else {
            view.showErrorMessage(data.getMessage().toString());
        }
    }


    private void catchError(Throwable ex) {
        ex.printStackTrace();
        view.showErrorMessage(ex.getMessage());
    }
}
