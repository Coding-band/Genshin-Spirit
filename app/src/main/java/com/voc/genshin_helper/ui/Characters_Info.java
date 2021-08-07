package com.voc.genshin_helper.ui;

import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.content.res.AssetManager;
import android.text.Html;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.Characters_Rss;

import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;

/**
 * Package com.voc.genshin_helper.data was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2020 Xectorda 版權所有
 */
//https://stackoverflow.com/questions/3592836/check-for-file-existence-in-androids-assets-folder/7337516

public class Characters_Info {
    /** Method of requirements */
    Context context;
    SharedPreferences sharedPreferences ;
    Characters_Rss characters_rss ;

    /** Method of Char's details' container */
    /** Since String can't be null, so there will have "XPR" for identify is result correct */
    String CharName_BASE = "";

    // Main
    String name = "XPR" ;
    int star = 4;
    String area = "XPR";
    String element = "XPR" ;
    String weapon = "XPR" ;
    String sex = "XPR" ;
    String birth = "XPR" ;
    String role = "XPR" ;
    boolean isComing = false ;
    String desc = "XPR" ;
    String nick = "XPR" ;

    // Battle Talent
    String normal_name = "XPR";
    String normal_img = "XPR";
    String normal_desc1 = "XPR";
    String normal_desc2 = "XPR";
    String normal_desc3 = "XPR";
    String element_name = "XPR";
    String element_img = "XPR";
    String element_desc = "XPR";
    String final_name = "XPR";
    String final_img = "XPR";
    String final_desc = "XPR";
    String other_name = "XPR";
    String other_img = "XPR";
    String other_desc = "XPR";

    // Basic Talent
    String talent1_name = "XPR";
    String talent1_img = "XPR";
    String talent1_desc = "XPR";

    String talent2_name = "XPR";
    String talent2_img = "XPR";
    String talent2_desc = "XPR";

    String talent3_name = "XPR";
    String talent3_img = "XPR";
    String talent3_desc = "XPR";
    // Other skill
    String talent4_name = "XPR";
    String talent4_img = "XPR";
    String talent4_desc = "XPR";

    // SOF
    String sof1_name = "XPR";
    String sof1_img = "XPR";
    String sof1_desc = "XPR";
    String sof2_name = "XPR";
    String sof2_img = "XPR";
    String sof2_desc = "XPR";
    String sof3_name = "XPR";
    String sof3_img = "XPR";
    String sof3_desc = "XPR";
    String sof4_name = "XPR";
    String sof4_img = "XPR";
    String sof4_desc = "XPR";
    String sof5_name = "XPR";
    String sof5_img = "XPR";
    String sof5_desc = "XPR";
    String sof6_name = "XPR";
    String sof6_img = "XPR";
    String sof6_desc = "XPR";

    String[] weapon_advice = {};
    int[] weapon_stars = {};

    String[] artifacts1_array = {};
    String[] artifacts2_array = {};
    String[] artifacts3_array = {};
    String[] artifacts4_array = {};
    String[] artifacts5_array = {};

    /** https://stackoverflow.com/questions/45247927/how-to-parse-json-object-inside-json-object-in-java */
    public void JsonToStr (String str){
        try {
            JSONObject jsonObject = new JSONObject(str);
            name = jsonObject.getString("name");
            star = jsonObject.getInt("rare");
            area = jsonObject.getString("area");
            element = jsonObject.getString("element");
            weapon = jsonObject.getString("weapon");
            sex = jsonObject.getString("sex");
            birth = jsonObject.getString("birth");
            role = jsonObject.getString("role");
            isComing = jsonObject.getBoolean("isComingSoon");
            desc = jsonObject.getString("desc");
            nick = jsonObject.getString("nick");

            JSONObject battle_talent = jsonObject.getJSONObject("battle_talent");
            normal_name = battle_talent.getString("normal_name");
            normal_img = battle_talent.getString("normal_img");
            normal_desc1 = battle_talent.getString("normal_desc1");
            normal_desc2 = battle_talent.getString("normal_desc2");
            normal_desc3 = battle_talent.getString("normal_desc3");
            element_name = battle_talent.getString("element_name");
            element_img = battle_talent.getString("element_img");
            element_desc = battle_talent.getString("element_desc");
            final_name = battle_talent.getString("final_name");
            final_img = battle_talent.getString("final_img");
            final_desc = battle_talent.getString("final_desc");
            if(battle_talent.has("other_name")){
                other_name = battle_talent.getString("other_name");
                other_img = battle_talent.getString("other_img");
                other_desc = battle_talent.getString("other_desc");
            }

            JSONObject basic_talent = jsonObject.getJSONObject("basic_talent");
            talent1_name = basic_talent.getString("talent1_name");
            talent1_img = basic_talent.getString("talent1_img");
            talent1_desc = basic_talent.getString("talent1_desc");
            talent2_name = basic_talent.getString("talent2_name");
            talent2_img = basic_talent.getString("talent2_img");
            talent2_desc = basic_talent.getString("talent2_desc");
            talent3_name = basic_talent.getString("talent3_name");
            talent3_img = basic_talent.getString("talent3_img");
            talent3_desc = basic_talent.getString("talent3_desc");
            if(basic_talent.has("talent4_name")){
                talent4_name = basic_talent.getString("talent4_name");
                talent4_img = basic_talent.getString("talent4_img");
                talent4_desc = basic_talent.getString("talent4_desc");
            }

            JSONObject sof = jsonObject.getJSONObject("sof");
            sof1_name = sof.getString("sof1_name");
            sof1_img = sof.getString("sof1_img");
            sof1_desc = sof.getString("sof1_desc");
            sof2_name = sof.getString("sof2_name");
            sof2_img = sof.getString("sof2_img");
            sof2_desc = sof.getString("sof2_desc");
            sof3_name = sof.getString("sof3_name");
            sof3_img = sof.getString("sof3_img");
            sof3_desc = sof.getString("sof3_desc");
            sof4_name = sof.getString("sof4_name");
            sof4_img = sof.getString("sof4_img");
            sof4_desc = sof.getString("sof4_desc");
            sof5_name = sof.getString("sof5_name");
            sof5_img = sof.getString("sof5_img");
            sof5_desc = sof.getString("sof5_desc");
            sof6_name = sof.getString("sof6_name");
            sof6_img = sof.getString("sof6_img");
            sof6_desc = sof.getString("sof6_desc");

            if(jsonObject.has("dps_weapons")){
                JSONArray weapons_temp = jsonObject.getJSONArray("dps_weapons");
                weapon_advice = weapons_temp.toString().replace("[", "").replace("]", "").replace(",", " ").split(" ");
                Log.wtf("EEE", Arrays.toString(weapon_advice));
            }
            /*

            if(jsonObject.has("weapons_star")) {
                JSONArray stars_temp = jsonObject.getJSONArray("weapons_star");
                String[] strx = stars_temp.toString().replace("[", "").replace("]", "").replace(", ", "").split(",");
                Log.wtf("DDD", Arrays.toString(strx));
                weapon_stars = new int[strx.length];
                for (int x = 0 ; x < strx.length ; x ++){
                    weapon_stars[x] = Integer.parseInt(strx[x]);
                }
            }
             */

            if(jsonObject.has("dps_art1")) {
                JSONArray art1_temp = jsonObject.getJSONArray("dps_art1");
                artifacts1_array = art1_temp.toString().replace("[", "").replace("]", "").replace(",", " ").split(" ");
            }

            if(jsonObject.has("dps_art2")) {
                JSONArray art2_temp = jsonObject.getJSONArray("dps_art2");
                artifacts2_array = art2_temp.toString().replace("[", "").replace("]", "").replace(",", " ").split(" ");
            }

            if(jsonObject.has("dps_art3")) {
                JSONArray art3_temp = jsonObject.getJSONArray("dps_art3");
                artifacts3_array = art3_temp.toString().replace("[", "").replace("]", "").replace(",", " ").split(" ");
            }

            show();
        } catch (JSONException e) {
            e.printStackTrace();
        }
    }

    public void setup (String CharName_BASE, Context context){
        sharedPreferences = context.getSharedPreferences("user_info",Context.MODE_PRIVATE);
        this.CharName_BASE = CharName_BASE.replace(" ","_");
        this.context = context;
        characters_rss = new Characters_Rss();

        String lang = sharedPreferences.getString("lang","zh-HK");
        AssetManager mg = context.getResources().getAssets();
        InputStream is = null;
        try {
            Log.wtf("ALPHA","db/"+lang+"/"+this.CharName_BASE+".json");
            is = mg.open("db/"+lang+"/"+this.CharName_BASE+".json");
            if (is != null) {
                String result = IOUtils.toString(is, StandardCharsets.UTF_8);
                JsonToStr(result );
                is.close();
            }
        } catch (IOException ex) {
            if(ex != null) {
                Toast.makeText(context, "暫時沒有他/她的相關資料", Toast.LENGTH_SHORT).show();
            }

        }
    }

    public void show() {
        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_char_info, null);

        /** Method of info_detail */
        ImageView char_img = view.findViewById(R.id.info_char_img);
        TextView char_name = view.findViewById(R.id.info_char_name);
        RatingBar char_stars = view.findViewById(R.id.info_stars);
        ImageView char_element = view.findViewById(R.id.info_element_img);
        TextView char_area = view.findViewById(R.id.info_area_tv);
        ImageView char_weapon = view.findViewById(R.id.info_weapon);
        TextView char_role = view.findViewById(R.id.info_role);
        TextView char_sex = view.findViewById(R.id.info_sex);
        TextView char_birth = view.findViewById(R.id.info_date);

        /** Method of introduce */
        TextView info_intro = view.findViewById(R.id.info_intro);

        /** Method of battle_talent */
        CardView char_talent1_card = view.findViewById(R.id.info_talent1_card);
        ImageView char_talent1_ico = view.findViewById(R.id.info_talent1_ico);
        TextView char_talent1_name = view.findViewById(R.id.info_talent1_name);
        TextView char_talent1_normal = view.findViewById(R.id.info_talent1_normal);
        TextView char_talent1_hard = view.findViewById(R.id.info_talent1_hard);
        TextView char_talent1_drop = view.findViewById(R.id.info_talent1_drop);

        CardView char_talent2_card = view.findViewById(R.id.info_talent2_card);
        ImageView char_talent2_ico = view.findViewById(R.id.info_talent2_ico);
        TextView char_talent2_name = view.findViewById(R.id.info_talent2_name);
        TextView char_talent2_normal = view.findViewById(R.id.info_talent2_normal);

        CardView char_talent3_card = view.findViewById(R.id.info_talent3_card);
        ImageView char_talent3_ico = view.findViewById(R.id.info_talent3_ico);
        TextView char_talent3_name = view.findViewById(R.id.info_talent3_name);
        TextView char_talent3_normal = view.findViewById(R.id.info_talent3_normal);

        CardView char_talent4_card = view.findViewById(R.id.info_talent4_card);
        ImageView char_talent4_ico = view.findViewById(R.id.info_talent4_ico);
        TextView char_talent4_name = view.findViewById(R.id.info_talent4_name);
        TextView char_talent4_normal = view.findViewById(R.id.info_talent4_normal);

        /** Method of basic_talent */
        CardView char_basic_talent1_card = view.findViewById(R.id.info_btalent1_card);
        ImageView char_basic_talent1_ico = view.findViewById(R.id.info_btalent1_ico);
        TextView char_basic_talent1_name = view.findViewById(R.id.info_btalent1_name);
        TextView char_basic_talent1_normal = view.findViewById(R.id.info_btalent1_normal);

        CardView char_basic_talent2_card = view.findViewById(R.id.info_btalent2_card);
        ImageView char_basic_talent2_ico = view.findViewById(R.id.info_btalent2_ico);
        TextView char_basic_talent2_name = view.findViewById(R.id.info_btalent2_name);
        TextView char_basic_talent2_normal = view.findViewById(R.id.info_btalent2_normal);

        CardView char_basic_talent3_card = view.findViewById(R.id.info_btalent3_card);
        ImageView char_basic_talent3_ico = view.findViewById(R.id.info_btalent3_ico);
        TextView char_basic_talent3_name = view.findViewById(R.id.info_btalent3_name);
        TextView char_basic_talent3_normal = view.findViewById(R.id.info_btalent3_normal);

        /** Method of sof */
        CardView char_sof1_card = view.findViewById(R.id.info_sof1_card);
        ImageView char_sof1_ico = view.findViewById(R.id.info_sof1_ico);
        TextView char_sof1_name = view.findViewById(R.id.info_sof1_name);
        TextView char_sof1_normal = view.findViewById(R.id.info_sof1_normal);

        CardView char_sof2_card = view.findViewById(R.id.info_sof2_card);
        ImageView char_sof2_ico = view.findViewById(R.id.info_sof2_ico);
        TextView char_sof2_name = view.findViewById(R.id.info_sof2_name);
        TextView char_sof2_normal = view.findViewById(R.id.info_sof2_normal);

        CardView char_sof3_card = view.findViewById(R.id.info_sof3_card);
        ImageView char_sof3_ico = view.findViewById(R.id.info_sof3_ico);
        TextView char_sof3_name = view.findViewById(R.id.info_sof3_name);
        TextView char_sof3_normal = view.findViewById(R.id.info_sof3_normal);

        CardView char_sof4_card = view.findViewById(R.id.info_sof4_card);
        ImageView char_sof4_ico = view.findViewById(R.id.info_sof4_ico);
        TextView char_sof4_name = view.findViewById(R.id.info_sof4_name);
        TextView char_sof4_normal = view.findViewById(R.id.info_sof4_normal);

        CardView char_sof5_card = view.findViewById(R.id.info_sof5_card);
        ImageView char_sof5_ico = view.findViewById(R.id.info_sof5_ico);
        TextView char_sof5_name = view.findViewById(R.id.info_sof5_name);
        TextView char_sof5_normal = view.findViewById(R.id.info_sof5_normal);

        CardView char_sof6_card = view.findViewById(R.id.info_sof6_card);
        ImageView char_sof6_ico = view.findViewById(R.id.info_sof6_ico);
        TextView char_sof6_name = view.findViewById(R.id.info_sof6_name);
        TextView char_sof6_normal = view.findViewById(R.id.info_sof6_normal);

        /**
         * PLS REMEMBER ADD BACK SUGGESTED WEAPON,ART IN XML
         */

        /** MAIN */
        char_name.setText("【"+nick+"】 "+context.getString(characters_rss.getCharByName(name)[1]));
        char_img.setImageResource(characters_rss.getCharByName(name)[2]);
        char_img.setBackgroundResource(characters_rss.getElementByName(element)[2]);
        char_stars.setNumStars(star);
        char_stars.setRating(star);
        char_element.setImageResource(characters_rss.getElementByName(element)[0]);
        char_area.setText(characters_rss.getLocaleName(area,context));
        char_weapon.setImageResource(characters_rss.getWeaponTypeIMG(weapon));
        char_role.setText(characters_rss.getLocaleName(role,context));
        char_sex.setText(characters_rss.getLocaleName(sex,context));
        char_birth.setText(birth); // -> If necessary will change to Month | Day -> E.g. July 24th
        info_intro.setText(desc);

        /** BATTLE_TALENT */
        char_talent1_ico.setImageDrawable(characters_rss.getTalentIcoByName(normal_img,context));
        char_talent1_name.setText(talent1_name);
        char_talent1_normal.setText(normal_desc1);
        char_talent1_hard.setText(normal_desc2);
        char_talent1_drop.setText(normal_desc3);

        char_talent2_ico.setImageDrawable(characters_rss.getTalentIcoByName(element_img,context));
        char_talent2_name.setText(element_name);
        char_talent2_normal.setText(Html.fromHtml(element_desc));

        char_talent3_ico.setImageDrawable(characters_rss.getTalentIcoByName(final_img,context));
        char_talent3_name.setText(final_name);
        char_talent3_normal.setText(Html.fromHtml(final_desc));

        if(!talent4_name.equals("XPR")){
            char_talent4_card.setVisibility(View.VISIBLE);
            char_talent4_ico.setImageDrawable(characters_rss.getTalentIcoByName(other_img,context));
            char_talent4_name.setText(other_name);
            char_talent4_normal.setText(Html.fromHtml(other_desc));
        }

        /** BASIC_TALENT */
        char_basic_talent1_ico.setImageDrawable(characters_rss.getTalentIcoByName(talent1_img,context));
        char_basic_talent1_name.setText(talent1_name);
        char_basic_talent1_normal.setText(talent1_desc);

        char_basic_talent2_ico.setImageDrawable(characters_rss.getTalentIcoByName(talent2_img,context));
        char_basic_talent2_name.setText(talent2_name);
        char_basic_talent2_normal.setText(talent2_desc);

        char_basic_talent3_ico.setImageDrawable(characters_rss.getTalentIcoByName(talent3_img,context));
        char_basic_talent3_name.setText(talent3_name);
        char_basic_talent3_normal.setText(talent3_desc);

        /** Soul of Life*/
        char_sof1_ico.setImageDrawable(characters_rss.getTalentIcoByName(sof1_img,context));
        char_sof1_name.setText(sof1_name);
        char_sof1_normal.setText(sof1_desc);

        char_sof2_ico.setImageDrawable(characters_rss.getTalentIcoByName(sof2_img,context));
        char_sof2_name.setText(sof2_name);
        char_sof2_normal.setText(sof2_desc);

        char_sof3_ico.setImageDrawable(characters_rss.getTalentIcoByName(sof3_img,context));
        char_sof3_name.setText(sof3_name);
        char_sof3_normal.setText(sof3_desc);

        char_sof4_ico.setImageDrawable(characters_rss.getTalentIcoByName(sof4_img,context));
        char_sof4_name.setText(sof4_name);
        char_sof4_normal.setText(sof4_desc);

        char_sof5_ico.setImageDrawable(characters_rss.getTalentIcoByName(sof5_img,context));
        char_sof5_name.setText(sof5_name);
        char_sof5_normal.setText(sof5_desc);

        char_sof6_ico.setImageDrawable(characters_rss.getTalentIcoByName(sof6_img,context));
        char_sof6_name.setText(sof6_name);
        char_sof6_normal.setText(sof6_desc);




        /** Method of dialog */
        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }
}
