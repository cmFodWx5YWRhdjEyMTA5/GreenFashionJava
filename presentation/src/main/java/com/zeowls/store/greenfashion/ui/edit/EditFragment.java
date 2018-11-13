package com.zeowls.store.greenfashion.ui.edit;

import android.Manifest;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.method.PasswordTransformationMethod;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.GenericTransitionOptions;
import com.bumptech.glide.request.RequestOptions;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.zeowls.domain.entity.User;
import com.zeowls.domain.entity.UserRequest;
import com.zeowls.domain.scope.ViewScope;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.home.HomeFragment;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import pub.devrel.easypermissions.AfterPermissionGranted;
import pub.devrel.easypermissions.EasyPermissions;

import static android.app.Activity.RESULT_OK;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.HOME;
import static com.zeowls.store.greenfashion.ui.utils.Utils.isValidEmail;
import static com.zeowls.store.greenfashion.ui.utils.Utils.isValidField;
import static com.zeowls.store.greenfashion.ui.utils.Utils.isValidPassword;
import static com.zeowls.store.greenfashion.ui.utils.Utils.setInputTextLayoutColor;

@ViewScope
public class EditFragment extends BaseFragment implements EditContract.View {

    @Inject
    HomeFragment homeFragment;
    private static final int PICK_IMAGE = 100;
    private static final int RC_READ_FILE = 999;

    @BindView(R.id.toolbar_title)
    TextView toolbarTitle;
    @BindView(R.id.firstname)
    EditText firstName;
    @BindView(R.id.lastname)
    EditText lastName;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.current_password)
    EditText currentPassword;
    @BindView(R.id.new_password)
    EditText newPassword;
    @BindView(R.id.confirm_password)
    EditText confirmPassword;
    @BindView(R.id.upload_image)
    ImageView uploadImage;
    @BindView(R.id.profile_image)
    ImageView profileImage;
    @BindView(R.id.preview_white_layer)
    ImageView whiteLayer;
    @BindView(R.id.progress_text)
    TextView progressText;
    @BindView(R.id.save)
    Button save;
    private EditContract.Presenter presenter;
    private CompositeDisposable disposable;
    private String password;
    private User user;
    private Uri temporaryImage;
    private String[] perms;

    @Inject
    public EditFragment() {
    }

    public static EditFragment newInstance() {
        EditFragment fragment = new EditFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_edit;
    }

    @Override
    public void initializeDependencies() {
        DaggerViewComponent.builder()
                .presenterModule(new PresenterModule())
                .applicationComponent(App.appInstance().appComponent())
                .build().inject(this);
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposable = new CompositeDisposable();
        user = new User();
        user = LoggedData.getUserObject(getContext());
        perms = new String[]{Manifest.permission.READ_EXTERNAL_STORAGE, Manifest.permission.WRITE_EXTERNAL_STORAGE};
    }

    @Override
    public void init() {
        toolbarTitle.setText(R.string.edit_profile);
        profileImage.getBackground().setLevel(5000);
        GlideApp.with(Objects.requireNonNull(getContext())).load(user.getProfilePic()).skipMemoryCache(true).transition(GenericTransitionOptions.with(R.anim.fade_in)).placeholder(R.drawable.ic_profile_pic).apply(RequestOptions.circleCropTransform()).into(profileImage);
        currentPassword.setTransformationMethod(new PasswordTransformationMethod());
        newPassword.setTransformationMethod(new PasswordTransformationMethod());
        confirmPassword.setTransformationMethod(new PasswordTransformationMethod());
        ObserveFields();
        methodRequiresReadPermission();
    }

    private void ObserveFields() {
        if (user.getUserLogin() == User.UserLogin.EDITABLE) {
            editableFields();
            unEditableFields();
            configurationSetAll();
        } else {
            editableFields();
            configurationSetEditable();
            email.setVisibility(View.GONE);
            currentPassword.setVisibility(View.GONE);
            newPassword.setVisibility(View.GONE);
            confirmPassword.setVisibility(View.GONE);
        }
    }

    private void configurationSetEditable() {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
    }

    private void configurationSetAll() {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        email.setText(user.getEmail());
    }

    private void editableFields() {
        disposable.add(RxTextView.textChanges(firstName)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (!string.isEmpty()) {
                        firstName.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(firstName, colorState);
                    } else {
                        firstName.setError(getString(R.string.required));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(firstName, colorState);
                    }
                }));

        disposable.add(RxTextView.textChanges(lastName)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (!string.isEmpty()) {
                        lastName.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(lastName, colorState);
                    } else {
                        lastName.setError(getString(R.string.required));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(lastName, colorState);
                    }
                }));
    }

    private void unEditableFields() {
        disposable.add(RxTextView.textChanges(email)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (isValidEmail(email)) {
                        email.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(email, colorState);
                    } else {
                        email.setError(getString(R.string.email_validation));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(email, colorState);
                    }
                }));

        disposable.add(RxTextView.textChanges(currentPassword)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (!string.isEmpty()) {
                        currentPassword.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(currentPassword, colorState);
                    } else {
                        currentPassword.setError(getString(R.string.required));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(currentPassword, colorState);
                    }
                }));

        disposable.add(RxTextView.textChanges(newPassword)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (isValidPassword(string)) {
                        password = string;
                        newPassword.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(newPassword, colorState);
                    } else {
                        password = null;
                        newPassword.setError(getString(R.string.password_limit));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(newPassword, colorState);
                    }
                }));

        disposable.add(RxTextView.textChanges(confirmPassword)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (!string.isEmpty()) {
                        if (string.equals(password)) {
                            confirmPassword.setError(null);
                            ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                            setInputTextLayoutColor(confirmPassword, colorState);
                        } else {
                            confirmPassword.setError(getString(R.string.password_match));
                            ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                            setInputTextLayoutColor(confirmPassword, colorState);
                        }
                    } else {
                        confirmPassword.setError(getString(R.string.required));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(confirmPassword, colorState);
                    }
                }));
    }

    @Inject
    @Override
    public void attachPresenter(@NonNull EditContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @AfterPermissionGranted(RC_READ_FILE)
    private void methodRequiresReadPermission() {
        if (!EasyPermissions.hasPermissions(getActivity(), perms)) {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_read_storage),
                    RC_READ_FILE, perms);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        EasyPermissions.onRequestPermissionsResult(requestCode, permissions, grantResults, this);
    }

    private boolean hasStoragePermission() {
        return EasyPermissions.hasPermissions(getActivity(), perms);
    }

    @OnClick(R.id.upload_image)
    protected void uploadImage() {
        if (hasStoragePermission()) {
            Intent intent = new Intent(Intent.ACTION_PICK);
            intent.setType("image/*");
            intent.setAction(Intent.ACTION_GET_CONTENT);
            startActivityForResult(Intent.createChooser(intent, "Select Image"), PICK_IMAGE);
        } else {
            EasyPermissions.requestPermissions(this, getString(R.string.rationale_read_storage), RC_READ_FILE, perms);
        }
    }

    @OnClick(R.id.save)
    protected void onSave() {
        if (user.getUserLogin() == User.UserLogin.NON_EDITABLE) {
            if (isValidField(firstName, getActivity()) && isValidField(lastName, getActivity())) {
                UserRequest body = new UserRequest();
                body.setFirstName(firstName.getText().toString());
                body.setLastName(lastName.getText().toString());
                body.setUserId(String.valueOf(user.getId()));
                presenter.onEditUser(body);
            }
        } else {
            if (isValidField(firstName, getActivity()) && isValidField(lastName, getActivity()) && isValidEmail(email) && isValidPassword(newPassword.getText()) && password.equals(newPassword.getText().toString())) {
                UserRequest body = new UserRequest();
                body.setFirstName(firstName.getText().toString());
                body.setLastName(lastName.getText().toString());
                body.setUserId(String.valueOf(user.getId()));
                body.setPassword(newPassword.getText().toString());
                body.setOldPassword(currentPassword.getText().toString());
                body.setEmail(email.getText().toString());
                presenter.onEditUser(body);
            }
        }
    }

    public void saveUserAuth(User newUser) {
        if (user.getUserLogin() == User.UserLogin.NON_EDITABLE) {
            newUser.setUserLogin(User.UserLogin.NON_EDITABLE);
        } else {
            newUser.setUserLogin(User.UserLogin.EDITABLE);
        }
        LoggedData.saveUserObject(getContext(), newUser, true);
        if (!getActivity().getSupportFragmentManager().popBackStackImmediate(HOME.name(), 0)) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment, HOME.name()).addToBackStack(HOME.name()).commit();
        }
    }

    @Override
    public void incorrectCurrentPassword() {
        Toast.makeText(getActivity(), R.string.incorrect_old_password, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(Objects.requireNonNull(getContext()).getContentResolver(), data.getData());
                if (bitmap != null) {
                    presenter.onImageSelected(getImageUri(bitmap), user.getId());
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public Uri getImageUri(Bitmap bitmap) {
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, new ByteArrayOutputStream());
        return Uri.parse(MediaStore.Images.Media.insertImage(getActivity().getContentResolver(), bitmap, "Profile_Picture", null));
    }

    @Override
    public void showThumbnail(Uri uri) {
        this.temporaryImage = uri;
        whiteLayer.setVisibility(View.VISIBLE);
        progressText.setVisibility(View.VISIBLE);
        GlideApp.with(Objects.requireNonNull(getActivity())).load(R.drawable.white_layer).transition(GenericTransitionOptions.with(R.anim.fade_in)).apply(RequestOptions.circleCropTransform()).into(whiteLayer);
        GlideApp.with(Objects.requireNonNull(getActivity())).load(uri).skipMemoryCache(true).transition(GenericTransitionOptions.with(R.anim.fade_in)).placeholder(R.drawable.ic_profile_pic).apply(RequestOptions.circleCropTransform()).into(profileImage);
    }

    public void showErrorMessage(String str) {
        Toast.makeText(getActivity(), str, Toast.LENGTH_SHORT).show();
    }

    public void uploadCompleted() {
        whiteLayer.setVisibility(View.GONE);
        progressText.setVisibility(View.GONE);
        Toast.makeText(Objects.requireNonNull(getActivity()).getApplicationContext(), "completed", Toast.LENGTH_SHORT).show();
        user.setProfilePic(temporaryImage.toString());
        LoggedData.saveUserObject(getActivity(), user, true);
    }

    public void setUploadProgress(int progress) {
        TextView textView = this.progressText;
        textView.setText(String.format(getString(R.string.progress), progress));
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        safelyDispose(disposable);
        presenter.dispose();
    }

    private void safelyDispose(Disposable... disposableArr) {
        for (Disposable disposable : disposableArr) {
            if (!(disposable == null || disposable.isDisposed())) {
                disposable.dispose();
            }
        }
    }
}
