package com.zeowls.store.greenfashion.ui.adapter.details;

import android.content.Context;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.view.ViewGroup;

import com.google.gson.reflect.TypeToken;
import com.zeowls.data.LocaleManager;
import com.zeowls.domain.entity.FeaturesAr;
import com.zeowls.domain.entity.FeaturesEn;
import com.zeowls.domain.entity.ProductDetails;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.detail.DescFragment;
import com.zeowls.store.greenfashion.ui.detail.SpecFragment;
import com.zeowls.store.greenfashion.ui.review.ReviewFragment;
import com.zeowls.store.greenfashion.ui.view.WrappingViewPager;

import java.util.List;

import static com.zeowls.store.greenfashion.ui.utils.Utils.convertToJsonString;


public class WrappingFragmentPagerAdapter extends FragmentPagerAdapter {
    private final Context context;
    private final ProductDetails data;
    private int mCurrentPosition = -1;

    public WrappingFragmentPagerAdapter(Context context, FragmentManager fm, ProductDetails data) {
        super(fm);
        this.context = context;
        this.data = data;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (LocaleManager.getLanguage(context).equals(LocaleManager.LANGUAGE_ENGLISH))
                    return SpecFragment.newInstance(convertToJsonString(data.getProduct().getFeaturesEn(), new TypeToken<List<FeaturesEn>>() {
                    }.getType()), true);
                else
                    return SpecFragment.newInstance(convertToJsonString(data.getProduct().getFeaturesAr(), new TypeToken<List<FeaturesAr>>() {
                    }.getType()), false);
            case 1:
                return DescFragment.newInstance(data.getProduct().getDescription());
            case 2:
                return ReviewFragment.newInstance(data.getProduct().getId());
            default:
                return null;
        }
    }


    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return context.getString(R.string.spec);
            case 1:
                return context.getString(R.string.desc);
            case 2:
                return context.getString(R.string.reviews);
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }

    @Override
    public void setPrimaryItem(ViewGroup container, int position, Object object) {
        super.setPrimaryItem(container, position, object);
        if (position != mCurrentPosition) {
            Fragment fragment = (Fragment) object;
            WrappingViewPager pager = (WrappingViewPager) container;
            if (fragment != null && fragment.getView() != null) {
                mCurrentPosition = position;
                pager.measureCurrentView(fragment.getView());
            }
        }
    }
}
