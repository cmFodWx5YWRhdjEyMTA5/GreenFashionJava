package com.zeowls.store.greenfashion.ui.edit;

import android.net.Uri;
import android.support.annotation.NonNull;
import android.text.TextUtils;

import com.zeowls.domain.entity.Response;
import com.zeowls.domain.entity.UserRequest;
import com.zeowls.domain.entity.UserResponse;
import com.zeowls.domain.interactor.MultiInteractor;
import com.zeowls.domain.interactor.useCases.UploadCase;
import com.zeowls.domain.scope.ViewScope;
import com.zeowls.store.greenfashion.ui.utils.FileResolver;

import javax.inject.Inject;

import io.reactivex.disposables.CompositeDisposable;


@ViewScope
public class EditPresenter implements EditContract.Presenter {
    private EditContract.View view;
    private UploadCase<Response, String, Integer> upload;
    private FileResolver fileResolver;
    private MultiInteractor<UserResponse, UserRequest> editInteractor;
    private CompositeDisposable disposables;

    @Inject
    public EditPresenter(UploadCase<Response, String, Integer> upload, FileResolver fileResolver, MultiInteractor<UserResponse, UserRequest> editInteractor) {
        this.upload = upload;
        this.fileResolver = fileResolver;
        this.editInteractor = editInteractor;
        this.disposables = new CompositeDisposable();
    }

    @Override
    public void attachView(@NonNull EditContract.View view) {
        this.view = view;
    }

    @Override
    public void onImageSelected(Uri selectedImage, int id) {
        String filePath = fileResolver.getFilePath(selectedImage);
        if (TextUtils.isEmpty(filePath)) {
            view.showErrorMessage("incorrect file uri");
            return;
        }
        view.showThumbnail(selectedImage);
        disposables.add(upload.uploadImage(filePath, id).subscribe(progress -> view.setUploadProgress((int) (100 * progress)),
                error -> view.showErrorMessage(error.getMessage()),
                view::uploadCompleted));
    }

    @Override
    public void onEditUser(UserRequest user) {
        editInteractor.execute(this::catchUser, this::catchError, user);
    }

    @Override
    public void dispose() {
        if (!disposables.isDisposed()) {
            disposables.clear();
        }
    }


    private void catchUser(UserResponse data) {
        if (!data.getError()) {
            view.saveUserAuth(data.getUser());
        } else {
            view.incorrectCurrentPassword();
        }
    }


    private void catchError(Throwable ex) {
        ex.printStackTrace();
    }


}
