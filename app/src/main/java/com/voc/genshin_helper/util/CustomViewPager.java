package com.voc.genshin_helper.util;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/*
 * Package com.voc.genshin_helper.util.CustomViewPager was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

//https://stackoverflow.com/questions/7814017/is-it-possible-to-disable-scrolling-on-a-viewpager
public class CustomViewPager extends ViewPager {

    private boolean isPagingEnabled = false;

    public CustomViewPager(Context context) {
        super(context);
    }

    public CustomViewPager(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onTouchEvent(event);
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        return this.isPagingEnabled && super.onInterceptTouchEvent(event);
    }

    public void setPagingEnabled(boolean b) {
        this.isPagingEnabled = b;
    }
}