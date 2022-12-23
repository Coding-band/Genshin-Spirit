package com.voc.genshin_helper.ui.MMXLVIII;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ArtifactsAdapter;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.IconCard;
import com.voc.genshin_helper.data.TCG;
import com.voc.genshin_helper.data.TCGAdapter;
import com.voc.genshin_helper.util.MyViewPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class TCG2048 {

    private ViewPager viewPager;
    private View rootView;
    private ArrayList<View> viewPager_List;
    TabLayout tcg_tablayout;
    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    public List<TCG> tcgList = new ArrayList<>();
    TCGAdapter mTCGAdapter;
    RecyclerView mTCGList;

    boolean firstSelect = false;
    int id = 0;
    long period = 0;
    int indicatorWidth;

    View viewPager0, viewPager1, viewPager2, viewPager3;
    View mIndicator;

    int[] tabTCGItemImageArray = new int[]{R.drawable.ic_2048_tcg_char,R.drawable.ic_2048_tcg_equip,R.drawable.ic_2048_tcg_support,R.drawable.ic_2048_tcg_event};
    int[] tabTCGItemImageSelectedArray = new int[]{R.drawable.ic_2048_tcg_char_selected,R.drawable.ic_2048_tcg_equip_selected,R.drawable.ic_2048_tcg_support_selected,R.drawable.ic_2048_tcg_event_selected};
    DisplayMetrics displayMetrics;

    public void setup(View rootView, Context context, Activity activity, SharedPreferences sharedPreferences){
        this.rootView = rootView;
        this.context = context;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();

        tcg_tablayout = rootView.findViewById(R.id.tcg_tablayout);
        mIndicator = rootView.findViewById(R.id.indicator);
        viewPager = rootView.findViewById(R.id.tcg_vp);

        final LayoutInflater mInflater = activity.getLayoutInflater().from(context);
        viewPager0 = mInflater.inflate(R.layout.fragment_tcg_char_2048, null,false);
        viewPager1 = mInflater.inflate(R.layout.fragment_tcg_equip_2048, null,false);
        viewPager2 = mInflater.inflate(R.layout.fragment_tcg_support_2048, null,false);
        viewPager3 = mInflater.inflate(R.layout.fragment_tcg_event_2048, null,false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(viewPager0);
        viewPager_List.add(viewPager1);
        viewPager_List.add(viewPager2);
        viewPager_List.add(viewPager3);
        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        for (int x = 0 ; x < 4 ; x++){
            View view1 = activity.getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);
            ico_img.setImageResource(tabTCGItemImageArray[x]);
            TextView ico_tv = view1.findViewById(R.id.name);
            ico_tv.setVisibility(View.GONE);
            displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int pix = displayMetrics.widthPixels;
            if (displayMetrics.heightPixels < displayMetrics.widthPixels){
                pix = displayMetrics.heightPixels;
            }

            ico_img.getLayoutParams().width = ico_img.getLayoutParams().height;
            tcg_tablayout.addTab(tcg_tablayout.newTab().setCustomView(view1).setId(x));
        }

        tcg_tablayout.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = tcg_tablayout.getWidth() / tcg_tablayout.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });

        tcg_tablayout.setTabIndicatorFullWidth(false);
        viewPager.setCurrentItem(0);
        tcg_tablayout.selectTab(tcg_tablayout.getTabAt(0));
        tcg_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(tabTCGItemImageSelectedArray[tab.getPosition()]);
                viewPager.setCurrentItem(tab.getPosition());

                if (!firstSelect || (System.currentTimeMillis() - period > 3000)){
                    id = tab.getId();
                    period = System.currentTimeMillis();
                    firstSelect = true;
                }else if(firstSelect && tab.getId() != id){
                    firstSelect = false;
                }

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(tabTCGItemImageArray[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                if (!firstSelect || (System.currentTimeMillis() - period > 3000)){
                    id = tab.getId();
                    period = System.currentTimeMillis();
                    firstSelect = true;
                }else if(firstSelect && tab.getId() != id){
                    firstSelect = false;
                }else if (firstSelect && (tab.getId() == id) && (System.currentTimeMillis() - period < 3000)){
                    RecyclerView.SmoothScroller smoothScroller = new LinearSmoothScroller(context) {
                        @Override protected int getVerticalSnapPreference() {
                            return LinearSmoothScroller.SNAP_TO_START;
                        }
                    };
                    smoothScroller.setTargetPosition(0);
                    switch (tab.getPosition()){
                        case 0 : ((ScrollView) viewPager0.findViewById(R.id.sc_root)).smoothScrollTo(0,0);break;
                        case 1 : ((RecyclerView) viewPager1.findViewById(R.id.main_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
                        case 2 : ((RecyclerView) viewPager2.findViewById(R.id.weapon_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
                        case 3 : ((RecyclerView) viewPager3.findViewById(R.id.artifact_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
                    }
                    firstSelect = false;
                }
            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
                FrameLayout.LayoutParams params = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                //Multiply positionOffset with indicatorWidth to get translation
                float translationOffset =  (positionOffset+i) * (indicatorWidth) ;
                params.leftMargin = (int) translationOffset;
                mIndicator.setLayoutParams(params);
            }

            @Override
            public void onPageSelected(int position) {
                tcg_tablayout.selectTab(tcg_tablayout.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    private void tcg_list_reload() {
        String name ,type,subType,diceType;
        int HP,recharge,diceCost;
        tcgList.clear();

        String json_base = LoadData("db/tcg/char_list.json");
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                type = object.getString("type");
                subType = object.getString("subType");
                diceType = object.getString("diceType");
                HP = object.getInt("HP");
                recharge = object.getInt("recharge");
                diceCost = object.getInt("diceCost");

                TCG tcg = new TCG();
                tcg.setName(name);
                tcg.setType(type);
                tcg.setRecharge(recharge);
                tcg.setSubType(subType);
                tcg.setDiceType(diceType);
                tcg.setDiceCost(diceCost);
                tcg.setHP(HP);
                tcgList.add(tcg);
            }
            mTCGAdapter.filterList(tcgList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public String LoadData(String inFile) {
        String tContents = "";
        try {
            File file = new File(context.getFilesDir()+"/"+inFile);
            InputStream stream = new FileInputStream(file);

            int size = stream.available();
            byte[] buffer = new byte[size];
            stream.read(buffer);
            stream.close();
            tContents = new String(buffer);
        } catch (IOException e) {
            // Handle exceptions here
        }

        return tContents;

    }
}
