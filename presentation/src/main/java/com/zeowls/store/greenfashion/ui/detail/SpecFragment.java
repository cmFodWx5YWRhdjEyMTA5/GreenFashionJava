package com.zeowls.store.greenfashion.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.zeowls.data.LocaleManager;
import com.zeowls.domain.entity.FeaturesAr;
import com.zeowls.domain.entity.FeaturesEn;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.adapter.details.SpecAdapter;
import com.zeowls.store.greenfashion.ui.adapter.details.SpecArAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.utils.Utils.convertFromJsonString;

public class SpecFragment extends Fragment {
    private static final String PARAM_SPEC = "param_spec";
    private static final String PARAM_LANG = "param_language";
    @BindView(R.id.list)
    RecyclerView list;
    private String mParam;
    private LinearLayoutManager layoutManager;
    private boolean isEnglish;

    public SpecFragment() {
    }

    public static SpecFragment newInstance(String spec, boolean b) {
        SpecFragment fragment = new SpecFragment();
        Bundle args = new Bundle();
        args.putString(PARAM_SPEC, spec);
        args.putBoolean(PARAM_LANG, b);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam = getArguments().getString(PARAM_SPEC);
            isEnglish = getArguments().getBoolean(PARAM_LANG);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_spec, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        layoutManager = new LinearLayoutManager(getContext(), RecyclerView.VERTICAL, false);
        list.setLayoutManager(layoutManager);
        if (LocaleManager.getLanguage(getContext()).equals(LocaleManager.LANGUAGE_ENGLISH)) {
            SpecAdapter adapter = new SpecAdapter(getContext());
            adapter.setData(convertFromJsonString(mParam, new TypeToken<List<FeaturesEn>>() {
            }.getType()));
            list.setAdapter(adapter);
        } else {
            SpecArAdapter adapter = new SpecArAdapter(getContext());
            adapter.setData(convertFromJsonString(mParam, new TypeToken<List<FeaturesAr>>() {
            }.getType()));
            list.setAdapter(adapter);
        }
    }
}
