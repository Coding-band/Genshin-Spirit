package com.voc.genshin_helper.data;

import static android.content.Context.MODE_PRIVATE;
import static com.voc.genshin_helper.util.RoundedCornersTransformation.CornerType.TOP;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
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
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.ui.CalculatorDBActivity;
import com.voc.genshin_helper.ui.CalculatorUI;
import com.voc.genshin_helper.ui.MMXLVIII.Artifact_Info_2048;
import com.voc.genshin_helper.ui.MMXLVIII.CalculatorDB_2048;
import com.voc.genshin_helper.ui.MMXLVIII.Characters_Info_2048;
import com.voc.genshin_helper.ui.MMXLVIII.Desk2048;
import com.voc.genshin_helper.ui.MMXLVIII.Weapon_Info_2048;
import com.voc.genshin_helper.ui.MainActivity;
import com.voc.genshin_helper.ui.SplashActivity;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.DownloadTask;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2022 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */


public class CalculatorDBAdapter extends RecyclerView.Adapter<CalculatorDBAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<CalculatorDB> dbList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;


    public ArrayList<String> choosedNameList = new ArrayList<String>();
    public ArrayList<String> weaponChoosedNameList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedRare = new ArrayList<>(); // INNER USE ONLY
    public ArrayList<String> artifactChoosedNameList = new ArrayList<>();
    public ArrayList<Integer> artifactChoosedRare = new ArrayList<>();
    public ArrayList<String> artifactChoosedType = new ArrayList<>();

    String[] rules = new String[]{
            "0","1","2","3","4","5","6","7","8","9",
            "&","*","@","\"","{","}","^",":",",","#","$","\"","!","/","<",">","-","%",".","+","?",";","'"," ","~","_","|","="};

    public CalculatorDBAdapter(Context context, List<CalculatorDB> dbList, Activity activity) {
        this.context = context;
        this.activity = activity;
        this.dbList = dbList;
    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        ViewHolder evh = null;

        if(context instanceof CalculatorDBActivity){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculator_db, parent, false);
            evh = new ViewHolder(v, (OnItemClickListener) mListener);
        }else if(context instanceof CalculatorDB_2048){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculator_db_2048, parent, false);
            evh = new ViewHolder(v, (OnItemClickListener) mListener);
        } // SipTIk
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CalculatorDB db = dbList.get(position);
        ItemRss itemRss = new ItemRss();

        holder.name = db.getName();

        if(context instanceof CalculatorDBActivity){
            holder.db_sheet_tv.setText(db.getName());
            holder.db_sheet_info.setText(
                    context.getString(R.string.title_char)+" : "+String.valueOf(db.getChar_cnt())+" / "+
                            context.getString(R.string.title_weapons)+" : "+String.valueOf(db.getWeapon_cnt())+" / "+
                            context.getString(R.string.title_artifacts)+" : "+String.valueOf(db.getArtifact_cnt())
            );
        }else if(context instanceof CalculatorDB_2048){
            Date date = new Date();
            ItemRss item_rss = new ItemRss();
            date.setTime(Long.parseLong(db.getCreate_unix()));
            initDBArray(db.getName());

            holder.db_name.setText(db.getName());
            holder.db_info.setText(
                    String.valueOf(db.getChar_cnt())+" "+context.getString(R.string.title_char)+" , "
                            +String.valueOf(db.getWeapon_cnt())+" "+ context.getString(R.string.title_weapons)+" , "
                   +String.valueOf(db.getArtifact_cnt())+" "+context.getString(R.string.title_artifacts));
            holder.db_date.setText((date.getYear()+1900)+"/"+(date.getMonth()+1)+"/"+date.getDate());
            holder.db_char_ll.removeAllViews();
            holder.db_weapon_ll.removeAllViews();
            holder.db_art_ll.removeAllViews();

            for (int x = 0 ; x < db.getChar_cnt() ; x++){
                View char_view = LayoutInflater.from(context).inflate(R.layout.item_img_2048, holder.db_char_ll, false);
                ImageView item_img = char_view.findViewById(R.id.item_img);

                String json_base = LoadData("db/char/char_require_asc_skill.json");
                String name = "";
                int rare = 1;
                int tmp_cnt = 0;
                try {
                    JSONArray array = new JSONArray(json_base);
                    while(!name.equals(choosedNameList.get(x))) {
                        JSONObject object = array.getJSONObject(tmp_cnt);
                        name = object.getString("name");
                        rare = object.getInt("rare");
                        tmp_cnt ++;
                    }

                    final int radius = 180;
                    final int margin = 0;
                    final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                    switch (rare){
                        case 1 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                        case 2 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                        case 3 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                        case 4 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                        case 5 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                        default:  item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                    }

                    Picasso.get()
                            .load (FileLoader.loadIMG(item_rss.getCharByName(choosedNameList.get(x),context)[3],context))
                            .transform(transformation)
                            .fit()
                            .error (R.drawable.paimon_full)
                            .into (item_img);

                    // if character is exist in list
                    //tick.setVisibility(View.VISIBLE);
                    String finalName = name;
                    item_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Characters_Info_2048 cif = new Characters_Info_2048();
                            cif.setup(finalName,context,activity);
                        }
                    });

                    holder.db_char_ll.addView(char_view);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            for (int x = 0 ; x < db.getWeapon_cnt() ; x++){
                View char_view = LayoutInflater.from(context).inflate(R.layout.item_img_2048, holder.db_weapon_ll, false);
                ImageView item_img = char_view.findViewById(R.id.item_img);

                String name = "";
                int rare = 1;

                final int radius = 180;
                final int margin = 0;
                final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                switch (weaponChoosedRare.get(x)){
                    case 1 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                    case 2 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                    case 3 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                    case 4 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                    case 5 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                    default:  item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                }

                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getWeaponByName(weaponChoosedNameList.get(x),context)[1],context))
                        .transform(transformation)
                        .fit()
                        .error (R.drawable.paimon_full)
                        .into (item_img);

                // if character is exist in list
                //tick.setVisibility(View.VISIBLE);
                String finalName = weaponChoosedNameList.get(x);
                item_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Weapon_Info_2048 wif = new Weapon_Info_2048();
                        wif.setup(finalName,context,activity);
                    }
                });

                holder.db_weapon_ll.addView(char_view);
            }

            for (int x = 0 ; x < db.getArtifact_cnt() ; x++){
                View char_view = LayoutInflater.from(context).inflate(R.layout.item_img_2048, holder.db_art_ll, false);
                ImageView item_img = char_view.findViewById(R.id.item_img);

                String name = "";
                int rare = 1;

                final int radius = 180;
                final int margin = 0;
                final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                switch (artifactChoosedRare.get(x)){
                    case 1 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                    case 2 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                    case 3 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                    case 4 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                    case 5 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                    default:  item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                }

                int tmp_artifact_type_id = 1;
                if(artifactChoosedType.get(x).equals("Flower")){
                    tmp_artifact_type_id = 4;
                }else if(artifactChoosedType.get(x).equals("Plume")){
                    tmp_artifact_type_id = 2;
                }else if(artifactChoosedType.get(x).equals("Sand")){
                    tmp_artifact_type_id = 5;
                }else if(artifactChoosedType.get(x).equals("Goblet")){
                    tmp_artifact_type_id = 1;
                }else if(artifactChoosedType.get(x).equals("Circlet")){
                    tmp_artifact_type_id = 3;
                }

                Picasso.get()
                        .load (FileLoader.loadIMG(item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(artifactChoosedNameList.get(x)),context)[tmp_artifact_type_id],context))
                        .transform(transformation)
                        .fit()
                        .error (R.drawable.paimon_full)
                        .into (item_img);

                // if character is exist in list
                //tick.setVisibility(View.VISIBLE);
                String finalName = artifactChoosedNameList.get(x);
                item_img.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Artifact_Info_2048 aif = new Artifact_Info_2048();
                        aif.setup(finalName,context,activity);
                    }
                });

                holder.db_art_ll.addView(char_view);
            }

        }


    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView db_sheet_tv,db_sheet_info;
        public ImageView db_sheet_ico;
        public LinearLayout db_sheet_bg ;

        public LinearLayout db_card;
        public TextView db_name, db_info, db_date;
        public ImageView db_edit, db_char, db_weapon, db_art, db_delete;
        public LinearLayout db_char_ll, db_weapon_ll, db_art_ll;

        public String name = "";

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            if(context instanceof CalculatorDBActivity){
                db_sheet_ico = itemView.findViewById(R.id.db_sheet_ico);
                db_sheet_tv = itemView.findViewById(R.id.db_sheet_tv);
                db_sheet_bg = itemView.findViewById(R.id.db_sheet_bg);
                db_sheet_info = itemView.findViewById(R.id.db_sheet_info);

                db_sheet_bg.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (context instanceof CalculatorDBActivity){Log.wtf("YES","IT's");
                            (((CalculatorDBActivity) context)).transferDataToUI(name);
                        }
                    }
                });

                db_sheet_bg.setOnLongClickListener(new View.OnLongClickListener() {
                    @Override
                    public boolean onLongClick(View v) {
                        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                        View view = View.inflate(context, R.layout.menu_edit_delete, null);

                        EditText menu_edit_tv = view.findViewById(R.id.menu_edit_tv);
                        Button cancel = view.findViewById(R.id.menu_cancel);
                        Button ok = view.findViewById(R.id.menu_ok);
                        Button delete = view.findViewById(R.id.menu_delete);

                        menu_edit_tv.setText(db_sheet_tv.getText());

                        String oldName = db_sheet_tv.getText().toString();

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });


                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String newName = menu_edit_tv.getText().toString();
                                DataBaseHelper dbHelperS = new DataBaseHelper(context);
                                SQLiteDatabase db_check = dbHelperS.getWritableDatabase();

                                if(!newName.equals("") && !newName.equals(" ") && menu_edit_tv.getText() != null) {
                                    if(Arrays.asList(rules).contains(newName.substring(0,1))){
                                        CustomToast.toast(context,activity,context.getString(R.string.name_start_with_num_err));
                                    }else if(tableExists(db_check, newName)){
                                        CustomToast.toast(context,activity,context.getString(R.string.name_used));
                                    }else {
                                        if(newName.contains(" ")){
                                            newName = newName.replace(" ","_");
                                        }

                                        if(!oldName.toLowerCase().equals(newName.toLowerCase())){
                                        /*
                                        Icon setting (Easter Egg) -> Maybe make a real for choosing ?

                                        ItemRss item_rss =  new ItemRss();
                                        final int radius = 25;
                                        final int margin = 4;
                                        final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                                        Picasso.get()
                                                .load(FileLoader.loadIMG(item_rss.getCharByName(item_rss.getCharNameByTranslatedName(newName.replace("_"," "), context),context)[3], context))
                                                .transform(transformation)
                                                .resize(72, 72)
                                                .error(R.drawable.ic_baseline_calculate_24)
                                                .into(db_sheet_ico);

                                         */

                                            DataBaseHelper dbHelper = new DataBaseHelper(context);
                                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                                            db.execSQL("ALTER TABLE "+oldName+"_char"+ " RENAME TO "+newName+"_char");
                                            db.execSQL("ALTER TABLE "+oldName+ "_weapon"+" RENAME TO "+newName +"_weapon");
                                            db.execSQL("ALTER TABLE "+oldName+ "_artifact"+" RENAME TO "+newName+ "_artifact");

                                            db.execSQL("UPDATE IndexDB SET name = \""+newName+"\" WHERE name = \""+oldName+"\";");

                                            SharedPreferences sharedPreferencesB = context.getSharedPreferences("buff_list",MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferencesB.edit();
                                            for (int x = 0 ; x < 5 ; x++){

                                                // Copy the old buff data to brand new part
                                                editor.putString(newName+"_"+"artifactBuffMainItem"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffMainItem"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec1Item"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec1Item"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec2Item"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec2Item"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec3Item"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec3Item"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec4Item"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec4Item"+String.valueOf(x),""));

                                                editor.putString(newName+"_"+"artifactBuffMainValue"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffMainValue"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec1Value"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec1Value"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec2Value"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec2Value"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec3Value"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec3Value"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec4Value"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec4Value"+String.valueOf(x),""));

                                                // Delete tge old buff data
                                                editor.remove(oldName+"_"+"artifactBuffMainItem"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec1Item"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec2Item"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec3Item"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec4Item"+String.valueOf(x));

                                                editor.remove(oldName+"_"+"artifactBuffMainValue"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec1Value"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec2Value"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec3Value"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec4Value"+String.valueOf(x));

                                                editor.apply();
                                            }

                                            dialog.dismiss();
                                            if (context instanceof CalculatorDBActivity){Log.wtf("YES","IT's");
                                                (((CalculatorDBActivity) context)).readIndexRecord();
                                            }
                                        }else {
                                            CustomToast.toast(context,activity,"You must not use the same name (Even different letter case) !");
                                        }
                                    }
                                }
                            }
                        });

                        delete.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                DataBaseHelper dbHelper = new DataBaseHelper(context);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                db.execSQL("DROP TABLE "+oldName+"_char");
                                db.execSQL("DROP TABLE "+oldName+"_weapon");
                                db.execSQL("DROP TABLE "+oldName+"_artifact");
                                // DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';
                                db.execSQL("DELETE FROM IndexDB WHERE name = \""+oldName+"\"");
                                dialog.dismiss();
                                if (context instanceof CalculatorDBActivity){Log.wtf("YES","IT's");
                                    (((CalculatorDBActivity) context)).readIndexRecord();
                                }
                            }
                        });

                        dialog.setContentView(view);
                        dialog.setCanceledOnTouchOutside(true);
                        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                        Window dialogWindow = dialog.getWindow();
                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp.gravity = Gravity.CENTER;
                        dialogWindow.setAttributes(lp);
                        dialog.show();
                        return false;
                    }
                });
            }else if(context instanceof CalculatorDB_2048){
                db_card = itemView.findViewById(R.id.db_card);
                db_name = itemView.findViewById(R.id.db_name);
                db_delete = itemView.findViewById(R.id.db_delete);
                db_info = itemView.findViewById(R.id.db_info);
                db_date = itemView.findViewById(R.id.db_date);
                db_edit = itemView.findViewById(R.id.db_edit);
                db_char = itemView.findViewById(R.id.db_char);
                db_weapon = itemView.findViewById(R.id.db_weapon);
                db_art = itemView.findViewById(R.id.db_art);
                db_char_ll = itemView.findViewById(R.id.db_char_ll);
                db_weapon_ll = itemView.findViewById(R.id.db_weapon_ll);
                db_art_ll = itemView.findViewById(R.id.db_art_ll);

                db_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (context instanceof CalculatorDB_2048){
                            (((CalculatorDB_2048) context)).transferDataToUI(name);
                        }
                    }
                });

                db_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                        View view = View.inflate(context, R.layout.fragment_delete_confirm, null);

                        FrameLayout db_ok = view.findViewById(R.id.db_ok);
                        FrameLayout db_cancel = view.findViewById(R.id.db_cancel);

                        db_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                DataBaseHelper dbHelper = new DataBaseHelper(context);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                String oldName = db_name.getText().toString();
                                db.execSQL("DROP TABLE "+oldName+"_char");
                                db.execSQL("DROP TABLE "+oldName+"_weapon");
                                db.execSQL("DROP TABLE "+oldName+"_artifact");
                                // DELETE FROM Customers WHERE CustomerName='Alfreds Futterkiste';
                                db.execSQL("DELETE FROM IndexDB WHERE name = \""+oldName+"\"");
                                if (context instanceof CalculatorDB_2048){
                                    (((CalculatorDB_2048) context)).readIndexRecord();
                                }
                                dialog.dismiss();
                            }
                        });

                        db_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.setContentView(view);
                        dialog.setCanceledOnTouchOutside(true);
                        Window dialogWindow = dialog.getWindow();
                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                        // 2O48 DESIGN
                        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
                        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp.gravity = Gravity.CENTER;
                        dialogWindow.setAttributes(lp);
                        dialog.show();

                        /*
                        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                        View view = View.inflate(context, R.layout.fragment_delete_confirm, null);

                        FrameLayout db_ok = view.findViewById(R.id.db_ok);
                        FrameLayout db_cancel = view.findViewById(R.id.db_cancel);

                        db_ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {

                            }
                        });

                        db_cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                dialog.dismiss();
                            }
                        });

                        dialog.setContentView(view);
                        dialog.setCanceledOnTouchOutside(true);
                        Window dialogWindow = dialog.getWindow();
                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                        // 2O48 DESIGN
                        dialogWindow.setStatusBarColor(context.getColor(R.color.status_bar_2048));
                        dialogWindow.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));
                        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp.gravity = Gravity.CENTER;
                        dialogWindow.setAttributes(lp);
                        dialog.show();
                         */
                    }
                });

                db_edit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                        View view = View.inflate(context, R.layout.menu_edit_2048, null);

                        EditText menu_edit_tv = view.findViewById(R.id.header_search_et);
                        FrameLayout cancel = view.findViewById(R.id.db_cancel);
                        FrameLayout ok = view.findViewById(R.id.db_ok);
                        ImageView reset = view.findViewById(R.id.header_search_reset);

                        menu_edit_tv.setText(db_name.getText());

                        String oldName = db_name.getText().toString();
                        reset.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View v) {
                                menu_edit_tv.setText("");
                            }
                        });

                        cancel.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                dialog.dismiss();
                            }
                        });

                        ok.setOnClickListener(new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                String newName = menu_edit_tv.getText().toString();
                                DataBaseHelper dbHelperS = new DataBaseHelper(context);
                                SQLiteDatabase db_check = dbHelperS.getWritableDatabase();

                                if(!newName.equals("") && !newName.equals(" ") && menu_edit_tv.getText() != null) {
                                    if(Arrays.asList(rules).contains(newName.substring(0,1))){
                                        CustomToast.toast(context,activity,context.getString(R.string.name_start_with_num_err));
                                    }else if(tableExists(db_check, newName)){
                                        CustomToast.toast(context,activity,context.getString(R.string.name_used));
                                    }else {
                                        if(newName.contains(" ")){
                                            newName = newName.replace(" ","_");
                                        }

                                        if(!oldName.toLowerCase().equals(newName.toLowerCase())){

                                            DataBaseHelper dbHelper = new DataBaseHelper(context);
                                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                                            db.execSQL("ALTER TABLE "+oldName+"_char"+ " RENAME TO "+newName+"_char");
                                            db.execSQL("ALTER TABLE "+oldName+ "_weapon"+" RENAME TO "+newName +"_weapon");
                                            db.execSQL("ALTER TABLE "+oldName+ "_artifact"+" RENAME TO "+newName+ "_artifact");

                                            db.execSQL("UPDATE IndexDB SET name = \""+newName+"\" WHERE name = \""+oldName+"\";");

                                            SharedPreferences sharedPreferencesB = context.getSharedPreferences("buff_list",MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferencesB.edit();
                                            for (int x = 0 ; x < 5 ; x++){

                                                // Copy the old buff data to brand new part
                                                editor.putString(newName+"_"+"artifactBuffMainItem"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffMainItem"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec1Item"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec1Item"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec2Item"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec2Item"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec3Item"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec3Item"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec4Item"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec4Item"+String.valueOf(x),""));

                                                editor.putString(newName+"_"+"artifactBuffMainValue"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffMainValue"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec1Value"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec1Value"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec2Value"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec2Value"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec3Value"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec3Value"+String.valueOf(x),""));
                                                editor.putString(newName+"_"+"artifactBuffSec4Value"+String.valueOf(x),sharedPreferencesB.getString(oldName+"_"+"artifactBuffSec4Value"+String.valueOf(x),""));

                                                // Delete tge old buff data
                                                editor.remove(oldName+"_"+"artifactBuffMainItem"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec1Item"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec2Item"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec3Item"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec4Item"+String.valueOf(x));

                                                editor.remove(oldName+"_"+"artifactBuffMainValue"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec1Value"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec2Value"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec3Value"+String.valueOf(x));
                                                editor.remove(oldName+"_"+"artifactBuffSec4Value"+String.valueOf(x));

                                                editor.apply();
                                            }

                                            dialog.dismiss();
                                            if (context instanceof CalculatorDB_2048){
                                                (((CalculatorDB_2048) context)).readIndexRecord();
                                            }
                                        }else {
                                            CustomToast.toast(context,activity,"You must not use the same name (Even different letter case) !");
                                        }
                                    }
                                }
                            }
                        });

                        dialog.setContentView(view);
                        dialog.setCanceledOnTouchOutside(true);
                        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
                        Window dialogWindow = dialog.getWindow();
                        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
                        lp.width = (int) (ScreenSizeUtils.getInstance(context).getScreenWidth());
                        lp.height = WindowManager.LayoutParams.WRAP_CONTENT;
                        lp.gravity = Gravity.CENTER;
                        dialogWindow.setAttributes(lp);
                        dialog.show();
                    }
                });

            }

        }
    }


    public void initDBArray(String name){
        SQLiteDatabase db = new DataBaseHelper(context).getReadableDatabase();
        choosedNameList.clear();
        weaponChoosedNameList.clear();
        weaponChoosedRare.clear();
        artifactChoosedNameList.clear();
        artifactChoosedRare.clear();
        artifactChoosedType.clear();

        Cursor cursor = db.query(
                name+"_char",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            choosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("charName")));
        }
        cursor.close();

        cursor = db.query(
                name+"_weapon",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            weaponChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("weaponName")));
            weaponChoosedRare.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponRare")));
        }
        cursor.close();

        /**
         * READ DATA -> Artifact
         */

        cursor = db.query(
                name+"_artifact",   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            artifactChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactName")));
            artifactChoosedRare.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactRare")));
            artifactChoosedType.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactType")));
        }
        cursor.close();

    }


    public void filterList(List<CalculatorDB> filteredList) {
        dbList = filteredList;
        notifyDataSetChanged();
    }

    public boolean tableExists(SQLiteDatabase db, String tableName)
    {
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM IndexDB WHERE name = ?",
                new String[] {tableName}
        );
        if (!cursor.moveToFirst())
        {
            cursor.close();
            return false;
        }
        int count = cursor.getInt(0);
        cursor.close();
        return count > 0;
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