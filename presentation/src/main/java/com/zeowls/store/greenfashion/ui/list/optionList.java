package com.zeowls.store.greenfashion.ui.list;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.Adapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeowls.domain.entity.Option;
import com.zeowls.store.greenfashion.R;

import java.util.ArrayList;
import java.util.List;

public class optionList extends BottomSheetDialogFragment {

    private static final String ARG_ITEM_ID = "item_id";
    private static final String ARG_ITEM_POSITION = "item_position";
    private static final String ARG_ITEM_PARENT_ID = "item_parent_id";
    private static final String ARG_ITEM_OPTION = "item_option";
    private static final String ARG_ITEM_TITLE = "item_parent_title";

    private String option;
    private int id;
    private Listener mListener;
    private int parentId;
    private int productPosition;
    private String title;

    public enum options {
        CAT, BRAND, EXTENDED
    }

    public interface Listener {
        void onOptionClicked(int item, int position, int id, int parentId, String title);
    }

    private class ViewHolder extends RecyclerView.ViewHolder {
        TextView text = itemView.findViewById(R.id.text);
        ImageView image = itemView.findViewById(R.id.image);
        View click = itemView.findViewById(R.id.click);

        ViewHolder(LayoutInflater layoutInflater, ViewGroup viewGroup) {
            super(layoutInflater.inflate(R.layout.item_option, viewGroup, false));
            this.click.setOnClickListener(view -> {
                if (mListener != null) {
                    mListener.onOptionClicked(getAdapterPosition(), productPosition, id, parentId, title);
                    dismiss();
                }
            });
        }
    }

    private class optionAdapter extends Adapter<ViewHolder> {
        private final List<Option> mOptionList;

        optionAdapter(List<Option> list) {
            this.mOptionList = list;
        }

        @NonNull
        public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()), viewGroup);
        }

        public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
            viewHolder.text.setText(this.mOptionList.get(i).getTitle());
            viewHolder.image.setImageResource(mOptionList.get(i).getImage());
        }

        public int getItemCount() {
            return this.mOptionList.size();
        }
    }

    public static optionList newInstance(int position, int id, int parentId, String option, String title) {
        optionList optionList = new optionList();
        Bundle bundle = new Bundle();
        bundle.putInt(ARG_ITEM_POSITION, position);
        bundle.putInt(ARG_ITEM_ID, id);
        bundle.putInt(ARG_ITEM_PARENT_ID, parentId);
        bundle.putString(ARG_ITEM_OPTION, option);
        bundle.putString(ARG_ITEM_TITLE, title);
        optionList.setArguments(bundle);
        return optionList;
    }

    @Nullable
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup viewGroup, @Nullable Bundle bundle) {
        View view = inflater.inflate(R.layout.fragment_option, viewGroup, false);
        if (getArguments() != null) {
            productPosition = getArguments().getInt(ARG_ITEM_POSITION);
            id = getArguments().getInt(ARG_ITEM_ID);
            parentId = getArguments().getInt(ARG_ITEM_PARENT_ID);
            option = getArguments().getString(ARG_ITEM_OPTION);
            title = getArguments().getString(ARG_ITEM_TITLE);
        }
        return view;
    }

    public void onViewCreated(@NonNull View view, @Nullable Bundle bundle) {
        RecyclerView recyclerView = (RecyclerView) view;
        List<Option> optionList = new ArrayList<>();
        int[] icons = {R.drawable.ic_cart, R.drawable.share_unactive};
        optionList.clear();
        switch (options.valueOf(option)) {
            case BRAND:
                String[] brand = getResources().getStringArray(R.array.options_brand);
                for (int i = 0; i < brand.length; i++) {
                    Option item = new Option();
                    item.setImage(icons[i]);
                    item.setTitle(brand[i]);
                    optionList.add(item);
                }
                break;
            case CAT:
                String[] cat = getResources().getStringArray(R.array.options_cat);
                for (int i = 0; i < cat.length; i++) {
                    Option item = new Option();
                    item.setImage(icons[i]);
                    item.setTitle(cat[i]);
                    optionList.add(item);
                }
                break;
            case EXTENDED:
                String[] extended = getResources().getStringArray(R.array.options_cat);
                for (int i = 0; i < extended.length; i++) {
                    Option item = new Option();
                    item.setImage(icons[i]);
                    item.setTitle(extended[i]);
                    optionList.add(item);
                }
                break;
            default:
                String[] extended1 = getResources().getStringArray(R.array.options_cat);
                for (int i = 0; i < extended1.length; i++) {
                    Option item = new Option();
                    item.setImage(icons[i]);
                    item.setTitle(extended1[i]);
                    optionList.add(item);
                }
                break;
        }
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(new optionAdapter(optionList));
    }

    public void onAttach(Context context) {
        super.onAttach(context);
        Fragment parentFragment = getParentFragment();
        if (parentFragment != null) {
            this.mListener = (Listener) parentFragment;
        }
    }

    public void onDetach() {
        this.mListener = null;
        super.onDetach();
    }
}
