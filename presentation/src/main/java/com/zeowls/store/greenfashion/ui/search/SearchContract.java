package com.zeowls.store.greenfashion.ui.search;

import android.support.annotation.NonNull;

import com.zeowls.domain.entity.MainCats;
import com.zeowls.domain.entity.Search;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;

import java.util.List;


public interface SearchContract {

    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void setCats(MainCats data);

        void appendMore(MainCats data);

        void setRecent(List<Search> list);

        void setSuggestion(List<Search> list);

        void showEmptyMessage();

        void showErrorMessage(String s);
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void getData();

        void getMore(int page);

        void getRecent();

        void getSuggestion();

        void deleteRecent(int id);

        void deleteAllRecent();

        void addRecent(String name);

        void dispose();


    }
}
