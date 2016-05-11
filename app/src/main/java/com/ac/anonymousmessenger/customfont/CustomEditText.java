package com.ac.anonymousmessenger.customfont;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.EditText;

public class CustomEditText extends EditText {

    public CustomEditText(Context context) {
        super(context);
        CustomFontUtils.applyCustomFont(this, context, null);
    }

    public CustomEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
        CustomFontUtils.applyCustomFont(this, context, attrs);
    }

    public CustomEditText(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        CustomFontUtils.applyCustomFont(this, context, attrs);
    }
}
