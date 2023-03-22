package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearSmoothScroller;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.alibaba.fastjson2.JSON;
import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.buff.obj.Artifact;
import com.voc.genshin_helper.buff.obj.BuffObject;
import com.voc.genshin_helper.buff.obj.Character;
import com.voc.genshin_helper.buff.obj.Weapon;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.ui.MMXLVIII.Calculator2048;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;
import com.willy.ratingbar.ScaleRatingBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class BuffAccountUI extends AppCompatActivity {

    ViewPager viewPager;
    ArrayList<View> dynamicView = new ArrayList<>();
    ArrayList<BuffObject> buffObjects = new ArrayList<>();

    ItemRss item_rss;

    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    BuffCatelogy buffCatelogy;

    TabLayout team_tablayout;
    DisplayMetrics displayMetrics;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_buff_account_2048);
        context = this;
        activity = this;
        sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        item_rss = new ItemRss();
        buffCatelogy = new BuffCatelogy();
        buffObjects = (ArrayList<BuffObject>) getIntent().getSerializableExtra("buffObjects");
        dynamicView.clear();

        team_tablayout = findViewById(R.id.team_tablayout);
        team_tablayout.removeAllTabs();
        displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);

        /*
        Just for checking
        for (int x = 0 ; x < buffObjects.size() ; x++){
            System.out.println(buffObjects.get(x).toString(0));
        }
        */

        TextView ui_title = findViewById(R.id.ui_title);
        TextView ui_uid = findViewById(R.id.ui_uid);
        ui_title.setText(sharedPreferences.getString("genshin_username","Unknown"));
        ui_uid.setText(sharedPreferences.getString("genshin_uid","-1"));

        list_init();

        team_tablayout.setTabIndicatorFullWidth(false);

        viewPager = (ViewPager) findViewById(R.id.ui_vp);
        viewPager.setAdapter(new MyViewPagerAdapter(dynamicView));
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float positionOffset, int positionOffsetPx) {
            }

            @Override
            public void onPageSelected(int position) {
                team_tablayout.selectTab(team_tablayout.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        team_tablayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setForeground(context.getDrawable(R.drawable.bg_buff_tab_selected_kwang));
                viewPager.setCurrentItem(tab.getPosition());

            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setForeground(null);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setForeground(context.getDrawable(R.drawable.bg_buff_tab_selected_kwang));
                viewPager.setCurrentItem(tab.getPosition());

            }
        });

        viewPager.setCurrentItem(0);
        team_tablayout.selectTab(team_tablayout.getTabAt(0));

    }

    private void list_init() {
        final LayoutInflater mInflater = getLayoutInflater().from(this);
        final int radius = 180;
        final int margin = 0;
        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

        for (int x = 0 ; x < buffObjects.size() ; x++){
            BuffObject buffObject = buffObjects.get(x);
            View view = mInflater.inflate(R.layout.fragment_buff_account_group_2048, null,false);
            ImageView buff_dmg_char_ico = view.findViewById(R.id.buff_dmg_char_ico);
            TextView buff_dmg_char_name = view.findViewById(R.id.buff_dmg_char_name);
            ScaleRatingBar buff_dmg_char_rare = view.findViewById(R.id.buff_dmg_char_rare);
            TextView buff_dmg_char_lvl = view.findViewById(R.id.buff_dmg_char_lvl);
            ImageView buff_dmg_char_talent1_ico = view.findViewById(R.id.buff_dmg_char_talent1_ico);
            ImageView buff_dmg_char_talent2_ico = view.findViewById(R.id.buff_dmg_char_talent2_ico);
            ImageView buff_dmg_char_talent3_ico = view.findViewById(R.id.buff_dmg_char_talent3_ico);
            TextView buff_dmg_char_talent1_lvl = view.findViewById(R.id.buff_dmg_char_talent1_lvl);
            TextView buff_dmg_char_talent2_lvl = view.findViewById(R.id.buff_dmg_char_talent2_lvl);
            TextView buff_dmg_char_talent3_lvl = view.findViewById(R.id.buff_dmg_char_talent3_lvl);
            ImageView buff_dmg_char_sof1_ico = view.findViewById(R.id.buff_dmg_char_sof1_ico);
            ImageView buff_dmg_char_sof2_ico = view.findViewById(R.id.buff_dmg_char_sof2_ico);
            ImageView buff_dmg_char_sof3_ico = view.findViewById(R.id.buff_dmg_char_sof3_ico);
            ImageView buff_dmg_char_sof4_ico = view.findViewById(R.id.buff_dmg_char_sof4_ico);
            ImageView buff_dmg_char_sof5_ico = view.findViewById(R.id.buff_dmg_char_sof5_ico);
            ImageView buff_dmg_char_sof6_ico = view.findViewById(R.id.buff_dmg_char_sof6_ico);

            ImageView buff_dmg_weapon_ico = view.findViewById(R.id.buff_dmg_weapon_ico);
            TextView buff_dmg_weapon_name = view.findViewById(R.id.buff_dmg_weapon_name);
            TextView buff_dmg_weapon_lvl = view.findViewById(R.id.buff_dmg_weapon_lvl);
            TextView buff_dmg_weapon_affix_lvl = view.findViewById(R.id.buff_dmg_weapon_affix_lvl);
            TextView buff_dmg_weapon_intro = view.findViewById(R.id.buff_dmg_weapon_intro);


            LinearLayout buff_dmg_art_ll = view.findViewById(R.id.buff_dmg_art_ll);

            //Character
            Character character = buffObject.getCharacter();
            int talentBg = R.drawable.bg_colored_talent_pyro;
            int sofBg = R.drawable.bg_colored_sof_pyro;
            String CharName_BASE_UNDERSCORE = character.getCharName().replace(" ", "_");
            String lang = sharedPreferences.getString("curr_lang", "zh-HK");
            String is = null; String is_default = null; String result1 = null;

            is_default = LoadData("db/char/en-US/" + CharName_BASE_UNDERSCORE + ".json");
            is = LoadData("db/char/" + lang + "/" + CharName_BASE_UNDERSCORE + ".json");

            if (is != null) {
                result1 = is;
            } else if (is_default != null) {
                result1 = is_default;
            }

            switch (character.getCharRare()){
                case 1 : buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                case 2 : buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                case 3 : buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                case 4 : buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                case 5 : buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                default:  buff_dmg_char_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
            }
            switch (character.getCharElement()){
                case Character.ANEMO: talentBg = R.drawable.bg_colored_talent_anemo; sofBg = R.drawable.bg_colored_sof_anemo;break;
                case Character.CRYO: talentBg = R.drawable.bg_colored_talent_cryo; sofBg = R.drawable.bg_colored_sof_cryo;break;
                case Character.DENDRO: talentBg = R.drawable.bg_colored_talent_dendro; sofBg = R.drawable.bg_colored_sof_dendro;break;
                case Character.ELECTRO: talentBg = R.drawable.bg_colored_talent_electro; sofBg = R.drawable.bg_colored_sof_electro;break;
                case Character.GEO: talentBg = R.drawable.bg_colored_talent_geo; sofBg = R.drawable.bg_colored_sof_geo;break;
                case Character.HYDRO: talentBg = R.drawable.bg_colored_talent_hydro; sofBg = R.drawable.bg_colored_sof_hydro;break;
                case Character.PYRO: talentBg = R.drawable.bg_colored_talent_pyro; sofBg = R.drawable.bg_colored_sof_pyro;break;
                default: talentBg = R.drawable.bg_colored_talent_pyro; sofBg = R.drawable.bg_colored_sof_pyro;break;
            }
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getCharByName(character.getCharName(),context)[3],context))
                    .transform(transformation)
                    .fit()
                    .error (R.drawable.paimon_full)
                    .into (buff_dmg_char_ico);
            buff_dmg_char_name.setText(item_rss.getCharByName(character.getCharName(),context)[1]);
            buff_dmg_char_lvl.setText("Lvl. "+String.valueOf(character.getCharLvl()));
            buff_dmg_char_rare.setNumStars(character.getCharRare());
            buff_dmg_char_rare.setRating(character.getCharRare());
            buff_dmg_char_rare.setClickable(false);
            buff_dmg_char_rare.setScrollable(false);
            if (result1 != null) {
                try {
                    JSONObject jsonObject = new JSONObject(result1);
                    JSONObject battle_talent = jsonObject.getJSONObject("battle_talent");
                    buff_dmg_char_talent1_ico.setImageDrawable(item_rss.getTalentIcoByName(battle_talent.getString("normal_img"),context));
                    buff_dmg_char_talent2_ico.setImageDrawable(item_rss.getTalentIcoByName(battle_talent.getString("element_img"),context));
                    buff_dmg_char_talent3_ico.setImageDrawable(item_rss.getTalentIcoByName(battle_talent.getString("final_img"),context));
                    buff_dmg_char_talent1_ico.setBackgroundResource(talentBg);
                    buff_dmg_char_talent2_ico.setBackgroundResource(talentBg);
                    buff_dmg_char_talent3_ico.setBackgroundResource(talentBg);

                    JSONObject sof = jsonObject.getJSONObject("sof");
                    buff_dmg_char_sof1_ico.setImageDrawable(item_rss.getTalentIcoByName(sof.getString("sof1_img"),context));
                    buff_dmg_char_sof2_ico.setImageDrawable(item_rss.getTalentIcoByName(sof.getString("sof2_img"),context));
                    buff_dmg_char_sof3_ico.setImageDrawable(item_rss.getTalentIcoByName(sof.getString("sof3_img"),context));
                    buff_dmg_char_sof4_ico.setImageDrawable(item_rss.getTalentIcoByName(sof.getString("sof4_img"),context));
                    buff_dmg_char_sof5_ico.setImageDrawable(item_rss.getTalentIcoByName(sof.getString("sof5_img"),context));
                    buff_dmg_char_sof6_ico.setImageDrawable(item_rss.getTalentIcoByName(sof.getString("sof6_img"),context));
                    buff_dmg_char_sof1_ico.setBackgroundResource(sofBg);
                    buff_dmg_char_sof2_ico.setBackgroundResource(sofBg);
                    buff_dmg_char_sof3_ico.setBackgroundResource(sofBg);
                    buff_dmg_char_sof4_ico.setBackgroundResource(sofBg);
                    buff_dmg_char_sof5_ico.setBackgroundResource(sofBg);
                    buff_dmg_char_sof6_ico.setBackgroundResource(sofBg);

                    buff_dmg_char_talent1_lvl.setText(String.valueOf(character.getCharTalentLvl()[0]));
                    buff_dmg_char_talent2_lvl.setText(String.valueOf(character.getCharTalentLvl()[1]));
                    buff_dmg_char_talent3_lvl.setText(String.valueOf(character.getCharTalentLvl()[2]));

                    if(character.getCharCon() >= 1){buff_dmg_char_sof1_ico.setAlpha(1f);}
                    if(character.getCharCon() >= 2){buff_dmg_char_sof2_ico.setAlpha(1f);}
                    if(character.getCharCon() >= 3){buff_dmg_char_sof3_ico.setAlpha(1f);}
                    if(character.getCharCon() >= 4){buff_dmg_char_sof4_ico.setAlpha(1f);}
                    if(character.getCharCon() >= 5){buff_dmg_char_sof5_ico.setAlpha(1f);}
                    if(character.getCharCon() >= 6){buff_dmg_char_sof6_ico.setAlpha(1f);}
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //Weapon
            Weapon weapon = buffObject.getWeapon();
            String WeaponName_BASE_UNDERSCORE = weapon.getWeaponName().replace(" ", "").replace("'", "").replace("-", "").toLowerCase();
            if(WeaponName_BASE_UNDERSCORE.equals(weapon.getWeaponName().toLowerCase())){
                WeaponName_BASE_UNDERSCORE = WeaponName_BASE_UNDERSCORE.substring(0, 1).toUpperCase() + WeaponName_BASE_UNDERSCORE.substring(1).toLowerCase();
            }

            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getWeaponByName(weapon.getWeaponName(),context)[1],context))
                    .transform(transformation)
                    .fit()
                    .error (R.drawable.paimon_full)
                    .into (buff_dmg_weapon_ico);
            switch (weapon.getWeaponRare()){
                case 1 : buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                case 2 : buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                case 3 : buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                case 4 : buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                case 5 : buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                default:  buff_dmg_weapon_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
            }
            buff_dmg_weapon_name.setText(item_rss.getWeaponByName(weapon.getWeaponName(),context)[0]);
            buff_dmg_weapon_lvl.setText("Lvl. "+String.valueOf(weapon.getWeaponLvl()));
            buff_dmg_weapon_affix_lvl.setText(context.getString(R.string.affix)+String.valueOf(weapon.getWeaponAffixLvl()));

            is_default = LoadData("db/weapons/en-US/" + WeaponName_BASE_UNDERSCORE + ".json");
            is = LoadData("db/weapons/" + lang + "/" + WeaponName_BASE_UNDERSCORE + ".json");
            if (is != null) {
                result1 = is;
            } else if (is_default != null) {
                result1 = is_default;
            }
            if(result1 != null){
                try {
                    JSONObject jsonObject = new JSONObject(result1);
                    String effect = jsonObject.getString("effect");
                    JSONArray effectValue = jsonObject.getJSONArray("r"+String.valueOf(weapon.getWeaponAffixLvl()));
                    ArrayList<String> effectValueList = new ArrayList<>();
                    for (int y = 0 ; y < effectValue.length() ; y++){
                        effect = effect.replace("{"+String.valueOf(y)+"}",String.valueOf(effectValue.get(y)));
                        effectValueList.add(String.valueOf(effectValue.get(y)));
                    }
                    buff_dmg_weapon_intro.setText(setSpanAndTv(effect, effectValueList), TextView.BufferType.SPANNABLE);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            //Artifact
            Artifact[] artifacts = {buffObject.getArtifactFlower(),buffObject.getArtifactPlume(),buffObject.getArtifactSand(),buffObject.getArtifactCirclet(),buffObject.getArtifactGoblet()};
            for (int y = 0 ; y < artifacts.length ; y++){
                Artifact artifact = artifacts[y];
                if(artifact != null){
                    View art_item = getLayoutInflater().inflate(R.layout.item_buff_artifact_sub_status_2048, null);
                    ImageView buff_dmg_art_ico = art_item.findViewById(R.id.buff_dmg_art_ico);
                    TextView buff_dmg_art_lvl = art_item.findViewById(R.id.buff_dmg_art_lvl);
                    ImageView buff_dmg_art_main_ico = art_item.findViewById(R.id.buff_dmg_art_main_ico);
                    TextView buff_dmg_art_main_value = art_item.findViewById(R.id.buff_dmg_art_main_value);
                    ImageView buff_dmg_art_sub1_ico = art_item.findViewById(R.id.buff_dmg_art_sub1_ico);
                    TextView buff_dmg_art_sub1_value = art_item.findViewById(R.id.buff_dmg_art_sub1_value);
                    ImageView buff_dmg_art_sub2_ico = art_item.findViewById(R.id.buff_dmg_art_sub2_ico);
                    TextView buff_dmg_art_sub2_value = art_item.findViewById(R.id.buff_dmg_art_sub2_value);
                    ImageView buff_dmg_art_sub3_ico = art_item.findViewById(R.id.buff_dmg_art_sub3_ico);
                    TextView buff_dmg_art_sub3_value = art_item.findViewById(R.id.buff_dmg_art_sub3_value);
                    ImageView buff_dmg_art_sub4_ico = art_item.findViewById(R.id.buff_dmg_art_sub4_ico);
                    TextView buff_dmg_art_sub4_value = art_item.findViewById(R.id.buff_dmg_art_sub4_value);

                    buff_dmg_art_lvl.setText(String.valueOf(artifact.getArtifactLvl()));
                    int artifactType = 1;
                    //[4,2,1,5,3]
                    switch (artifact.getArtifactType()) {
                        case Artifact.FLOWER : artifactType = 4; break;
                        case Artifact.PLUME : artifactType = 2; break;
                        case Artifact.SAND: artifactType = 1; break;
                        case Artifact.CIRCLET: artifactType = 5; break;
                        case Artifact.GOBLET: artifactType = 3; break;
                    }

                    Picasso.get()
                            .load (FileLoader.loadIMG(item_rss.getArtifactByName(artifact.getArtifactName(),context)[artifactType],context))
                            .transform(transformation)
                            .resize((int) (48*displayMetrics.density), (int) (48*displayMetrics.density))
                            .error (R.drawable.paimon_full)
                            .into (buff_dmg_art_ico);
                    switch (artifact.getArtifactRare()){
                        case 1 : buff_dmg_art_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                        case 2 : buff_dmg_art_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                        case 3 : buff_dmg_art_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                        case 4 : buff_dmg_art_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                        case 5 : buff_dmg_art_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                        default:  buff_dmg_art_ico.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                    }

                    buff_dmg_art_main_ico.setImageResource(buffCatelogy.getStatusIcoByName(artifact.getArtifactStatStr()[0]));
                    buff_dmg_art_sub1_ico.setImageResource(buffCatelogy.getStatusIcoByName(artifact.getArtifactStatStr()[1]));
                    buff_dmg_art_sub2_ico.setImageResource(buffCatelogy.getStatusIcoByName(artifact.getArtifactStatStr()[2]));
                    buff_dmg_art_sub3_ico.setImageResource(buffCatelogy.getStatusIcoByName(artifact.getArtifactStatStr()[3]));
                    buff_dmg_art_sub4_ico.setImageResource(buffCatelogy.getStatusIcoByName(artifact.getArtifactStatStr()[4]));
                    buff_dmg_art_main_value.setText(prettyBuffCount(artifact.getArtifactStatValue()[0],artifact.getArtifactStatStr()[0]));
                    buff_dmg_art_sub1_value.setText(prettyBuffCount(artifact.getArtifactStatValue()[1], artifact.getArtifactStatStr()[1]));
                    buff_dmg_art_sub2_value.setText(prettyBuffCount(artifact.getArtifactStatValue()[2],artifact.getArtifactStatStr()[2]));
                    buff_dmg_art_sub3_value.setText(prettyBuffCount(artifact.getArtifactStatValue()[3],artifact.getArtifactStatStr()[3]));
                    buff_dmg_art_sub4_value.setText(prettyBuffCount(artifact.getArtifactStatValue()[4],artifact.getArtifactStatStr()[4]));
                    buff_dmg_art_ll.addView(art_item);
                }
            }

            //TabLayout
            View view1 = activity.getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);

            TextView ico_tv = view1.findViewById(R.id.name);
            ico_tv.setVisibility(View.GONE);

            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getCharByName(character.getCharName(),context)[3],context))
                    .transform(transformation)
                    .resize((int) (48*displayMetrics.density),(int) (48*displayMetrics.density))
                    .error (R.drawable.paimon_full)
                    .into (ico_img);
            switch (character.getCharRare()){
                case 1 : ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                case 2 : ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                case 3 : ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                case 4 : ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                case 5 : ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                default:  ico_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
            }

            ico_img.setPadding((int) (displayMetrics.density*4),(int) (displayMetrics.density*4),(int) (displayMetrics.density*4),(int) (displayMetrics.density*4));
            team_tablayout.addTab(team_tablayout.newTab().setCustomView(view1).setId(x));


            //ValueBoard
            TextView buff_dmg_value_hp = view.findViewById(R.id.buff_dmg_value_hp);
            TextView buff_dmg_value_atk = view.findViewById(R.id.buff_dmg_value_atk);
            TextView buff_dmg_value_def = view.findViewById(R.id.buff_dmg_value_def);
            TextView buff_dmg_value_elemas = view.findViewById(R.id.buff_dmg_value_elemas);
            TextView buff_dmg_value_crit_rate = view.findViewById(R.id.buff_dmg_value_crit_rate);
            TextView buff_dmg_value_crit_dmg = view.findViewById(R.id.buff_dmg_value_crit_dmg);
            TextView buff_dmg_value_enrech = view.findViewById(R.id.buff_dmg_value_enrech);
            TextView buff_dmg_value_element_anemo_dmg = view.findViewById(R.id.buff_dmg_value_element_anemo_dmg);
            TextView buff_dmg_value_element_cryo_dmg = view.findViewById(R.id.buff_dmg_value_element_cryo_dmg);
            TextView buff_dmg_value_element_dendro_dmg = view.findViewById(R.id.buff_dmg_value_element_dendro_dmg);
            TextView buff_dmg_value_element_electro_dmg = view.findViewById(R.id.buff_dmg_value_element_electro_dmg);
            TextView buff_dmg_value_element_geo_dmg = view.findViewById(R.id.buff_dmg_value_element_geo_dmg);
            TextView buff_dmg_value_element_hydro_dmg = view.findViewById(R.id.buff_dmg_value_element_hydro_dmg);
            TextView buff_dmg_value_element_pyro_dmg = view.findViewById(R.id.buff_dmg_value_element_pyro_dmg);
            TextView buff_dmg_value_element_phy_dmg = view.findViewById(R.id.buff_dmg_value_element_phy_dmg);

            buff_dmg_value_hp.setText(prettyCountY(character.getCharMaxHP(),true));
            buff_dmg_value_atk.setText(prettyCountY(character.getCharATK(),true));
            buff_dmg_value_def.setText(prettyCountY(character.getCharDEF(),true));
            buff_dmg_value_elemas.setText(prettyCountY(character.getCharEleMas(),true));
            buff_dmg_value_crit_rate.setText(prettyCountY(character.getCharCritRate(),false));
            buff_dmg_value_crit_dmg.setText(prettyCountY(character.getCharCritDMG(),false));
            buff_dmg_value_enrech.setText(prettyCountY(character.getCharEnRech(),false));
            buff_dmg_value_element_anemo_dmg.setText(prettyCountY(character.getCharAnemoDMGP(),false));
            buff_dmg_value_element_cryo_dmg.setText(prettyCountY(character.getCharCryoDMGP(),false));
            buff_dmg_value_element_dendro_dmg.setText(prettyCountY(character.getCharDendroDMGP(),false));
            buff_dmg_value_element_electro_dmg.setText(prettyCountY(character.getCharElectroDMGP(),false));
            buff_dmg_value_element_geo_dmg.setText(prettyCountY(character.getCharGeoDMGP(),false));
            buff_dmg_value_element_hydro_dmg.setText(prettyCountY(character.getCharHydroDMGP(),false));
            buff_dmg_value_element_pyro_dmg.setText(prettyCountY(character.getCharPyroDMGP(),false));
            buff_dmg_value_element_phy_dmg.setText(prettyCountY(character.getCharPhyDMGP(),false));

            //DamageType
            String[] enemyType = {"丘丘人","丘丘Boss"};

            //

            dynamicView.add(view);
        }
    }



    public String prettyCountY(Number number, boolean isInt) {
        String suffix = "";
        if(isInt){
            return new DecimalFormat("#,###").format(number.longValue());
        }else{
            return new DecimalFormat("#,###.#").format(number.doubleValue()*100)+"%";
        }
    }

    public String prettyBuffCount(Number number, String buffTag) {
        String suffix = "";
        if(
                buffTag.equals(BuffObject.FIGHT_PROP_ATK) ||
                buffTag.equals(BuffObject.FIGHT_PROP_DEF) ||
                buffTag.equals(BuffObject.FIGHT_PROP_HP) ||
                buffTag.equals(BuffObject.FIGHT_PROP_BASE_ATK) ||
                buffTag.equals(BuffObject.FIGHT_PROP_ELE_MAS)){
            if(number.longValue() < 1000){
                return "+"+ new DecimalFormat("#,###.#").format(number);
            }else{
                return "+"+ new DecimalFormat("#,###").format(number);
            }
        }
        return "+"+new DecimalFormat("#,###.#").format(number)+"%";
    }

    public SpannableString setSpanAndTv(String str, ArrayList<String> value){
        SpannableString mSpannavleString = new SpannableString(str);

        for (int x = 0 ; x < value.size() ; x++){
            for (int i = -1; (i = str.indexOf(value.get(x), i + 1)) != -1; i++) {
                mSpannavleString.setSpan(new ForegroundColorSpan(context.getResources().getColor(R.color.buff_tv_main_2048)),str.indexOf(value.get(x)),str.indexOf(value.get(x))+value.get(x).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return mSpannavleString;
    }

    public String LoadData(String inFile) {
        String tContents = "";
        try {
            File file = new File(context.getFilesDir() + "/" + inFile);
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


    public class MyViewPagerAdapter extends PagerAdapter {
        private List<View> mListViews;

        public MyViewPagerAdapter(List<View> mListViews) {
            this.mListViews = mListViews;
        }

        @Override
        public void destroyItem(ViewGroup container, int position, Object object) {
            container.removeView((View) object);
        }

        @Override
        public Object instantiateItem(ViewGroup container, int position) {
            View view = mListViews.get(position);
            container.addView(view);
            return view;
        }

        @Override
        public int getCount() {
            return mListViews.size();
        }

        @Override
        public boolean isViewFromObject(View arg0, Object arg1) {
            return arg0 == arg1;
        }

    }
}
