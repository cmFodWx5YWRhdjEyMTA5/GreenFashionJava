package com.zeowls.store.greenfashion.ui.adapter.orders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeowls.domain.entity.Order;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class OrdersAdapter extends BaseAdapter<Order> {
    private OrdersEvents ordersEvents;

    public OrdersAdapter(Context context, OrdersEvents ordersEvents) {
        super(context);
        this.ordersEvents = ordersEvents;
    }

    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new BrandsViewHolder(inflater.inflate(R.layout.item_orders, parent, false));
    }

    @Override
    public void onBindViewHolder(GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    public interface OrdersEvents {
        void openDetails(Integer id);
    }

    protected class BrandsViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        private SimpleDateFormat dateFormatter;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.ship_no)
        TextView ship_no;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.state)
        TextView state;
        @BindView(R.id.click)
        View click;
        Order order;


        BrandsViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            click.setOnClickListener(this);
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");

        }

        @Override
        public void onBindData() {
            order = at(getAdapterPosition());
            ship_no.setText(String.format("%s %d", context.getString(R.string.shipping_no), order.getId()));
            date.setText(dateFormatter.format(order.getDateAdd()));
            state.setText(order.getCurrentState().getName());
            switch (order.getCurrentState().getId()) {
                case 1:
                    image.setImageResource(R.drawable.ic_inprocessing);
                    break;
                case 2:
                    image.setImageResource(R.drawable.ic_confirmed);
                    break;
                case 3:
                    image.setImageResource(R.drawable.ic_shipped);
                    break;
                case 4:
                    image.setImageResource(R.drawable.ic_deliverd);
                    break;
                case -1:
                    image.setImageResource(R.drawable.ic_canceled);
                    break;
            }
        }

        @Override
        public void onClick(View v) {
            int position = getAdapterPosition();
            ordersEvents.openDetails(at(position).getId());

        }
    }
}
