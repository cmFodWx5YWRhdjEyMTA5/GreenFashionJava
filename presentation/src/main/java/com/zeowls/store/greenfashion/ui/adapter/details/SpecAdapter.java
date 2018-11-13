package com.zeowls.store.greenfashion.ui.adapter.details;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeowls.domain.entity.FeaturesEn;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SpecAdapter extends BaseAdapter<FeaturesEn> {


    public SpecAdapter(Context context) {
        super(context);
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SpecViewHolder(inflater.inflate(R.layout.item_spec, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }


    protected class SpecViewHolder extends GenericViewHolder {
        private final Context context;

        @BindView(R.id.key)
        TextView key;
        @BindView(R.id.value)
        TextView value;
        @BindView(R.id.divider)
        TextView divider;

        SpecViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
        }

        @Override
        public void onBindData() {
            FeaturesEn item = at(getAdapterPosition());
            if (item.value != null) {
                item.value.replaceAll("\\s+","");
                if (!item.value.isEmpty()) {
                    key.setVisibility(View.VISIBLE);
                    value.setVisibility(View.VISIBLE);
                    divider.setVisibility(View.VISIBLE);
                    key.setText(item.key);
                    value.setText(item.value);
                } else {
                    divider.setVisibility(View.GONE);
                    key.setVisibility(View.GONE);
                    value.setVisibility(View.GONE);
                }
            } else {
                divider.setVisibility(View.GONE);
                key.setVisibility(View.GONE);
                value.setVisibility(View.GONE);
            }
        }
    }
}
