package com.zeowls.store.greenfashion.ui.view;

import android.content.Context;
import android.support.v7.widget.AppCompatEditText;
import android.util.AttributeSet;
import android.view.KeyEvent;

public class ClearFocusEditText extends AppCompatEditText {
    public ClearFocusEditText(Context context) {
        super(context);
    }

    public ClearFocusEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public ClearFocusEditText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean dispatchKeyEvent(KeyEvent event) {
        return super.dispatchKeyEvent(event);
    }



    @Override
    public boolean dispatchKeyEventPreIme(KeyEvent event) {
        if(event.getKeyCode() == KeyEvent.KEYCODE_BACK){
            clearFocus();
        }
        return super.dispatchKeyEventPreIme(event);
    }
}
