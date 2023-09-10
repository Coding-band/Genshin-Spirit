package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.content.Context;
import android.content.res.Resources;
import android.util.AttributeSet;
import android.util.TypedValue;
import android.widget.ImageView;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

//https://stackoverflow.com/questions/21888674/apply-fading-edges-to-imageview
public class FadingImageView extends androidx.appcompat.widget.AppCompatImageView {
    private FadeSide mFadeSide;

    private Context c;

    public enum FadeSide {
        RIGHT_SIDE, LEFT_SIDE
    }

    public FadingImageView(Context c, AttributeSet attrs, int defStyle) {
        super(c, attrs, defStyle);

        this.c = c;

        init();
    }

    public FadingImageView(Context c, AttributeSet attrs) {
        super(c, attrs);

        this.c = c;

        init();
    }

    public FadingImageView(Context c) {
        super(c);

        this.c = c;

        init();
    }

    private void init() {
        // Enable horizontal fading
        this.setHorizontalFadingEdgeEnabled(true);
        // Apply default fading length
        this.setEdgeLength(14);
        // Apply default side
        this.setFadeDirection(FadeSide.RIGHT_SIDE);
    }

    public void setFadeDirection(FadeSide side) {
        this.mFadeSide = side;
    }

    public void setEdgeLength(int length) {
        this.setFadingEdgeLength(getPixels(length));
    }

    @Override
    protected float getLeftFadingEdgeStrength() {
        return mFadeSide.equals(FadeSide.LEFT_SIDE) ? 1.0f : 0.0f;
    }

    @Override
    protected float getRightFadingEdgeStrength() {
        return mFadeSide.equals(FadeSide.RIGHT_SIDE) ? 1.0f : 0.0f;
    }

    @Override
    public boolean hasOverlappingRendering() {
        return true;
    }

    @Override
    public boolean onSetAlpha(int alpha) {
        return false;
    }

    private int getPixels(int dipValue) {
        Resources r = c.getResources();

        return (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                dipValue, r.getDisplayMetrics());
    }
}