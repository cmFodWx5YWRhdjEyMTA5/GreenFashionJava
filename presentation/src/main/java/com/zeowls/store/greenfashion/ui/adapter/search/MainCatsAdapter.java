package com.zeowls.store.greenfashion.ui.adapter.search;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zeowls.domain.entity.Category;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.categories.CategoriesFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.main.MainActivity.navigationState.CHILD;

public class MainCatsAdapter extends BaseAdapter<Category> {
    private static final int HEADER = 0;
    private AppCompatActivity activity;

    public MainCatsAdapter(Context context) {
        super(context);
        this.activity = (AppCompatActivity) context;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        if (viewType == HEADER)
            return new HeaderViewHolder(inflater.inflate(R.layout.item_headline, parent, false));
        else
            return new CategoriesViewHolder(inflater.inflate(R.layout.item_single_text, parent, false));
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


    protected class CategoriesViewHolder extends BaseAdapter<Category>.GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.title)
        TextView title;

        CategoriesViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            title.setOnClickListener(this);
        }

        @Override
        public void onBindData() {
            Category category = at(getAdapterPosition());
            title.setText(category.getName().trim());
        }

        @Override
        public void onClick(View v) {
            CategoriesFragment fragment = CategoriesFragment.newInstance(at(getAdapterPosition()).getId(), at(getAdapterPosition()).getName());
            activity.getSupportFragmentManager().beginTransaction().add(R.id.container, fragment, CHILD.name()).addToBackStack(null).commit();
        }
    }


    protected class CategoriesHeaderViewHolder extends BaseAdapter<Category>.GenericViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        TextView title;

        public CategoriesHeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }

        @Override
        public void onBindData() {
            title.setText(R.string.all_categories);
        }

        @Override
        public void onClick(View v) {
        }
    }

    protected class HeaderViewHolder extends GenericViewHolder implements View.OnClickListener {
        @BindView(R.id.title)
        TextView title;

        HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

        }

        @Override
        public void onBindData() {
            title.setText(R.string.all_categories);
        }


        @Override
        public void onClick(View v) {

        }
    }
}