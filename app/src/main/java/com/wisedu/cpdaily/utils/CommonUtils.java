package com.wisedu.cpdaily.utils;

import android.content.res.ColorStateList;
import android.graphics.drawable.Drawable;
import android.support.v4.graphics.drawable.DrawableCompat;
import android.widget.TextView;

/**
 * 通用工具类
 * Created by wjj on 2017/7/21 10:32.
 */

public class CommonUtils {
    public static Drawable tintDrawable(Drawable drawable, ColorStateList colors) {
        Drawable wrappedDrawable = DrawableCompat.wrap(drawable);
        DrawableCompat.setTintList(wrappedDrawable, colors);
        return wrappedDrawable;
    }

    public static void tintTextView(TextView textView, ColorStateList colors) {
        Drawable[] drawables = textView.getCompoundDrawables();
        if (drawables.length == 4) {
            if (drawables[0] != null || drawables[1] != null || drawables[2] != null ||
                    drawables[3] != null) {
                for (int i = 0; i < drawables.length; i++) {
                    if (drawables[i] != null) {
                        drawables[i] = CommonUtils.tintDrawable(drawables[i], colors);
                    }
                }
                textView.setCompoundDrawablesWithIntrinsicBounds(drawables[0], drawables[1],
                        drawables[2],
                        drawables[3]);
            }
        }
    }

}
