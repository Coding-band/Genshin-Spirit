package com.voc.genshin_helper.data;/*
 * Package com.voc.genshin_helper.data.ArtifactsAdapter was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import static android.content.Context.MODE_PRIVATE;
import static android.view.ViewGroup.LayoutParams.WRAP_CONTENT;
import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.TOP;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.Configuration;

import androidx.cardview.widget.CardView;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.AlphaAnimation;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.data.buff_old.SipTikCal;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MMXLVIII.Calculator2048;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.ui.SipTik.DeskSipTik;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FadingImageView;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;
import com.voc.genshin_helper.R;

import java.util.ArrayList;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

/* loaded from: classes.dex */
public class ArtifactsAdapter extends RecyclerView.Adapter<ArtifactsAdapter.ViewHolder> {
    private List<Artifacts> artifactsList;
    private Context context;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView;
    private Activity activity ;
    private AlphaAnimation buttonClick = new AlphaAnimation(1F, 0.8F);
    private ArrayList<Artifacts> artifactsA = new ArrayList<Artifacts>();
    private int pos = 1;
    SharedPreferences sharedPreferences;

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public ArtifactsAdapter(Context context, List<Artifacts> list, Activity activity, SharedPreferences sharedPreferences) {
        this.context = context;
        this.artifactsList = list;
        this.activity = activity;
        this.sharedPreferences = sharedPreferences;

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("user_name",MODE_PRIVATE);
        }
    }
    public ArtifactsAdapter(Context context, List<Artifacts> list, Activity activity,int pos, SharedPreferences sharedPreferences) {
        this.context = context;
        this.artifactsList = list;
        this.activity = activity;
        this.pos = pos;
        this.sharedPreferences = sharedPreferences;

        if (sharedPreferences == null) {
            sharedPreferences = context.getSharedPreferences("user_name",MODE_PRIVATE);
        }
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artifact_ico_rectangle_2048, viewGroup, false);
        switch (sharedPreferences.getString("curr_ui_grid", "2")) {
            case "2":{
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artifact_ico_rectangle_2048, viewGroup, false);
                break;
            }
            case "3":{
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artifact_ico_square_2048, viewGroup, false);
                break;
            }
            case "4":{
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artifact_ico_card_siptik, viewGroup, false);
                break;
            }
            case "5":{
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artifact_ico_detail_siptik, viewGroup, false);
                break;
            }
            default:{
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_artifact_ico_square_2048, viewGroup, false);
                break;
            }
        }
        return new ViewHolder(inflate, (OnItemClickListener) this.mListener);
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Artifacts artifacts = this.artifactsList.get(i);
        int width = 0, height = 0;
        viewHolder.isComing = artifacts.getIsComing();
        ItemRss itemRss = new ItemRss();

        artifactsA.add(artifacts);

        boolean isNight = false;
        // Background of item
        if (context.getResources().getString(R.string.mode).equals("Night")) {
            isNight = true;
        }

        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.TOP);

        final int radius_circ_siptik = 80;
        final int margin_circ_siptik = 0;
        final Transformation transformation_circ_siptik = new RoundedCornersTransformation(radius_circ_siptik, margin_circ_siptik);
        final int radius_circ_siptik_ico = 30;
        final int margin_circ_siptik_ico = 0;
        final Transformation transformation_circ_siptik_ico = new RoundedCornersTransformation(radius_circ_siptik_ico, margin_circ_siptik_ico);

        System.out.println(artifacts.getBaseName());

        viewHolder.str = artifacts.getName();
        viewHolder.artifact_name.setText(artifacts.getName());
        viewHolder.artifact_base_name.setText(artifacts.getBaseName());

        if(context.getSharedPreferences("user_info",MODE_PRIVATE).getString("curr_ui_grid", "2").equals("4") ){
            String baseName = artifacts.getBaseName();
            SharedPreferences sharedPreferences = ArtifactsAdapter.this.context.getSharedPreferences("user_info", 0);
            String string = sharedPreferences.getString("curr_lang", "zh-HK");
            String replace = baseName.replace("'", "_");
            String str = ItemRss.LoadAssestData(context,"db/artifacts/" + string + "/" + replace + ".json");

            if (str == null) {
                str = ItemRss.LoadAssestData(context,"db/artifacts/en-US/" + replace + ".json");
            }
            if (str != null) {
                try {
                    JSONObject jSONObject = new JSONObject(str);

                    if (jSONObject.has("1pc")) {
                        viewHolder.artifact_desc_2_title.setText(context.getString(R.string.artifact_stat1)+" : ");
                        viewHolder.artifact_desc_2.setText(jSONObject.getString("1pc"));
                        viewHolder.artifact_desc_4.setVisibility(View.GONE);
                        viewHolder.artifact_desc_4_title.setVisibility(View.GONE);
                    }else if (jSONObject.has("2pc") && (jSONObject.has("4pc"))) {
                        viewHolder.artifact_desc_2_title.setText(context.getString(R.string.artifact_stat2)+" : ");
                        viewHolder.artifact_desc_2.setText(jSONObject.getString("2pc"));
                        viewHolder.artifact_desc_4_title.setText(context.getString(R.string.artifact_stat4)+" : ");
                        viewHolder.artifact_desc_4.setText(jSONObject.getString("4pc"));

                        viewHolder.artifact_desc_2.setVisibility(View.VISIBLE);
                        viewHolder.artifact_desc_2_title.setVisibility(View.VISIBLE);
                        viewHolder.artifact_desc_4.setVisibility(View.VISIBLE);
                        viewHolder.artifact_desc_4_title.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }else if(context.getSharedPreferences("user_info",MODE_PRIVATE).getString("curr_ui_grid", "2").equals("5") ){
            String baseName = artifacts.getBaseName();
            SharedPreferences sharedPreferences = ArtifactsAdapter.this.context.getSharedPreferences("user_info", 0);
            String string = sharedPreferences.getString("curr_lang", "zh-HK");
            String replace = baseName.replace("'", "_");
            String str = ItemRss.LoadAssestData(context,"db/artifacts/" + string + "/" + replace + ".json");

            if (str == null) {
                str = ItemRss.LoadAssestData(context,"db/artifacts/en-US/" + replace + ".json");
            }
            if (str != null) {
                try {
                    JSONObject jSONObject = new JSONObject(str);

                    if (jSONObject.has("1pc")) {
                        viewHolder.artifact_desc_2_title.setText(context.getString(R.string.artifact_stat1)+" : ");
                        viewHolder.artifact_desc_2.setText(jSONObject.getString("1pc"));
                        viewHolder.artifact_desc_4.setVisibility(View.GONE);
                        viewHolder.artifact_desc_4_title.setVisibility(View.GONE);
                    }else if (jSONObject.has("2pc") && (jSONObject.has("4pc"))) {
                        viewHolder.artifact_desc_2_title.setText(context.getString(R.string.artifact_stat2)+" : ");
                        viewHolder.artifact_desc_2.setText(jSONObject.getString("2pc"));
                        viewHolder.artifact_desc_4_title.setText(context.getString(R.string.artifact_stat4)+" : ");
                        viewHolder.artifact_desc_4.setText(jSONObject.getString("4pc"));

                        viewHolder.artifact_desc_2.setVisibility(View.VISIBLE);
                        viewHolder.artifact_desc_2_title.setVisibility(View.VISIBLE);
                        viewHolder.artifact_desc_4.setVisibility(View.VISIBLE);
                        viewHolder.artifact_desc_4_title.setVisibility(View.VISIBLE);
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }


            switch (artifacts.getRare()){
                case 1 : viewHolder.artifact_icon.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
                case 2 : viewHolder.artifact_icon.setBackgroundResource(R.drawable.bg_rare2_char_siptik);break;
                case 3 : viewHolder.artifact_icon.setBackgroundResource(R.drawable.bg_rare3_char_siptik);break;
                case 4 : viewHolder.artifact_icon.setBackgroundResource(R.drawable.bg_rare4_char_siptik);break;
                case 5 : viewHolder.artifact_icon.setBackgroundResource(R.drawable.bg_rare5_char_siptik);break;
                default:  viewHolder.artifact_icon.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
            }
            viewHolder.artifact_star.setVisibility(View.GONE);
            viewHolder.artifact_star_ll.setVisibility(View.GONE);
        }

        if (artifacts.getIsComing() == 0) {
            viewHolder.artifact_isComing.setVisibility(View.GONE);
        }
        if (artifacts.getIsComing() == 1) {
            viewHolder.artifact_isComing.setVisibility(View.VISIBLE);
        }
        if (artifacts.getRare() > 0 && artifacts.getRare() < 6) {
            viewHolder.artifact_star.setNumStars(artifacts.getRare());
            viewHolder.artifact_star.setRating((float) artifacts.getRare());
        }

        Context context = this.context;
        DisplayMetrics displayMetrics = new DisplayMetrics();
        activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int height_curr = displayMetrics.heightPixels;
        int width_curr = displayMetrics.widthPixels;
        int curr = width_curr;
        int size_per_img = width_curr/2;
        int size_per_img_sq = width_curr/3;
        int size_per_img_siptik = width_curr;

        if(activity.getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            size_per_img = 480;
            size_per_img_sq = 360;
            size_per_img_siptik = 960;
        }

        switch (sharedPreferences.getString("curr_ui_grid", "2")){
            case "2":{
                if(width_curr / ((int)width_curr/size_per_img+1) > size_per_img){
                    width = (width_curr) / ((int)width_curr/size_per_img+1);
                    height = (int) ((width * 9) / 8);
                }else{
                    width = size_per_img;
                    height = (int) ((width * 9) / 8);
                }
                break;
            }
            case "3":{
                if(width_curr / ((int)width_curr/size_per_img_sq+1) > size_per_img_sq){
                    width = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                    height = (width_curr) / ((int)width_curr/size_per_img_sq+1);
                }else{
                    width = size_per_img_sq;
                    height = size_per_img_sq;
                }
                break;
            }
            case "4":{
                if(width_curr / ((int)width_curr/size_per_img_siptik+1) > size_per_img_siptik){
                    width = (width_curr) / ((int)width_curr/size_per_img_siptik+1);
                    height = (int) ((width) / 2.1);

                }else{
                    width = (width_curr) / (int) (width_curr/size_per_img_siptik);
                    height = (int) ((width) / 2.1);
                }

                viewHolder.artifact_card_bg.getLayoutParams().width = width;
                viewHolder.artifact_card_bg.getLayoutParams().height = height;
                viewHolder.artifact_card_mask.getLayoutParams().width = width;
                viewHolder.artifact_card_mask.getLayoutParams().height = height;
                viewHolder.artifact_card.getLayoutParams().width = width-16;
                viewHolder.artifact_card.getLayoutParams().height = height-16;
                break;
            }
            case "5":{
                if(width_curr / ((int)width_curr/size_per_img_siptik+1) > size_per_img_siptik){
                    width = (width_curr) / ((int)width_curr/size_per_img_siptik+1);
                    height = (int) ((width) / 2.1);

                }else{
                    width = (width_curr) / (int) (width_curr/size_per_img_siptik);
                    height = (int) ((width) / 2.1);
                }

                viewHolder.artifact_card_bg.getLayoutParams().width = width;
                viewHolder.artifact_card_bg.getLayoutParams().height = height;
                viewHolder.artifact_card_mask.getLayoutParams().width = width;
                viewHolder.artifact_card_mask.getLayoutParams().height = height;
                viewHolder.artifact_cbg.getLayoutParams().width = width-16;
                viewHolder.artifact_cbg.getLayoutParams().height = height-16;
                break;
            }
        }

        int one_curr = height;
        if(height > width){
            one_curr = width;
        }

        switch (context.getSharedPreferences("user_info", MODE_PRIVATE).getString("curr_ui_grid", "2")) {
            case "2":
                switch (artifacts.getRare()) {
                    case 1: {
                        if (isNight == true) {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare1_800x1400_dark);
                        } else {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare1_800x1400_light);
                        }
                        break;
                    }
                    case 2: {
                        if (isNight == true) {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare2_800x1400_dark);
                        } else {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare2_800x1400_light);
                        }
                        break;
                    }
                    case 3: {
                        if (isNight == true) {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare3_800x1400_dark);
                        } else {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare3_800x1400_light);
                        }
                        break;
                    }
                    case 4: {
                        if (isNight == true) {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare4_800x1400_dark);
                        } else {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare4_800x1400_light);
                        }
                        break;
                    }
                    case 5: {
                        if (isNight == true) {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare5_800x1400_dark);
                        } else {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare5_800x1400_light);
                        }
                        break;
                    }

                }

                viewHolder.artifact_name_ll.getLayoutParams().width = width;
                viewHolder.artifact_name_ll.getLayoutParams().height = width * 4 / 8;
                viewHolder.artifact_main.getLayoutParams().width = width;
                viewHolder.artifact_main.getLayoutParams().height = height * 10 / 9;
                break;
            case "3":
                switch (artifacts.getRare()) {
                    case 1: {
                        if (isNight == true) {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare1_800x1000_dark);
                        } else {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare1_800x1000_light);
                        }
                        break;
                    }
                    case 2: {
                        if (isNight == true) {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare2_800x1000_dark);
                        } else {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare2_800x1000_light);
                        }
                        break;
                    }
                    case 3: {
                        if (isNight == true) {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare3_800x1000_dark);
                        } else {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare3_800x1000_light);
                        }
                        break;
                    }
                    case 4: {
                        if (isNight == true) {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare4_800x1000_dark);
                        } else {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare4_800x1000_light);
                        }
                        break;
                    }
                    case 5: {
                        if (isNight == true) {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare5_800x1000_dark);
                        } else {
                            viewHolder.artifact_bg.setBackgroundResource(R.drawable.rare5_800x1000_light);
                        }
                        break;
                    }

                }

                viewHolder.artifact_name_ll.getLayoutParams().width = width;
                viewHolder.artifact_name_ll.getLayoutParams().height = width * 2 / 8;
                viewHolder.artifact_main.getLayoutParams().width = width;
                viewHolder.artifact_main.getLayoutParams().height = width;
                break;
            case "4": {
                switch (artifacts.getRare()) {
                    case 1: {
                        Picasso.get()
                                .load(R.drawable.bg_rare1_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error(R.drawable.bg_rare1_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.artifact_card_bg);
                        break;
                    }
                    case 2: {
                        Picasso.get()
                                .load(R.drawable.bg_rare2_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error(R.drawable.bg_rare2_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.artifact_card_bg);
                        break;
                    }
                    case 3: {
                        Picasso.get()
                                .load(R.drawable.bg_rare3_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error(R.drawable.bg_rare3_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.artifact_card_bg);
                        break;
                    }
                    case 4: {
                        Picasso.get()
                                .load(R.drawable.bg_rare4_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error(R.drawable.bg_rare4_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.artifact_card_bg);
                        break;
                    }
                    case 5: {
                        Picasso.get()
                                .load(R.drawable.bg_rare5_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error(R.drawable.bg_rare5_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.artifact_card_bg);
                        break;
                    }

                }

                viewHolder.artifact_card_bg.getLayoutParams().width = width + 32;
                viewHolder.artifact_card_bg.getLayoutParams().height = height + 32;

                //viewHolder.artifact_name_ll.getLayoutParams().width = width;
                //viewHolder.artifact_name_ll.getLayoutParams().height = width*2/8;
                //viewHolder.artifact_main.getLayoutParams().width = width;
                //viewHolder.artifact_main.getLayoutParams().height = width;

                int frame = R.drawable.bg_day_frame;

                if (isNight) {
                    frame = R.drawable.bg_night_frame;
                }

                Picasso.get()
                        .load(frame).resize((int) (width_curr), (int) ((width_curr) / 2.1)).transform(transformation_circ_siptik)
                        .error(frame).transform(transformation_circ_siptik)
                        .into(viewHolder.artifact_card_mask);

                Picasso.get()
                        .load(R.drawable.bg_artifact_siptik_square).resize((int) (height_curr), (int) (height_curr)).transform(transformation_circ_siptik)
                        .error(R.drawable.bg_artifact_siptik_square)
                        .into(viewHolder.artifact_card_ico_deco);

                //viewHolder.artifact_card_bg.setPadding(8,8,8,8);
                viewHolder.artifact_card_mask.setPadding(8, 8, 8, 8);
                viewHolder.artifact_card_ico_deco.setPadding(8, 8, 8, 8);
                viewHolder.artifact_card.setPadding(8, 8, 8, 8);

                viewHolder.artifact_card_ico_deco.setEdgeLength(100);
                viewHolder.artifact_card_ico_deco.setFadeDirection(FadingImageView.FadeSide.RIGHT_SIDE);

                break;
            }
            case "5": {
                switch (artifacts.getRare()) {
                    case 1: {
                        Picasso.get()
                                .load(R.drawable.bg_rare1_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error(R.drawable.bg_rare1_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.artifact_card_bg);
                        break;
                    }
                    case 2: {
                        Picasso.get()
                                .load(R.drawable.bg_rare2_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error(R.drawable.bg_rare2_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.artifact_card_bg);
                        break;
                    }
                    case 3: {
                        Picasso.get()
                                .load(R.drawable.bg_rare3_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error(R.drawable.bg_rare3_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.artifact_card_bg);
                        break;
                    }
                    case 4: {
                        Picasso.get()
                                .load(R.drawable.bg_rare4_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error(R.drawable.bg_rare4_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.artifact_card_bg);
                        break;
                    }
                    case 5: {
                        Picasso.get()
                                .load(R.drawable.bg_rare5_siptik).resize(width, height)//.transform(transformation_circ_siptik)
                                .error(R.drawable.bg_rare5_siptik)//.transform(transformation_circ_siptik)
                                .into(viewHolder.artifact_card_bg);
                        break;
                    }

                }

                viewHolder.artifact_card_bg.getLayoutParams().width = width + 32;
                viewHolder.artifact_card_bg.getLayoutParams().height = height + 32;

                //viewHolder.artifact_name_ll.getLayoutParams().width = width;
                //viewHolder.artifact_name_ll.getLayoutParams().height = width*2/8;
                //viewHolder.artifact_main.getLayoutParams().width = width;
                //viewHolder.artifact_main.getLayoutParams().height = width;

                int frame = R.drawable.bg_day_frame;

                if (isNight) {
                    frame = R.drawable.bg_night_frame;
                }

                Picasso.get()
                        .load(frame).resize((int) (width_curr), (int) ((width_curr) / 2.1)).transform(transformation_circ_siptik)
                        .error(frame).transform(transformation_circ_siptik)
                        .into(viewHolder.artifact_card_mask);

                Picasso.get()
                        .load(R.drawable.bg_artifact_siptik_square).resize((int) (height_curr), (int) (height_curr)).transform(transformation_circ_siptik)
                        .error(R.drawable.bg_artifact_siptik_square)
                        .into(viewHolder.artifact_card_ico_deco);

                //viewHolder.artifact_card_bg.setPadding(8,8,8,8);
                viewHolder.artifact_card_mask.setPadding(8, 8, 8, 8);
                viewHolder.artifact_card_ico_deco.setPadding(8, 8, 8, 8);
                viewHolder.artifact_cbg.setPadding(8, 8, 8, 8);

                viewHolder.artifact_card_ico_deco.setEdgeLength(100);
                viewHolder.artifact_card_ico_deco.setFadeDirection(FadingImageView.FadeSide.RIGHT_SIDE);

                break;
            }
        }

       // viewHolder.artifact_bg.setBackgroundResource(itemRss.getRareColorByName(artifacts.getRare())[0]);
//        viewHolder.artifact_nl.setBackgroundResource(itemRss.getRareColorByName(artifacts.getRare())[1]);
        viewHolder.artifact_icon.getLayoutParams().width = width;
        viewHolder.artifact_icon.getLayoutParams().height = height;

        switch (sharedPreferences.getString("curr_ui_grid", "2")){
            case "2":{
                Picasso.get().
                        load(itemRss.getArtifactByName(artifacts.getName())[1])
                        .fit()
                        .centerCrop()
                        .error(R.drawable.paimon_full)
                        .into(viewHolder.artifact_icon);
                break;
            }
            case "3":{
                Picasso.get()
                        .load(itemRss.getArtifactByName(artifacts.getName())[1])
                        .resize(one_curr,one_curr)
                        .error(R.drawable.paimon_full)
                        .into(viewHolder.artifact_icon);
                break;
            }
            case "4":
            case "5":{
                viewHolder.artifact_icon.getLayoutParams().width = 96*width/315;
                viewHolder.artifact_icon.getLayoutParams().height = 96*width/315;
                Picasso.get()
                        .load(itemRss.getArtifactByName(artifacts.getName())[1])
                        .resize(96*width/315,96*width/315)
                        .transform(transformation_circ_siptik_ico)
                        .error (R.drawable.paimon_full)
                        .into (viewHolder.artifact_icon);
                break;
            }
        }

        viewHolder.artifact_name.setText(itemRss.getArtifactByName(artifacts.getName())[0]);
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.artifactsList.size();
    }

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView artifact_base_name;
        public ConstraintLayout artifact_bg;
        public ImageView artifact_element;
        public ImageView artifact_icon;
        public ImageView artifact_isComing;
        public TextView artifact_name;
        public LinearLayout artifact_nl;
        public RatingBar artifact_star;
        public LinearLayout artifact_star_ll;
        public TextView artifact_stat1;
        public TextView artifact_stat2;
        public String str;
        public ImageView artifact_press_mask;
        public ConstraintLayout artifact_main;
        public LinearLayout artifact_name_ll;
        public ImageView artifact_card_bg;
        public ImageView artifact_card_mask;
        public FadingImageView artifact_card_ico_deco;
        public TextView artifact_desc_2;
        public TextView artifact_desc_2_title;
        public TextView artifact_desc_4;
        public TextView artifact_desc_4_title;
        public CardView artifact_card;
        public LinearLayout artifact_cbg;
        public int isComing = 0;


        public ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            this.artifact_icon = (ImageView) view.findViewById(R.id.artifact_img);
            this.artifact_name = (TextView) view.findViewById(R.id.artifact_name);
            this.artifact_star = (RatingBar) view.findViewById(R.id.artifact_star);
            this.artifact_star_ll = view.findViewById(R.id.artifact_star_ll);
            this.artifact_isComing = (ImageView) view.findViewById(R.id.artifact_is_coming);
            this.artifact_base_name = (TextView) view.findViewById(R.id.artifact_base_name);
            this.artifact_nl = (LinearLayout) view.findViewById(R.id.artifact_nl);
            this.artifact_bg = (ConstraintLayout) view.findViewById(R.id.artifact_bg);
            this.artifact_press_mask = (ImageView) view.findViewById(R.id.artifact_press_mask);
            this.artifact_name_ll = (LinearLayout) view.findViewById(R.id.artifact_name_ll);
            this.artifact_main = (ConstraintLayout) view.findViewById(R.id.artifact_main);

            this.artifact_card_bg = view.findViewById(R.id.artifact_card_bg);
            this.artifact_card_mask = view.findViewById(R.id.artifact_card_mask);
            this.artifact_card_ico_deco = view.findViewById(R.id.artifact_card_ico_deco);
            this.artifact_desc_2 = view.findViewById(R.id.artifact_desc_2);
            this.artifact_desc_2_title = view.findViewById(R.id.artifact_desc_2_title);
            this.artifact_desc_4 = view.findViewById(R.id.artifact_desc_4);
            this.artifact_desc_4_title = view.findViewById(R.id.artifact_desc_4_title);

            this.artifact_card = view.findViewById(R.id.artifact_card);
            this.artifact_cbg = view.findViewById(R.id.artifact_cbg);

            artifact_press_mask.startAnimation(buttonClick);

            this.artifact_press_mask.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ImageView imageView;
                    if (isComing == 1) {CustomToast.toast(context, view, context.getString(R.string.unreleased)); return;}
                    Log.wtf("is context instanceof MainActivity ?", ArtifactsAdapter.this.context.getPackageName());
                    if (ArtifactsAdapter.this.context instanceof MainActivity) {
                        Log.wtf("YES", "IT's");
                        Log.wtf("BETA", String.valueOf(ViewHolder.this.artifact_base_name.getText()));
                        String valueOf = String.valueOf(ViewHolder.this.artifact_base_name.getText());
                        SharedPreferences sharedPreferences = ArtifactsAdapter.this.context.getSharedPreferences("user_info", 0);
                        new ItemRss();
                        String string = sharedPreferences.getString("curr_lang", "zh-HK");
                        //AssetManager assets = ArtifactsAdapter.this.context.getAssets();
                        Log.wtf("CharName_BASE", valueOf);
                        String replace = valueOf.replace("'", "_");
                        Log.wtf("lang", string);
                        String str = ItemRss.LoadAssestData(context,"db/artifacts/" + string + "/" + replace + ".json");

                        if (str == null) {
                            str = ItemRss.LoadAssestData(context,"db/artifacts/en-US/" + replace + ".json");
                        }
                        if (str != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                String str4 = ViewHolder.this.str;
                                String str5 = (String) ViewHolder.this.artifact_name.getText();
                                JSONArray jSONArray = jSONObject.getJSONArray("rarity");
                                int parseInt = Integer.parseInt((String) jSONArray.get(jSONArray.length() - 1));
                                Dialog dialog = new Dialog(ArtifactsAdapter.this.context, R.style.NormalDialogStyle_N);
                                View inflate = View.inflate(ArtifactsAdapter.this.context, R.layout.item_artifact_info, null);
                                ItemRss itemRss = new ItemRss();

                                final int radius = 25;
                                final int margin = 0;
                                final Transformation transformation = new RoundedCornersTransformation(radius, margin,TOP);

                                ImageView imageView2 = (ImageView) inflate.findViewById(R.id.item_img);
                                LinearLayout linearLayout = (LinearLayout) inflate.findViewById(R.id.item_nl);
                                RatingBar ratingBar = (RatingBar) inflate.findViewById(R.id.item_star);
                                ImageView imageView3 = (ImageView) inflate.findViewById(R.id.item_is_coming);
                                TextView textView = (TextView) inflate.findViewById(R.id.item_2pcs);
                                TextView textView2 = (TextView) inflate.findViewById(R.id.item_4pcs);
                                ConstraintLayout constraintLayout = (ConstraintLayout) inflate.findViewById(R.id.item_bg);
                                ImageView imageView4 = (ImageView) inflate.findViewById(R.id.info_item1);
                                ImageView imageView5 = (ImageView) inflate.findViewById(R.id.info_item2);
                                ImageView imageView6 = (ImageView) inflate.findViewById(R.id.info_item3);
                                ImageView imageView7 = (ImageView) inflate.findViewById(R.id.info_item4);
                                ImageView imageView8 = (ImageView) inflate.findViewById(R.id.info_item5);
                                Picasso.get().load(itemRss.getArtifactByName(str4)[1]).fit().centerInside().transform(transformation).error(R.drawable.paimon_lost).into(imageView2);
                                ((TextView) inflate.findViewById(R.id.item_name)).setText(ViewHolder.this.artifact_name.getText());
                                //((TextView) inflate.findViewById(R.id.item_name)).setText(ViewHolder.this.artifact_base_name.getText());
                                if (jSONObject.has("1pc")) {
                                    StringBuilder sb = new StringBuilder();
                                    imageView = imageView4;
                                    sb.append(ArtifactsAdapter.this.context.getString(R.string.artifact_stat1));
                                    sb.append(" : ");
                                    sb.append(jSONObject.getString("1pc"));
                                    textView.setText(sb.toString());
                                    textView2.setVisibility(View.GONE);
                                } else {
                                    imageView = imageView4;
                                    textView.setText(ArtifactsAdapter.this.context.getString(R.string.artifact_stat2) + " : " + jSONObject.getString("2pc"));
                                    textView2.setText(ArtifactsAdapter.this.context.getString(R.string.artifact_stat4) + " : " + jSONObject.getString("4pc"));
                                }
                                ratingBar.setNumStars(parseInt);
                                ratingBar.setRating((float) parseInt);
                                imageView2.setBackgroundResource(itemRss.getRareColorByName(parseInt)[0]);
                                linearLayout.setBackgroundResource(itemRss.getRareColorByName(parseInt)[1]);
                                Picasso.get().load(itemRss.getArtifactByName(str4)[1]).fit().centerInside().transform(transformation).error(R.drawable.paimon_lost).into(imageView);
                                imageView.setBackgroundResource(itemRss.getRareColorByName(parseInt)[3]);
                                if (itemRss.getArtifactByName(str4).length > 2) {
                                    imageView5.setVisibility(View.VISIBLE);
                                    imageView6.setVisibility(View.VISIBLE);
                                    imageView7.setVisibility(View.VISIBLE);
                                    imageView8.setVisibility(View.VISIBLE);
                                    Picasso.get().load(itemRss.getArtifactByName(str4)[2]).fit().centerInside().transform(transformation).error(R.drawable.paimon_lost).into(imageView5);
                                    imageView5.setBackgroundResource(itemRss.getRareColorByName(parseInt)[3]);
                                    Picasso.get().load(itemRss.getArtifactByName(str4)[3]).fit().centerInside().transform(transformation).error(R.drawable.paimon_lost).into(imageView6);
                                    imageView6.setBackgroundResource(itemRss.getRareColorByName(parseInt)[3]);
                                    Picasso.get().load(itemRss.getArtifactByName(str4)[4]).fit().centerInside().transform(transformation).error(R.drawable.paimon_lost).into(imageView7);
                                    imageView7.setBackgroundResource(itemRss.getRareColorByName(parseInt)[3]);
                                    Picasso.get().load(itemRss.getArtifactByName(str4)[5]).fit().centerInside().transform(transformation).error(R.drawable.paimon_lost).into(imageView8);
                                    imageView8.setBackgroundResource(itemRss.getRareColorByName(parseInt)[3]);
                                }
                                if (ViewHolder.this.artifact_isComing.getVisibility() == View.VISIBLE) {
                                    imageView3.setVisibility(View.VISIBLE);
                                } else {
                                    imageView3.setVisibility(View.GONE);
                                }
                                dialog.setContentView(inflate);
                                dialog.setCanceledOnTouchOutside(true);
                                Window window = dialog.getWindow();
                                WindowManager.LayoutParams attributes = window.getAttributes();
                                DisplayMetrics displayMetrics = new DisplayMetrics();
                                activity.getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
                                int height = displayMetrics.heightPixels;
                                int width = displayMetrics.widthPixels;
                                attributes.width = width;
                                attributes.height = WRAP_CONTENT;
                                attributes.gravity = Gravity.BOTTOM;
                                window.setAttributes(attributes);
                                dialog.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //Toast.makeText(ArtifactsAdapter.this.context, ArtifactsAdapter.this.context.getString(R.string.none_info), Toast.LENGTH_SHORT).show();
                            CustomToast.toast(context,view,context.getString(R.string.none_info));
                        }
                    } else if (ArtifactsAdapter.this.context instanceof CalculatorUI) {
                        Log.wtf("YES", "IT's");
                        if (!((CalculatorUI) ArtifactsAdapter.this.context).checkNameList().contains(String.valueOf(ViewHolder.this.artifact_base_name.getText()).replace("_", StringUtils.SPACE))) {
                            Log.w("WEAPON_BASE", (String) ViewHolder.this.artifact_base_name.getText());
                            ((CalculatorUI) context).artifactQuestion(String.valueOf(artifact_base_name.getText()), "ADD", 0, (int) artifact_star.getRating());
                            return;
                        } else {
                            //Toast.makeText((CalculatorUI) ArtifactsAdapter.this.context, ArtifactsAdapter.this.context.getString(R.string.cal_choosed_already), Toast.LENGTH_SHORT).show();
                            CustomToast.toast(context,view,context.getString(R.string.cal_choosed_already));
                        }
                    } else if (ArtifactsAdapter.this.context instanceof Desk2048) {
                        System.out.println(artifact_base_name.getText());
                        (((Desk2048) context)).startArtifactInfo(String.valueOf(artifact_base_name.getText()),activity);
                    } else if (ArtifactsAdapter.this.context instanceof DeskSipTik) {
                        (((DeskSipTik) context)).startArtifactInfo(String.valueOf(artifact_base_name.getText()),activity);
                    } else if (ArtifactsAdapter.this.context instanceof Calculator2048) {
                        (((Calculator2048) context)).addArtifact(String.valueOf(artifact_base_name.getText()), (int) artifact_star.getRating());
                    } else if (ArtifactsAdapter.this.context instanceof SipTikCal){
                        for (int x = 0 ; x < artifactsList.size() ; x++){
                            if (artifactsList.get(x).getBaseName().equals(artifact_base_name.getText())){
                                Log.wtf("YES","IT's ABC");
                                (((SipTikCal) context)).artifactChoose(artifactsA.get(x),pos);
                            }
                        }
                    }
                }
            });
        }
    }

    public void filterList(List<Artifacts> list) {
        this.artifactsList = list;
        notifyDataSetChanged();
    }
}