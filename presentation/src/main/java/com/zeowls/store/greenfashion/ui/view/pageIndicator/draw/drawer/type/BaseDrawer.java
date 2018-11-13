package com.zeowls.store.greenfashion.ui.view.pageIndicator.draw.drawer.type;

import android.graphics.Paint;
import android.support.annotation.NonNull;
import com.zeowls.store.greenfashion.ui.view.pageIndicator.draw.data.Indicator;

class BaseDrawer {

    Paint paint;
    Indicator indicator;

    BaseDrawer(@NonNull Paint paint, @NonNull Indicator indicator) {
        this.paint = paint;
        this.indicator = indicator;
    }
}