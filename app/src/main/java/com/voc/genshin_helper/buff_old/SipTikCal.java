package com.voc.genshin_helper.buff_old;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SeekBar;
import android.widget.Switch;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.Artifacts;
import com.voc.genshin_helper.data.ArtifactsAdapter;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.Weapons;
import com.voc.genshin_helper.data.WeaponsAdapter;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.NumberPickerDialog;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class SipTikCal extends AppCompatActivity {

    Context context;
    Activity activity;
    public SharedPreferences sharedPreferences;
    ItemRss item_rss;
    Characters characters;
    Weapons weapons;
    Artifacts artifact1;
    Artifacts artifact2;
    NumberPickerDialog npd;
    RecyclerView.LayoutManager mLayoutManager;

    int char_after_lvl = 90;
    int char_after_break = 6;
    int char_constellation_lvl = 6;
    int[] char_skill_lvl = {1,1,1};

    int weapon_after_lvl = 1;
    int weapon_refine_lvl = 1;

    WeaponsAdapter mWeaponAdapter;
    RecyclerView mWeaponList;
    public List<Weapons> weaponsList = new ArrayList();

    ArtifactsAdapter mArtifactAdapter;
    RecyclerView mArtifactList;
    public List<Artifacts> artifactsList = new ArrayList();

    SharedPreferences.Editor editor;
    Dialog dialog1 ;
    Dialog dialog2 ;
    Dialog dialog3 ;
    /**
     * Part 1 Method
     */
    ImageView siptik_char_icon;
    ImageView siptik_char_element;
    TextView siptik_char_name;
    Switch siptik_break_lvl_after_switch;
    Button siptik_char_lvl_btn;
    Button siptik_cons_lvl_btn;
    SeekBar siptik_skill_pb1;
    SeekBar siptik_skill_pb2;
    SeekBar siptik_skill_pb3;
    TextView siptik_skill1_title;
    TextView siptik_skill2_title;
    TextView siptik_skill3_title;
    TextView siptik_skill_tv1;
    TextView siptik_skill_tv2;
    TextView siptik_skill_tv3;

    /**
     * Part 2 Method
     */
    ImageView siptik_weapon_icon;
    TextView siptik_weapon_name;
    TextView siptik_weapon_lvl_btn;
    TextView siptik_refine_lvl_btn;

    /**
     * Part 3 Method
     */
    ImageView siptik_buff_artifact_ico1;
    ImageView siptik_buff_artifact_ico2;
    TextView siptik_set1_info;
    TextView siptik_set2_info;
    TextView siptik_set1_name;
    TextView siptik_set2_name;

    /**
     * Part 4 Method
     */
    EditText siptik_atk_et;
    EditText siptik_hp_et;
    EditText siptik_def_et;
    EditText siptik_crit_rate_et;
    EditText siptik_crit_dmg_et;
    EditText siptik_enrech_et;
    EditText siptik_elemas_et;
    EditText siptik_element_dmg_et1;
    EditText siptik_element_dmg_et2;
    EditText siptik_element_dmg_et3;
    EditText siptik_element_dmg_et4;
    EditText siptik_element_dmg_et5;
    EditText siptik_element_dmg_et6;
    EditText siptik_element_dmg_et7;

    /**
     * Part 5 Method
     */
    ImageView siptik_team_icon;
    TextView siptik_team_name;
    TextView siptik_team_talent_name;
    Button siptik_team_lvl_btn;
    Switch siptik_team_buff_switch1;
    Switch siptik_team_buff_switch2;

    /**
     * Part 6 Method
     */
    ImageView siptik_enemy_icon;
    TextView siptik_enemy_name;
    Button siptik_enemy_lvl_btn;
    TextView siptik_enemy_res1;
    TextView siptik_enemy_res2;
    TextView siptik_enemy_res3;
    TextView siptik_enemy_res4;
    TextView siptik_enemy_res5;
    TextView siptik_enemy_res6;
    TextView siptik_enemy_res7;
    TextView siptik_enemy_res8;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_siptik_buff);

        item_rss = new ItemRss();
        context = this;
        activity = this;
        npd = new NumberPickerDialog(activity);
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        editor = sharedPreferences.edit();
        //https://stackoverflow.com/questions/2736389/how-to-pass-an-object-from-one-activity-to-another-on-android
        Intent i = getIntent();
        characters = (Characters) i.getSerializableExtra("characters");

        /**
         * Part 1 Method
         */
        siptik_char_icon = findViewById(R.id.siptik_char_icon);
        siptik_char_element = findViewById(R.id.siptik_char_element);
        siptik_char_name = findViewById(R.id.siptik_char_name);
        siptik_break_lvl_after_switch = findViewById(R.id.siptik_break_lvl_after_switch);
        siptik_char_lvl_btn = findViewById(R.id.siptik_char_lvl_btn);
        siptik_cons_lvl_btn = findViewById(R.id.siptik_cons_lvl_btn);
        siptik_skill_pb1 = findViewById(R.id.siptik_skill_pb1);
        siptik_skill_pb2 = findViewById(R.id.siptik_skill_pb2);
        siptik_skill_pb3 = findViewById(R.id.siptik_skill_pb3);
        siptik_skill1_title = findViewById(R.id.siptik_skill1_title);
        siptik_skill2_title = findViewById(R.id.siptik_skill2_title);
        siptik_skill3_title = findViewById(R.id.siptik_skill3_title);
        siptik_skill_tv1 = findViewById(R.id.siptik_skill_tv1);
        siptik_skill_tv2 = findViewById(R.id.siptik_skill_tv2);
        siptik_skill_tv3 = findViewById(R.id.siptik_skill_tv3);

        /**
         * Part 2 Method
         */
        siptik_weapon_icon = findViewById(R.id.siptik_weapon_icon);
        siptik_weapon_name = findViewById(R.id.siptik_weapon_name);
        siptik_weapon_lvl_btn = findViewById(R.id.siptik_weapon_lvl_btn);
        siptik_refine_lvl_btn = findViewById(R.id.siptik_refine_lvl_btn);

        /**
         * Part 3 Method
         */
        siptik_buff_artifact_ico1 = findViewById(R.id.siptik_buff_artifact_ico1);
        siptik_buff_artifact_ico2 = findViewById(R.id.siptik_buff_artifact_ico2);
        siptik_set1_info = findViewById(R.id.siptik_set1_info);
        siptik_set2_info = findViewById(R.id.siptik_set2_info);
        siptik_set1_name = findViewById(R.id.siptik_set1_name);
        siptik_set2_name = findViewById(R.id.siptik_set2_name);

        /**
         * Part 4 Method
         */
        siptik_atk_et = findViewById(R.id.siptik_atk_et);
        siptik_hp_et = findViewById(R.id.siptik_hp_et);
        siptik_def_et = findViewById(R.id.siptik_def_et);
        siptik_crit_rate_et = findViewById(R.id.siptik_crit_rate_et);
        siptik_crit_dmg_et = findViewById(R.id.siptik_crit_dmg_et);
        siptik_enrech_et = findViewById(R.id.siptik_enrech_et);
        siptik_elemas_et = findViewById(R.id.siptik_elemas_et);
        siptik_element_dmg_et1 = findViewById(R.id.siptik_element_dmg_et1);
        siptik_element_dmg_et2 = findViewById(R.id.siptik_element_dmg_et2);
        siptik_element_dmg_et3 = findViewById(R.id.siptik_element_dmg_et3);
        siptik_element_dmg_et4 = findViewById(R.id.siptik_element_dmg_et4);
        siptik_element_dmg_et5 = findViewById(R.id.siptik_element_dmg_et5);
        siptik_element_dmg_et6 = findViewById(R.id.siptik_element_dmg_et6);
        siptik_element_dmg_et7 = findViewById(R.id.siptik_element_dmg_et7);

        /**
         * Part 5 Method
         */
        siptik_team_icon = findViewById(R.id.siptik_team_icon);
        siptik_team_name = findViewById(R.id.siptik_team_name);
        siptik_team_talent_name = findViewById(R.id.siptik_team_talent_name);
        siptik_team_lvl_btn = findViewById(R.id.siptik_team_lvl_btn);
        siptik_team_buff_switch1 = findViewById(R.id.siptik_team_buff_switch1);
        siptik_team_buff_switch2 = findViewById(R.id.siptik_team_buff_switch2);

        /**
         * Part 6 Method
         */
        siptik_enemy_icon = findViewById(R.id.siptik_enemy_icon);
        siptik_enemy_name = findViewById(R.id.siptik_enemy_name);
        siptik_enemy_lvl_btn = findViewById(R.id.siptik_enemy_lvl_btn);
        siptik_enemy_res1 = findViewById(R.id.siptik_enemy_res1);
        siptik_enemy_res2 = findViewById(R.id.siptik_enemy_res2);
        siptik_enemy_res3 = findViewById(R.id.siptik_enemy_res3);
        siptik_enemy_res4 = findViewById(R.id.siptik_enemy_res4);
        siptik_enemy_res5 = findViewById(R.id.siptik_enemy_res5);
        siptik_enemy_res6 = findViewById(R.id.siptik_enemy_res6);
        siptik_enemy_res7 = findViewById(R.id.siptik_enemy_res7);
        siptik_enemy_res8 = findViewById(R.id.siptik_enemy_res8);

        final int radius_circ = 360;
        final int margin_circ = 0;
        final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);

        //Number Dialog Method
        npd.onDialogRespond = new NumberPickerDialog.OnDialogRespond() {
            @Override
            public void onRespond(int value , String XPR) {
                if(XPR.equals("CHAR_LVL")){
                    char_after_lvl = value;
                    if(char_after_lvl > 80 ){char_after_break =6;}
                    else if(char_after_lvl > 70 && char_after_lvl <=80){char_after_break =5;}
                    else if(char_after_lvl > 60 && char_after_lvl <=70){char_after_break =4;}
                    else if(char_after_lvl > 50 && char_after_lvl <=60){char_after_break =3;}
                    else if(char_after_lvl > 40 && char_after_lvl <=50){char_after_break =2;}
                    else if(char_after_lvl > 20 && char_after_lvl <=40){char_after_break =1;}
                    else if(char_after_lvl <= 20 ){char_after_break =0;}
                    siptik_char_lvl_btn.setText(String.valueOf(char_after_lvl));

                    if(siptik_break_lvl_after_switch.isChecked()){
                        if(char_after_lvl !=20 && char_after_lvl !=40 && char_after_lvl !=50 && char_after_lvl !=60 && char_after_lvl !=70 && char_after_lvl !=80){
                            siptik_break_lvl_after_switch.setChecked(false);
                        }
                    }
                }else if(XPR.equals("CHAR_CON")){
                    char_constellation_lvl = value;
                    siptik_cons_lvl_btn.setText(String.valueOf(char_constellation_lvl));
                }else if(XPR.equals("WEAPON_LVL")){
                    weapon_after_lvl = value;
                    siptik_weapon_lvl_btn.setText(String.valueOf(weapon_after_lvl));
                }else if(XPR.equals("WEAPON_REFINE")){
                    weapon_refine_lvl = value;
                    siptik_refine_lvl_btn.setText(String.valueOf(weapon_refine_lvl));
                }

            }
        };

        /**
         * Part 1 Function
         */
        //Character Name
        siptik_char_name.setText(item_rss.getCharByName(characters.getName(),context)[1]);

        //Character Icon
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getCharByName(characters.getName(),context)[3],context)).transform(transformation_circ)
                .error (R.drawable.paimon_full)
                .into (siptik_char_icon);
        siptik_char_element.setImageResource(item_rss.getElementByName(characters.getElement())[0]);

        //Character Is Break Switch
        siptik_break_lvl_after_switch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(char_after_lvl !=20 && char_after_lvl !=40 && char_after_lvl !=50 && char_after_lvl !=60 && char_after_lvl !=70 && char_after_lvl !=80){
                    siptik_break_lvl_after_switch.setChecked(false);
                }
            }
        });

        //Character Level Button
        siptik_char_lvl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npd.setLastValue(char_after_lvl);
                npd.setMaxValue(90);
                npd.setMinValue(1);
                npd.showDialog("CHAR_LVL");
            }
        });

        //Character Constellation Level Button
        siptik_cons_lvl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npd.setLastValue(char_constellation_lvl);
                npd.setMaxValue(6);
                npd.setMinValue(1);
                npd.showDialog("CHAR_CON");
            }
        });

        //Character Skill 1-3 SeekBar
        siptik_skill_pb1.setOnSeekBarChangeListener(seekBarChangeListener(0,siptik_skill_tv1));
        siptik_skill_pb2.setOnSeekBarChangeListener(seekBarChangeListener(1,siptik_skill_tv2));
        siptik_skill_pb3.setOnSeekBarChangeListener(seekBarChangeListener(2,siptik_skill_tv3));

        /**
         * Part 2 Function
         */

        //Weapon Icon
        siptik_weapon_icon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog1 = new Dialog(context, R.style.NormalDialogStyle_N);
                View view1 = View.inflate(context, R.layout.fragment_weapon, null);

                mWeaponList = view1.findViewById(R.id.weapon_list);
                mLayoutManager = new GridLayoutManager(context, 2);
                DisplayMetrics displayMetrics_w = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_w);
                int height_w = displayMetrics_w.heightPixels;
                int width_w = displayMetrics_w.widthPixels;
                mWeaponAdapter = new WeaponsAdapter(context,weaponsList,activity,sharedPreferences);
                LinearLayout linearLayout2 = view1.findViewById(R.id.linearLayout2);
                linearLayout2.setVisibility(View.GONE);
                mWeaponList.setPadding(0,0,0,0);

                if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    mLayoutManager = new GridLayoutManager(context,  width_w/400+1);
                }else{
                    mLayoutManager = new GridLayoutManager(context,  3);
                }

                LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
                paramsMsg.gravity = Gravity.CENTER;
                mWeaponList.setLayoutManager(mLayoutManager);
                mWeaponList.setLayoutParams(paramsMsg);
                mWeaponList.setAdapter(mWeaponAdapter);
                mWeaponList.removeAllViewsInLayout();
                weapon_list_reload(characters.getWeapon());

                dialog1.setContentView(view1);
                dialog1.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog1.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.BOTTOM;
                dialogWindow.setAttributes(lp);
                dialog1.show();
            }
        });

        //Weapon Name
        siptik_weapon_name.setText(R.string.unknown);
        siptik_weapon_lvl_btn.setText(String.valueOf(1));
        siptik_refine_lvl_btn.setText(String.valueOf(1));

        /**
         * Part 3 Function
         */

        //Artifact1 Icon
        siptik_buff_artifact_ico1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog2 = new Dialog(context, R.style.NormalDialogStyle_N);
                View view1 = View.inflate(context, R.layout.fragment_art, null);

                mArtifactList = view1.findViewById(R.id.artifact_list);
                mLayoutManager = new GridLayoutManager(context, 2);
                DisplayMetrics displayMetrics_w = new DisplayMetrics();
                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_w);
                int height_w = displayMetrics_w.heightPixels;
                int width_w = displayMetrics_w.widthPixels;
                mArtifactAdapter = new ArtifactsAdapter(context,artifactsList,activity,1,sharedPreferences);
                LinearLayout linearLayout2 = view1.findViewById(R.id.linearLayout2);
                linearLayout2.setVisibility(View.GONE);
                mArtifactList.setPadding(0,0,0,0);

                if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    mLayoutManager = new GridLayoutManager(context,  width_w/400+1);
                }else{
                    mLayoutManager = new GridLayoutManager(context,  3);
                }

                LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
                paramsMsg.gravity = Gravity.CENTER;
                mArtifactList.setLayoutManager(mLayoutManager);
                mArtifactList.setLayoutParams(paramsMsg);
                mArtifactList.setAdapter(mArtifactAdapter);
                mArtifactList.removeAllViewsInLayout();
                artifact_list_reload();

                dialog2.setContentView(view1);
                dialog2.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog2.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.BOTTOM;
                dialogWindow.setAttributes(lp);
                dialog2.show();
            }
        });

        //Artifact2 Icon
        siptik_buff_artifact_ico2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog3 = new Dialog(context, R.style.NormalDialogStyle_N);
                View view1 = View.inflate(context, R.layout.fragment_art, null);

                mArtifactList = view1.findViewById(R.id.artifact_list);
                mLayoutManager = new GridLayoutManager(context, 2);
                DisplayMetrics displayMetrics_w = new DisplayMetrics();
                getWindowManager().getDefaultDisplay().getMetrics(displayMetrics_w);
                int height_w = displayMetrics_w.heightPixels;
                int width_w = displayMetrics_w.widthPixels;
                mArtifactAdapter = new ArtifactsAdapter(context,artifactsList,activity,2,sharedPreferences);
                LinearLayout linearLayout2 = view1.findViewById(R.id.linearLayout2);
                linearLayout2.setVisibility(View.GONE);
                mArtifactList.setPadding(0,0,0,0);

                if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
                    mLayoutManager = new GridLayoutManager(context,  width_w/400+1);
                }else{
                    mLayoutManager = new GridLayoutManager(context,  3);
                }

                LinearLayout.LayoutParams paramsMsg = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, android.view.ViewGroup.LayoutParams.WRAP_CONTENT);
                paramsMsg.gravity = Gravity.CENTER;
                mArtifactList.setLayoutManager(mLayoutManager);
                mArtifactList.setLayoutParams(paramsMsg);
                mArtifactList.setAdapter(mArtifactAdapter);
                mArtifactList.removeAllViewsInLayout();
                artifact_list_reload();

                dialog3.setContentView(view1);
                dialog3.setCanceledOnTouchOutside(true);
                //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                Window dialogWindow = dialog3.getWindow();
                WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                lp.height = WindowManager.LayoutParams.MATCH_PARENT;
                lp.gravity = Gravity.BOTTOM;
                dialogWindow.setAttributes(lp);
                dialog3.show();
            }
        });

    }

    //Weapon Choose Feedback
    public void weaponChoosed(Weapons weapons){
        if (dialog1 != null){
            dialog1.dismiss();
        }
        this.weapons = weapons;

        final int radius_circ = 360;
        final int margin_circ = 0;
        final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);

        siptik_weapon_name.setText(item_rss.getWeaponByName(weapons.getName(),context)[0]);
        Picasso.get()
                .load (FileLoader.loadIMG(item_rss.getWeaponByName(weapons.getName(),context)[1],context)).transform(transformation_circ)
                .error (R.drawable.paimon_full)
                .into (siptik_weapon_icon);

        //Weapon Level Button
        siptik_weapon_lvl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npd.setLastValue(weapon_after_lvl);
                npd.setMaxValue(90);
                npd.setMinValue(1);
                npd.showDialog("WEAPON_LVL");
            }
        });

        //Weapon Refine Level Button
        siptik_refine_lvl_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                npd.setLastValue(weapon_refine_lvl);
                npd.setMaxValue(5);
                npd.setMinValue(1);
                npd.showDialog("WEAPON_REFINE");
            }
        });
    }

    //Artifact Choose Feedback
    public void artifactChoose(Artifacts artifacts, int pos){
        if (dialog2 != null){
            dialog2.dismiss();
        }

        if (dialog3 != null){
            dialog3.dismiss();
        }

        Log.wtf("YES","IT's ABCD");
        final int radius_circ = 360;
        final int margin_circ = 0;
        final Transformation transformation_circ = new RoundedCornersTransformation(radius_circ, margin_circ);

        switch (pos){
            case 1 : {

                Log.wtf("YES","IT's ABCE");
                this.artifact1 = artifacts;
                siptik_set1_name.setText(item_rss.getArtifactByName(artifacts.getName(),context)[0]);
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getArtifactByName(artifacts.getName(),context)[1],context)).transform(transformation_circ)
                        .error (R.drawable.paimon_full)
                        .into (siptik_buff_artifact_ico1);
                //siptik_set1_info.setText();
                break;
            }
            case 2 : {
                this.artifact2 = artifacts;
                siptik_set2_name.setText(item_rss.getArtifactByName(artifacts.getName(),context)[0]);
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getArtifactByName(artifacts.getName(),context)[1],context)).transform(transformation_circ)
                        .error (R.drawable.paimon_full)
                        .into (siptik_buff_artifact_ico2);
                //siptik_set2_info.setText();
                break;
            }
        }
    }

    //SeekBar Method
    public SeekBar.OnSeekBarChangeListener seekBarChangeListener (int pos ,TextView textView){
        SeekBar.OnSeekBarChangeListener sbC = new SeekBar.OnSeekBarChangeListener() {


            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                // TODO Auto-generated method stub
            }

            @Override
            public void onProgressChanged(SeekBar seekBar, int progress,boolean fromUser) {
                // TODO Auto-generated method stub
                textView.setText(String.valueOf(progress));
                if (progress <1){
                    seekBar.setProgress(1);
                    char_skill_lvl[pos] = 1 ;
                    textView.setText(String.valueOf(1));
                }

            }
        };
        return sbC;
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

    public void weapon_list_reload(String type) {
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


                if(weapon.equals(type)){System.out.println("Weapon Info : {"+type+","+weapon+","+String.valueOf(i)+"}");
                    Weapons weapons = new Weapons();
                    weapons.setName(name);
                    weapons.setWeapon(weapon);
                    weapons.setRare(rare);
                    weapons.setStat_1(stat_1);
                    weapons.setIsComing(isComing);
                    weaponsList.add(weapons);
                }
            }
            mWeaponAdapter.filterList(weaponsList);
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
