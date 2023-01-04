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
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.TCG;
import com.voc.genshin_helper.data.TCGAdapter;
import com.voc.genshin_helper.data.Weapons;
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
    public ArrayList<TCG> charList = new ArrayList<>();
    public ArrayList<TCG> supportList = new ArrayList<>();
    public ArrayList<TCG> equipList = new ArrayList<>();
    public ArrayList<TCG> eventList = new ArrayList<>();
    public ArrayList<TCG> backsideList = new ArrayList<>();
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
    ImageView tcg_search, tcg_filter;

    boolean firstSelect = false;
    int id = 0;
    long period = 0;
    int indicatorWidth;
    final int itemNum = 4;

    boolean show_dice_pyro = false;
    boolean show_dice_hydro = false;
    boolean show_dice_anemo = false;
    boolean show_dice_electro = false;
    boolean show_dice_dendro = false;
    boolean show_dice_cryo = false;
    boolean show_dice_geo = false;
    boolean show_dice_spec = false;
    boolean show_dice_rand = false;

    boolean show_tcg_Mondstadt = false;
    boolean show_tcg_Liyue = false;
    boolean show_tcg_Inazuma = false;
    boolean show_tcg_Sumeru = false;
    boolean show_tcg_Fatui = false;
    boolean show_tcg_Monster = false;
    boolean show_tcg_Talent = false;
    boolean show_tcg_Weapon = false;
    boolean show_tcg_Artifact = false;
    boolean show_tcg_Environment = false;
    boolean show_tcg_Partner = false;
    boolean show_tcg_Tool = false;
    boolean show_tcg_ElementalResonance = false;
    boolean show_tcg_Special = false;
    boolean show_tcg_Food = false;
    boolean show_tcg_CardBack = false;

    View viewPager0, viewPager1, viewPager2, viewPager3, viewPager4;
    View mIndicator;

    int[] tabTCGItemImageArray = new int[]{R.drawable.ic_2048_tcg_char,R.drawable.ic_2048_tcg_equip,R.drawable.ic_2048_tcg_support,R.drawable.ic_2048_tcg_event,R.drawable.ic_2048_tcg_backside};
    int[] tabTCGItemImageSelectedArray = new int[]{R.drawable.ic_2048_tcg_char_selected,R.drawable.ic_2048_tcg_equip_selected,R.drawable.ic_2048_tcg_support_selected,R.drawable.ic_2048_tcg_event_selected,R.drawable.ic_2048_tcg_backside_selected};
    DisplayMetrics displayMetrics;
    ItemRss item_rss;

    public void setup(View rootView, Context context, Activity activity, SharedPreferences sharedPreferences){
        this.rootView = rootView;
        this.context = context;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
        item_rss = new ItemRss();

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

        tcg_search = rootView.findViewById(R.id.tcg_search);
        tcg_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout header_con = rootView.findViewById(R.id.header_con);
                View header_search = rootView.findViewById(R.id.header_search);
                EditText header_search_et = rootView.findViewById(R.id.header_search_et);
                Button menu_search_cancel = rootView.findViewById(R.id.menu_search_cancel);
                ImageView header_search_reset = rootView.findViewById(R.id.header_search_reset);

                header_con.animate()
                        .alpha(0.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                header_con.setVisibility(View.GONE);
                            }
                        });

                header_search.animate()
                        .alpha(1.0f)
                        .setDuration(300)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                header_search.setVisibility(View.VISIBLE);
                            }
                        });

                header_search_et.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {

                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {

                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                        ArrayList<TCG> tempList = charList;
                        TCGAdapter mTempAdapter = mCharAdapter;

                        switch (viewPager.getCurrentItem()){
                            case 0 : {tempList = charList; mTempAdapter = mCharAdapter;break;}
                            case 1 : {tempList = equipList; mTempAdapter = mEquipAdapter;break;}
                            case 2 : {tempList = supportList; mTempAdapter = mSupportAdapter;break;}
                            case 3 : {tempList = eventList; mTempAdapter = mEventAdapter;break;}
                            case 4 : {tempList = backsideList; mTempAdapter = mBackSideAdapter;break;}
                        }

                        if (header_search_et.getText() != null){
                            String request = header_search_et.getText().toString();
                            if (!request.equals("")){
                                ArrayList<TCG> filteredList = new ArrayList<>();
                                int x = 0;
                                for (TCG item : tempList) {
                                    String str = request.toLowerCase();
                                    if (item_rss.getTCGByName(item.getName(),context)[0].contains(str)||item_rss.getTCGByName(item.getName(),context)[0].toLowerCase().contains(str)||item_rss.getTCGByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)||item.getLocaleName().toLowerCase().contains(str)){ // EN -> ZH
                                        filteredList.add(item);
                                    }
                                    x = x +1;
                                }
                                mTempAdapter.filterList(filteredList);
                            }else{
                                mTempAdapter.filterList(tempList);
                            }
                        }else{
                            mTempAdapter.filterList(tempList);
                        }
                    }
                });

                menu_search_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        header_search_et.setText("");
                        header_search.animate()
                                .alpha(0.0f)
                                .setDuration(300)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        header_search.setVisibility(View.GONE);
                                    }
                                });
                        header_con.animate()
                                .alpha(1.0f)
                                .setDuration(300)
                                .setListener(new AnimatorListenerAdapter() {
                                    @Override
                                    public void onAnimationEnd(Animator animation) {
                                        super.onAnimationEnd(animation);
                                        header_con.setVisibility(View.VISIBLE);
                                    }
                                });
                    }
                });

                header_search_reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        header_search_et.setText("");
                    }
                });

            };
        });

        tcg_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_tcg_filter_2048, null);
                // Element
                TextView menu_elements_title_tv = view.findViewById(R.id.menu_elements_title_tv);
                LinearLayout menu_elements_ll = view.findViewById(R.id.menu_elements_ll);
                menu_elements_title_tv.setVisibility(View.GONE);
                menu_elements_ll.setVisibility(View.GONE);

                // Weapons
                ImageView ico_sword = view.findViewById(R.id.ico_sword);
                ImageView ico_claymore = view.findViewById(R.id.ico_claymore);
                ImageView ico_polearm = view.findViewById(R.id.ico_polearm);
                ImageView ico_bow = view.findViewById(R.id.ico_bow);
                ImageView ico_catalyst = view.findViewById(R.id.ico_catalyst);
                // Rarity
                CheckBox menu_rare4 = view.findViewById(R.id.menu_rare4);
                CheckBox menu_rare5 = view.findViewById(R.id.menu_rare5);
                RatingBar menu_rating = view.findViewById(R.id.menu_rating);

                menu_rare4.setVisibility(View.GONE);
                menu_rare5.setVisibility(View.GONE);
                menu_rating.setVisibility(View.VISIBLE);

                // Release
                CheckBox menu_release_0 = view.findViewById(R.id.menu_release_0);
                CheckBox menu_release_1 = view.findViewById(R.id.menu_release_1);

                // Role
                TextView menu_role_title_tv = view.findViewById(R.id.menu_role_title_tv);
                LinearLayout menu_role_ll = view.findViewById(R.id.menu_role_ll);
                menu_role_title_tv.setVisibility(View.GONE);
                menu_role_ll.setVisibility(View.GONE);

                // Function Buttons
                ImageView cancel = view.findViewById(R.id.menu_cancel);
                FrameLayout reset = view.findViewById(R.id.menu_reset);
                FrameLayout ok = view.findViewById(R.id.menu_ok);

                show_dice_pyro = sharedPreferences.getBoolean("show_dice_pyro",false);
                show_dice_hydro = sharedPreferences.getBoolean("show_dice_hydro",false);
                show_dice_anemo = sharedPreferences.getBoolean("show_dice_anemo",false);
                show_dice_electro = sharedPreferences.getBoolean("show_dice_electro",false);
                show_dice_dendro = sharedPreferences.getBoolean("show_dice_dendro",false);
                show_dice_cryo = sharedPreferences.getBoolean("show_dice_cryo",false);
                show_dice_geo = sharedPreferences.getBoolean("show_dice_geo",false);
                show_dice_spec = sharedPreferences.getBoolean("show_dice_spec",false);
                show_dice_rand = sharedPreferences.getBoolean("show_dice_rand",false);

                show_tcg_Mondstadt = sharedPreferences.getBoolean("show_tcg_Mondstadt",false);
                show_tcg_Liyue = sharedPreferences.getBoolean("show_tcg_Liyue",false);
                show_tcg_Inazuma = sharedPreferences.getBoolean("show_tcg_Inazuma",false);
                show_tcg_Sumeru = sharedPreferences.getBoolean("show_tcg_Sumeru",false);
                show_tcg_Fatui = sharedPreferences.getBoolean("show_tcg_Fatui",false);
                show_tcg_Monster = sharedPreferences.getBoolean("show_tcg_Monster",false);
                show_tcg_Talent = sharedPreferences.getBoolean("show_tcg_Talent",false);
                show_tcg_Weapon = sharedPreferences.getBoolean("show_tcg_Weapon",false);
                show_tcg_Artifact = sharedPreferences.getBoolean("show_tcg_Artifact",false);
                show_tcg_Environment = sharedPreferences.getBoolean("show_tcg_Environment",false);
                show_tcg_Partner = sharedPreferences.getBoolean("show_tcg_Partner",false);
                show_tcg_Tool = sharedPreferences.getBoolean("show_tcg_Tool",false);
                show_tcg_ElementalResonance = sharedPreferences.getBoolean("show_tcg_ElementalResonance",false);
                show_tcg_Special = sharedPreferences.getBoolean("show_tcg_Special",false);
                show_tcg_Food = sharedPreferences.getBoolean("show_tcg_Food",false);
                show_tcg_CardBack = sharedPreferences.getBoolean("show_tcg_CardBack",false);

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show_dice_pyro = false;
                        show_dice_hydro = false;
                        show_dice_anemo = false;
                        show_dice_electro = false;
                        show_dice_dendro = false;
                        show_dice_cryo = false;
                        show_dice_geo = false;
                        show_dice_spec = false;
                        show_dice_rand = false;

                        show_tcg_Mondstadt = false;
                        show_tcg_Liyue = false;
                        show_tcg_Inazuma = false;
                        show_tcg_Sumeru = false;
                        show_tcg_Fatui = false;
                        show_tcg_Monster = false;
                        show_tcg_Talent = false;
                        show_tcg_Weapon = false;
                        show_tcg_Artifact = false;
                        show_tcg_Environment = false;
                        show_tcg_Partner = false;
                        show_tcg_Tool = false;
                        show_tcg_ElementalResonance = false;
                        show_tcg_Special = false;
                        show_tcg_Food = false;
                        show_tcg_CardBack = false;

                        editor.putBoolean("show_dice_pyro",show_dice_pyro);
                        editor.putBoolean("show_dice_hydro",show_dice_hydro);
                        editor.putBoolean("show_dice_anemo",show_dice_anemo);
                        editor.putBoolean("show_dice_electro",show_dice_electro);
                        editor.putBoolean("show_dice_dendro",show_dice_dendro);
                        editor.putBoolean("show_dice_cryo",show_dice_cryo);
                        editor.putBoolean("show_dice_geo",show_dice_geo);
                        editor.putBoolean("show_dice_spec",show_dice_spec);
                        editor.putBoolean("show_dice_rand",show_dice_rand);
                        editor.putBoolean("show_tcg_Mondstadt",show_tcg_Mondstadt);
                        editor.putBoolean("show_tcg_Liyue",show_tcg_Liyue);
                        editor.putBoolean("show_tcg_Inazuma",show_tcg_Inazuma);
                        editor.putBoolean("show_tcg_Sumeru",show_tcg_Sumeru);
                        editor.putBoolean("show_tcg_Fatui",show_tcg_Fatui);
                        editor.putBoolean("show_tcg_Monster",show_tcg_Monster);
                        editor.putBoolean("show_tcg_Talent",show_tcg_Talent);
                        editor.putBoolean("show_tcg_Weapon",show_tcg_Weapon);
                        editor.putBoolean("show_tcg_Artifact",show_tcg_Artifact);
                        editor.putBoolean("show_tcg_Environment",show_tcg_Environment);
                        editor.putBoolean("show_tcg_Partner",show_tcg_Partner);
                        editor.putBoolean("show_tcg_Tool",show_tcg_Tool);
                        editor.putBoolean("show_tcg_ElementalResonance",show_tcg_ElementalResonance);
                        editor.putBoolean("show_tcg_Special",show_tcg_Special);
                        editor.putBoolean("show_tcg_Food",show_tcg_Food);
                        editor.putBoolean("show_tcg_CardBack",show_tcg_CardBack);

                        editor.apply();
                        dialog.dismiss();


                        ArrayList<TCG> tempList = charList;
                        TCGAdapter mTempAdapter = mCharAdapter;
                        switch (viewPager.getCurrentItem()){
                            case 0 : {tempList = charList; mTempAdapter = mCharAdapter;break;}
                            case 1 : {tempList = equipList; mTempAdapter = mEquipAdapter;break;}
                            case 2 : {tempList = supportList; mTempAdapter = mSupportAdapter;break;}
                            case 3 : {tempList = eventList; mTempAdapter = mEventAdapter;break;}
                            case 4 : {tempList = backsideList; mTempAdapter = mBackSideAdapter;break;}
                        }

                        mTempAdapter.filterList(tempList);

                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        filterTCGAlgothm();
                        dialog.dismiss();
                    }
                });

                dialog.setContentView(view);
                dialog.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                // 2O48 DESIGN
                dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
                dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

                DisplayMetrics displayMetrics = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                int height = displayMetrics.heightPixels;
                int width = displayMetrics.widthPixels;

                lp.width = width;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.gravity = Gravity.BOTTOM;
                dialogWindow.setAttributes(lp);
                dialog.show();
            }
        });


    }

    private void filterTCGAlgothm() {
        ArrayList<TCG> tempList = charList;
        TCGAdapter mTempAdapter = mCharAdapter;
        switch (viewPager.getCurrentItem()){
            case 0 : {tempList = charList; mTempAdapter = mCharAdapter;break;}
            case 1 : {tempList = equipList; mTempAdapter = mEquipAdapter;break;}
            case 2 : {tempList = supportList; mTempAdapter = mSupportAdapter;break;}
            case 3 : {tempList = eventList; mTempAdapter = mEventAdapter;break;}
            case 4 : {tempList = backsideList; mTempAdapter = mBackSideAdapter;break;}
        }

        ArrayList<TCG> filteredList = new ArrayList<>();
        for (TCG item : tempList) {
            // DEFAULT
            if((show_dice_pyro == false && show_dice_hydro == false && show_dice_anemo == false && show_dice_electro == false && show_dice_dendro == false && show_dice_cryo == false && show_dice_geo == false && show_dice_spec == false && show_dice_rand == false ) &&
                    (show_tcg_Mondstadt == false && show_tcg_Liyue == false && show_tcg_Inazuma == false && show_tcg_Sumeru == false && show_tcg_Fatui == false && show_tcg_Monster == false ) &&
                    (show_tcg_Talent == false && show_tcg_Weapon == false && show_tcg_Artifact == false) &&
                    (show_tcg_Environment == false && show_tcg_Partner == false && show_tcg_Tool == false) &&
                    (show_tcg_ElementalResonance == false && show_tcg_Special == false && show_tcg_Food == false) &&
                    (show_tcg_CardBack == false )) {
                filteredList.add(item);
            }else{
                boolean isAllTrue = true;


                if (isAllTrue == true){
                    filteredList.add(item);
                }
            }
        }
        mWeaponList.removeAllViews();
        mWeaponAdapter.filterList(filteredList);
        editor.putBoolean("show_pyro",show_pyro);
        editor.putBoolean("show_hydro",show_hydro);
        editor.putBoolean("show_anemo",show_anemo);
        editor.putBoolean("show_electro",show_electro);
        editor.putBoolean("show_dendor",show_dendor);
        editor.putBoolean("show_cryo",show_cryo);
        editor.putBoolean("show_geo",show_geo);
        editor.putBoolean("show_sword",show_sword);
        editor.putBoolean("show_claymore",show_claymore);
        editor.putBoolean("show_polearm",show_polearm);
        editor.putBoolean("show_bow",show_bow);
        editor.putBoolean("show_catalyst",show_catalyst);
        editor.putBoolean("show_rare1",show_rare1);
        editor.putBoolean("show_rare2",show_rare2);
        editor.putBoolean("show_rare3",show_rare3);
        editor.putBoolean("show_rare4",show_rare4);
        editor.putBoolean("show_rare5",show_rare5);
        editor.putBoolean("show_released",show_released);
        editor.putBoolean("show_unreleased",show_unreleased);
        editor.putBoolean("show_dps",show_dps);
        editor.putBoolean("show_sub_dps",show_sub_dps);
        editor.putBoolean("show_util",show_util);
        editor.apply();

        mTempAdapter.filterList(tempList);
    }


    private void setup_char() {
        if (context instanceof Desk2048){
            ((Desk2048) context).setCheckSpinner(0);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int itemWidth = (int) (320+displayMetrics.density*(20));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (width > itemWidth*5){
                mLayoutManager = new GridLayoutManager(context,  5);
            }else{
                mLayoutManager = new GridLayoutManager(context,  (int) (width/itemWidth));
            }
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }

        mCharList = viewPager0.findViewById(R.id.tcg_char_list);
        mCharAdapter = new TCGAdapter(context,charList,activity,sharedPreferences);
        mCharList.setLayoutManager(mLayoutManager);
        mCharList.setAdapter(mCharAdapter);
        mCharList.removeAllViewsInLayout();
    }
    private void setup_equip() {
        if (context instanceof Desk2048){
            ((Desk2048) context).setCheckSpinner(0);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int itemWidth = (int) (320+displayMetrics.density*(20));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (width > itemWidth*5){
                mLayoutManager = new GridLayoutManager(context,  5);
            }else{
                mLayoutManager = new GridLayoutManager(context,  (int) (width/itemWidth));
            }
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }

        mEquipList = viewPager1.findViewById(R.id.tcg_equip_list);
        mEquipAdapter = new TCGAdapter(context,equipList,activity,sharedPreferences);
        mEquipList.setLayoutManager(mLayoutManager);
        mEquipList.setAdapter(mEquipAdapter);
        mEquipList.removeAllViewsInLayout();
    }

    private void setup_support() {
        if (context instanceof Desk2048){
            ((Desk2048) context).setCheckSpinner(0);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int itemWidth = (int) (320+displayMetrics.density*(20));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (width > itemWidth*5){
                mLayoutManager = new GridLayoutManager(context,  5);
            }else{
                mLayoutManager = new GridLayoutManager(context,  (int) (width/itemWidth));
            }
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }
        mSupportList = viewPager2.findViewById(R.id.tcg_support_list);
        mSupportAdapter = new TCGAdapter(context,supportList,activity,sharedPreferences);
        mSupportList.setLayoutManager(mLayoutManager);
        mSupportList.setAdapter(mSupportAdapter);
        mSupportList.removeAllViewsInLayout();
    }

    private void setup_event() {
        if (context instanceof Desk2048){
            ((Desk2048) context).setCheckSpinner(0);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int itemWidth = (int) (320+displayMetrics.density*(20));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (width > itemWidth*5){
                mLayoutManager = new GridLayoutManager(context,  5);
            }else{
                mLayoutManager = new GridLayoutManager(context,  (int) (width/itemWidth));
            }
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }

        mEventList = viewPager3.findViewById(R.id.tcg_event_list);
        mEventAdapter = new TCGAdapter(context,eventList,activity,sharedPreferences);
        mEventList.setLayoutManager(mLayoutManager);
        mEventList.setAdapter(mEventAdapter);
        mEventList.removeAllViewsInLayout();
    }

    private void setup_backside() {
        if (context instanceof Desk2048){
            ((Desk2048) context).setCheckSpinner(0);
        }
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;
        int itemWidth = (int) (320+displayMetrics.density*(20));

        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 3);

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            if (width > itemWidth*5){
                mLayoutManager = new GridLayoutManager(context,  5);
            }else{
                mLayoutManager = new GridLayoutManager(context,  (int) (width/itemWidth));
            }
        }else{
            mLayoutManager = new GridLayoutManager(context,  3);
        }
        mBackSideList = viewPager4.findViewById(R.id.tcg_backside_list);
        mBackSideAdapter = new TCGAdapter(context,backsideList,activity,sharedPreferences);
        mBackSideList.setLayoutManager(mLayoutManager);
        mBackSideList.setAdapter(mBackSideAdapter);
        mBackSideList.removeAllViewsInLayout();
    }

    private void tcg_list_reload() {
        String name,fileName ,type,subType,diceType;
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
                fileName = object.getString("fileName");
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
                tcg.setFileName(fileName);
                tcg.setLocaleName(item_rss.getTCGByName(name,context)[1]);

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
