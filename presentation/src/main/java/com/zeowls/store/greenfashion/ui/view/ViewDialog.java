package com.zeowls.store.greenfashion.ui.view;

import android.app.Activity;
import android.app.Dialog;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.load.resource.bitmap.CenterInside;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.zeowls.domain.entity.AddReview;
import com.zeowls.store.greenfashion.GlideApp;
import com.zeowls.store.greenfashion.R;
import com.zeowls.store.greenfashion.ui.orderDetails.OrdersDetailsContract;
import com.zeowls.store.greenfashion.ui.utils.SharedTool.LoggedData;

import static com.zeowls.store.greenfashion.ui.utils.Utils.isValidField;

public class ViewDialog {


    public void showRateDialog(Activity activity, int id, OrdersDetailsContract.Presenter presenter) {
        Dialog dialog = new Dialog(activity, R.style.Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.item_rate);

        RatingBar ratingBar = dialog.findViewById(R.id.rating);
        EditText review = dialog.findViewById(R.id.review);

        AddReview addReview = new AddReview();

        Button dialogButton = dialog.findViewById(R.id.btn_dialog);
        dialogButton.setOnClickListener(v -> {
            if (isValidField(review, activity)) {
                review.setError(null);
                addReview.setUserId(LoggedData.getUserObject(activity).getId());
                addReview.setItemId(id);
                addReview.setRate(Double.valueOf(ratingBar.getRating()));
                addReview.setReview(review.getText().toString());
                presenter.addReview(addReview);
                dialog.dismiss();
            } else {
                review.setError(activity.getString(R.string.required));
            }

        });
        dialog.show();

    }


    public void showConfirmationDialog(Activity activity, int id, OrdersDetailsContract.Presenter presenter) {
        Dialog dialog = new Dialog(activity, R.style.Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.item_confirm);
        ImageView image = dialog.findViewById(R.id.image);
        GlideApp.with(activity)
                .load(R.drawable.ic_confirm)
                .transforms(new CenterInside(), new RoundedCorners(5))
                .into(image);

        Button yes = dialog.findViewById(R.id.yes);
        Button no = dialog.findViewById(R.id.no);

        yes.setOnClickListener(view -> {
            presenter.cancelOrder(id);
            dialog.dismiss();
        });

        no.setOnClickListener(view -> dialog.dismiss());

        dialog.show();

    }


    public interface Action {
        void onConfirm();
    }

    public void showLanguageDialog(Activity activity, String data, Action action) {
        Dialog dialog = new Dialog(activity, R.style.Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.item_confirm);

        TextView content = dialog.findViewById(R.id.content);
        TextView yes = dialog.findViewById(R.id.yes);
        TextView no = dialog.findViewById(R.id.no);

        content.setText(data);
        yes.setText(R.string.yes);
        no.setText(R.string.no);

        yes.setOnClickListener(v -> {
            action.onConfirm();
            dialog.dismiss();
        });

        no.setOnClickListener(v -> dialog.dismiss());
        dialog.show();
    }


    public void showDialog(Activity activity, String data, Action action) {
        Dialog dialog = new Dialog(activity, R.style.Dialog);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setCancelable(true);
        dialog.setContentView(R.layout.item_single_choice);

        TextView content = dialog.findViewById(R.id.content);
        TextView ok = dialog.findViewById(R.id.ok);

        content.setText(data);
        ok.setText(R.string.ok);

        ok.setOnClickListener(v -> {
            action.onConfirm();
            dialog.dismiss();
        });

        dialog.show();
    }
}