package com.zeowls.store.greenfashion.ui.adapter.list;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.CheckBox;

import com.zeowls.domain.entity.Category;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.list.optionList.options.CAT;

public class FilterCategoriesAdapter extends BaseAdapter<Category> {
    private final String  mParent;
    private final ArrayList<Integer> cats;
    onPickCategory pickCategory;

    public FilterCategoriesAdapter(Context context, String mParent, ArrayList<Integer> arrayList, onPickCategory com_zeowls_store_greenfashion_ui_adapter_other_FilterCategoriesAdapter_onPickCategory) {
        super(context);
        this.mParent = mParent;
        this.cats = arrayList;
        this.pickCategory = com_zeowls_store_greenfashion_ui_adapter_other_FilterCategoriesAdapter_onPickCategory;
    }

    @NonNull
    public GenericViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new CheckboxViewHolder(this.inflater.inflate(R.layout.item_checkbox, viewGroup, false));
    }

    public interface onPickCategory {
        void addCategory(String str, int i);

        void deleteCategory(String str);
    }


    public void onBindViewHolder(GenericViewHolder genericViewHolder, int i) {
        genericViewHolder.onBindData();
    }


    protected class CheckboxViewHolder extends GenericViewHolder implements OnClickListener {
        private Category category;
        private final Context context;
        @BindView(R.id.title)
        CheckBox title;


        public void onClick(View view) {
        }

        CheckboxViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            this.context = FilterCategoriesAdapter.this.inflater.getContext();
            title.setOnCheckedChangeListener((buttonView, isChecked) -> {
                if (isChecked)
                    pickCategory.addCategory(category.getName().trim(), category.getId());
                else
                    pickCategory.deleteCategory(category.getName().trim());
            });
        }

        public void onBindData() {
            category = at(getAdapterPosition());
            title.setText(category.getName().trim());
            if (mParent.equals(CAT.name())) {
                title.setChecked(true);
                title.setEnabled(false);
                pickCategory.addCategory(category.getName().trim(), category.getId());
            }
            for (int entity : cats) {
                if (entity == category.getId()) {
                    title.setChecked(true);
                    title.setEnabled(true);
                    pickCategory.addCategory(category.getName().trim(), category.getId());
                    if (mParent.equals(CAT.name())) {
                        title.setEnabled(false);
                    }
                }
            }
        }
    }
}
