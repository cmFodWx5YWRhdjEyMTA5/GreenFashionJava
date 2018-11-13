package com.zeowls.store.greenfashion.ui.detail;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.DialogFragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.adapter.details.HorizontalAdapter;
import com.zeowls.store.greenfashion.ui.adapter.details.ViewPagerAdapter;
import com.zeowls.store.greenfashion.ui.view.GalleryViewPager;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.utils.Utils.convertFromJsonString;

public class GalleryFragment extends DialogFragment implements ViewPager.OnPageChangeListener, HorizontalAdapter.OnImgClick {
    @BindView(R.id.pager)
    GalleryViewPager mViewPager;
    @BindView(R.id.horz_list)
    RecyclerView imagesHorzList;
    private HorizontalAdapter hAdapter;
    private static String ARG_POSITION = "position";
    private static String ARG_IMAGES = "images";
    private int selectedImagePosition;
    private List<String> images;

    public GalleryFragment() {
    }

    public static GalleryFragment newInstance(int position, String images) {
        GalleryFragment fragment = new GalleryFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_POSITION, position);
        args.putString(ARG_IMAGES, images);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            selectedImagePosition = getArguments().getInt(ARG_POSITION);
            images = convertFromJsonString(getArguments().getString(ARG_IMAGES), new TypeToken<List<String>>() {
            }.getType());
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_gallery, container, false);
    }


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ButterKnife.bind(this, view);
        mViewPager.setAdapter(new ViewPagerAdapter(getActivity(), images, imagesHorzList));
        hAdapter = new HorizontalAdapter(getActivity(), this);
        imagesHorzList.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.HORIZONTAL, false));
        imagesHorzList.setAdapter(hAdapter);
        hAdapter.setData(images);
        mViewPager.addOnPageChangeListener(this);
        hAdapter.setSelectedItem(selectedImagePosition);
        mViewPager.setCurrentItem(selectedImagePosition);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        imagesHorzList.smoothScrollToPosition(position);
        hAdapter.setSelectedItem(position);    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onClick(int pos) {
        mViewPager.setCurrentItem(pos, true);
    }
}

