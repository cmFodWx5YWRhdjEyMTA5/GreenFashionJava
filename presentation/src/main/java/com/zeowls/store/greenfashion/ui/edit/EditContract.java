package com.zeowls.store.greenfashion.ui.edit;

import android.net.Uri;
import android.support.annotation.NonNull;

import com.zeowls.domain.entity.User;
import com.zeowls.domain.entity.UserRequest;
import com.zeowls.store.greenfashion.ui.base.BasePresenter;
import com.zeowls.store.greenfashion.ui.base.BaseView;


public interface EditContract {
    interface View extends BaseView<Presenter> {
        void attachPresenter(@NonNull Presenter presenter);

        void showErrorMessage(String msg);

        void uploadCompleted();

        void setUploadProgress(int progress);

        void saveUserAuth(User data);

        void showThumbnail(Uri uri);

        void incorrectCurrentPassword();
    }

    interface Presenter extends BasePresenter<View> {
        void attachView(@NonNull View view);

        void onImageSelected(Uri imageUri, int id);

        void onEditUser(UserRequest user);

        void dispose();
    }
}
