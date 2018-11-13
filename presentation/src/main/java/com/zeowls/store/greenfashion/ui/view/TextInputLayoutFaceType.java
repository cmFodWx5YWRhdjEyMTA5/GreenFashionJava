package com.zeowls.store.greenfashion.ui.view;

import android.content.Context;
import android.graphics.Typeface;
import android.support.design.widget.TextInputLayout;
import android.support.v4.content.res.ResourcesCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.widget.EditText;

import com.zeowls.store.greenfashion.R;

import java.lang.reflect.Field;

public class TextInputLayoutFaceType extends TextInputLayout {
    public TextInputLayoutFaceType(Context context) {
        super(context);
        initFont(context);
    }

    public TextInputLayoutFaceType(Context context, AttributeSet attrs) {
        super(context, attrs);
        initFont(context);
    }

    private void initFont(Context context) {
        final Typeface typeface = ResourcesCompat.getFont(context, R.font.font);

        EditText editText = getEditText();
        if (editText != null) {
            editText.setTypeface(typeface);
        }
        try {
            // Retrieve the CollapsingTextHelper Field
            final Field cthf = TextInputLayout.class.getDeclaredField("mCollapsingTextHelper");
            cthf.setAccessible(true);

            // Retrieve an instance of CollapsingTextHelper and its TextPaint
            final Object cth = cthf.get(this);
            final Field tpf = cth.getClass().getDeclaredField("mTextPaint");
            tpf.setAccessible(true);

            // Apply your Typeface to the CollapsingTextHelper TextPaint
            ((TextPaint) tpf.get(cth)).setTypeface(typeface);
        } catch (Exception ignored) {
            // Nothing to do
        }
    }
}