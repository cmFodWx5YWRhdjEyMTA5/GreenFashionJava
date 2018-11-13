package com.zeowls.store.greenfashion.ui.detail;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeowls.store.greenfashion.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class DescFragment extends Fragment {

    private static final String ARG_PARAM1 = "param";
    @BindView(R.id.desc)
    TextView desc;
    private String mParam;

    public DescFragment() {
    }

    public static DescFragment newInstance(String param) {
        DescFragment fragment = new DescFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_desc, container, false);
        ButterKnife.bind(this, view);
        desc.setText(mParam);
        return view;
    }
}
