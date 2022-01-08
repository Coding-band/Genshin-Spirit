package com.voc.genshin_helper.data;/*
 * Package com.voc.genshin_helper.data.WeaponsAdapter was
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
import android.widget.Toast;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;
import com.squareup.picasso.Picasso;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.util.RoundedCornersTransformation;
import com.voc.genshin_helper.R;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.List;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.io.IOUtils;
import org.json.JSONException;
import org.json.JSONObject;

/*
 * Package com.voc.genshin_helper.data.WeaponsAdapter was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

/* loaded from: classes.dex */
public class WeaponsAdapter extends RecyclerView.Adapter<WeaponsAdapter.ViewHolder> {
    private Context context;
    private AdapterView.OnItemClickListener mListener;
    private List<Weapons> weaponsList;
    private WebView webView;

    /* loaded from: classes.dex */
    public interface OnItemClickListener {
        void onItemClick(int i);
    }

    public WeaponsAdapter(Context context, List<Weapons> list) {
        this.context = context;
        this.weaponsList = list;
    }

    @Override // androidx.recyclerview.widget.RecyclerView.Adapter
    public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        Context context = this.context;
        if (context instanceof MainActivity) {
            View inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon_ico, viewGroup, false);
            if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon_ico, viewGroup, false);
            } else if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
                inflate = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon_ico_square, viewGroup, false);
            }
            return new ViewHolder(inflate, (OnItemClickListener) this.mListener);
        } else if (context instanceof CalculatorUI) {
            return new ViewHolder(LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_weapon_ico_square, viewGroup, false), (OnItemClickListener) this.mListener);
        } else {
            return null;
        }
    }

    public void onBindViewHolder(ViewHolder viewHolder, int i) {
        Weapons weapons = this.weaponsList.get(i);
        ItemRss itemRss = new ItemRss();
        Context context = this.context;
        RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.TOP);

        int width = 0;
        int height = 0;

        viewHolder.weapon_name.setText(weapons.getName());
        viewHolder.weapon_base_name.setText(weapons.getName());

        if (weapons.getIsComing() == 0) {
            viewHolder.weapon_isComing.setVisibility(View.GONE);
        }
        if (weapons.getIsComing() == 1) {
            viewHolder.weapon_isComing.setVisibility(View.GONE);
        }

        if (weapons.getRare() > 0 && weapons.getRare() < 6) {
            viewHolder.weapon_star.setNumStars(weapons.getRare());
            viewHolder.weapon_star.setRating((float) weapons.getRare());
        }

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

        viewHolder.weapon_stat.setText(this.context.getString(R.string.weapon_stat) + this.context.getString(itemRss.getSecAttr(weapons.stat_1)));
        viewHolder.weapon_icon.setBackgroundResource(itemRss.getRareColorByName(weapons.getRare())[0]);
        viewHolder.weapon_bg.setBackgroundResource(itemRss.getRareColorByName(weapons.getRare())[0]);
        viewHolder.weapon_nl.setBackgroundResource(itemRss.getRareColorByName(weapons.getRare())[1]);
        viewHolder.weapon_icon.getLayoutParams().width = width;
        viewHolder.weapon_icon.getLayoutParams().height = height;
        Context context2 = this.context;
        if (context2 instanceof MainActivity) {
            if (((MainActivity) context2).sharedPreferences.getString("curr_ui_grid", "2").equals("2")) {
                Picasso.get().load(itemRss.getWeaponByName(weapons.getName())[1]).fit().centerInside().transform(roundedCornersTransformation).error(R.drawable.paimon_full).into(viewHolder.weapon_icon);
            } else if (((MainActivity) this.context).sharedPreferences.getString("curr_ui_grid", "2").equals("3")) {
                Picasso.get().load(itemRss.getWeaponByName(weapons.getName())[1]).fit().centerInside().transform(roundedCornersTransformation).error(R.drawable.paimon_full).into(viewHolder.weapon_icon);
            }
        } else if (context2 instanceof CalculatorUI) {
            Picasso.get().load(itemRss.getWeaponByName(weapons.getName())[1]).fit().centerInside().transform(roundedCornersTransformation).error(R.drawable.paimon_full).into(viewHolder.weapon_icon);
        }
        viewHolder.weapon_name.setText(this.context.getString(itemRss.getWeaponByName(weapons.getName())[0]));

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
        return this.weaponsList.size();
    }

    /* loaded from: classes.dex */
    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView weapon_base_name;
        public ConstraintLayout weapon_bg;
        public ImageView weapon_element;
        public ImageView weapon_icon;
        public ImageView weapon_isComing;
        public TextView weapon_name;
        public LinearLayout weapon_nl;
        public RatingBar weapon_star;
        public TextView weapon_stat;

        public ViewHolder(View view, OnItemClickListener onItemClickListener) {
            super(view);
            this.weapon_icon = (ImageView) view.findViewById(R.id.weapon_img);
            this.weapon_name = (TextView) view.findViewById(R.id.weapon_name);
            this.weapon_star = (RatingBar) view.findViewById(R.id.weapon_star);
            this.weapon_isComing = (ImageView) view.findViewById(R.id.weapon_is_coming);
            this.weapon_base_name = (TextView) view.findViewById(R.id.weapon_base_name);
            this.weapon_nl = (LinearLayout) view.findViewById(R.id.weapon_nl);
            this.weapon_bg = (ConstraintLayout) view.findViewById(R.id.weapon_bg);
            this.weapon_stat = (TextView) view.findViewById(R.id.weapon_stat);
            this.weapon_icon.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    Log.wtf("is context instanceof MainActivity ?", WeaponsAdapter.this.context.getPackageName());
                    if (context instanceof MainActivity) {

                        String valueOf = String.valueOf(ViewHolder.this.weapon_base_name.getText());

                        SharedPreferences sharedPreferences = WeaponsAdapter.this.context.getSharedPreferences("user_info", 0);
                        String CharName_BASE = valueOf.replace(" ", "_");

                        String lang = sharedPreferences.getString("curr_lang", "zh-HK");
                        AssetManager assets = WeaponsAdapter.this.context.getResources().getAssets();
                        Log.wtf("CharName_BASE", CharName_BASE);

                        Log.wtf("lang", lang);
                        String str = LoadData("db/weapons/" + lang + "/" + CharName_BASE + ".json");
                        if (str == null){
                            str = LoadData("db/weapons/en-US/" + CharName_BASE + ".json");
                        }

                        if (str != null) {
                            try {
                                JSONObject jSONObject = new JSONObject(str);
                                String name = jSONObject.getString("name");
                                int rare = jSONObject.getInt("rare");
                                String weapon = jSONObject.getString("weapon");
                                Boolean isComingSoon = jSONObject.getBoolean("isComingSoon");
                                String desc = jSONObject.getString("desc");
                                String first_status = jSONObject.getString("first_status");
                                String second_status = jSONObject.getString("second_status");
                                String skill_name = jSONObject.getString("skill_name");
                                String skill_desc = jSONObject.getString("skill_desc");
                                String obtain_way = jSONObject.getString("obtain_way");

                                ItemRss itemRss = new ItemRss();
                                RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(25, 0, RoundedCornersTransformation.CornerType.TOP);
                                Dialog dialog = new Dialog(WeaponsAdapter.this.context, R.style.NormalDialogStyle_N);
                                View inflate = View.inflate(WeaponsAdapter.this.context, R.layout.item_weapon_info, null);

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
                                        .load(itemRss.getWeaponByName(name)[1]).fit().centerInside().transform(roundedCornersTransformation)
                                        .error(R.drawable.paimon_lost)
                                        .into(item_img);

                                ((TextView) inflate.findViewById(R.id.item_name)).setText(itemRss.getWeaponByName(name)[0]);
                                ((TextView) inflate.findViewById(R.id.item_info)).setText(desc);
                                ((ImageView) inflate.findViewById(R.id.item_element)).setVisibility(View.GONE);

                                item_star.setNumStars(rare);
                                item_star.setRating(rare);

                                ((ImageView) inflate.findViewById(R.id.item_weapon)).setImageResource(itemRss.getWeaponTypeIMG(weapon));
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
                                attributes.width = ScreenSizeUtils.getInstance(WeaponsAdapter.this.context).getScreenWidth();
                                attributes.height = ViewGroup.LayoutParams.WRAP_CONTENT;
                                attributes.gravity = Gravity.BOTTOM;
                                window.setAttributes(attributes);
                                dialog.show();
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        } else {
                            Toast.makeText(context, context.getString(R.string.none_info), Toast.LENGTH_SHORT).show();
                            return;
                        }

                    } else {
                        if (WeaponsAdapter.this.context instanceof CalculatorUI) {
                            Log.wtf("YES", "IT's");
                            Log.w("WEAPON_BASE", (String) ViewHolder.this.weapon_base_name.getText());
                            ((CalculatorUI) WeaponsAdapter.this.context).weaponQuestion(String.valueOf(weapon_base_name.getText()), "ADD", 0, (int) weapon_star.getRating());
                            return;
                        }
                        return;
                    }
                }
            });
        }

    }

    public void filterList(List<Weapons> list) {
        this.weaponsList = list;
        notifyDataSetChanged();
    }

    public String LoadData(String inFile) {
        String tContents = "";

        try {
            InputStream stream = context.getAssets().open(inFile);

            int size = stream.available();
            System.out.println("size"+ size);
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