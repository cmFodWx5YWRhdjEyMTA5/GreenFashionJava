package com.zeowls.store.greenfashion.ui.base;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("WeakerAccess")
public abstract class BaseFilter<T> extends
        RecyclerView.Adapter<BaseFilter<T>.GenericViewHolder> {

    protected List<T> data;
    protected LayoutInflater inflater;

    public BaseFilter(@NonNull Context context) {
        this.inflater = LayoutInflater.from(context);
        this.data = new ArrayList<>();
    }

    public void setData(List<T> data) {
        if (data == null) return;
        this.data = data;
        notifyDataSetChanged();
    }

    public void editData(List<T>data ,int position){
        this.data = data;
        notifyItemChanged(position);
    }

    public void deleteData(List<T>data ,int position){
        this.data = data;
        notifyItemRemoved(position);
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    protected View inflate(@LayoutRes int id, ViewGroup container) {
        return inflater.inflate(id, container, false);
    }

    protected T at(int index) {
        return data.get(index);
    }

    public void appendData(@NonNull List<T> data) {
        int size = getItemCount();
        this.data.addAll(data);
        notifyItemRangeInserted(size, getItemCount());
    }

    public BaseFilter<T> addItem(T item) {
        int size = getItemCount();
        data.add(item);
        notifyItemRangeInserted(size, getItemCount());
        return this;
    }

    public abstract class GenericViewHolder extends RecyclerView.ViewHolder {
        public GenericViewHolder(View itemView) {
            super(itemView);
        }

        public abstract void onBindData();

        public abstract void onBindBrands();

        public abstract void onBindCategories();
    }
}