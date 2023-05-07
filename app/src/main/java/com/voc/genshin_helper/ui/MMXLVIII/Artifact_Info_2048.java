package com.voc.genshin_helper.ui.MMXLVIII;

import static android.content.Context.MODE_PRIVATE;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.google.android.material.tabs.TabLayout.MODE_FIXED;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.MyViewPagerAdapter;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.util.ArrayList;

//https://stackoverflow.com/questions/3592836/check-for-file-existence-in-androids-assets-folder/7337516

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class Artifact_Info_2048 {
    /** Method of requirements */
    Context context;
    Activity activity;
    SharedPreferences sharedPreferences ;
    ItemRss item_rss;
    BackgroundReload backgroundReload;

    /** Method of Artifact's details' container */
    /** Since String can't be null, so there will have "XPR" for identify is result correct */
    String ArtifactName_BASE = "";

    // Main
    String name = "XPR" ;
    int star = 4;
    String artifact = "XPR" ;
    boolean isComing = false ;
    String desc = "XPR" ;
    String skill_name = "XPR" ;
    String skill_desc = "XPR" ;
    String obtain_way = "XPR" ;
    String status = "XPR" ;
    JSONObject jsonObject;
    JSONObject jsonObjectDps;

    // Basic Talent
    String talent_name = "XPR";
    String talent_img = "XPR";
    String talent_desc = "XPR";

    private ViewPager viewPager;
    private ArrayList<View> viewPager_List;
    View artifactDescPage, artifactSkillPage;

    String artifactSet1PC = "XPR";
    String artifactSet2PC = "XPR";
    String artifactSet4PC = "XPR";
    String[] artifactNameLocale = new String[5];
    String[] artifactDescLocale = new String[5];

    /** https://stackoverflow.com/questions/45247927/how-to-parse-json-object-inside-json-object-in-java */
    public void JsonToStr (String str){
        if(!str.equals("")){
            try {
                jsonObject = new JSONObject(str);
                name = ArtifactName_BASE;
                if (jsonObject.has("1pc")){
                    artifactSet1PC = jsonObject.getString("1pc");
                }else if(jsonObject.has("2pc") && jsonObject.has("4pc")){
                    artifactSet2PC = jsonObject.getString("2pc");
                    artifactSet4PC = jsonObject.getString("4pc");
                }
                artifactNameLocale[0] = jsonObject.getJSONObject("flower").getString("name");
                artifactNameLocale[1] = jsonObject.getJSONObject("plume").getString("name");
                artifactNameLocale[2] = jsonObject.getJSONObject("sands").getString("name");
                artifactNameLocale[3] = jsonObject.getJSONObject("goblet").getString("name");
                artifactNameLocale[4] = jsonObject.getJSONObject("circlet").getString("name");

                artifactDescLocale[0] = jsonObject.getJSONObject("flower").getString("description");
                artifactDescLocale[1] = jsonObject.getJSONObject("plume").getString("description");
                artifactDescLocale[2] = jsonObject.getJSONObject("sands").getString("description");
                artifactDescLocale[3] = jsonObject.getJSONObject("goblet").getString("description");
                artifactDescLocale[4] = jsonObject.getJSONObject("circlet").getString("description");

                star = Integer.parseInt(jsonObject.getJSONArray("rarity").getString(jsonObject.getJSONArray("rarity").length()-1));

                show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            CustomToast.toast(context,activity,context.getString(R.string.none_info));
        }
    }

    public void setup (String ArtifactName_BASE, Context context,Activity activity){
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        this.ArtifactName_BASE = ArtifactName_BASE.replace(" ","_");
        this.context = context;
        this.activity = activity;
        item_rss = new ItemRss();


        String lang = sharedPreferences.getString("curr_lang","zh-HK");
        String is = null;
        String is_dps = null;
        String is_default = null;

        //is_dps = LoadData("db/artifact/artifact_advice/"+this.ArtifactName_BASE+".json");
        is_default = LoadData("db/artifacts/en-US/"+this.ArtifactName_BASE+".json");
        is = LoadData("db/artifacts/"+lang+"/"+this.ArtifactName_BASE+".json");

        if(!is.equals("")){
            //JsonToStr(is,is_dps);
            JsonToStr(is);
        }else if(!is_default.equals("")){
            //JsonToStr(is_default,is_dps);
            JsonToStr(is_default);
        }
    }


    public void show() {
        final Dialog dialog = new Dialog(context, R.style.PageDialogStyle_P);
        View view = View.inflate(context, R.layout.fragment_artifact_info_frame_2048, null);
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height = displayMetrics.heightPixels;
        int width = displayMetrics.widthPixels;

        Window dialogWindowX = activity.getWindow();
        // 2O48 DESIGN
        dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        dialogWindowX.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindowX.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        /** Method of header */
        TabLayout info_tablelayout = view.findViewById(R.id.info_tablelayout);
        ImageView info_back_btn = view.findViewById(R.id.info_back_btn);
        ImageView info_header_bg = view.findViewById(R.id.info_header_bg);
        viewPager = (ViewPager) view.findViewById(R.id.vp);

        final LayoutInflater mInflater = activity.getLayoutInflater().from(context);
        artifactDescPage = mInflater.inflate(R.layout.fragment_artifact_info_desc_2048, null,false);
        artifactSkillPage = mInflater.inflate(R.layout.fragment_artifact_info_skill_2048, null,false);

        viewPager_List = new ArrayList<View>();
        viewPager_List.add(artifactDescPage);
        viewPager_List.add(artifactSkillPage);
        viewPager.setAdapter(new MyViewPagerAdapter(viewPager_List));

        BackgroundReload.BackgroundReload(context,view);

        /** Method of info_detail */
        ConstraintLayout art_con = artifactDescPage.findViewById(R.id.art_con);
        ImageView artifact_img = artifactDescPage.findViewById(R.id.info_artifact_img);
        ImageView artifact_img1 = artifactDescPage.findViewById(R.id.info_artifact_img1);
        ImageView artifact_img2 = artifactDescPage.findViewById(R.id.info_artifact_img2);
        ImageView artifact_img3 = artifactDescPage.findViewById(R.id.info_artifact_img3);
        ImageView artifact_img4 = artifactDescPage.findViewById(R.id.info_artifact_img4);
        ImageView artifact_img5 = artifactDescPage.findViewById(R.id.info_artifact_img5);
        TextView artifact_name = artifactDescPage.findViewById(R.id.info_artifact_name);
        TextView artifact_name_base = artifactDescPage.findViewById(R.id.info_artifact_name_base);
        TextView artifact_obtain_way_tv = artifactDescPage.findViewById(R.id.info_obtain_way_tv);
        RatingBar artifact_stars = artifactDescPage.findViewById(R.id.info_stars);

        TextView info_1pc_desc_info = artifactSkillPage.findViewById(R.id.info_1pc_desc_info);
        TextView info_2pc_desc_info = artifactSkillPage.findViewById(R.id.info_2pc_desc_info);
        TextView info_4pc_desc_info = artifactSkillPage.findViewById(R.id.info_4pc_desc_info);
        TextView info_1pc_desc_title = artifactSkillPage.findViewById(R.id.info_1pc_desc_title);
        TextView info_2pc_desc_title = artifactSkillPage.findViewById(R.id.info_2pc_desc_title);
        TextView info_4pc_desc_title= artifactSkillPage.findViewById(R.id.info_4pc_desc_title);

        TextView info_each_title1 = artifactSkillPage.findViewById(R.id.info_each_title1);
        TextView info_each_title2 = artifactSkillPage.findViewById(R.id.info_each_title2);
        TextView info_each_title3 = artifactSkillPage.findViewById(R.id.info_each_title3);
        TextView info_each_title4 = artifactSkillPage.findViewById(R.id.info_each_title4);
        TextView info_each_title5 = artifactSkillPage.findViewById(R.id.info_each_title5);
        TextView info_each_info1 = artifactSkillPage.findViewById(R.id.info_each_info1);
        TextView info_each_info2 = artifactSkillPage.findViewById(R.id.info_each_info2);
        TextView info_each_info3 = artifactSkillPage.findViewById(R.id.info_each_info3);
        TextView info_each_info4 = artifactSkillPage.findViewById(R.id.info_each_info4);
        TextView info_each_info5 = artifactSkillPage.findViewById(R.id.info_each_info5);

        if(artifactSet1PC.equals("XPR")){
            info_1pc_desc_info.setVisibility(View.GONE);
            info_1pc_desc_title.setVisibility(View.GONE);
        }
        if (artifactSet2PC.equals("XPR") && artifactSet4PC.equals("XPR")){
            info_2pc_desc_info.setVisibility(View.GONE);
            info_4pc_desc_info.setVisibility(View.GONE);
            info_2pc_desc_title.setVisibility(View.GONE);
            info_4pc_desc_title.setVisibility(View.GONE);
        }

        info_1pc_desc_info.setText(artifactSet1PC);
        info_2pc_desc_info.setText(artifactSet2PC);
        info_4pc_desc_info.setText(artifactSet4PC);

        info_each_title1.setText(artifactNameLocale[0]);
        info_each_title2.setText(artifactNameLocale[1]);
        info_each_title3.setText(artifactNameLocale[2]);
        info_each_title4.setText(artifactNameLocale[3]);
        info_each_title5.setText(artifactNameLocale[4]);
        info_each_info1.setText(artifactDescLocale[0]);
        info_each_info2.setText(artifactDescLocale[1]);
        info_each_info3.setText(artifactDescLocale[2]);
        info_each_info4.setText(artifactDescLocale[3]);
        info_each_info5.setText(artifactDescLocale[4]);

        /** Method of battle_talent */

        /** THEME COLOR SET*/
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #
        boolean isColorGradient = sharedPreferences.getBoolean("theme_color_gradient",false); // Must include #
        String start_color = sharedPreferences.getString("start_color","#AEFEFF"); // Must include #
        String end_color = sharedPreferences.getString("end_color","#35858B"); // Must include #

        if(isColorGradient){
            color_hex = start_color;
        }

        ColorStateList myList ;

        if (isColorGradient){
            myList= new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_pressed},
                            new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked},
                    },
                    new int[] {
                            Color.parseColor(start_color),
                            Color.parseColor(start_color),
                            Color.parseColor(end_color)
                    }
            );
        }else{
            myList = new ColorStateList(
                    new int[][]{
                            new int[]{android.R.attr.state_pressed},
                            new int[]{-android.R.attr.state_checked},
                            new int[]{android.R.attr.state_checked},
                    },
                    new int[] {
                            context.getResources().getColor(R.color.tv_color),
                            context.getResources().getColor(R.color.tv_color),
                            Color.parseColor(color_hex)
                    }
            );
        }

        //TextView barrier1 = view.findViewById(R.id.barrier1);
        //TextView barrier2 = view.findViewById(R.id.barrier2);
        //TextView barrier3 = view.findViewById(R.id.barrier3);
        //TextView barrier4 = view.findViewById(R.id.barrier4);
        // TextView barrier5 = view.findViewById(R.id.barrier5);
        //TextView info_intro_title = view.findViewById(R.id.info_intro_title);
        //TextView info_talent = view.findViewById(R.id.info_talent);
        //TextView info_btalent = view.findViewById(R.id.info_btalent);
        //TextView info_sof = view.findViewById(R.id.info_sof);
        //TextView info_advice = view.findViewById(R.id.info_advice);

        /*
        colorGradient(info_talent1_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent1_normal_title ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent1_hard_title ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent1_normal_title ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent1_drop_title ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent2_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent3_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_talent4_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_btalent1_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_btalent2_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_btalent3_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof1_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof2_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof3_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof4_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof5_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_sof6_name ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_advice_main_art_info ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_advice_support_art_info ,start_color,end_color,isColorGradient,color_hex);
        colorGradient(info_advice_util_art_info ,start_color,end_color,isColorGradient,color_hex);
         */

        /**
         * PLS REMEMBER ADD BACK SUGGESTED WEAPON,ART IN XML
         */

        /** HEADER */
        int[] tabItemImageArray = new int[]{R.drawable.ic_2048_artifact_intro_btn,R.drawable.ic_2048_talent_btn};
        int[] tabItemImageSelectedArray = new int[]{R.drawable.ic_2048_artifact_intro_btn_selected,R.drawable.ic_2048_talent_btn_selected};

        for (int x = 0 ; x < 2 ; x++){
            View view1 = activity.getLayoutInflater().inflate(R.layout.item_custom_tab, null);
            ImageView ico_img = view1.findViewById(R.id.icon);
            ico_img.setImageResource(tabItemImageArray[x]);
            TextView ico_tv = view1.findViewById(R.id.name);
            ico_tv.setVisibility(View.GONE);
            info_tablelayout.addTab(info_tablelayout.newTab().setCustomView(view1).setId(x));
        }

        info_header_bg.getLayoutParams().height = info_tablelayout.getLayoutParams().height;

        info_tablelayout.selectTab(info_tablelayout.getTabAt(0));

        View view1 = info_tablelayout.getTabAt(0).getCustomView();
        ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
        tab_icon.setImageResource(tabItemImageSelectedArray[0]);

        info_tablelayout.setTabMode(MODE_FIXED);
        info_tablelayout.setTabIndicatorAnimationMode(TabLayout.INDICATOR_ANIMATION_MODE_ELASTIC);
        info_tablelayout.getLayoutParams().width = WRAP_CONTENT;
        info_tablelayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(tabItemImageSelectedArray[tab.getPosition()]);

                viewPager.setCurrentItem(tab.getPosition());
            }


            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                View view1 = tab.getCustomView();
                ImageView tab_icon = (ImageView) view1.findViewById(R.id.icon);
                tab_icon.setImageResource(tabItemImageArray[tab.getPosition()]);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });

        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                //info_tablelayout.selectTab(info_tablelayout.getTabAt(position));
            }

            @Override
            public void onPageSelected(int position) {
                info_tablelayout.selectTab(info_tablelayout.getTabAt(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });


        info_back_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
            }
        });

        /** MAIN */

        artifact_name.setText(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[0]);
        if(sharedPreferences.getBoolean("isBaseNameDisplay",false) == true){
            artifact_name_base.setVisibility(View.VISIBLE);
            artifact_name_base.setText(name);
        }
        artifact_obtain_way_tv.setText(item_rss.getObtainCode(obtain_way,context));
        //artifact_title.setText(nick);
        //Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(name,context)[0],context)).centerCrop().into(artifact_img);

        displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_curr = displayMetrics.heightPixels;
        int width_curr = displayMetrics.widthPixels;

        /**
         * Animation
         */

        /*
        Animation animImgLTR = AnimationUtils.loadAnimation(context,R.anim.img_ltr);
        Animation animImgRTL = AnimationUtils.loadAnimation(context,R.anim.img_rtl);
        art_con.setAnimation(animImgLTR);
        LinearLayout info_detail = artifactDescPage.findViewById(R.id.info_detail);
        info_detail.setAnimation(animImgRTL);

         */

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){

            ConstraintLayout art_imgL = view.findViewById(R.id.art_con);
            /**
             * Animation
             */
            /*art_imgL.setAnimation(animImgLTR); */
            ImageView artifact_imgL = view.findViewById(R.id.info_artifact_img);
            ImageView artifact_img1L = view.findViewById(R.id.info_artifact_img1);
            ImageView artifact_img2L = view.findViewById(R.id.info_artifact_img2);
            ImageView artifact_img3L = view.findViewById(R.id.info_artifact_img3);
            ImageView artifact_img4L = view.findViewById(R.id.info_artifact_img4);
            ImageView artifact_img5L = view.findViewById(R.id.info_artifact_img5);
            if (!artifactSet1PC.equals("XPR")){
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[1],context)).into(artifact_imgL);
            }else if (!artifactSet2PC.equals("XPR") && !artifactSet4PC.equals("XPR")){
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[1],context)).into(artifact_img1L);
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[2],context)).into(artifact_img2L);
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[3],context)).into(artifact_img3L);
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[4],context)).into(artifact_img4L);
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[5],context)).into(artifact_img5L);
            }
            art_imgL.getLayoutParams().width = width;
        }else{
            if (!artifactSet1PC.equals("XPR")){
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[1],context)).into(artifact_img);
            }else if (!artifactSet2PC.equals("XPR") && !artifactSet4PC.equals("XPR")){
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[1],context)).into(artifact_img1);
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[2],context)).into(artifact_img2);
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[3],context)).into(artifact_img3);
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[4],context)).into(artifact_img4);
                Picasso.get().load(FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(name),context)[5],context)).into(artifact_img5);
            }
            art_con.getLayoutParams().width = width;
        }

        //artifact_img.setBackgroundResource(item_rss.getElementByName(element)[2]);

        boolean isNight = false;
        // Background of item
        if (context.getResources().getString(R.string.mode).equals("Night")) {
            isNight = true;
        }

        artifact_stars.setNumStars(star);
        artifact_stars.setRating(star);
        //artifact_element.setImageResource(item_rss.getElementByName(element)[0]);
        //artifact_area.setText(item_rss.getLocaleName(area,context));
        //artifact_area_ico.setImageResource(item_rss.getDistrictIMG(area));
        //artifact_role.setText(item_rss.getLocaleName(role,context));
        //artifact_sex.setText(item_rss.getLocaleName(sex,context));
        //artifact_birth.setText(item_rss.getLocaleBirth(birth,context)); // -> If necessary will change to Month | Day -> E.g. July 24th
        //info_intro.setText(desc);

        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.ALL);


        /** Method of dialog */
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    private boolean isType0(double v) {
        if ((int) v == v){
            return true;
        }
        return false;
    }

    public Bitmap findArtifactByName(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        filePath = filePath.replace("\"","");
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            if(e != null){Log.wtf("MISSED_IMG","暫時沒有 >>"+filePath+"<< 的相片");};
        }

        return bitmap;
    }

    public Bitmap findArtByName(Context context, String filePath) {
        AssetManager assetManager = context.getAssets();
        filePath = filePath.replace("\"","");
        InputStream istr;
        Bitmap bitmap = null;
        try {
            istr = assetManager.open(filePath);
            bitmap = BitmapFactory.decodeStream(istr);
        } catch (IOException e) {
            if(e != null){Log.wtf("MISSED_IMG","暫時沒有 >>"+filePath+"<< 的相片");};
        }

        return bitmap;
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

    public void colorGradient(TextView textView,String start_color, String end_color, boolean isColorGradient , String color){
        if(isColorGradient){
            Shader shader = new LinearGradient(0, 0, textView.getLineCount(), textView.getLineHeight(),
                    Color.parseColor(start_color),  Color.parseColor(end_color), Shader.TileMode.CLAMP);
            textView.getPaint().setShader(shader);
            textView.setTextColor(Color.parseColor(start_color));
        }else{
            textView.setTextColor(Color.parseColor(color));
        }

    }

    public void setAdviceText(String part, LinearLayout info_main_ll, TextView info_main_sand_tv, TextView info_main_goblet_tv, TextView info_main_circlet_tv, TextView info_main_sub_tv){
        String dps_advice = "";
        info_main_ll.setVisibility(View.VISIBLE);

        if(jsonObjectDps.has(part+"_art_sand_entry")){
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_sand_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+" > ";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info_main_sand_tv.setText(dps_advice);
            info_main_sand_tv.setVisibility(View.VISIBLE);
        }

        dps_advice = "";
        if(jsonObjectDps.has(part+"_art_goblet_entry")){
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_goblet_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+" > ";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info_main_goblet_tv.setText(dps_advice);
            info_main_goblet_tv.setVisibility(View.VISIBLE);
        }

        dps_advice = "";
        if(jsonObjectDps.has(part+"_art_circlet_entry")){
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_circlet_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+" > ";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info_main_circlet_tv.setText(dps_advice);
            info_main_circlet_tv.setVisibility(View.VISIBLE);
        }
        dps_advice = "";
        if(jsonObjectDps.has(part+"_art_sub_entry")){
            try {
                JSONArray dps_advice_tmpJ = jsonObjectDps.getJSONArray(part+"_art_sub_entry");
                String[] dps_advice_tmp = dps_advice_tmpJ.toString().replace("[", "").replace("]", "").replace(",", " ").replace("\"", "").split(" ");
                for (int x = 0 ; x < dps_advice_tmp.length ; x++){
                    dps_advice = dps_advice+item_rss.getArtifactBuffName(dps_advice_tmp[x],context);
                    if(x+1 < dps_advice_tmpJ.length()){
                        dps_advice = dps_advice+" > ";
                    }
                }
            } catch (JSONException e) {
                e.printStackTrace();
            }
            info_main_sub_tv.setText(dps_advice);
            info_main_sub_tv.setVisibility(View.VISIBLE);
        }
        System.out.println(dps_advice);
    }

    public SpannableString setSpanAndTv(ForegroundColorSpan mColor, String str, String mWord){
        SpannableString mSpannavleString = new SpannableString(str);
        for (int i = -1; (i = str.indexOf(mWord, i + 1)) != -1; i++) {
            mSpannavleString.setSpan(mColor,str.indexOf(mWord),str.indexOf(mWord)+mWord.length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
        }
        return mSpannavleString;
    }

    /** UI -> PrettyCount */
    /**
     * @param type : Type of number's display
     *             0 : Int display
     *             1 : Percentage display
     * @return
     */
    public String prettyCount(Number number,int type) {
        char[] suffix = {' ', 'K', 'M', 'G', 'T', 'P', 'E', 'Z', 'Y'};
        double numDouble = number.doubleValue();
        String plus = "";
        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info", Context.MODE_PRIVATE);
        int decimal_num =  sharedPreferences.getInt("decimal_num", 0);
        boolean decimal  = sharedPreferences.getBoolean("decimal", false);

        switch (type){
            case 0 : return plus+new DecimalFormat("###,###,###,###,###").format(number);
            case 1 : return plus+(new DecimalFormat("###,###,###,###,###.##").format(numDouble*100))+"%";
            default: return plus+new DecimalFormat("###,###,###,###,###.#").format(number);
        }
    }


}
