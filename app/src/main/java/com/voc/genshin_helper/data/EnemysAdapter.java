package com.voc.genshin_helper.data;/*
 * Package com.voc.genshin_helper.data.EnemysAdapter was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import static android.content.Context.MODE_PRIVATE;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.List;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

/* loaded from: classes.dex */
public class EnemysAdapter extends RecyclerView.Adapter<EnemysAdapter.ViewHolder> {
    private Context context;
    private AdapterView.OnItemClickListener mListener;
    private List<Enemys> enemysList;
    private WebView webView;

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public EnemysAdapter(Context context, List<Enemys> list) {
        this.context = context;
        this.enemysList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = this.context;
        if (context instanceof CalculatorUI) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_char_ico_square, viewGroup, false), (OnItemClickListener) this.mListener);
        } else {
            return null;
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Enemys enemys = this.enemysList.get(i);
        ItemRss itemRss = new ItemRss();
        Context context = this.context;
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.TOP);

        int width = 0;
        int height = 0;

        viewHolder.enemy_name.setText(enemys.getName());
        viewHolder.enemy_base_name.setText(enemys.getName());

        if (context instanceof MainActivity) {
            width = (ScreenSizeUtils.getInstance(context).getScreenWidth() - 128) / 2;
            if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
                width = (ScreenSizeUtils.getInstance(this.context).getScreenWidth() - 128) / 2;
                height = (width * 58) / 32;
            } else {
                if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
                    width = (ScreenSizeUtils.getInstance(this.context).getScreenWidth() - 128) / 3;
                    height = (ScreenSizeUtils.getInstance(this.context).getScreenWidth() - 128) / 3;
                }
            }
        } else if (context instanceof CalculatorUI) {
            width = (ScreenSizeUtils.getInstance(context).getScreenWidth() - 128) / 3;
            height = (ScreenSizeUtils.getInstance(this.context).getScreenWidth() - 128) / 3;
        }

        viewHolder.enemy_stat.setText(this.context.getString(R.string.enemy_stat) + this.context.getString(itemRss.getSecAttr(enemys.stat_1)));
        viewHolder.enemy_icon.setBackgroundResource(itemRss.getRareColorByName(enemys.getRare())[0]);
        viewHolder.enemy_bg.setBackgroundResource(itemRss.getRareColorByName(enemys.getRare())[0]);
        viewHolder.enemy_nl.setBackgroundResource(itemRss.getRareColorByName(enemys.getRare())[1]);
        viewHolder.enemy_icon.getLayoutParams().width = width;
        viewHolder.enemy_icon.getLayoutParams().height = height;
        Context context2 = this.context;
        if (context2 instanceof MainActivity) {
            if (((MainActivity) context2).sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
                Picasso.get().load(FileLoader.loadIMG(itemRss.getEnemyByName(enemys.getName(),context)[1],context)).fit().centerInside().transform(roundedCornersTransformation).error(R.drawable.paimon_full).into(viewHolder.enemy_icon);
            } else if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
                Picasso.get().load(FileLoader.loadIMG(itemRss.getEnemyByName(enemys.getName(),context)[1],context)).fit().centerInside().transform(roundedCornersTransformation).error(R.drawable.paimon_full).into(viewHolder.enemy_icon);
            }
        } else if (context2 instanceof CalculatorUI) {
            Picasso.get().load(FileLoader.loadIMG(itemRss.getEnemyByName(enemys.getName(),context)[1],context)).fit().centerInside().transform(roundedCornersTransformation).error(R.drawable.paimon_full).into(viewHolder.enemy_icon);
        }
        viewHolder.enemy_name.setText(itemRss.getEnemyByName(enemys.getName(),context)[0]);

        SharedPreferences sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #

        ColorStateList myList = new ColorStateList(
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

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public int getItemCount() {
        return this.enemysList.size();
    }

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView enemy_base_name;
        public ConstraintLayout enemy_bg;
        public ImageView enemy_element;
        public ImageView enemy_icon;
        public ImageView enemy_isComing;
        public TextView enemy_name;
        public LinearLayout enemy_nl;
        public RatingBar enemy_star;
        public TextView enemy_stat;

        public ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            this.enemy_icon = (ImageView) view.findViewById(R.id.enemy_img);
            this.enemy_name = (TextView) view.findViewById(R.id.enemy_name);
            this.enemy_star = (RatingBar) view.findViewById(R.id.enemy_star);
            this.enemy_isComing = (ImageView) view.findViewById(R.id.enemy_is_coming);
            this.enemy_base_name = (TextView) view.findViewById(R.id.enemy_base_name);
            this.enemy_nl = (LinearLayout) view.findViewById(R.id.enemy_nl);
            this.enemy_bg = (ConstraintLayout) view.findViewById(R.id.enemy_bg);
            this.enemy_stat = (TextView) view.findViewById(R.id.enemy_stat);
            this.enemy_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.wtf("is context instanceof MainActivity ?", EnemysAdapter.this.context.getPackageName());
                    if (context instanceof MainActivity) {

                        String valueOf = String.valueOf(ViewHolder.this.enemy_base_name.getText());

                        SharedPreferences sharedPreferences = EnemysAdapter.this.context.getSharedPreferences("user_info", 0);
                        String CharName_BASE = valueOf.replace(" ", "_");

                        String lang = sharedPreferences.getString("curr_lang", "zh-HK");
                        AssetManager assets = EnemysAdapter.this.context.getResources().getAssets();
                        Log.wtf("CharName_BASE", CharName_BASE);

                        Log.wtf("lang", lang);
                        String str = LoadData("db/enemys/" + lang + "/" + CharName_BASE + ".json");
                        if (str == null){
                            str = LoadData("db/enemys/en-US/" + CharName_BASE + ".json");
                        }

                        if (str != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                String name = jSONObject.getString("name");
                                int rare = jSONObject.getInt("rare");
                                String enemy = jSONObject.getString("enemy");
                                Boolean isComingSoon = jSONObject.getBoolean("isComingSoon");
                                String desc = jSONObject.getString("desc");
                                String first_status = jSONObject.getString("first_status");
                                String second_status = jSONObject.getString("second_status");
                                String skill_name = jSONObject.getString("skill_name");
                                String skill_desc = jSONObject.getString("skill_desc");
                                String obtain_way = jSONObject.getString("obtain_way");

                                ItemRss itemRss = new ItemRss();
                                RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.TOP);
                                Dialog dialog = new Dialog(EnemysAdapter.this.context, R.style.NormalDialogStyle_N);
                                View inflate = View.inflate(EnemysAdapter.this.context, R.layout.item_enemy_info, null);

                                ImageView item_img = (ImageView) inflate.findViewById(R.id.item_img);
                                LinearLayout item_nl = (LinearLayout) inflate.findViewById(R.id.item_nl);
                                RatingBar item_star = (RatingBar) inflate.findViewById(R.id.item_star);
                                ImageView item_is_coming = (ImageView) inflate.findViewById(R.id.item_is_coming);
                                ImageView info_item1 = (ImageView) inflate.findViewById(R.id.info_item1);
                                ImageView info_item2 = (ImageView) inflate.findViewById(R.id.info_item2);
                                ImageView info_item3 = (ImageView) inflate.findViewById(R.id.info_item3);
                                ImageView info_item4 = (ImageView) inflate.findViewById(R.id.info_item4);
                                ImageView info_item5 = (ImageView) inflate.findViewById(R.id.info_item5);
                                LinearLayout item_talent = (LinearLayout) inflate.findViewById(R.id.item_talent);
                                LinearLayout item_skill = (LinearLayout) inflate.findViewById(R.id.item_skill);
                                TextView info_skill_title = (TextView) inflate.findViewById(R.id.info_skill_title);

                                Picasso.get()
                                        .load(FileLoader.loadIMG(itemRss.getEnemyByName(name,context)[1],context)).fit().centerInside().transform(roundedCornersTransformation)
                                        .error(R.drawable.paimon_lost)
                                        .into(item_img);

                                ((TextView) inflate.findViewById(R.id.item_name)).setText(itemRss.getEnemyByName(name,context)[0]);
                                ((TextView) inflate.findViewById(R.id.item_info)).setText(desc);
                                ((ImageView) inflate.findViewById(R.id.item_element)).setVisibility(View.GONE);

                                item_star.setNumStars(rare);
                                item_star.setRating(rare);

                                ((ImageView) inflate.findViewById(R.id.item_enemy)).setImageResource(itemRss.getEnemyTypeIMG(enemy));
                                item_img.setBackgroundResource(itemRss.getRareColorByName(rare)[0]);
                                item_nl.setBackgroundResource(itemRss.getRareColorByName(rare)[1]);
                                item_talent.setVisibility(View.GONE);
                                item_skill.setVisibility(View.VISIBLE);
                                info_skill_title.setText(skill_name);
                                ((TextView) inflate.findViewById(R.id.info_skill_desc)).setText(skill_desc);
                                if (isComingSoon ) {
                                    item_is_coming.setVisibility(View.VISIBLE);
                                } else {
                                    item_is_coming.setVisibility(View.GONE);
                                }
                                dialog.setContentView(inflate);
                                dialog.setCanceledOnTouchOutside(true);
                                Window window = dialog.getWindow();
                                WindowManager.LayoutParams attributes = window.getAttributes();
                                attributes.width = ScreenSizeUtils.getInstance(EnemysAdapter.this.context).getScreenWidth();
                                attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                                attributes.gravity = Gravity.BOTTOM;
                                window.setAttributes(attributes);
                                dialog.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            //Toast.makeText(context, context.getString(R.string.none_info), Toast.LENGTH_SHORT).show();

                            CustomToast.toast(context,v,context.getString(R.string.none_info));
                            return;
                        }

                    } else {
                        if (EnemysAdapter.this.context instanceof CalculatorUI) {
                            Log.wtf("YES", "IT's");
                            Log.w("WEAPON_BASE", (String) ViewHolder.this.enemy_base_name.getText());
                            ((CalculatorUI) EnemysAdapter.this.context).enemyQuestion(String.valueOf(enemy_base_name.getText()), "ADD", 0, (int) enemy_star.getRating());
                            return;
                        }
                        return;
                    }
                }
            });
        }

    }

    public void filterList(List<Enemys> list) {
        this.enemysList = list;
        notifyDataSetChanged();
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