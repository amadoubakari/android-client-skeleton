package com.flys.notification.adapter;

import android.view.View;

import androidx.core.widget.NestedScrollView;

public class Tools {
    public static void nestedScrollTo(final NestedScrollView nested, final View targetView) {
        nested.post(() -> nested.scrollTo(500, targetView.getBottom()));
    }
}
