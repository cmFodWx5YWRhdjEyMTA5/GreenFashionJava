package com.zeowls.store.greenfashion.ui.signup;

import android.content.res.ColorStateList;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.ConnectionResult;
import com.jakewharton.rxbinding2.widget.RxTextView;
import com.zeowls.domain.entity.DeviceToken;
import com.zeowls.domain.entity.Signup;
import com.zeowls.domain.entity.User;
import com.zeowls.domain.scope.ViewScope;
import com.zeowls.store.greenfashion.App;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.di.component.DaggerViewComponent;
import com.zeowls.store.greenfashion.di.module.PresenterModule;
import com.zeowls.store.greenfashion.ui.base.BaseFragment;
import com.zeowls.store.greenfashion.ui.home.HomeFragment;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;

import java.util.concurrent.TimeUnit;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.OnClick;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.HOME;
import static com.zeowls.store.greenfashion.ui.utils.Utils.isValidEmail;
import static com.zeowls.store.greenfashion.ui.utils.Utils.isValidField;
import static com.zeowls.store.greenfashion.ui.utils.Utils.isValidPassword;
import static com.zeowls.store.greenfashion.ui.utils.Utils.setInputTextLayoutColor;

@ViewScope
public class SignupFragment extends BaseFragment implements SignUpContract.View {
    @Inject
    HomeFragment homeFragment;
    @BindView(R.id.name)
    EditText firstname;
    @BindView(R.id.email)
    EditText email;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.mobile)
    EditText mobile;
    @BindView(R.id.male)
    TextView male;
    @BindView(R.id.female)
    TextView female;

    private SignUpContract.Presenter presenter;
    private CompositeDisposable disposable;
    private DeviceToken token;
    private String gender;


    @Inject
    public SignupFragment() {
    }

    public static SignupFragment newInstance() {
        SignupFragment fragment = new SignupFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposable = new CompositeDisposable();
        token = new DeviceToken();
        token.setDeviceToken(LoggedData.getToken(getActivity()));
    }

    @Override
    protected int layoutId() {
        return R.layout.fragment_signup;
    }

    @Override
    public void initializeDependencies() {
        DaggerViewComponent.builder()
                .presenterModule(new PresenterModule())
                .applicationComponent(App.appInstance().appComponent())
                .build().inject(this);
    }

    private void onConnectionFailed(ConnectionResult connectionResult) {

    }

    private void observeFields() {
        disposable.add(RxTextView.textChanges(firstname)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (!string.isEmpty()) {
                        firstname.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(firstname, colorState);
                    } else {
                        firstname.setError(getString(R.string.required));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(firstname, colorState);
                    }
                }));


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

        disposable.add(RxTextView.textChanges(password)
                .skipInitialValue()
                .debounce(500, TimeUnit.MILLISECONDS, AndroidSchedulers.mainThread())
                .map(CharSequence::toString)
                .subscribe(string -> {
                    if (isValidPassword(string)) {
                        password.setError(null);
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorAccent));
                        setInputTextLayoutColor(password, colorState);
                    } else {
                        password.setError(getString(R.string.password_limit));
                        ColorStateList colorState = ColorStateList.valueOf(getResources().getColor(R.color.colorPrimary));
                        setInputTextLayoutColor(password, colorState);
                    }
                }));
    }


    @Override
    public void init() {
        observeFields();
    }

    @OnClick(R.id.sign_up)
    public void register() {
        if (isValidField(firstname, getActivity()) && isValidField(mobile, getActivity()) && isValidEmail(email) && isValidPassword(password.getText())) {
            Signup signup = new Signup();
            signup.setFirstName(firstname.getText().toString().trim());
            signup.setEmail(email.getText().toString().trim());
            signup.setPassword(password.getText().toString());
            signup.setGender(gender);
            presenter.signup(signup);
        }
    }


    @Inject
    @Override
    public void attachPresenter(@NonNull SignUpContract.Presenter presenter) {
        this.presenter = presenter;
        this.presenter.attachView(this);
    }

    @Override
    public void saveSignup(User user) {
        user.setUserLogin(User.UserLogin.EDITABLE);
        LoggedData.saveUserObject(getActivity(), user, true);
        token.setUserId(user.getId());
        presenter.saveToken(token);
        redirect();
    }

    private void redirect() {
        if (!getActivity().getSupportFragmentManager().popBackStackImmediate(HOME.name(), 0)) {
            getActivity().getSupportFragmentManager().beginTransaction().replace(R.id.container, homeFragment, HOME.name()).addToBackStack(HOME.name()).commit();
        }
    }

    @Override
    public void duplicatedEmail() {
        Toast.makeText(getActivity(), R.string.duplicated_email, Toast.LENGTH_SHORT).show();
    }


    @Override
    public void showErrorMessage() {

    }

    private void genderPicker(int value) {
        switch (value) {
            case 0: {
                male.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_male, 0, 0);
                female.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_offfemenine, 0, 0);
                gender = "m";
            }
            case 1: {
                male.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_offmale, 0, 0);
                female.setCompoundDrawablesWithIntrinsicBounds(0, R.drawable.ic_femenine, 0, 0);
                gender = "f";
            }
        }
    }
}
