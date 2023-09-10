package com.voc.genshin_helper.tutorial;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.tabs.TabLayout;
import com.voc.genshin_helper.R;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class TutorialUI {
    Context context;
    Activity activity;
    View homeVP, charVP, weaponVP, artVP, settingVP;
    TabLayout desk_tablayout;
    BottomNavigationView bottomNavigationView;

    int message_id = 0;
    int plot_id = 0;

    int homePos = 0;
    int charPos = 1;
    int weaponPos = 2;
    int artPos = 3;
    int settingPos = 4;

    int[] tutorial_message = {
            R.string.tut_part0_1,
            R.string.tut_part0_2,
            R.string.tut_part0_3,
            R.string.tut_part0_4,
            R.string.tut_part0_5,
            R.string.tut_part0_6,
            R.string.tut_part0_7,
            R.string.tut_part1_1,
            R.string.tut_part1_2,
            R.string.tut_part1_3,
            R.string.tut_part1_4,
            R.string.tut_part2_1,
            R.string.tut_part2_2,
            R.string.tut_part2_3,
            R.string.tut_part2_4,
            R.string.tut_part3_5,
            R.string.tut_part3_6,
            R.string.tut_part4_1,
            R.string.tut_part5_1,
            R.string.tut_part5_2,
            R.string.tut_part5_3,
            R.string.tut_part5_4,
            R.string.tut_part5_5
    };
    // Pressed -> Start next step
    int[] tutorial_message_id = {
            // Part 0
            0,
            0,
            0,
            0,
            0,

            // Part 0.5 -- Navigation Bar
            0,
            0,

            // Part 1 -- Home
            1,
            1,
            1,
            1,

            // Part 2 -- Character List
            2,
            2,
            2,
            2,

            // Part 3 -- Weapon List
            3,
            3,

            // Part 4 -- Artifact List
            4,

            // Part 5 -- Setting / Paimon
            5,
            5,
            5,
            5,
            5
    };

    public void setup(Context context, Activity activity, View homeVP, View charVP, View weaponVP, View artVP, View settingVP, TabLayout desk_tablayout, BottomNavigationView bottomNavigationView) {
        this.context = context;
        this.activity = activity;
        this.homeVP = homeVP;
        this.charVP = charVP;
        this.weaponVP = weaponVP;
        this.artVP = artVP;
        this.settingVP = settingVP;
        this.desk_tablayout = desk_tablayout;
        this.bottomNavigationView = bottomNavigationView;

        homeVP.findViewById(R.id.tut_char_card).setVisibility(View.GONE);
        charVP.findViewById(R.id.tut_char_card).setVisibility(View.GONE);
        artVP.findViewById(R.id.tut_char_card).setVisibility(View.GONE);
        weaponVP.findViewById(R.id.tut_char_card).setVisibility(View.GONE);
        settingVP.findViewById(R.id.tut_char_card).setVisibility(View.GONE);

        if (context.getSharedPreferences("user_info",Context.MODE_PRIVATE).getBoolean("isTutorialFinished",false) == false){
            start();
        }
    }

    public void start(){
        View view = homeVP;
        View lastView = homeVP;
        switch (plot_id){
            case 0 :
            case 1 : view = homeVP;break;
            case 2 : view = charVP;break;
            case 3 : view = weaponVP;break;
            case 4 : view = artVP;break;
            case 5 : view = settingVP;break;
        }

        switch (plot_id-1){
            case 0 :
            case 1 : lastView = homeVP;break;
            case 2 : lastView = charVP;break;
            case 3 : lastView = weaponVP;break;
            case 4 : lastView = artVP;break;
            case 5 : lastView = settingVP;break;
        }

        TextView tut_char_message = view.findViewById(R.id.tut_char_message);
        CardView tut_char_card = view.findViewById(R.id.tut_char_card);
        CardView tut_char_card_last = lastView.findViewById(R.id.tut_char_card);
        tut_char_card_last.setVisibility(View.GONE);
        tut_char_card.setVisibility(View.VISIBLE);
        tut_char_message.setText(context.getString(tutorial_message[message_id]));

        tut_char_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Next part
                if (message_id+1 < tutorial_message.length){
                    message_id ++;
                    if (tutorial_message_id[message_id] == plot_id){
                        tut_char_message.setText(tutorial_message[message_id]);
                    }else{
                        int pos = homePos;
                        int posID = R.id.navigation_home;
                        switch (plot_id+1){
                            case 1 : pos = homePos;posID = R.id.navigation_home;break;
                            case 2 : pos = charPos;posID = R.id.navigation_char;break;
                            case 3 : pos = weaponPos;posID = R.id.navigation_weapons;break;
                            case 4 : pos = artPos;posID = R.id.navigation_artifacts;break;
                            case 5 : pos = settingPos;posID = R.id.navigation_settings;break;
                            case 6 : pos = settingPos;posID = R.id.navigation_settings;break;
                        }
                        if (desk_tablayout != null){
                            desk_tablayout.selectTab(desk_tablayout.getTabAt(pos));
                        }else if(bottomNavigationView != null){
                            bottomNavigationView.setSelectedItemId(posID);
                        }
                        plot_id++;
                        start();
                    }

                }else{
                    tut_char_card.setVisibility(View.GONE);
                    context.getSharedPreferences("user_info",Context.MODE_PRIVATE).edit().putBoolean("isTutorialFinished",true).apply();
                }
            }
        });
    }

    public void deskSetPosArray(int homePos, int charPos, int weaponPos, int artPos, int settingPos) {
        this.homePos = homePos;
        this.charPos = charPos;
        this.weaponPos = weaponPos;
        this.artPos = artPos;
        this.settingPos = settingPos;
    }
}
