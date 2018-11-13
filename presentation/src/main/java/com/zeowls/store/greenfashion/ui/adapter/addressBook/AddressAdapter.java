package com.zeowls.store.greenfashion.ui.adapter.addressBook;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeowls.domain.entity.Address;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AddressAdapter extends BaseAdapter<Address> {
    private AddressEvents addressEvents;

    public AddressAdapter(Context context, AddressEvents addressEvents) {
        super(context);
        this.addressEvents = addressEvents;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BrandsViewHolder(inflater.inflate(R.layout.item_address, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    public interface AddressEvents {
        void onFavorite(Address address);

        void onEdit(Address address);
    }

    protected class BrandsViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.street)
        TextView street;
        @BindView(R.id.phone)
        TextView phone;
        @BindView(R.id.favorite)
        ImageView favorite;
        @BindView(R.id.click)
        View click;
        Address address;
        int favoriteId;

        BrandsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            click.setOnClickListener(this);
            favorite.setOnClickListener(this);
        }

        @Override
        public void onBindData() {
            if (LoggedData.getFavAddress(context) != null)
                favoriteId = LoggedData.getFavAddress(context).getId();
            else
                favoriteId = -1;
            address = at(getAdapterPosition());
            name.setText(address.getUsername());
            street.setText(address.getStreetName());
            phone.setText(address.getPhone().getMobile());
            if (favoriteId == address.getId())
                favorite.setImageResource(R.drawable.ic_fav_active);
            else
                favorite.setImageResource(R.drawable.ic_fav_unactive);
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            switch (v.getId()) {
                case R.id.click:
                    addressEvents.onEdit(address);
                    break;
                case R.id.favorite:
                    addressEvents.onFavorite(address);
                    break;
            }
        }
    }
}
