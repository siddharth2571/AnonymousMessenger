package com.ac.anonymousmessenger.customfont;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.TextView;


public class CustomeTextView extends TextView {

    public CustomeTextView(Context context) {
        super(context);
        CustomFontUtils.applyCustomFont(this, context, null);
    }

    public CustomeTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontUtils.applyCustomFont(this, context, attrs);
    }

    public CustomeTextView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontUtils.applyCustomFont(this, context, attrs);
    }
}
