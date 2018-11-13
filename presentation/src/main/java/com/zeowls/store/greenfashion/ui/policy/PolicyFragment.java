package com.zeowls.store.greenfashion.ui.policy;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zeowls.store.greenfashion.R;

public class PolicyFragment extends Fragment {

    public PolicyFragment() {
    }

    public static PolicyFragment newInstance() {
        PolicyFragment fragment = new PolicyFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_policy, container, false);
    }
}
