package com.zeowls.store.greenfashion.ui.adapter.list;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.zeowls.domain.entity.Brand;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.list.optionList.options.BRAND;

public class FilterBrandsAdapter extends BaseAdapter<Brand> {
    private final String mParent;
    private final ArrayList<Integer> brands;
    private onPickBrand pickBrand;

    public FilterBrandsAdapter(Context context, String mParent, ArrayList<Integer> arrayList, onPickBrand pickBrand) {
        super(context);
        this.mParent = mParent;
        this.brands = arrayList;
        this.pickBrand = pickBrand;
    }

    public GenericViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CheckboxViewHolder(inflater.inflate(R.layout.item_checkbox, viewGroup, false));
    }

    public void onBindViewHolder(GenericViewHolder holder, int i) {
        holder.onBindData();
    }

    public interface onPickBrand {
        void addBrand(String str, int i);

        void deleteBrand(String str);
    }

    protected class CheckboxViewHolder extends GenericViewHolder implements OnClickListener {
        private Brand brand;
        private final Context context;
        @BindView(R.id.title)
        CheckBox title;

        public void onClick(View view) {
        }

        CheckboxViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            context = inflater.getContext();
            title.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked)
                    pickBrand.addBrand(brand.getName().trim(), brand.getId());
                else
                    pickBrand.deleteBrand(brand.getName().trim());
            });
        }


        public void onBindData() {
            brand = at(getAdapterPosition());
            title.setText(brand.getName().trim());
            if (mParent.equals(BRAND.name())) {
                title.setChecked(true);
                title.setEnabled(false);
                pickBrand.addBrand(brand.getName().trim(), brand.getId());
            }
            for (int entity : brands) {
                if (entity == brand.getId()) {
                    title.setChecked(true);
                    title.setEnabled(true);
                    pickBrand.addBrand(brand.getName().trim(), brand.getId());
                    if (mParent.equals(BRAND.name())) {
                        title.setEnabled(false);
                    }
                }
            }
        }
    }
}
