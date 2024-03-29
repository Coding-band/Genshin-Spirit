package com.voc.genshin_helper.util;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.view.View;
import android.view.ViewGroup;

import androidx.viewpager.widget.PagerAdapter;

import java.util.List;
/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class MyViewPagerAdapter extends PagerAdapter
{
    private List<View> mListViews;

    public MyViewPagerAdapter(List<View> mListViews)
    {
        this.mListViews = mListViews;
    }

    @Override
    public void destroyItem(ViewGroup container, int position, Object object)
    {
        container.removeView((View) object);
    }

    @Override
    public Object instantiateItem(ViewGroup container, int position)
    {
        View view = mListViews.get(position);
        container.addView(view);
        return view;
    }

    @Override
    public int getCount()
    {
        return  mListViews.size();
    }

    @Override
    public boolean isViewFromObject(View arg0, Object arg1)
    {
        return arg0 == arg1;
    }

}