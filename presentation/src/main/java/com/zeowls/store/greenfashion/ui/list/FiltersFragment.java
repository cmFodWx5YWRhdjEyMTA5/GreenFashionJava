package com.zeowls.store.greenfashion.ui.list;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.zeowls.domain.entity.Filter;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.adapter.list.FilterBrandsAdapter;
import com.zeowls.store.greenfashion.ui.adapter.list.FilterCategoriesAdapter;
import com.zeowls.store.greenfashion.ui.base.BaseFilter;
import com.zeowls.store.greenfashion.ui.view.RangeSeekBar;
import com.zeowls.store.greenfashion.ui.view.expandableLayout.ExpandableLayout;
import com.zeowls.store.greenfashion.ui.view.expandableLayout.util.FastOutSlowInInterpolator;

import java.util.ArrayList;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.zeowls.store.greenfashion.ui.utils.Utils.convertFromJsonString;

public class FiltersFragment extends BottomSheetDialogFragment implements FilterCategoriesAdapter.onPickCategory, FilterBrandsAdapter.onPickBrand {
    private static final String ARG_ITEM_FILTER = "item_filter";
    private static final String ARG_ITEM_FILTER_PARENT = "item_filter_parent";
    private static final String ARG_PICK_CATS = "pick_cats";
    private static final String ARG_PICK_BRANDS = "pick_brands";
    private String BRAND_TAG = "BRAND";
    private String SUBCAT_TAG = "CAT";
    private ArrayList<Integer> brands;
    private ArrayList<Integer> cats;
    @BindView(R.id.clear)
    TextView clear;
    @BindView(R.id.close)
    TextView close;
    @BindView(R.id.done)
    Button done;
    private Listener mListener;
    private RecyclerView recyclerView;
    private Filter mParam;
    private String mParent;
    private HashMap<String, Integer> mapCategory = new HashMap<>();
    private HashMap<String, Integer> mapBrand = new HashMap<>();
    private int minPrice, maxPrice;
    private FiltersAdapter mAdapter;

    // TODO: Customize parameters
    public static FiltersFragment newInstance(String data, String parent, ArrayList<Integer> cats, ArrayList<Integer> brands) {
        final FiltersFragment fragment = new FiltersFragment();
        final Bundle args = new Bundle();
        args.putString(ARG_ITEM_FILTER, data);
        args.putString(ARG_ITEM_FILTER_PARENT, parent);
        args.putIntegerArrayList(ARG_PICK_CATS, cats);
        args.putIntegerArrayList(ARG_PICK_BRANDS, brands);
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_filters, container, false);
        ButterKnife.bind(this, view);
        if (getArguments() != null) {
            mParam = convertFromJsonString(getArguments().getString(ARG_ITEM_FILTER), Filter.class);
            mParent = getArguments().getString(ARG_ITEM_FILTER_PARENT);
            cats = getArguments().getIntegerArrayList(ARG_PICK_CATS);
            brands = getArguments().getIntegerArrayList(ARG_PICK_BRANDS);
        }
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        recyclerView = view.findViewById(R.id.list);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.addItemDecoration(new DividerItemDecoration(getActivity(), DividerItemDecoration.VERTICAL));
        recyclerView.setAdapter(mAdapter = new FiltersAdapter(getActivity()));
        recyclerView.setHasFixedSize(true);
        recyclerView.setFocusable(false);

        done.setOnClickListener(v -> {
            mListener.onFiltersClicked(mapBrand, mapCategory, minPrice, maxPrice);
            mapBrand.clear();
            mapCategory.clear();
            dismiss();
        });

        clear.setOnClickListener(v -> {
            mListener.onFilterClear();
            mapBrand.clear();
            mapCategory.clear();
            brands.clear();
            cats.clear();
            dismiss();
        });
        close.setOnClickListener(v -> dismiss());
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        final Fragment parent = getParentFragment();
        if (parent != null) {
            mListener = (Listener) parent;
        }
    }

    @Override
    public void onDetach() {
        mListener = null;
        super.onDetach();
    }

    @Override
    public void addCategory(String name, int id) {
        mapCategory.put(name, id);
    }

    @Override
    public void deleteCategory(String name) {
        mapCategory.remove(name);
    }

    @Override
    public void addBrand(String name, int id) {
        mapBrand.put(name, id);

    }

    @Override
    public void deleteBrand(String name) {
        mapBrand.remove(name);
    }

    private Drawable rotate(float degree) {
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(),
                R.drawable.ic_arrow_up);
        Matrix matrix = new Matrix();
        matrix.postRotate(degree);
        Bitmap targetBitmap = Bitmap.createBitmap(bitmap, 0, 0, bitmap.getWidth(), bitmap.getHeight(), matrix, true);

        return new BitmapDrawable(getResources(), targetBitmap);
    }


    public interface Listener {

        void onFiltersClicked(HashMap<String, Integer> mapBrand, HashMap<String, Integer> mapCategory, int minPrice, int maxPrice);

        void onFilterClear();
    }

    public class FiltersAdapter extends BaseFilter<Filter> {
        private static final int UNSELECTED = -1;
        private final int VIEW_TYPE_RANGEBAR = 0;
        private final int VIEW_TYPE_BRANDS = 1;
        private final int VIEW_TYPE_CATEGORIES = 2;
        private final int VIEW_TYPE_COLORS = 3;
        private int selectedItem = UNSELECTED;


        FiltersAdapter(Context context) {
            super(context);
        }

        @Override
        public GenericViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            switch (viewType) {
                case VIEW_TYPE_RANGEBAR:
                    return new FilterViewHolder(inflater.inflate(R.layout.item_filters_price, parent, false));
                case VIEW_TYPE_BRANDS:
                    return new FilterViewHolder(inflater.inflate(R.layout.item_filters_check, parent, false));
                case VIEW_TYPE_CATEGORIES:
                    return new FilterViewHolder(inflater.inflate(R.layout.item_filters_check, parent, false));
                default:
                    return null;
            }
        }

        @Override
        public void onBindViewHolder(GenericViewHolder holder, int position) {
            if (position == VIEW_TYPE_RANGEBAR)
                holder.onBindData();
            else if (position == VIEW_TYPE_BRANDS)
                holder.onBindBrands();
            else if (position == VIEW_TYPE_CATEGORIES)
                holder.onBindCategories();
        }

        @Override
        public int getItemViewType(int position) {
            switch (position) {
                case 0:
                    return VIEW_TYPE_RANGEBAR;
                case 1:
                    return VIEW_TYPE_BRANDS;
                case 2:
                    return VIEW_TYPE_CATEGORIES;
                case 3:
                    return VIEW_TYPE_COLORS;
                default:
                    return VIEW_TYPE_CATEGORIES;
            }
        }

        @Override
        public int getItemCount() {
            return 3;
        }

        protected class FilterViewHolder extends GenericViewHolder implements View.OnClickListener, ExpandableLayout.OnExpansionUpdateListener {
            private final Context context;
            @BindView(R.id.expand_layout)
            ExpandableLayout expandableLayout;
            @BindView(R.id.title)
            TextView Heading;
            @Nullable
            @BindView(R.id.rv_check)
            RecyclerView CbrecyclerView;
            @Nullable
            @BindView(R.id.min)
            TextView minValue;
            @Nullable
            @BindView(R.id.max)
            TextView maxValue;
            @Nullable
            @BindView(R.id.rangebar1)
            RangeSeekBar rangeBar;
            private FilterCategoriesAdapter adapter2;
            private FilterBrandsAdapter adapter;
            private LinearLayoutManager layoutManager;

            @SuppressLint("ClickableViewAccessibility")
            FilterViewHolder(View itemView) {
                super(itemView);
                ButterKnife.bind(this, itemView);
                context = inflater.getContext();
                if (rangeBar != null) {
                    if (context.getResources().getBoolean(R.bool.isEnglish))
                        rangeBar.onRtlPropertiesChanged(View.LAYOUT_DIRECTION_LTR);
                    else rangeBar.onRtlPropertiesChanged(View.LAYOUT_DIRECTION_RTL);
                }
                Heading.setOnClickListener(this);
                expandableLayout.setInterpolator(new FastOutSlowInInterpolator());
                expandableLayout.setOnExpansionUpdateListener(this);
                layoutManager = new LinearLayoutManager(getActivity());
                if (CbrecyclerView != null) {
                    RecyclerView.OnItemTouchListener mScrollTouchListener = new RecyclerView.OnItemTouchListener() {
                        @Override
                        public boolean onInterceptTouchEvent(RecyclerView rv, MotionEvent e) {
                            int action = e.getAction();
                            switch (action) {
                                case MotionEvent.ACTION_MOVE:
                                    rv.getParent().requestDisallowInterceptTouchEvent(true);
                                    break;
                            }
                            return false;
                        }

                        @Override
                        public void onTouchEvent(RecyclerView rv, MotionEvent e) {
                        }

                        @Override
                        public void onRequestDisallowInterceptTouchEvent(boolean disallowIntercept) {
                        }
                    };
                    CbrecyclerView.addOnItemTouchListener(mScrollTouchListener);
                }
            }

            @Override
            public void onBindData() {
                int position = getAdapterPosition();
                boolean isSelected = position == selectedItem;
                Heading.setSelected(isSelected);
                expandableLayout.setExpanded(isSelected, false);
                if (rangeBar != null) {
                    rangeBar.setRules(0, 100000);
                    rangeBar.setValue(0, 100000);
                    minValue.setText(String.valueOf(0));
                    maxValue.setText(String.valueOf(100000));
                    minPrice = 0;
                    maxPrice = 100000;
                    rangeBar.setOnRangeChangedListener((view, min, max) -> {
                        minValue.setText(String.valueOf(min));
                        maxValue.setText(String.valueOf(max));
                        minPrice = min;
                        maxPrice = max;
                    });
                }
            }

            @Override
            public void onBindBrands() {
                Heading.setText(getString(R.string.brands));
                adapter = new FilterBrandsAdapter(getActivity(), mParent, brands, FiltersFragment.this);
                CbrecyclerView.setLayoutManager(layoutManager);
                CbrecyclerView.setAdapter(adapter);
                int position = getAdapterPosition();
                boolean isSelected = position == selectedItem;
                Heading.setSelected(isSelected);
                expandableLayout.setExpanded(isSelected, false);
                if (mParent.equals(BRAND_TAG)) {
                    Heading.setSelected(true);
                    expandableLayout.expand();
                    selectedItem = position;
                }
                if (CbrecyclerView != null) {
                    adapter.setData(mParam.getBrands());
                }
            }

            @Override
            public void onBindCategories() {
                Heading.setText(getString(R.string.categories));
                adapter2 = new FilterCategoriesAdapter(getActivity(), mParent, cats, FiltersFragment.this);
                CbrecyclerView.setLayoutManager(layoutManager);
                CbrecyclerView.setAdapter(adapter2);
                int position = getAdapterPosition();
                boolean isSelected = position == selectedItem;
                Heading.setSelected(isSelected);
                expandableLayout.setExpanded(isSelected, false);
                if (mParent.equals(SUBCAT_TAG)) {
                    Heading.setSelected(true);
                    expandableLayout.expand();
                    selectedItem = position;
                }
                if (CbrecyclerView != null) {
                    adapter2.setData(mParam.getCategories());
                }
            }


            @Override
            public void onClick(View v) {
                FilterViewHolder holder = (FilterViewHolder) recyclerView.findViewHolderForAdapterPosition(selectedItem);
                if (holder != null) {
                    holder.Heading.setSelected(false);
                    holder.expandableLayout.collapse();
                }
                int position = getAdapterPosition();
                if (position == selectedItem) {
                    selectedItem = UNSELECTED;
                } else {
                    Heading.setSelected(true);
                    expandableLayout.expand();
                    selectedItem = position;
                }
            }


            @Override
            public void onExpansionUpdate(float expansionFraction, int state) {
                if (isAdded())
                    Heading.setCompoundDrawablesWithIntrinsicBounds(null, null, rotate(expansionFraction * 180), null);
            }
        }
    }
}