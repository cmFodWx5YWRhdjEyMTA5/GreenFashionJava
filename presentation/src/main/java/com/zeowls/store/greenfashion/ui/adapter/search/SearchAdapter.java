package com.zeowls.store.greenfashion.ui.adapter.search;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeowls.domain.entity.Search;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SearchAdapter extends BaseAdapter<Search> implements Filterable {
    private List<Search> searchList;
    private AdapterListener listener;
    private boolean isRecent;

    public SearchAdapter(@NonNull Context context, AdapterListener listener) {
        super(context);
        this.searchList = new ArrayList<>();
        this.listener = listener;
    }


    public void isRecent(boolean isRecent) {
        this.isRecent = isRecent;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new SearchViewHolder(inflater.inflate(R.layout.item_search, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    protected class SearchViewHolder extends BaseAdapter<Search>.GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.clear)
        ImageView clear;
        @BindView(R.id.click)
        View clicker;

        SearchViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            clicker.setOnClickListener(this);
            clear.setOnClickListener(this);
        }

        @Override
        public void onBindData() {
            Search search = at(getAdapterPosition());
            title.setText(search.getName().trim());
            if (isRecent) {
                clear.setVisibility(View.VISIBLE);
            } else {
                clear.setVisibility(View.GONE);
            }
        }

        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.click:
                    listener.onSelected(data.get(getAdapterPosition()));
                    break;
                case R.id.clear:
                    listener.onDelete(data.get(getAdapterPosition()).getId());
                    break;
            }
        }
    }

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                FilterResults results = new FilterResults();
                String charString = charSequence.toString().toLowerCase();
                if (charString.isEmpty()) {
                    results.count = searchList.size();
                    results.values = searchList;
                } else {
                    List<Search> filteredList = new ArrayList<>();
                    for (Search row : searchList) {
                        if (row.getName().toLowerCase().startsWith(charString)) {
                            filteredList.add(row);
                        }
                    }
                    results.count = filteredList.size();
                    results.values = filteredList;
                }
                return results;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                setData((List<Search>) filterResults.values);
            }
        };
    }

    public interface AdapterListener {
        void onSelected(Search search);

        void onDelete(int id);
    }

    public void setSuggestion(List<Search> data) {
        if (data == null) return;
        this.searchList = data;
        notifyDataSetChanged();
    }
}