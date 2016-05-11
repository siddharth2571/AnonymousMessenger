package com.ac.anonymousmessenger.customfont;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

import com.ac.anonymousmessenger.R;


public class CustomFontUtils {

    public static final String ANDROID_SCHEMA = "http://schemas.android.com/apk/res/android";

    public static void applyCustomFont(TextView customFontTextView, Context context, AttributeSet attrs) {
        TypedArray attributeArray = context.obtainStyledAttributes(attrs, R.styleable.CustomFontTextView);

        String fontName = attributeArray.getString(R.styleable.CustomFontTextView_font);
        int textStyle = attrs.getAttributeIntValue(ANDROID_SCHEMA, "textStyle", 0);

        Typeface customFont = selectTypeface(context, fontName, textStyle);
        customFontTextView.setTypeface(customFont);
        attributeArray.recycle();

    }

    private static Typeface selectTypeface(Context context, String fontName, int textStyle) {
        if (fontName.contentEquals(context.getString(R.string.Variane))) {
            switch (textStyle) {
                case 1: // bold
                    return FontCache.getTypeface("variane.ttf", context);

                default:
                    return FontCache.getTypeface("variane.ttf", context);
            }
        }
        //if more than one font use in single application at that time add more else if
        else if (fontName.contentEquals(context.getString(R.string.Variane))) {
            switch (textStyle) {
                case 1: // bold
                    return FontCache.getTypeface("orkney_bold.otf", context);
                case 2: // italic
                    return FontCache.getTypeface("orkney_light.otf", context);
                case 0: // regular
                    return FontCache.getTypeface("orkney_medium.otf", context);
                default:
                    return FontCache.getTypeface("orkney_regular.otf", context);
            }
        } else {
            return null;
        }
    }

}

/*    switch (textStyle) {
        case 1: // bold
            return FontCache.getTypeface("Roboto-Black.ttf", context);
        case 2: // italic
            return FontCache.getTypeface("Roboto-Black.ttf", context);
        case 0: // regular
        default:
            return FontCache.getTypeface("Roboto-Black.ttf", context);
    }
} else if (fontName.contentEquals(context.getString(R.string.roboto_thin))) {
        switch (textStyle) {
        case 1: // bold
        return FontCache.getTypeface("Roboto-Thin.ttf", context);
        case 2: // italic
        return FontCache.getTypeface("Roboto-Thin.ttf", context);
        case 0: // regular
default:
        return FontCache.getTypeface("Roboto-Thin.ttf", context);
        }
        } else if (fontName.contentEquals(context.getString(R.string.roboto_regular))) {
        switch (textStyle) {
        case 1: // bold
        return FontCache.getTypeface("Roboto-Regular.ttf", context);
        case 2: // italic
        return FontCache.getTypeface("Roboto-Regular.ttf", context);
        case 0: // regular
default:
        return FontCache.getTypeface("Roboto-Regular.ttf", context);
        }
        } else {
        return null;
        }*/

