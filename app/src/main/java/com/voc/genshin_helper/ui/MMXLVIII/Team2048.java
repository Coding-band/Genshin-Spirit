package com.voc.genshin_helper.ui.MMXLVIII;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
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
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.Artifacts;
import com.voc.genshin_helper.data.ArtifactsAdapter;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.TCG;
import com.voc.genshin_helper.data.TCGAdapter;
import com.voc.genshin_helper.data.Weapons;
import com.voc.genshin_helper.data.WeaponsAdapter;
import com.voc.genshin_helper.util.MyViewPagerAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class Team2048 {
    View rootView;
    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    ItemRss item_rss;

    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;

    TabLayout team_tablayout;
    View viewPager0, viewPager1, viewPager2;
    View mIndicator;

    RecyclerView mCharList, mWeaponList, mArtifactList;
    CharactersAdapter mCharAdapter;
    WeaponsAdapter mWeaponsAdapter;
    ArtifactsAdapter mArtifactAdapter;
    ArrayList<Characters> charactersList = new ArrayList<>();
    ArrayList<Weapons> weaponsList = new ArrayList<>();
    ArrayList<Artifacts> artifactsList = new ArrayList<>();

    public boolean show_pyro = true;
    public boolean show_hydro = true;
    public boolean show_anemo = true;
    public boolean show_dendor = true;
    public boolean show_electro = true;
    public boolean show_cryo = true;
    public boolean show_geo = true;

    public boolean show_sword = true;
    public boolean show_claymore = true;
    public boolean show_polearm = true;
    public boolean show_bow = true;
    public boolean show_catalyst = true;

    public boolean show_rare1 = false;
    public boolean show_rare2 = false;
    public boolean show_rare3 = false;
    public boolean show_rare4 = false;
    public boolean show_rare5 = false;
    public boolean show_released = false;
    public boolean show_unreleased = false;
    public boolean show_dps = false;
    public boolean show_sub_dps = false;
    public boolean show_util = false;

    int[] tabteamItemImageArray = new int[]{R.drawable.ic_2048_team_char,R.drawable.ic_2048_team_weapon,R.drawable.ic_2048_team_art};
    int[] tabteamItemImageSelectedArray = new int[]{R.drawable.ic_2048_team_char_selected,R.drawable.ic_2048_team_weapon_selected,R.drawable.ic_2048_team_art_selected};

    final int itemNum = 3;
    DisplayMetrics displayMetrics;
    long period = 0;
    int indicatorWidth;
    ImageView team_search, team_filter;

    boolean firstSelect = false;
    int id = 0;

    public void setup(View rootView, Context context, Activity activity, SharedPreferences sharedPreferences) {
        this.rootView = rootView;
        this.context = context;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
        editor = sharedPreferences.edit();
        item_rss = new ItemRss();

        team_tablayout = rootView.findViewById(R.id.team_tablayout);
        mIndicator = rootView.findViewById(R.id.indicator);
        viewPager = rootView.findViewById(R.id.tcg_vp);

        final LayoutInflater mInflater = activity.getLayoutInflater().from(context);
        viewPager0 = mInflater.inflate(R.layout.fragment_char_2048, null,false);
        viewPager1 = mInflater.inflate(R.layout.fragment_weapon_2048, null,false);
        viewPager2 = mInflater.inflate(R.layout.fragment_art_2048, null,false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(viewPager0);
        viewPager_List.add(viewPager1);
        viewPager_List.add(viewPager2);

        viewPager = rootView.findViewById(R.id.team_vp);
        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        viewPager0.findViewById(R.id.tut_char_card).setVisibility(View.GONE);
        viewPager1.findViewById(R.id.tut_char_card).setVisibility(View.GONE);
        viewPager2.findViewById(R.id.tut_char_card).setVisibility(View.GONE);

        setup_char();
        setup_weapon();
        setup_art();

        team_tablayout.removeAllTabs();

        for (int x = 0 ; x < itemNum ; x++){
            View view1 = activity.getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);
            ico_img.setImageResource(tabteamItemImageArray[x]);
            TextView ico_tv = view1.findViewById(R.id.name);
            ico_tv.setVisibility(View.GONE);
            displayMetrics = new DisplayMetrics();
            activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
            int pix = displayMetrics.widthPixels;
            if (displayMetrics.heightPixels < displayMetrics.widthPixels){
                pix = displayMetrics.heightPixels;
            }

            ico_img.setPadding((int) (-displayMetrics.density*6),(int) (-displayMetrics.density*6),(int) (-displayMetrics.density*6),(int) (-displayMetrics.density*6));

            ico_img.getLayoutParams().width = ico_img.getLayoutParams().height;
            team_tablayout.addTab(team_tablayout.newTab().setCustomView(view1).setId(x));
        }

        team_tablayout.getLayoutParams().width = (int) (displayMetrics.density*(52*itemNum));

        team_tablayout.post(new Runnable() {
            @Override
            public void run() {
                indicatorWidth = team_tablayout.getWidth() / team_tablayout.getTabCount();

                //Assign new width
                FrameLayout.LayoutParams indicatorParams = (FrameLayout.LayoutParams) mIndicator.getLayoutParams();
                indicatorParams.width = indicatorWidth;
                mIndicator.setLayoutParams(indicatorParams);
            }
        });

        team_tablayout.setTabIndicatorFullWidth(false);
        viewPager.setCurrentItem(0);
        team_tablayout.selectTab(team_tablayout.getTabAt(0));

        View view1 = team_tablayout.getTabAt(0).getCustomView();
        ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
        tab_icon.setImageResource(tabteamItemImageSelectedArray[0]);
        team_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(tabteamItemImageSelectedArray[tab.getPosition()]);
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
                tab_icon.setImageResource(tabteamItemImageArray[tab.getPosition()]);
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
                        case 0 : ((RecyclerView) viewPager0.findViewById(R.id.main_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
                        case 1 : ((RecyclerView) viewPager1.findViewById(R.id.weapon_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
                        case 2 : ((RecyclerView) viewPager2.findViewById(R.id.artifact_list)).getLayoutManager().startSmoothScroll(smoothScroller);break;
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
                team_tablayout.selectTab(team_tablayout.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        team_search = rootView.findViewById(R.id.team_searchX);
        team_filter = rootView.findViewById(R.id.team_filter);

        team_search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ConstraintLayout header_con = rootView.findViewById(R.id.header_conX);
                View header_search = rootView.findViewById(R.id.header_searchX);
                EditText header_search_et = rootView.findViewById(R.id.header_search_etX);
                Button menu_search_cancel = rootView.findViewById(R.id.menu_search_cancelX);
                ImageView header_search_reset = rootView.findViewById(R.id.header_search_resetX);

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
                        switch (viewPager.getCurrentItem()){
                            case 0:{
                                if (header_search_et.getText() != null){
                                    String request = header_search_et.getText().toString();
                                    if (!request.equals("")){
                                        ArrayList<Characters> filteredList = new ArrayList<>();
                                        int x = 0;
                                        for (Characters item : charactersList) {
                                            String str = request.toLowerCase();
                                            if (item_rss.getCharByName(item.getName(),context)[1].contains(str)||item_rss.getCharByName(item.getName(),context)[1].toLowerCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                                filteredList.add(item);
                                            }
                                            x = x +1;
                                        }
                                        mCharAdapter.filterList(filteredList);
                                    }else{
                                        mCharAdapter.filterList(charactersList);
                                    }
                                }else{
                                    mCharAdapter.filterList(charactersList);
                                }
                                break;
                            }
                            case 1:{
                                if (header_search_et.getText() != null){
                                    String request = header_search_et.getText().toString();
                                    if (!request.equals("")){
                                        ArrayList<Weapons> filteredList = new ArrayList<>();
                                        int x = 0;
                                        for (Weapons item : weaponsList) {
                                            String str = request.toLowerCase();
                                            if (item_rss.getWeaponByName(item.getName(),context)[0].contains(str)||item_rss.getWeaponByName(item.getName(),context)[0].toLowerCase().contains(str)||item_rss.getWeaponByName(item.getName(),context)[0].toUpperCase().contains(str)||item.getName().toLowerCase().contains(str)){
                                                filteredList.add(item);
                                            }
                                            x = x +1;
                                        }
                                        mWeaponsAdapter.filterList(filteredList);
                                    }else{
                                        mWeaponsAdapter.filterList(weaponsList);
                                    }
                                }else{
                                    mWeaponsAdapter.filterList(weaponsList);
                                }
                                break;
                            }
                            case 2:{
                                if (header_search_et.getText() != null){
                                    String request = header_search_et.getText().toString();
                                    if (!request.equals("")){
                                        ArrayList<Artifacts> filteredList = new ArrayList<>();
                                        int x = 0;
                                        for (Artifacts item : artifactsList) {
                                            String str = request.toLowerCase();
                                            if (item_rss.getCharByName(item.getName(),context)[1].contains(str)||item_rss.getCharByName(item.getName(),context)[1].toLowerCase().contains(str)||item.getName().toLowerCase().contains(str)){ // EN -> ZH
                                                filteredList.add(item);
                                            }
                                            x = x +1;
                                        }
                                        mArtifactAdapter.filterList(filteredList);
                                    }else{
                                        mArtifactAdapter.filterList(artifactsList);
                                    }
                                }else{
                                    mArtifactAdapter.filterList(artifactsList);
                                }
                                break;
                            }
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

        team_filter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_char_filter_2048, null);
                // Elements
                ImageView pyro = view.findViewById(R.id.pyro_ico);
                ImageView hydro = view.findViewById(R.id.hydro_ico);
                ImageView anemo = view.findViewById(R.id.anemo_ico);
                ImageView electro = view.findViewById(R.id.electro_ico);
                ImageView dendor = view.findViewById(R.id.dendor_ico);
                ImageView cryo = view.findViewById(R.id.cryo_ico);
                ImageView geo = view.findViewById(R.id.geo_ico);
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
                menu_rating.setVisibility(View.GONE);
                // Release
                CheckBox menu_release_0 = view.findViewById(R.id.menu_release_0);
                CheckBox menu_release_1 = view.findViewById(R.id.menu_release_1);
                // Role
                CheckBox menu_role_dps = view.findViewById(R.id.menu_role_dps);
                CheckBox menu_role_sub_dps = view.findViewById(R.id.menu_role_sub_dps);
                CheckBox menu_role_utility = view.findViewById(R.id.menu_role_utility);
                // Function Buttons
                ImageView cancel = view.findViewById(R.id.menu_cancel);
                FrameLayout reset = view.findViewById(R.id.menu_reset);
                FrameLayout ok = view.findViewById(R.id.menu_ok);

                TextView menu_elements_title_tv = view.findViewById(R.id.menu_elements_title_tv);
                LinearLayout menu_elements_ll = view.findViewById(R.id.menu_elements_ll);
                TextView menu_role_title_tv = view.findViewById(R.id.menu_role_title_tv);
                LinearLayout menu_role_ll = view.findViewById(R.id.menu_role_ll);
                TextView menu_weapons_title_tv = view.findViewById(R.id.menu_weapons_title_tv);
                LinearLayout menu_weapons_ll = view.findViewById(R.id.menu_weapons_ll);

                switch (viewPager.getCurrentItem()){
                    case 1 : {
                        menu_elements_title_tv.setVisibility(View.GONE);
                        menu_elements_ll.setVisibility(View.GONE);
                        menu_rare4.setVisibility(View.GONE);
                        menu_rare5.setVisibility(View.GONE);
                        menu_role_title_tv.setVisibility(View.GONE);
                        menu_role_ll.setVisibility(View.GONE);
                        menu_rating.setVisibility(View.VISIBLE);
                        break;
                    }
                    case 2 : {
                        menu_elements_title_tv.setVisibility(View.GONE);
                        menu_elements_ll.setVisibility(View.GONE);
                        menu_rare4.setVisibility(View.GONE);
                        menu_rare5.setVisibility(View.GONE);
                        menu_role_title_tv.setVisibility(View.GONE);
                        menu_role_ll.setVisibility(View.GONE);
                        menu_rating.setVisibility(View.VISIBLE);
                        menu_weapons_title_tv.setVisibility(View.GONE);
                        menu_weapons_ll.setVisibility(View.GONE);
                        break;
                    }
                }

                show_pyro = sharedPreferences.getBoolean("show_pyro",false);
                show_hydro = sharedPreferences.getBoolean("show_hydro",false);
                show_anemo = sharedPreferences.getBoolean("show_anemo",false);
                show_electro = sharedPreferences.getBoolean("show_electro",false);
                show_dendor = sharedPreferences.getBoolean("show_dendor",false);
                show_cryo = sharedPreferences.getBoolean("show_cryo",false);
                show_geo = sharedPreferences.getBoolean("show_geo",false);
                show_sword = sharedPreferences.getBoolean("show_sword",false);
                show_claymore = sharedPreferences.getBoolean("show_claymore",false);
                show_polearm = sharedPreferences.getBoolean("show_polearm",false);
                show_bow = sharedPreferences.getBoolean("show_bow",false);
                show_catalyst = sharedPreferences.getBoolean("show_catalyst",false);
                show_rare4 = sharedPreferences.getBoolean("show_rare4",false);
                show_rare5 = sharedPreferences.getBoolean("show_rare5",false);
                show_released = sharedPreferences.getBoolean("show_released",false);
                show_unreleased = sharedPreferences.getBoolean("show_unreleased",false);
                show_dps = sharedPreferences.getBoolean("show_dps",false);
                show_sub_dps = sharedPreferences.getBoolean("show_sub_dps",false);
                show_util = sharedPreferences.getBoolean("show_util",false);

                if(show_pyro){show_pyro = true;pyro.setColorFilter(Color.parseColor("#00000000"));}else{show_pyro = false;pyro.setColorFilter(Color.parseColor("#66313131"));}
                if(show_hydro){show_hydro = true;hydro.setColorFilter(Color.parseColor("#00000000"));}else{show_hydro = false;hydro.setColorFilter(Color.parseColor("#66313131"));}
                if(show_anemo){show_anemo = true;anemo.setColorFilter(Color.parseColor("#00000000"));}else{show_anemo = false;anemo.setColorFilter(Color.parseColor("#66313131"));}
                if(show_electro){show_electro = true;electro.setColorFilter(Color.parseColor("#00000000"));}else{show_electro = false;electro.setColorFilter(Color.parseColor("#66313131"));}
                if(show_dendor){show_dendor = true;dendor.setColorFilter(Color.parseColor("#00000000"));}else{show_dendor = false;dendor.setColorFilter(Color.parseColor("#66313131"));}
                if(show_cryo){show_cryo = true;cryo.setColorFilter(Color.parseColor("#00000000"));}else{show_cryo = false;cryo.setColorFilter(Color.parseColor("#66313131"));}
                if(show_geo){show_geo = true;geo.setColorFilter(Color.parseColor("#00000000"));}else{show_geo = false;geo.setColorFilter(Color.parseColor("#66313131"));}
                if(show_sword){show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}else{show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}
                if(show_claymore){show_claymore = true;ico_claymore.setColorFilter(Color.parseColor("#00000000"));}else{show_claymore = false;ico_claymore.setColorFilter(Color.parseColor("#66313131"));}
                if(show_polearm){show_polearm = true;ico_polearm.setColorFilter(Color.parseColor("#00000000"));}else{show_polearm = false;ico_polearm.setColorFilter(Color.parseColor("#66313131"));}
                if(show_bow){show_bow = true;ico_bow.setColorFilter(Color.parseColor("#00000000"));}else{show_bow = false;ico_bow.setColorFilter(Color.parseColor("#66313131"));}
                if(show_catalyst){show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}else{show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}

                if (show_rare4){ menu_rare4.setChecked(true); }
                if (show_rare5){ menu_rare5.setChecked(true); }
                if (show_released){ menu_release_0.setChecked(true); }
                if (show_unreleased){ menu_release_1.setChecked(true); }
                if (show_dps){ menu_role_dps.setChecked(true); }
                if (show_sub_dps){ menu_role_sub_dps.setChecked(true); }
                if (show_util){ menu_role_utility.setChecked(true); }

                pyro.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_pyro){show_pyro = false;pyro.setColorFilter(Color.parseColor("#66313131"));}else{show_pyro = true;pyro.setColorFilter(Color.parseColor("#00000000"));}}});
                hydro.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_hydro){show_hydro = false;hydro.setColorFilter(Color.parseColor("#66313131"));}else{show_hydro = true;hydro.setColorFilter(Color.parseColor("#00000000"));}}});
                anemo.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_anemo){show_anemo = false;anemo.setColorFilter(Color.parseColor("#66313131"));}else{show_anemo = true;anemo.setColorFilter(Color.parseColor("#00000000"));}}});
                electro.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_electro){show_electro = false;electro.setColorFilter(Color.parseColor("#66313131"));}else{show_electro = true;electro.setColorFilter(Color.parseColor("#00000000"));}}});
                dendor.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_dendor){show_dendor = false;dendor.setColorFilter(Color.parseColor("#66313131"));}else{show_dendor = true;dendor.setColorFilter(Color.parseColor("#00000000"));}}});
                cryo.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_cryo){show_cryo = false;cryo.setColorFilter(Color.parseColor("#66313131"));}else{show_cryo = true;cryo.setColorFilter(Color.parseColor("#00000000"));}}});
                geo.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_geo){show_geo = false;geo.setColorFilter(Color.parseColor("#66313131"));}else{show_geo = true;geo.setColorFilter(Color.parseColor("#00000000"));}}});
                ico_sword.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_sword){show_sword = false;ico_sword.setColorFilter(Color.parseColor("#66313131"));}else{show_sword = true;ico_sword.setColorFilter(Color.parseColor("#00000000"));}}});
                ico_claymore.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_claymore){show_claymore = false;ico_claymore.setColorFilter(Color.parseColor("#66313131"));}else{show_claymore = true;ico_claymore.setColorFilter(Color.parseColor("#00000000"));}}});
                ico_polearm.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_polearm){show_polearm = false;ico_polearm.setColorFilter(Color.parseColor("#66313131"));}else{show_polearm = true;ico_polearm.setColorFilter(Color.parseColor("#00000000"));}}});
                ico_bow.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_bow){show_bow = false;ico_bow.setColorFilter(Color.parseColor("#66313131"));}else{show_bow = true;ico_bow.setColorFilter(Color.parseColor("#00000000"));}}});
                ico_catalyst.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_catalyst){show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}else{show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}}});
                ico_catalyst.setOnClickListener(new View.OnClickListener() { @Override public void onClick(View v) { if(show_catalyst){show_catalyst = false;ico_catalyst.setColorFilter(Color.parseColor("#66313131"));}else{show_catalyst = true;ico_catalyst.setColorFilter(Color.parseColor("#00000000"));}}});

                cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        show_pyro = false;
                        show_hydro = false;
                        show_anemo = false;
                        show_dendor = false;
                        show_electro = false;
                        show_cryo = false;
                        show_geo = false;

                        show_sword = false;
                        show_claymore = false;
                        show_polearm = false;
                        show_bow = false;
                        show_catalyst = false;

                        show_rare1 = false;
                        show_rare2 = false;
                        show_rare3 = false;
                        show_rare4 = false;
                        show_rare5 = false;
                        show_released = false;
                        show_unreleased = false;
                        show_dps = false;
                        show_sub_dps = false;
                        show_util = false;

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
                        dialog.dismiss();

                        switch (viewPager.getCurrentItem()){
                            case 0 : mCharAdapter.filterList(charactersList);break;
                            case 1 : mWeaponsAdapter.filterList(weaponsList);break;
                            case 2 : mArtifactAdapter.filterList(artifactsList);break;
                        }

                    }
                });

                ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (menu_rare4.isChecked()){show_rare4 = true;}else{show_rare4 = false;}
                        if (menu_rare5.isChecked()){show_rare5 = true;}else{show_rare5 = false;}
                        if (menu_release_0.isChecked()){show_released = true;}else{show_released = false;}
                        if (menu_release_1.isChecked()){show_unreleased = true;}else{show_unreleased = false;}
                        if (menu_role_dps.isChecked()){show_dps = true;}else{show_dps = false;}
                        if (menu_role_sub_dps.isChecked()){show_sub_dps = true;}else{show_sub_dps = false;}
                        if (menu_role_utility.isChecked()){show_util = true;}else{show_util = false;}
                        filterCharAlgothm();

                        switch (viewPager.getCurrentItem()){
                            case 0 : filterCharAlgothm();break;
                            case 1 : filterWeaponAlgothm((int) menu_rating.getRating());break;
                            case 2 : filterArtifactAlgothm((int) menu_rating.getRating());break;
                        }

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


    public RecyclerView getCurrList(){
        switch (viewPager.getCurrentItem()){
            case 0 : return mCharList;
            case 1 : return mWeaponList;
            case 2 : return mArtifactList;
        }
        return mCharList;
    }

    private void setup_char() {
        mCharList = viewPager0.findViewById(R.id.main_list);
        mCharAdapter = new CharactersAdapter(context,charactersList,activity,sharedPreferences);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        if (sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width/480+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  2);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width/400+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  3);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("5")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mCharList.setLayoutManager(mLayoutManager);
        mCharList.setLayoutParams(paramsMsg);
        mCharList.setAdapter(mCharAdapter);
        mCharList.removeAllViewsInLayout();
        char_list_reload();
    }

    private void setup_weapon() {
        mWeaponList = viewPager1.findViewById(R.id.weapon_list);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);
        DisplayMetrics displayMetrics_w = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_w);
        int height_w = displayMetrics_w.heightPixels;
        int width_w = displayMetrics_w.widthPixels;
        mWeaponsAdapter = new WeaponsAdapter(context,weaponsList,activity,sharedPreferences);


        if (sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width_w/480+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  2);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width_w/400+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  3);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width_w/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("5")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width_w/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }
        LinearLayout.LayoutParams  paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mWeaponList.setLayoutManager(mLayoutManager);
        mWeaponList.setLayoutParams(paramsMsg);
        mWeaponList.setAdapter(mWeaponsAdapter);
        mWeaponList.removeAllViewsInLayout();
        weapon_list_reload();
    }

    private void setup_art(){
        mArtifactList = viewPager2.findViewById(R.id.artifact_list);
        mArtifactAdapter = new ArtifactsAdapter(context,artifactsList,activity,sharedPreferences);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 2);

        DisplayMetrics displayMetrics_a = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_a);
        int height_a = displayMetrics_a.heightPixels;
        int width_a = displayMetrics_a.widthPixels;

        if (sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width_a/480+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  2);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                mLayoutManager = new GridLayoutManager(context,  width_a/400+1);
            }else{
                mLayoutManager = new GridLayoutManager(context,  3);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("4")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width_a/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }else if (sharedPreferences.getString("curr_ui_grid", "2").equals("5")) {
            if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                int tmp_cnt = (int) width_a/960;
                if (tmp_cnt < 1){tmp_cnt = 1;}
                mLayoutManager = new GridLayoutManager(context,  tmp_cnt);
            }else{
                mLayoutManager = new GridLayoutManager(context,  1);
            }
        }
        LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
        paramsMsg.gravity = Gravity.CENTER;
        mArtifactList.setLayoutManager(mLayoutManager);
        mArtifactList.setLayoutParams(paramsMsg);
        mArtifactList.setAdapter(mArtifactAdapter);
        mArtifactList.removeAllViewsInLayout();
        artifact_list_reload();

    }

    private void char_list_reload() {
        Log.wtf("DAAM","YEE");
        String name ,element,weapon,nation,sex,mainStat,role;
        int rare,isComing;
        charactersList.clear();

        String json_base = LoadData("db/char/char_list.json");
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                element = object.getString("element");
                weapon = object.getString("weapon");
                nation = object.getString("nation");
                sex = object.getString("sex");
                role = object.getString("role");
                mainStat = object.getString("mainStat");
                rare = object.getInt("rare");
                isComing = object.getInt("isComing");

                Characters characters = new Characters();
                characters.setName(name);
                characters.setElement(element);
                characters.setWeapon(weapon);
                characters.setNation(nation);
                characters.setSex(sex);
                characters.setRole(role);
                characters.setRare(rare);
                characters.setMainStat(mainStat);
                characters.setIsComing(isComing);
                charactersList.add(characters);
            }
            mCharAdapter.filterList(charactersList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void weapon_list_reload() {
        Log.wtf("DAAM", "YEE");
        weaponsList.clear();
        String name,weapon,stat_1;
        int rare,isComing;

        String json_base = LoadData("db/weapons/weapon_list.json");
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                weapon = object.getString("weapon");
                stat_1 = object.getString("stat_1");
                rare = object.getInt("rare");
                isComing = object.getInt("isComing");

                Weapons weapons = new Weapons();
                weapons.setName(name);
                weapons.setWeapon(weapon);
                weapons.setRare(rare);
                weapons.setStat_1(stat_1);
                weapons.setIsComing(isComing);
                weaponsList.add(weapons);
            }
            mWeaponsAdapter.filterList(weaponsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void artifact_list_reload() {
        Log.wtf("DAAM", "YEE");
        artifactsList.clear();
        String name ,img;
        int rare,isComing;

        String json_base = LoadData("db/artifacts/artifact_list.json");
        //Get data from JSON
        try {
            JSONArray array = new JSONArray(json_base);
            for (int i = 0; i < array.length(); i++) {
                JSONObject object = array.getJSONObject(i);
                name = object.getString("name");
                img = object.getString("img");
                rare = object.getInt("rare");
                isComing = object.getInt("isComing");

                Artifacts artifacts = new Artifacts();
                artifacts.setName(name);
                artifacts.setBaseName(img);
                artifacts.setRare(rare);
                artifacts.setIsComing(isComing);
                artifactsList.add(artifacts);
            }
            mArtifactAdapter.filterList(artifactsList);
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void filterCharAlgothm(){
        ArrayList<Characters> filteredList = new ArrayList<>();
        for (Characters item : charactersList) {
            // DEFAULT
            if((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) &&
                    (show_anemo == false  && show_cryo == false && show_dendor == false && show_electro == false && show_hydro == false && show_geo == false && show_pyro == false) &&
                    (show_released == false  && show_unreleased == false) &&
                    (show_rare1 == false && show_rare2 == false && show_rare3 == false && show_rare4 == false && show_rare5 == false) &&
                    (show_dps == false && show_sub_dps == false && show_util == false)) {
                filteredList.add(item);
            }else{
                boolean isAllTrue = true;
                int isSingleElement = 0;
                int isSingleWeapon = 0;
                int isSingleRare = 0;
                int isSingleRelease = 0;
                int isSingleRole = 0;

                if (show_pyro){isSingleElement = isSingleElement+1;}
                if (show_hydro){isSingleElement = isSingleElement+1;}
                if (show_anemo){isSingleElement = isSingleElement+1;}
                if (show_dendor){isSingleElement = isSingleElement+1;}
                if (show_electro){isSingleElement = isSingleElement+1;}
                if (show_cryo){isSingleElement = isSingleElement+1;}
                if (show_geo){isSingleElement = isSingleElement+1;}

                if (show_sword){isSingleWeapon = isSingleWeapon+1;}
                if (show_claymore){isSingleWeapon = isSingleWeapon+1;}
                if (show_polearm){isSingleWeapon = isSingleWeapon+1;}
                if (show_bow){isSingleWeapon = isSingleWeapon+1;}
                if (show_catalyst){isSingleWeapon = isSingleWeapon+1;}

                if (show_rare4){isSingleRare = isSingleRare+1;}
                if (show_rare5){isSingleRare = isSingleRare+1;}
                if (show_released){isSingleRelease = isSingleRelease+1;}
                if (show_unreleased){isSingleRelease = isSingleRelease+1;}
                if (show_dps){isSingleRelease = isSingleRelease+1;}
                if (show_sub_dps){isSingleRelease = isSingleRelease+1;}
                if (show_util){isSingleRelease = isSingleRelease+1;}

                if (isSingleElement == 1){
                    if(show_pyro && !item.getElement().toLowerCase().equals("pyro") ){isAllTrue = false;}
                    if(show_hydro && !item.getElement().toLowerCase().equals("hydro") ){isAllTrue = false;}
                    if(show_anemo && !item.getElement().toLowerCase().equals("anemo") ){isAllTrue = false;}
                    if(show_dendor && !item.getElement().toLowerCase().equals("dendro")){isAllTrue = false;}
                    if(show_electro && !item.getElement().toLowerCase().equals("electro")){isAllTrue = false;}
                    if(show_cryo && !item.getElement().toLowerCase().equals("cryo")){isAllTrue = false;}
                    if(show_geo && !item.getElement().toLowerCase().equals("geo") ){isAllTrue = false;}
                }else if ((show_anemo == false  && show_cryo == false && show_dendor == false && show_electro == false && show_hydro == false && show_geo == false && show_pyro == false) == false){
                    if(!show_pyro && item.getElement().toLowerCase().equals("pyro") ){isAllTrue = false;}
                    if(!show_hydro && item.getElement().toLowerCase().equals("hydro") ){isAllTrue = false;}
                    if(!show_anemo && item.getElement().toLowerCase().equals("anemo") ){isAllTrue = false;}
                    if(!show_dendor && item.getElement().toLowerCase().equals("dendro")){isAllTrue = false;}
                    if(!show_electro && item.getElement().toLowerCase().equals("electro")){isAllTrue = false;}
                    if(!show_cryo && item.getElement().toLowerCase().equals("cryo")){isAllTrue = false;}
                    if(!show_geo && item.getElement().toLowerCase().equals("geo") ){isAllTrue = false;}
                }

                if(isSingleWeapon == 1){
                    if(show_sword && !item.getWeapon().toLowerCase().equals("sword")){isAllTrue = false;}
                    if(show_claymore && !item.getWeapon().toLowerCase().equals("claymore")){isAllTrue = false;}
                    if(show_polearm && !item.getWeapon().toLowerCase().equals("polearm")){isAllTrue = false;}
                    if(show_bow && !item.getWeapon().toLowerCase().equals("bow") ){isAllTrue = false;}
                    if(show_catalyst && !item.getWeapon().toLowerCase().equals("catalyst") ){isAllTrue = false;}
                }else if ((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) == false){
                    if(!show_sword && item.getWeapon().toLowerCase().equals("sword")){isAllTrue = false;}
                    if(!show_claymore && item.getWeapon().toLowerCase().equals("claymore")){isAllTrue = false;}
                    if(!show_polearm && item.getWeapon().toLowerCase().equals("polearm")){isAllTrue = false;}
                    if(!show_bow && item.getWeapon().toLowerCase().equals("bow") ){isAllTrue = false;}
                    if(!show_catalyst && item.getWeapon().toLowerCase().equals("catalyst") ){isAllTrue = false;}
                }

                if(isSingleRare == 1){
                    if(show_rare4 && item.getRare() !=4 ){isAllTrue = false;}
                    if(show_rare5 && item.getRare() !=5 ){isAllTrue = false;}
                }else if ((show_rare4 == false && show_rare5 == false ) == false){
                    if(!show_rare4 && item.getRare() ==4 ){isAllTrue = false;}
                    if(!show_rare5 && item.getRare() ==5 ){isAllTrue = false;}
                }

                if (isSingleRelease == 1){
                    if(show_released && item.getIsComing() !=0){isAllTrue = false;}
                    if(show_unreleased && item.getIsComing() !=1 ){isAllTrue = false;}
                }else if ((show_released == false && show_unreleased == false ) == false){
                    if(!show_released && item.getIsComing() ==0){isAllTrue = false;}
                    if(!show_unreleased && item.getIsComing() ==1 ){isAllTrue = false;}
                }

                if (isSingleRole == 1){
                    if(show_dps && !item.getRole().equals("Main_DPS") ){isAllTrue = false;}
                    if(show_sub_dps && !item.getRole().equals("Support_DPS") ){isAllTrue = false;}
                    if(show_util && !item.getRole().equals("Utility")){isAllTrue = false;}
                }else if ((show_dps == false && show_sub_dps == false && show_util == false) == false){
                    if(!show_dps && item.getRole().equals("Main_DPS") ){isAllTrue = false;}
                    if(!show_sub_dps && item.getRole().equals("Support_DPS") ){isAllTrue = false;}
                    if(!show_util && item.getRole().equals("Utility")){isAllTrue = false;}
                }

                if (isAllTrue == true){
                    filteredList.add(item);
                }
            }
        }

        mCharList.removeAllViews();
        mCharAdapter.filterList(filteredList);
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
    }

    public void filterWeaponAlgothm(int star){
        switch (star){
            case 0: show_rare1 = false; show_rare2 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 1: show_rare1 = true; show_rare2 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 2: show_rare2 = true; show_rare1 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 3: show_rare3 = true;show_rare1 = false; show_rare2 = false ;show_rare4 = false ; show_rare5 = false;break;
            case 4: show_rare4 = true;show_rare1 = false; show_rare2 = false ; show_rare3 = false ; show_rare5 = false;break;
            case 5: show_rare5 = true;show_rare1 = false; show_rare2 = false ; show_rare3 = false;show_rare4 = false ;break;
        }

        ArrayList<Weapons> filteredList = new ArrayList<>();
        for (Weapons item : weaponsList) {
            // DEFAULT
            if((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) &&
                    (show_released == false  && show_unreleased == false) &&
                    (show_rare1 == false && show_rare2 == false && show_rare3 == false && show_rare4 == false && show_rare5 == false)) {
                filteredList.add(item);
            }else{
                boolean isAllTrue = true;
                int isSingleWeapon = 0;
                int isSingleRare = 0;
                int isSingleRelease = 0;

                if (show_sword){isSingleWeapon = isSingleWeapon+1;}
                if (show_claymore){isSingleWeapon = isSingleWeapon+1;}
                if (show_polearm){isSingleWeapon = isSingleWeapon+1;}
                if (show_bow){isSingleWeapon = isSingleWeapon+1;}
                if (show_catalyst){isSingleWeapon = isSingleWeapon+1;}

                if (show_rare1){isSingleRare = isSingleRare+1;}
                if (show_rare2){isSingleRare = isSingleRare+1;}
                if (show_rare3){isSingleRare = isSingleRare+1;}
                if (show_rare4){isSingleRare = isSingleRare+1;}
                if (show_rare5){isSingleRare = isSingleRare+1;}
                if (show_released){isSingleRelease = isSingleRelease+1;}
                if (show_unreleased){isSingleRelease = isSingleRelease+1;}

                if(isSingleWeapon == 1){
                    if(show_sword && !item.getWeapon().toLowerCase().equals("sword")){isAllTrue = false;}
                    if(show_claymore && !item.getWeapon().toLowerCase().equals("claymore")){isAllTrue = false;}
                    if(show_polearm && !item.getWeapon().toLowerCase().equals("polearm")){isAllTrue = false;}
                    if(show_bow && !item.getWeapon().toLowerCase().equals("bow") ){isAllTrue = false;}
                    if(show_catalyst && !item.getWeapon().toLowerCase().equals("catalyst") ){isAllTrue = false;}
                }else if ((show_sword == false && show_claymore == false && show_catalyst == false && show_bow == false && show_polearm == false) == false){
                    if(!show_sword && item.getWeapon().toLowerCase().equals("sword")){isAllTrue = false;}
                    if(!show_claymore && item.getWeapon().toLowerCase().equals("claymore")){isAllTrue = false;}
                    if(!show_polearm && item.getWeapon().toLowerCase().equals("polearm")){isAllTrue = false;}
                    if(!show_bow && item.getWeapon().toLowerCase().equals("bow") ){isAllTrue = false;}
                    if(!show_catalyst && item.getWeapon().toLowerCase().equals("catalyst") ){isAllTrue = false;}
                }

                if(isSingleRare == 1){
                    if(show_rare1 && item.getRare() !=1 ){isAllTrue = false;}
                    if(show_rare2 && item.getRare() !=2 ){isAllTrue = false;}
                    if(show_rare3 && item.getRare() !=3 ){isAllTrue = false;}
                    if(show_rare4 && item.getRare() !=4 ){isAllTrue = false;}
                    if(show_rare5 && item.getRare() !=5 ){isAllTrue = false;}
                }else if ((show_rare1 == false &&show_rare2 == false &&show_rare3 == false &&show_rare4 == false && show_rare5 == false ) == false){
                    if(!show_rare1 && item.getRare() ==1 ){isAllTrue = false;}
                    if(!show_rare2 && item.getRare() ==2 ){isAllTrue = false;}
                    if(!show_rare3 && item.getRare() ==3 ){isAllTrue = false;}
                    if(!show_rare4 && item.getRare() ==4 ){isAllTrue = false;}
                    if(!show_rare5 && item.getRare() ==5 ){isAllTrue = false;}
                }

                if (isSingleRelease == 1){
                    if(show_released && item.getIsComing() !=0){isAllTrue = false;}
                    if(show_unreleased && item.getIsComing() !=1 ){isAllTrue = false;}
                }else if ((show_released == false && show_unreleased == false ) == false){
                    if(!show_released && item.getIsComing() ==0){isAllTrue = false;}
                    if(!show_unreleased && item.getIsComing() ==1 ){isAllTrue = false;}
                }

                if (isAllTrue == true){
                    filteredList.add(item);
                }
            }
        }
        mWeaponList.removeAllViews();
        mWeaponsAdapter.filterList(filteredList);
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
    }

    public void filterArtifactAlgothm(int star){
        switch (star){

            case 0: show_rare1 = false; show_rare2 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 1: show_rare1 = true; show_rare2 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 2: show_rare2 = true; show_rare1 = false ; show_rare3 = false;show_rare4 = false ; show_rare5 = false;break;
            case 3: show_rare3 = true;show_rare1 = false; show_rare2 = false ;show_rare4 = false ; show_rare5 = false;break;
            case 4: show_rare4 = true;show_rare1 = false; show_rare2 = false ; show_rare3 = false ; show_rare5 = false;break;
            case 5: show_rare5 = true;show_rare1 = false; show_rare2 = false ; show_rare3 = false;show_rare4 = false ;break;
        }
        ArrayList<Artifacts> filteredList = new ArrayList<>();
        for (Artifacts item : artifactsList) {
            // DEFAULT
            if((show_released == false  && show_unreleased == false) &&
                    (show_rare1 == false && show_rare2 == false && show_rare3 == false && show_rare4 == false && show_rare5 == false)) {
                filteredList.add(item);
            }else{
                boolean isAllTrue = true;
                int isSingleRare = 0;
                int isSingleRelease = 0;

                if (show_rare1){isSingleRare = isSingleRare+1;}
                if (show_rare2){isSingleRare = isSingleRare+1;}
                if (show_rare3){isSingleRare = isSingleRare+1;}
                if (show_rare4){isSingleRare = isSingleRare+1;}
                if (show_rare5){isSingleRare = isSingleRare+1;}
                if (show_released){isSingleRelease = isSingleRelease+1;}
                if (show_unreleased){isSingleRelease = isSingleRelease+1;}


                if(isSingleRare == 1){
                    if(show_rare1 && item.getRare() !=1 ){isAllTrue = false;}
                    if(show_rare2 && item.getRare() !=2 ){isAllTrue = false;}
                    if(show_rare3 && item.getRare() !=3 ){isAllTrue = false;}
                    if(show_rare4 && item.getRare() !=4 ){isAllTrue = false;}
                    if(show_rare5 && item.getRare() !=5 ){isAllTrue = false;}
                }else if ((show_rare1 == false &&show_rare2 == false &&show_rare3 == false &&show_rare4 == false && show_rare5 == false ) == false){
                    if(!show_rare1 && item.getRare() ==1 ){isAllTrue = false;}
                    if(!show_rare2 && item.getRare() ==2 ){isAllTrue = false;}
                    if(!show_rare3 && item.getRare() ==3 ){isAllTrue = false;}
                    if(!show_rare4 && item.getRare() ==4 ){isAllTrue = false;}
                    if(!show_rare5 && item.getRare() ==5 ){isAllTrue = false;}
                }

                if (isSingleRelease == 1){
                    if(show_released && item.getIsComing() !=0){isAllTrue = false;}
                    if(show_unreleased && item.getIsComing() !=1 ){isAllTrue = false;}
                }else if ((show_released == false && show_unreleased == false ) == false){
                    if(!show_released && item.getIsComing() ==0){isAllTrue = false;}
                    if(!show_unreleased && item.getIsComing() ==1 ){isAllTrue = false;}
                }

                if (isAllTrue == true){
                    filteredList.add(item);
                }
            }
        }
        mArtifactList.removeAllViews();
        mArtifactAdapter.filterList(filteredList);
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
