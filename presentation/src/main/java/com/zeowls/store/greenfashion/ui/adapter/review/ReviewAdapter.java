package com.zeowls.store.greenfashion.ui.adapter.review;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.GenericTransitionOptions;
import com.zeowls.domain.entity.Review;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ReviewAdapter extends BaseAdapter<Review> {

    protected class BrandsViewHolder extends GenericViewHolder implements OnClickListener {
        private  SimpleDateFormat dateFormatter;
        @BindView(R.id.view_container)
        ConstraintLayout container;
        @BindView(R.id.content)
        TextView content;
        private final Context context;
        @BindView(R.id.date)
        TextView date;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.name)
        TextView name;
        @BindView(R.id.rating)
        RatingBar rate;


        public void onClick(View view) {
        }

        BrandsViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
            content.setOnClickListener(this);
            context = inflater.getContext();
            dateFormatter = new SimpleDateFormat("dd/MM/yyyy hh:mm a");
        }

        public void onBindData() {
            Review review = at(getAdapterPosition());
            content.setText(review.getReview());
            date.setText(dateFormatter.format(review.getDateAdd()));
            name.setText(String.format("%s %s", review.getUser().getFirstName(), review.getUser().getLastName()));
            GlideApp.with(context).load(review.getUser().getProfilePic()).skipMemoryCache(true).transition(GenericTransitionOptions.with(R.anim.fade_in)).placeholder(R.drawable.ic_avatar).into(this.image);
            rate.setRating((float) review.getRate());
        }
    }


    public ReviewAdapter(@NonNull Context context) {
        super(context);
    }

    public GenericViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        return new BrandsViewHolder(this.inflater.inflate(R.layout.item_review, viewGroup, false));
    }

    public void onBindViewHolder(GenericViewHolder genericViewHolder, int i) {
        genericViewHolder.onBindData();
    }

    public int getItemCount() {
        if (this.data.size() > 3) {
            return 3;
        }
        return super.getItemCount();
    }
}
