package com.voc.genshin_helper.ui.MMXLVIII;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

import static android.view.ViewGroup.LayoutParams.MATCH_PARENT;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.os.Handler;
import android.text.SpannableString;
import android.text.Spanned;
import android.text.style.ForegroundColorSpan;
import android.util.DisplayMetrics;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.Transformation;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;

import com.alibaba.fastjson2.JSON;
import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.TCG;
import com.voc.genshin_helper.util.CustomTextView;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import pl.droidsonroids.gif.GifDrawable;
import pl.droidsonroids.gif.GifImageView;

public class TCG_Info_2048 {

    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;
    String itemName = "N/A";
    int[] screenPos = new int[2];
    ItemRss item_rss;
    TCG tcg;

    ImageView tcg_press_mask ;
    TextView tcg_card_name, tcg_card_name_base;
    ImageView tcg_card_kwang;
    GifImageView tcg_card_img;
    ImageView tcg_hp_bg, tcg_dice_bg;
    CustomTextView tcg_hp_tv, tcg_dice_tv;
    FrameLayout tcg_card_item, tcg_card_hp, tcg_card_dice;
    View tcg_card_include;
    LinearLayout tcg_detail_ll;
    LinearLayout tcg_intro_ll;
    ConstraintLayout tcg_scroll_sc;

    TextView tcg_intro_tv, tcg_intro_type, tcg_intro_source, tcg_intro_location;
    ImageView tcg_intro_element, tcg_intro_weapon;
    LinearLayout tcg_nonchar_ll, tcg_normal_ll, tcg_normal2_ll, tcg_element_ll, tcg_element2_ll, tcg_final_ll, tcg_other_ll;
    View view4;

    FrameLayout tcg_normal_element, tcg_normal_spec, tcg_normal_rand, tcg_normal_recharge;
    ImageView tcg_normal_ico, tcg_normal_element_ico;
    TextView tcg_normal_name, tcg_normal_info, tcg_normal_element_tv, tcg_normal_spec_tv, tcg_normal_rand_tv, tcg_normal_recharge_tv;
    FrameLayout tcg_normal2_element, tcg_normal2_spec, tcg_normal2_rand, tcg_normal2_recharge;
    ImageView tcg_normal2_ico, tcg_normal2_element_ico;
    TextView tcg_normal2_name, tcg_normal2_info, tcg_normal2_element_tv, tcg_normal2_spec_tv, tcg_normal2_rand_tv, tcg_normal2_recharge_tv;
    FrameLayout tcg_element_element, tcg_element_spec, tcg_element_rand, tcg_element_recharge;
    ImageView tcg_element_ico, tcg_element_element_ico;
    TextView tcg_element_name, tcg_element_info, tcg_element_element_tv, tcg_element_spec_tv, tcg_element_rand_tv, tcg_element_recharge_tv;
    FrameLayout tcg_element2_element, tcg_element2_spec, tcg_element2_rand, tcg_element2_recharge;
    ImageView tcg_element2_ico, tcg_element2_element_ico;
    TextView tcg_element2_name, tcg_element2_info, tcg_element2_element_tv, tcg_element2_spec_tv, tcg_element2_rand_tv, tcg_element2_recharge_tv;
    FrameLayout tcg_final_element, tcg_final_spec, tcg_final_rand, tcg_final_recharge;
    ImageView tcg_final_ico, tcg_final_element_ico;
    TextView tcg_final_name, tcg_final_info, tcg_final_element_tv, tcg_final_spec_tv, tcg_final_rand_tv, tcg_final_recharge_tv;
    ImageView tcg_other_ico;
    TextView tcg_other_name, tcg_other_info;
    TextView tcg_nonchar_info;

    LinearLayout tcg_card_sample;

    //Information
    JSONObject jsonObject;

    int tcg_width = 1;
    int split = 1;
    DisplayMetrics displayMetrics;

    FrameLayout tcg_card_from_adapter;

    ArrayList<String> talentName = new ArrayList<>();

    boolean isCardAdapterGONE = false;

    public void setup(FrameLayout tcg_card,TCG tcg,int tcg_width,Context context,Activity activity,SharedPreferences sharedPreferences,SharedPreferences.Editor editor, int[] screenPos) {
        this.context = context;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;
        this.editor = editor;
        this.screenPos = screenPos;
        this.tcg = tcg;
        this.tcg_card_from_adapter = tcg_card;
        this.tcg_width = tcg_width;
        talentName.clear();
        String lang = sharedPreferences.getString("curr_lang","zh-HK");
        String is = null;
        String is_dps = null;
        String is_default = null;

        //is_dps = LoadData("db/weapon/weapon_advice/"+this.WeaponName_BASE+".json");
        is_default = LoadData("db/tcg/en-US/"+this.tcg.getFileName().replace("_","")+".json");
        is = LoadData("db/tcg/"+lang+"/"+this.tcg.getFileName().replace("_","")+".json");

        if(is != null){
            JsonToStr(is,tcg);
        }else if(is_default != null){
            JsonToStr(is_default,tcg);
        }
    }

    public void show() {
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_tcg_detail, null);
        displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_curr = displayMetrics.heightPixels;
        int width_curr = displayMetrics.widthPixels;
        item_rss = new ItemRss();

        /** Method of tcg_detail*/
        tcg_card_include = view.findViewById(R.id.tcg_card_include);
        tcg_card_sample = view.findViewById(R.id.tcg_card_sample);
        tcg_detail_ll = view.findViewById(R.id.tcg_detail_ll);
        tcg_intro_ll = view.findViewById(R.id.tcg_intro_ll);
        tcg_scroll_sc = view.findViewById(R.id.tcg_scroll_sc);
        tcg_normal_ll = view.findViewById(R.id.tcg_normal_ll);
        tcg_normal2_ll = view.findViewById(R.id.tcg_normal2_ll);
        tcg_element_ll = view.findViewById(R.id.tcg_element_ll);
        tcg_element2_ll = view.findViewById(R.id.tcg_element2_ll);
        tcg_final_ll = view.findViewById(R.id.tcg_final_ll);
        tcg_other_ll = view.findViewById(R.id.tcg_other_ll);
        tcg_nonchar_ll = view.findViewById(R.id.tcg_nonchar_ll);
        view4 = view.findViewById(R.id.view4);

        tcg_nonchar_info = view.findViewById(R.id.tcg_nonchar_info);

        tcg_intro_tv = view.findViewById(R.id.tcg_intro_tv);
        tcg_intro_type = view.findViewById(R.id.tcg_intro_type);
        tcg_intro_source = view.findViewById(R.id.tcg_intro_source);
        tcg_intro_location = view.findViewById(R.id.tcg_intro_location);
        tcg_intro_element = view.findViewById(R.id.tcg_intro_element);
        tcg_intro_weapon = view.findViewById(R.id.tcg_intro_weapon);

        tcg_normal_element = view.findViewById(R.id.tcg_normal_element);
        tcg_normal_spec = view.findViewById(R.id.tcg_normal_spec);
        tcg_normal_rand = view.findViewById(R.id.tcg_normal_rand);
        tcg_normal_ico = view.findViewById(R.id.tcg_normal_ico);
        tcg_normal_element_ico = view.findViewById(R.id.tcg_normal_element_ico);
        tcg_normal_name = view.findViewById(R.id.tcg_normal_name);
        tcg_normal_info = view.findViewById(R.id.tcg_normal_info);
        tcg_normal_element_tv = view.findViewById(R.id.tcg_normal_element_tv);
        tcg_normal_spec_tv = view.findViewById(R.id.tcg_normal_spec_tv);
        tcg_normal_rand_tv = view.findViewById(R.id.tcg_normal_rand_tv);
        tcg_normal_recharge = view.findViewById(R.id.tcg_normal_recharge);
        tcg_normal_recharge_tv = view.findViewById(R.id.tcg_normal_recharge_tv);

        tcg_normal2_element = view.findViewById(R.id.tcg_normal2_element);
        tcg_normal2_spec = view.findViewById(R.id.tcg_normal2_spec);
        tcg_normal2_rand = view.findViewById(R.id.tcg_normal2_rand);
        tcg_normal2_ico = view.findViewById(R.id.tcg_normal2_ico);
        tcg_normal2_element_ico = view.findViewById(R.id.tcg_normal2_element_ico);
        tcg_normal2_name = view.findViewById(R.id.tcg_normal2_name);
        tcg_normal2_info = view.findViewById(R.id.tcg_normal2_info);
        tcg_normal2_element_tv = view.findViewById(R.id.tcg_normal2_element_tv);
        tcg_normal2_spec_tv = view.findViewById(R.id.tcg_normal2_spec_tv);
        tcg_normal2_rand_tv = view.findViewById(R.id.tcg_normal2_rand_tv);
        tcg_normal2_recharge = view.findViewById(R.id.tcg_normal2_recharge);
        tcg_normal2_recharge_tv = view.findViewById(R.id.tcg_normal2_recharge_tv);

        tcg_element_element = view.findViewById(R.id.tcg_element_element);
        tcg_element_spec = view.findViewById(R.id.tcg_element_spec);
        tcg_element_rand = view.findViewById(R.id.tcg_element_rand);
        tcg_element_ico = view.findViewById(R.id.tcg_element_ico);
        tcg_element_element_ico = view.findViewById(R.id.tcg_element_element_ico);
        tcg_element_name = view.findViewById(R.id.tcg_element_name);
        tcg_element_info = view.findViewById(R.id.tcg_element_info);
        tcg_element_element_tv = view.findViewById(R.id.tcg_element_element_tv);
        tcg_element_spec_tv = view.findViewById(R.id.tcg_element_spec_tv);
        tcg_element_rand_tv = view.findViewById(R.id.tcg_element_rand_tv);
        tcg_element_recharge = view.findViewById(R.id.tcg_element_recharge);
        tcg_element_recharge_tv = view.findViewById(R.id.tcg_element_recharge_tv);

        tcg_element2_element = view.findViewById(R.id.tcg_element2_element);
        tcg_element2_spec = view.findViewById(R.id.tcg_element2_spec);
        tcg_element2_rand = view.findViewById(R.id.tcg_element2_rand);
        tcg_element2_ico = view.findViewById(R.id.tcg_element2_ico);
        tcg_element2_element_ico = view.findViewById(R.id.tcg_element2_element_ico);
        tcg_element2_name = view.findViewById(R.id.tcg_element2_name);
        tcg_element2_info = view.findViewById(R.id.tcg_element2_info);
        tcg_element2_element_tv = view.findViewById(R.id.tcg_element2_element_tv);
        tcg_element2_spec_tv = view.findViewById(R.id.tcg_element2_spec_tv);
        tcg_element2_rand_tv = view.findViewById(R.id.tcg_element2_rand_tv);
        tcg_element2_recharge = view.findViewById(R.id.tcg_element2_recharge);
        tcg_element2_recharge_tv = view.findViewById(R.id.tcg_element2_recharge_tv);

        tcg_final_element = view.findViewById(R.id.tcg_final_element);
        tcg_final_spec = view.findViewById(R.id.tcg_final_spec);
        tcg_final_rand = view.findViewById(R.id.tcg_final_rand);
        tcg_final_ico = view.findViewById(R.id.tcg_final_ico);
        tcg_final_element_ico = view.findViewById(R.id.tcg_final_element_ico);
        tcg_final_name = view.findViewById(R.id.tcg_final_name);
        tcg_final_info = view.findViewById(R.id.tcg_final_info);
        tcg_final_element_tv = view.findViewById(R.id.tcg_final_element_tv);
        tcg_final_spec_tv = view.findViewById(R.id.tcg_final_spec_tv);
        tcg_final_rand_tv = view.findViewById(R.id.tcg_final_rand_tv);
        tcg_final_recharge = view.findViewById(R.id.tcg_final_recharge);
        tcg_final_recharge_tv = view.findViewById(R.id.tcg_final_recharge_tv);

        tcg_other_ico = view.findViewById(R.id.tcg_other_ico);
        tcg_other_name = view.findViewById(R.id.tcg_other_name);
        tcg_other_info = view.findViewById(R.id.tcg_other_info);

        try {
            tcg_info_setup();
        } catch (JSONException e) {
            e.printStackTrace();
        }


        /** Method of tcg_card*/
        tcg_hp_bg = view.findViewById(R.id.tcg_hp_bg);
        tcg_dice_bg = view.findViewById(R.id.tcg_dice_bg);
        tcg_card_name = view.findViewById(R.id.tcg_card_name);
        tcg_card_name_base = view.findViewById(R.id.tcg_card_name_base);
        tcg_card_img = view.findViewById(R.id.tcg_card_img);
        tcg_card_kwang = view.findViewById(R.id.tcg_card_kwang);
        tcg_hp_tv = view.findViewById(R.id.tcg_hp_tv);
        tcg_dice_tv = view.findViewById(R.id.tcg_dice_tv);
        tcg_card_item = view.findViewById(R.id.tcg_card_item);
        tcg_card_hp = view.findViewById(R.id.tcg_card_hp);
        tcg_card_dice = view.findViewById(R.id.tcg_card_dice);
        tcg_press_mask = view.findViewById(R.id.tcg_press_mask);

        // init tcg_card
        split = 0;
        isCardAdapterGONE = true;
        tcg_card_change();

        tcg_detail_ll.setY(displayMetrics.widthPixels - displayMetrics.density*(120) - ((displayMetrics.widthPixels - displayMetrics.density*(32))/2));
        tcg_intro_ll.setAlpha(0f);
        tcg_detail_ll.setAlpha(0f);
        tcg_card_sample.getLayoutParams().height = (int) (((displayMetrics.widthPixels - displayMetrics.density*(32))/2)*12/7);
        // init tcg_detail -> ANIMATION
        tcg_card_include.setX(screenPos[0]);
        tcg_card_include.setY(screenPos[1]-displayMetrics.density*(84));
        tcg_card_include.getLayoutParams().width = (int) (tcg_width-displayMetrics.density*(10));
        tcg_card_include.getLayoutParams().height = (int)(tcg_width*12/7-displayMetrics.density*(18));
        Animation ani = new ZoomAnimation(
                tcg_card_include,
                (int) (screenPos[0]),
                (int) (screenPos[1]-displayMetrics.density*(24)),
                (int) tcg_width,
                (int) ((displayMetrics.widthPixels - displayMetrics.density*(32))/2),
                false);
        ani.setDuration(300);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
                tcg_card_from_adapter.animate()
                        .alpha(0.0f)
                        .setDuration(50)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                                tcg_card_from_adapter.setVisibility(View.INVISIBLE);
                                tcg_card_from_adapter.setAlpha(1.0f);
                            }
                        });

                tcg_detail_ll.animate()
                        .alpha(1.0f)
                        .translationY(0)
                        .setDuration(250)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        });
                tcg_intro_ll.animate()
                        .alpha(1.0f)
                        .setDuration(250)
                        .setListener(new AnimatorListenerAdapter() {
                            @Override
                            public void onAnimationEnd(Animator animation) {
                                super.onAnimationEnd(animation);
                            }
                        });
            }

            @Override
            public void onAnimationEnd(Animation animation) {
                //smallZoom();
                System.out.println("ACE");
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        tcg_card_include.startAnimation(ani);
        tcg_card_dx();


        /** Method of dialog */
        DialogInterface.OnKeyListener keyListener = new DialogInterface.OnKeyListener() {
            @Override
            public boolean onKey(DialogInterface dialog, int keyCode, KeyEvent event) {
                if (isCardAdapterGONE && tcg_card_from_adapter != null){
                    isCardAdapterGONE = false;

                    tcg_card_change();
                    Animation ani = new ZoomAnimation(
                            tcg_card_include,
                            (int) (screenPos[0]),
                            (int) (screenPos[1]-displayMetrics.density*(24)),
                            (int) tcg_width,
                            (int) ((displayMetrics.widthPixels - displayMetrics.density*(32))/2),
                            true);
                    ani.setDuration(300);
                    ani.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {
                            Handler handler = new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    tcg_card_from_adapter.animate()
                                            .alpha(1.0f)
                                            .setDuration(50)
                                            .setListener(new AnimatorListenerAdapter() {
                                                @Override
                                                public void onAnimationEnd(Animator animation) {
                                                    super.onAnimationEnd(animation);
                                                    tcg_card_from_adapter.setVisibility(View.VISIBLE);
                                                    if (dialog != null){
                                                        dialog.dismiss();
                                                    }
                                                }
                                            });
                                }
                            },250);
                            tcg_detail_ll.animate()
                                    .alpha(0.0f)
                                    .translationY(displayMetrics.widthPixels - displayMetrics.density*(120) - ((displayMetrics.widthPixels - displayMetrics.density*(32))/2))
                                    .setDuration(250)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                        }
                                    });
                            tcg_intro_ll.animate()
                                    .alpha(0.0f)
                                    .setDuration(250)
                                    .setListener(new AnimatorListenerAdapter() {
                                        @Override
                                        public void onAnimationEnd(Animator animation) {
                                            super.onAnimationEnd(animation);
                                        }
                                    });
                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {
                            //smallZoom();
                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {

                        }
                    });
                    tcg_card_include.startAnimation(ani);
                    tcg_card_dx();
                    return true;
                }
                return false;
            }
        };
        dialog.setOnKeyListener(keyListener);
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        // 2O48 DESIGN
        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048_less));
        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    public void JsonToStr (String str, TCG tcg){
        if(!str.equals("")){
            try {
                jsonObject = new JSONObject(str);
                show();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }else{
            CustomToast.toast(context,activity,context.getString(R.string.none_info));
        }
    }

    public void tcg_info_setup() throws JSONException {
        //Common
        tcg_detail_ll.setVisibility(View.GONE);
        tcg_intro_tv.setText(item_rss.getTCGByName(tcg.getName(),context)[1]);
        tcg_intro_element.setVisibility(View.GONE);
        tcg_intro_location.setVisibility(View.GONE);
        tcg_intro_weapon.setVisibility(View.GONE);
        tcg_intro_source.setVisibility(View.GONE);

        tcg_normal_ll.setVisibility(View.GONE);
        tcg_normal2_ll.setVisibility(View.GONE);
        tcg_element_ll.setVisibility(View.GONE);
        tcg_element2_ll.setVisibility(View.GONE);
        tcg_final_ll.setVisibility(View.GONE);
        tcg_other_ll.setVisibility(View.GONE);

        view4.setVisibility(View.GONE);
        tcg_intro_type.setText(item_rss.getTypeLocaleByName(tcg.getType(),context));

        if (jsonObject.has("source")){
            tcg_intro_source.setVisibility(View.VISIBLE);
            tcg_intro_source.setText(jsonObject.getString("source"));
        }

        if(jsonObject.has("description")){
            tcg_detail_ll.setVisibility(View.VISIBLE);
            tcg_nonchar_ll.setVisibility(View.VISIBLE);
            tcg_nonchar_info.setText(setSpanAndTv(jsonObject.getString("description")), TextView.BufferType.SPANNABLE);
        }

        if (tcg.getType().equals(TCG.CHAR)){
            tcg_nonchar_ll.setVisibility(View.GONE);
            JSONArray tagstext = jsonObject.getJSONArray("tagstext");
            JSONArray battleTalent = jsonObject.getJSONArray("skills");
            tcg_detail_ll.setVisibility(View.VISIBLE);
            tcg_intro_element.setVisibility(View.VISIBLE);
            tcg_intro_location.setVisibility(View.VISIBLE);
            tcg_intro_weapon.setVisibility(View.VISIBLE);
            view4.setVisibility(View.VISIBLE);

            tcg_intro_element.setImageResource(item_rss.getElementByNameTCG(tagstext.get(0).toString(),context)[0]);
            tcg_intro_weapon.setImageResource(item_rss.getWeaponTypeIMG(tagstext.get(1).toString(),context));
            tcg_intro_location.setText(tagstext.get(2).toString());

            tcg_ll_setup(0,0,tcg_normal_ll,tcg_normal_ico,tcg_normal_name,tcg_normal_info,tcg_normal_element,tcg_normal_element_tv,tcg_normal_element_ico,tcg_normal_spec,tcg_normal_spec_tv,tcg_normal_rand,tcg_normal_rand_tv,tcg_normal_recharge,tcg_normal_recharge_tv);
            tcg_ll_setup(1,1,tcg_element_ll,tcg_element_ico,tcg_element_name,tcg_element_info,tcg_element_element,tcg_element_element_tv,tcg_element_element_ico,tcg_element_spec,tcg_element_spec_tv,tcg_element_rand,tcg_element_rand_tv,tcg_element_recharge,tcg_element_recharge_tv);
            if (battleTalent.length() > 2){
                if (battleTalent.getJSONObject(2).getString("type").equals(context.getString(R.string.noraml_atk))){
                    tcg_ll_setup(2,0,tcg_normal2_ll,tcg_normal2_ico,tcg_normal2_name,tcg_normal2_info,tcg_normal2_element,tcg_normal2_element_tv,tcg_normal2_element_ico,tcg_normal2_spec,tcg_normal2_spec_tv,tcg_normal2_rand,tcg_normal2_rand_tv,tcg_normal2_recharge,tcg_normal2_recharge_tv);
                    tcg_ll_setup(3,2,tcg_final_ll,tcg_final_ico,tcg_final_name,tcg_final_info,tcg_final_element,tcg_final_element_tv,tcg_final_element_ico,tcg_final_spec,tcg_final_spec_tv,tcg_final_rand,tcg_final_rand_tv,tcg_final_recharge,tcg_final_recharge_tv);
                }else if (battleTalent.getJSONObject(2).getString("type").equals(context.getString(R.string.final_atk))){
                    tcg_ll_setup(2,2,tcg_final_ll,tcg_final_ico,tcg_final_name,tcg_final_info,tcg_final_element,tcg_final_element_tv,tcg_final_element_ico,tcg_final_spec,tcg_final_spec_tv,tcg_final_rand,tcg_final_rand_tv,tcg_final_recharge,tcg_final_recharge_tv);
                }else if (battleTalent.getJSONObject(2).getString("type").equals(context.getString(R.string.element_atk))){
                    tcg_ll_setup(2,1,tcg_element2_ll,tcg_element2_ico,tcg_element2_name,tcg_element2_info,tcg_element2_element,tcg_element2_element_tv,tcg_element2_element_ico,tcg_element2_spec,tcg_element2_spec_tv,tcg_element2_rand,tcg_element2_rand_tv,tcg_element2_recharge,tcg_element2_recharge_tv);
                    tcg_ll_setup(3,2,tcg_final_ll,tcg_final_ico,tcg_final_name,tcg_final_info,tcg_final_element,tcg_final_element_tv,tcg_final_element_ico,tcg_final_spec,tcg_final_spec_tv,tcg_final_rand,tcg_final_rand_tv,tcg_final_recharge,tcg_final_recharge_tv);
                }


                if (battleTalent.getJSONObject(2).getString("type").equals(context.getString(R.string.passive_atk))){
                    tcg_ll_setup_other(3,3, tcg_other_ll, tcg_other_ico, tcg_other_name, tcg_other_info);
                }else if (battleTalent.getJSONObject(3).getString("type").equals(context.getString(R.string.passive_atk))){
                    tcg_ll_setup_other(3,3, tcg_other_ll, tcg_other_ico, tcg_other_name, tcg_other_info);
                }
            }
        }
    }

    public void tcg_ll_setup(int index,int talentIndex,
                             LinearLayout tcg_item_ll,
                             ImageView tcg_item_ico,
                             TextView tcg_item_name,
                             TextView tcg_item_info,
                             FrameLayout tcg_item_element,
                             TextView tcg_item_element_tv,
                             ImageView tcg_item_element_ico,
                             FrameLayout tcg_item_spec,
                             TextView tcg_item_spec_tv,
                             FrameLayout tcg_item_rand,
                             TextView tcg_item_rand_tv,
                             FrameLayout tcg_item_recharge,
                             TextView tcg_item_recharge_tv) throws JSONException {
        JSONArray battleTalent = jsonObject.getJSONArray("skills");
        JSONArray tagstext = jsonObject.getJSONArray("tagstext");
        tcg_item_ll.setVisibility(View.VISIBLE);
        tcg_item_ico.setImageDrawable(item_rss.getTalentIcoByName(getTalentFromCharFile(tcg.getName())[talentIndex],context));
        tcg_item_name.setText(battleTalent.getJSONObject(index).getString("name"));
        tcg_item_info.setText(setSpanAndTv(battleTalent.getJSONObject(index).getString("description")), TextView.BufferType.SPANNABLE);
        // {ELEMENT, SPEC, RAND, ENERGY}
        int[] normalDice = getDiceNumFromData(battleTalent.getJSONObject(index).getJSONArray("playcost"));
        if (normalDice[0] != -1){
            tcg_item_element.setVisibility(View.VISIBLE);
            tcg_item_element_tv.setText(String.valueOf(normalDice[0]));
            tcg_item_element_ico.setImageResource(item_rss.getElementByNameTCG(tagstext.get(0).toString(),context)[1]);
        }
        if (normalDice[1] != -1){
            tcg_item_spec.setVisibility(View.VISIBLE);
            tcg_item_spec_tv.setText(String.valueOf(normalDice[1]));
        }
        if (normalDice[2] != -1){
            tcg_item_rand.setVisibility(View.VISIBLE);
            tcg_item_rand_tv.setText(String.valueOf(normalDice[2]));
        }
        if (normalDice[3] != -1){
            tcg_item_recharge.setVisibility(View.VISIBLE);
            tcg_item_recharge_tv.setText(String.valueOf(normalDice[3]));
        }
    }

    public void tcg_ll_setup_other(int index,int talentIndex,
                                   LinearLayout tcg_item_ll,
                                   ImageView tcg_item_ico,
                                   TextView tcg_item_name,
                                   TextView tcg_item_info) throws JSONException {
        JSONArray battleTalent = jsonObject.getJSONArray("skills");
        tcg_item_ll.setVisibility(View.VISIBLE);
        tcg_item_ico.setImageDrawable(item_rss.getTalentIcoByName(getTalentFromCharFile(tcg.getName())[talentIndex],context));
        tcg_item_name.setText(battleTalent.getJSONObject(index).getString("name"));
        // Word Color replacement will do later
        tcg_item_info.setText(battleTalent.getJSONObject(index).getString("description"));
    }

    public int[] getDiceNumFromData(JSONArray playcost) throws JSONException {
        int[] diceNum = {-1,-1,-1,-1};

        if (playcost == null) return diceNum;

        for (int x = 0 ; x < playcost.length() ; x++){
            switch (playcost.getJSONObject(x).getString("costtype")){
                case "GCG_COST_DICE_ANEMO":
                case "GCG_COST_DICE_CRYO":
                case "GCG_COST_DICE_DENDRO":
                case "GCG_COST_DICE_ELECTRO":
                case "GCG_COST_DICE_HYDRO":
                case "GCG_COST_DICE_GEO":
                case "GCG_COST_DICE_PYRO":
                    diceNum[0] = playcost.getJSONObject(x).getInt("count");break;

                case "GCG_COST_DICE_SAME":
                    diceNum[1] = playcost.getJSONObject(x).getInt("count");break;

                case "GCG_COST_DICE_VOID":
                    diceNum[2] = playcost.getJSONObject(x).getInt("count");break;

                case "GCG_COST_ENERGY":
                    diceNum[3] = playcost.getJSONObject(x).getInt("count");break;
            }
        }

        return diceNum;
    }

    public String[] getTalentFromCharFile(String name){
        String json = LoadData("db/char/"+"en-US"+"/"+this.tcg.getName().replace(" ","_")+".json");
        if (json == null){return new String[]{"N/A","N/A","N/A","N/A"};}
        try {
            JSONObject jsonObject = new JSONObject(json);
            JSONObject battle_talent = jsonObject.getJSONObject("battle_talent");
            String other_talent = "N/A";
            if (battle_talent.has("other_img")){
                other_talent = battle_talent.getString("other_img");
            }
            return new String[]{battle_talent.getString("normal_img"), battle_talent.getString("element_img"), battle_talent.getString("final_img"), other_talent};


        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new String[]{"N/A","N/A","N/A","N/A"};
    }

    public void smallZoom(){
        split = 0;Animation ani = new SmallZoomAnimation(
                tcg_card_include,
                (int) ((displayMetrics.widthPixels - displayMetrics.density*(32))/2),
                (int) ((displayMetrics.widthPixels - displayMetrics.density*(32))/2 +displayMetrics.density*(6)),
                false);
        ani.setDuration(200);
        tcg_card_dx();
        tcg_card_include.startAnimation(ani);
        ani.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                split = 0;
                Animation ani = new SmallZoomAnimation(
                        tcg_card_include,
                        (int) ((displayMetrics.widthPixels - displayMetrics.density*(32))/2 +displayMetrics.density*(6)),
                        (int) ((displayMetrics.widthPixels - displayMetrics.density*(32))/2 - displayMetrics.density*(12)),
                        true);
                ani.setDuration(200);
                tcg_card_include.startAnimation(ani);
                tcg_card_dx();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    public void tcg_card_dx() {
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                if (split < 15){
                    tcg_card_change();
                    tcg_card_dx();
                    split++;
                }
            }
        },20);
    }

    public void tcg_card_change(){
        int oneDP = (int) (displayMetrics.density*4);
        int widthNew = (int) ((displayMetrics.widthPixels - displayMetrics.density*(32))/2);

        if (tcg.getType().equals(TCG.CHAR)){
            File gifFile = FileLoader.loadIMG(item_rss.getTCGByName(tcg.getName(),context)[0],context);
            GifDrawable gifFromFile = null;
            try { gifFromFile = new GifDrawable(gifFile);
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (gifFile != null){
                tcg_card_img.setImageDrawable(gifFromFile);
                tcg_card_img.getLayoutParams().width = MATCH_PARENT;
                tcg_card_img.getLayoutParams().height = MATCH_PARENT;
                tcg_card_kwang.setVisibility(View.GONE);
            }else{
                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getTCGByName(tcg.getName(),context)[0],context))
                        .resize(widthNew-oneDP,(int) ((widthNew-oneDP)*12/7))
                        .error (R.drawable.paimon_lost)
                        .into(tcg_card_img);
            }
        }else{
            Picasso.get()
                    .load (FileLoader.loadIMG(item_rss.getTCGByName(tcg.getName(),context)[0],context))
                    .resize(widthNew-oneDP,(int) ((widthNew-oneDP)*12/7))
                    .error (R.drawable.paimon_lost)
                    .into(tcg_card_img);
        }


        tcg_card_name.setText(item_rss.getTCGByName(tcg.getName(),context)[1]);
        tcg_card_name_base.setText(tcg.getName());
        tcg_press_mask.getLayoutParams().width = (int) (0);
        tcg_press_mask.getLayoutParams().height = (int) (0);

        switch (tcg.getType()){
            case TCG.CHAR:{
                tcg_card_hp.setVisibility(View.VISIBLE);
                tcg_card_dice.setVisibility(View.GONE);

                tcg_hp_tv.setText(String.valueOf(tcg.getHP()));
                break;
            }
            case TCG.EQUIP:
            case TCG.SUPPORT:
            case TCG.EVENT:
            case TCG.BACKSIDE:{
                tcg_card_hp.setVisibility(View.GONE);
                tcg_card_dice.setVisibility(View.VISIBLE);
                int diceType = R.drawable.bg_tcg_dice_specific;

                switch (tcg.getDiceType()){
                    case TCG.Anemo: diceType = R.drawable.bg_tcg_dice_anemo;break;
                    case TCG.Cryo: diceType = R.drawable.bg_tcg_dice_cryo;break;
                    case TCG.Dendro: diceType = R.drawable.bg_tcg_dice_dendro;break;
                    case TCG.Electro: diceType = R.drawable.bg_tcg_dice_electro;break;
                    case TCG.Geo: diceType = R.drawable.bg_tcg_dice_geo;break;
                    case TCG.Hydro: diceType = R.drawable.bg_tcg_dice_hydro;break;
                    case TCG.Pyro: diceType = R.drawable.bg_tcg_dice_pyro;break;
                    case TCG.SPEC: diceType = R.drawable.bg_tcg_dice_specific;break;
                    case TCG.RAND: diceType = R.drawable.bg_tcg_dice_random;break;
                }

                tcg_dice_bg.setImageResource(diceType);
                tcg_dice_tv.setText(String.valueOf(tcg.getDiceCost()));
                break;
            }
        }
    }

    public class ZoomAnimation extends Animation {
        int fromX;
        int fromY;
        int widthOld;
        int widthNew;
        View view;
        boolean isZoomOutAnim = false;

        public ZoomAnimation(View view, int fromX, int fromY, int widthOld, int widthNew, boolean isZoomOutAnim) {
            this.view = view;
            this.fromX = fromX;
            this.fromY = fromY;
            this.widthOld = widthOld;
            this.widthNew = widthNew;
            this.isZoomOutAnim = isZoomOutAnim;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            float interpolatedTime_auth = interpolatedTime;
            if (isZoomOutAnim){
                interpolatedTime_auth = (1-interpolatedTime);
            }

            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            view.setY ((fromY - displayMetrics.density*(60))*(1-interpolatedTime_auth)+ displayMetrics.density*(60));
            view.setX ((fromX)*(1-interpolatedTime_auth));
            layoutParams.height = (int) (widthOld*12/7-displayMetrics.density*(18) + (widthNew-widthOld)*12/7*interpolatedTime_auth);
            layoutParams.width = (int) (widthOld-displayMetrics.density*(10) + (widthNew-widthOld)*interpolatedTime_auth);
            System.out.println("(fromX, fromY, widthNew) : ("+fromX+", "+fromY+", "+widthNew+")");

            view.setLayoutParams(layoutParams);
        }

        @Override
        public void initialize(int width, int height, int parentWidth,
                               int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
        }
    }


    public class SmallZoomAnimation extends Animation {
        int widthOld;
        int widthNew;
        View view;
        boolean isZoomOutAnim = false;

        public SmallZoomAnimation(View view, int widthOld, int widthNew, boolean isZoomOutAnim) {
            this.view = view;
            this.widthOld = widthOld;
            this.widthNew = widthNew;
            this.isZoomOutAnim = isZoomOutAnim;
        }

        @Override
        protected void applyTransformation(float interpolatedTime, Transformation t) {

            float interpolatedTime_auth = interpolatedTime;

            ViewGroup.MarginLayoutParams layoutParams =
                    (ViewGroup.MarginLayoutParams) view.getLayoutParams();
            layoutParams.height = (int) (widthOld*12/7+ (widthNew-widthOld)*12/7*interpolatedTime_auth);
            layoutParams.width = (int) (widthOld + (widthNew-widthOld)*interpolatedTime_auth);

            view.setLayoutParams(layoutParams);
        }

        @Override
        public void initialize(int width, int height, int parentWidth,
                               int parentHeight) {
            super.initialize(width, height, parentWidth, parentHeight);
        }

        @Override
        public boolean willChangeBounds() {
            return true;
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

    public SpannableString setSpanAndTv(String str){
        SpannableString mSpannavleString = new SpannableString(str);
        int[] keywords = {
                R.string.tcg_keyword_anemo,
                R.string.tcg_keyword_anemodmg,
                R.string.tcg_keyword_combataction,
                R.string.tcg_keyword_cryo,
                R.string.tcg_keyword_cryoapplication,
                R.string.tcg_keyword_cryodmg,
                R.string.tcg_keyword_dendro,
                R.string.tcg_keyword_dendroapplication,
                R.string.tcg_keyword_dendrodmg,
                R.string.tcg_keyword_dendrorelatedreactions,
                R.string.tcg_keyword_durationrounds,
                R.string.tcg_keyword_electro,
                R.string.tcg_keyword_electroapplication,
                R.string.tcg_keyword_electrodmg,
                R.string.tcg_keyword_electroinfusion,
                R.string.tcg_keyword_elementalburst,
                R.string.tcg_keyword_energy,
                R.string.tcg_keyword_fastaction,
                R.string.tcg_keyword_geo,
                R.string.tcg_keyword_geodmg,
                R.string.tcg_keyword_hydro,
                R.string.tcg_keyword_hydroapplication,
                R.string.tcg_keyword_hydrodmg,
                R.string.tcg_keyword_hydrorelatedreactions,
                R.string.tcg_keyword_matchingelement,
                R.string.tcg_keyword_omnielement,
                R.string.tcg_keyword_passiveskill,
                R.string.tcg_keyword_physicaldmg,
                R.string.tcg_keyword_piercingdmg,
                R.string.tcg_keyword_pyro,
                R.string.tcg_keyword_pyroapplication,
                R.string.tcg_keyword_pyrodmg,
                R.string.tcg_keyword_pyrorelatedreactions,
                R.string.tcg_keyword_randomhilichurlsummon,
                R.string.tcg_keyword_shield,
                R.string.tcg_keyword_unalignedelement,
                R.string.tcg_keyword_usages
        };

        int[] keywordsColor = {
                R.color.anemo,
                R.color.anemo,
                R.color.tcg_other,
                R.color.cryo,
                R.color.cryo,
                R.color.cryo,
                R.color.dendor,
                R.color.dendor,
                R.color.dendor,
                R.color.dendor,
                R.color.tcg_other,
                R.color.electro,
                R.color.electro,
                R.color.electro,
                R.color.electro,
                R.color.tcg_other,
                R.color.tcg_energy,
                R.color.tcg_other,
                R.color.geo,
                R.color.geo,
                R.color.hydro,
                R.color.hydro,
                R.color.hydro,
                R.color.hydro,
                R.color.tcg_spec,
                R.color.tcg_white,
                R.color.tcg_other,
                R.color.tcg_white,
                R.color.tcg_other,
                R.color.pyro,
                R.color.pyro,
                R.color.pyro,
                R.color.pyro,
                R.color.tcg_other,
                R.color.tcg_other,
                R.color.tcg_rand,
                R.color.tcg_other
        };

        for (int x = 0 ; x < keywords.length ; x++){
            for (int i = -1; (i = str.indexOf(context.getString(keywords[x]), i + 1)) != -1; i++) {
                mSpannavleString.setSpan(new ForegroundColorSpan(context.getResources().getColor(keywordsColor[x])),str.indexOf(context.getString(keywords[x])),str.indexOf(context.getString(keywords[x]))+context.getString(keywords[x]).length(), Spanned.SPAN_EXCLUSIVE_EXCLUSIVE);
            }
        }
        return mSpannavleString;
    }
}
