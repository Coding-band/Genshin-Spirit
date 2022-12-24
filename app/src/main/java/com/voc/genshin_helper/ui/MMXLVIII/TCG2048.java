package com.voc.genshin_helper.ui.MMXLVIII;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.ScrollView;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ArtifactsAdapter;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
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
    public List<TCG> charList = new ArrayList<>();
    public List<TCG> supportList = new ArrayList<>();
    public List<TCG> equipList = new ArrayList<>();
    public List<TCG> eventList = new ArrayList<>();
    public List<TCG> backsideList = new ArrayList<>();
    TCGAdapter mCharAdapter;
    TCGAdapter mSupportAdapter;
    TCGAdapter mEquipAdapter;
    TCGAdapter mEventAdapter;
    TCGAdapter mBackSideAdapter;
    RecyclerView mCharList;
    RecyclerView mSupportList;
    RecyclerView mEquipList;
    RecyclerView mEventList;
    RecyclerView mBackSideList;

    boolean firstSelect = false;
    int id = 0;
    long period = 0;
    int indicatorWidth;
    final int itemNum = 4;

    View viewPager0, viewPager1, viewPager2, viewPager3, viewPager4;
    View mIndicator;

    int[] tabTCGItemImageArray = new int[]{R.drawable.ic_2048_tcg_char,R.drawable.ic_2048_tcg_equip,R.drawable.ic_2048_tcg_support,R.drawable.ic_2048_tcg_event,R.drawable.ic_2048_tcg_backside};
    int[] tabTCGItemImageSelectedArray = new int[]{R.drawable.ic_2048_tcg_char_selected,R.drawable.ic_2048_tcg_equip_selected,R.drawable.ic_2048_tcg_support_selected,R.drawable.ic_2048_tcg_event_selected,R.drawable.ic_2048_tcg_backside_selected};
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
        viewPager4 = mInflater.inflate(R.layout.fragment_tcg_backside_2048, null,false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(viewPager0);
        viewPager_List.add(viewPager1);
        viewPager_List.add(viewPager2);
        viewPager_List.add(viewPager3);
        //viewPager_List.add(viewPager4);
        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        setup_char();
        setup_equip();
        setup_support();
        setup_event();
        setup_backside();
        tcg_list_reload();

        for (int x = 0 ; x < itemNum ; x++){
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

        tcg_tablayout.getLayoutParams().width = (int) (displayMetrics.density*(52*itemNum));

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

        View view1 = tcg_tablayout.getTabAt(0).getCustomView();
        ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
        tab_icon.setImageResource(tabTCGItemImageSelectedArray[0]);
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
                        case 0 : ((RecyclerView) viewPager0.findViewById(R.id.tcg_char_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
                        case 1 : ((RecyclerView) viewPager1.findViewById(R.id.tcg_equip_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
                        case 2 : ((RecyclerView) viewPager2.findViewById(R.id.tcg_support_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
                        case 3 : ((RecyclerView) viewPager3.findViewById(R.id.tcg_event_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
                        case 4 : ((RecyclerView) viewPager4.findViewById(R.id.tcg_backside_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
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


    private void setup_char() {
        if (context instanceof Desk2048){
            ((Desk2048) context).setCheckSpinner(0);
        }

        mCharList = viewPager0.findViewById(R.id.tcg_char_list);
        mCharAdapter = new TCGAdapter(context,charList,activity,sharedPreferences);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            int img_width = 320;
            int width_a = (int) (width - displayMetrics.density*(4+4));
            if ((width_a/img_width - (int)(width_a/img_width)) > 0){
                img_width = img_width + ((width_a/img_width - (int)(width_a/img_width)) / (int)(width_a/img_width));
            }

            System.out.println("width_a : "+width_a);
            System.out.println("img_width : "+img_width);
            mLayoutManager = new GridLayoutManager(context,  (int) (width_a/(img_width + displayMetrics.density*(12+4))));
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }

        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mCharList.setLayoutManager(mLayoutManager);
        mCharList.setLayoutParams(paramsMsg);
        mCharList.setAdapter(mCharAdapter);
        mCharList.removeAllViewsInLayout();
    }
    private void setup_equip() {
        if (context instanceof Desk2048){
            ((Desk2048) context).setCheckSpinner(0);
        }

        mEquipList = viewPager1.findViewById(R.id.tcg_equip_list);
        mEquipAdapter = new TCGAdapter(context,equipList,activity,sharedPreferences);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            int img_width = 320;
            int width_a = (int) (width - displayMetrics.density*(4+4));
            if ((width_a/img_width - (int)(width_a/img_width)) > 0){
                img_width = img_width + ((width_a/img_width - (int)(width_a/img_width)) / (int)(width_a/img_width));
            }

            System.out.println("width_a : "+width_a);
            System.out.println("img_width : "+img_width);
            mLayoutManager = new GridLayoutManager(context,  (int) (width_a/(img_width + displayMetrics.density*(12+4))));
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }

        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mEquipList.setLayoutManager(mLayoutManager);
        mEquipList.setLayoutParams(paramsMsg);
        mEquipList.setAdapter(mEquipAdapter);
        mEquipList.removeAllViewsInLayout();
    }

    private void setup_support() {
        if (context instanceof Desk2048){
            ((Desk2048) context).setCheckSpinner(0);
        }

        mSupportList = viewPager2.findViewById(R.id.tcg_support_list);
        mSupportAdapter = new TCGAdapter(context,supportList,activity,sharedPreferences);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            int img_width = 320;
            int width_a = (int) (width - displayMetrics.density*(4+4));
            if ((width_a/img_width - (int)(width_a/img_width)) > 0){
                img_width = img_width + ((width_a/img_width - (int)(width_a/img_width)) / (int)(width_a/img_width));
            }

            System.out.println("width_a : "+width_a);
            System.out.println("img_width : "+img_width);
            mLayoutManager = new GridLayoutManager(context,  (int) (width_a/(img_width + displayMetrics.density*(12+4))));
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }

        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mSupportList.setLayoutManager(mLayoutManager);
        mSupportList.setLayoutParams(paramsMsg);
        mSupportList.setAdapter(mSupportAdapter);
        mSupportList.removeAllViewsInLayout();
    }

    private void setup_event() {
        if (context instanceof Desk2048){
            ((Desk2048) context).setCheckSpinner(0);
        }

        mEventList = viewPager3.findViewById(R.id.tcg_event_list);
        mEventAdapter = new TCGAdapter(context,eventList,activity,sharedPreferences);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            int img_width = 320;
            int width_a = (int) (width - displayMetrics.density*(4+4));
            if ((width_a/img_width - (int)(width_a/img_width)) > 0){
                img_width = img_width + ((width_a/img_width - (int)(width_a/img_width)) / (int)(width_a/img_width));
            }

            System.out.println("width_a : "+width_a);
            System.out.println("img_width : "+img_width);
            mLayoutManager = new GridLayoutManager(context,  (int) (width_a/(img_width + displayMetrics.density*(12+4))));
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }

        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mEventList.setLayoutManager(mLayoutManager);
        mEventList.setLayoutParams(paramsMsg);
        mEventList.setAdapter(mEventAdapter);
        mEventList.removeAllViewsInLayout();
    }

    private void setup_backside() {
        if (context instanceof Desk2048){
            ((Desk2048) context).setCheckSpinner(0);
        }

        mBackSideList = viewPager4.findViewById(R.id.tcg_backside_list);
        mBackSideAdapter = new TCGAdapter(context,backsideList,activity,sharedPreferences);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            int img_width = 320;
            int width_a = (int) (width - displayMetrics.density*(4+4));
            if ((width_a/img_width - (int)(width_a/img_width)) > 0){
                img_width = img_width + ((width_a/img_width - (int)(width_a/img_width)) / (int)(width_a/img_width));
            }

            System.out.println("width_a : "+width_a);
            System.out.println("img_width : "+img_width);
            mLayoutManager = new GridLayoutManager(context,  (int) (width_a/(img_width + displayMetrics.density*(12+4))));
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }

        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mBackSideList.setLayoutManager(mLayoutManager);
        mBackSideList.setLayoutParams(paramsMsg);
        mBackSideList.setAdapter(mBackSideAdapter);
        mBackSideList.removeAllViewsInLayout();
    }

    private void tcg_list_reload() {
        String name ,type,subType,diceType;
        int HP,recharge,diceCost;
        charList.clear();
        supportList.clear();
        equipList.clear();
        eventList.clear();
        backsideList.clear();

        String json_base = LoadData("db/tcg/tcg_list.json");
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

                switch (type){
                    case TCG.CHAR:charList.add(tcg);break;
                    case TCG.EQUIP:equipList.add(tcg);break;
                    case TCG.EVENT:eventList.add(tcg);break;
                    case TCG.SUPPORT:supportList.add(tcg);break;
                    case TCG.BACKSIDE:backsideList.add(tcg);break;
                }
            }
            mCharAdapter.filterList(charList);
            mSupportAdapter.filterList(supportList);
            mEquipAdapter.filterList(equipList);
            mEventAdapter.filterList(eventList);
            mBackSideAdapter.filterList(backsideList);
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
