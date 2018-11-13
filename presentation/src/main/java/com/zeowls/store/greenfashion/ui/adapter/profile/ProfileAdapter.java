package com.zeowls.store.greenfashion.ui.adapter.profile;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zeowls.domain.entity.Profile;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.base.BaseAdapter;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ProfileAdapter extends BaseAdapter<Profile> {
    private onProfileActive onProfileActive;
    private boolean isActive;
    private onProfile onProfile;

    public ProfileAdapter(@NonNull Context context, onProfileActive onProfileActive, boolean isActive) {
        super(context);
        this.onProfileActive = onProfileActive;
        this.isActive = isActive;
    }

    public ProfileAdapter(@NonNull Context context, onProfile onProfile) {
        super(context);
        this.onProfile = onProfile;
    }

    @NonNull
    @Override
    public GenericViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ProfileViewHolder(inflater.inflate(R.layout.item_profile, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull GenericViewHolder holder, int position) {
        holder.onBindData();
    }

    public interface onProfileActive {
        void onOrders();

        void onLanguage();

        void onAddressBook();

        void onRateUs();

        void onReview();

        void onShare();

        void onLogout();
    }


    public interface onProfile {

        void onSignIn();

        void onShare();

        void onRateUs();

        void onLanguage();

    }

    protected class ProfileViewHolder extends GenericViewHolder implements View.OnClickListener {
        private final Context context;
        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.title)
        TextView title;
        @BindView(R.id.view_click)
        View click;

        ProfileViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            context = inflater.getContext();
            click.setOnClickListener(this);
        }

        @Override
        public void onBindData() {
            Profile profile = at(getAdapterPosition());
            title.setText(profile.getTitle());
            image.setImageResource(profile.getImage());
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            if (isActive) {
                switch (position) {
                    case 0:
                        onProfileActive.onOrders();
                        break;
                    case 1:
                        onProfileActive.onAddressBook();
                        break;
                    case 2:
                        onProfileActive.onReview();
                        break;
                    case 3:
                        onProfileActive.onRateUs();
                        break;
                    case 4:
                        onProfileActive.onShare();
                        break;
                    case 5:
                        onProfileActive.onLanguage();
                        break;
                    case 6:
                        onProfileActive.onLogout();
                        break;
                }
            } else {
                switch (position) {
                    case 0:
                        onProfile.onSignIn();
                        break;
                    case 1:
                        onProfile.onRateUs();
                        break;
                    case 2:
                        onProfile.onShare();
                        break;
                    case 3:
                        onProfile.onLanguage();
                        break;
                }
            }
        }
    }
}
