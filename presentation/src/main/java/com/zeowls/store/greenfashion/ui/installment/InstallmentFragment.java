package com.zeowls.store.greenfashion.ui.installment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeowls.store.greenfashion.R;

public class InstallmentFragment extends Fragment {


    public InstallmentFragment() {
    }


    public static InstallmentFragment newInstance() {
        InstallmentFragment fragment = new InstallmentFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_installment, container, false);
    }

}
