package com.zeowls.store.greenfashion.ui.adapter.brands;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeowls.domain.entity.Brand;
import com.zeowls.domain.entity.Products;
import com.zeowls.domain.interactor.SingleInteractor;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.list.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.list.optionList.options.BRAND;
import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class BrandsAdapter extends BaseAdapter<Brand> {
    private final int HEADER = 0;
    private final SingleInteractor<Products> interactor;

    public BrandsAdapter(Context context, SingleInteractor<Products> interactor) {
        super(context);
        this.interactor = interactor;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == HEADER)
            return new BrandsHeaderViewHolder(inflater.inflate(R.layout.item_headline, parent, false));
        else
            return new BrandsViewHolder(inflater.inflate(R.layout.item_single_text, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    @Override
    public int getItemViewType(int position) {
        if (position == 0)
            return HEADER;
        else
            return position;
    }

    protected class BrandsViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.title)
        TextView title;
        AppCompatActivity activity;
        private Brand brand;

        BrandsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            title.setOnClickListener(this);
            context = inflater.getContext();
            activity = (AppCompatActivity) context;
        }

        @Override
        public void onBindData() {
            brand = at(getAdapterPosition() - 1);
            title.setText(brand.getName().trim());
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition() - 1;
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, ListFragment.newInstance(interactor, at(position).getId(), true, false, brand.getName(), BRAND.name(), -1), CHILD.name()).addToBackStack(null).commit();

        }
    }


    protected class BrandsHeaderViewHolder extends GenericViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        TextView title;

        BrandsHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @Override
        public void onBindData() {
            title.setText(R.string.all_brands);
        }


        @Override
        public void onClick(View v) {

        }
    }
}
