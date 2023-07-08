package com.voc.genshin_helper.buff.db;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.webkit.WebView;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.buff.BuffDatabaseUI;
import com.voc.genshin_helper.buff.EnkaDataCollect;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.ui.MMXLVIII.Artifact_Info_2048;
import com.voc.genshin_helper.ui.MMXLVIII.Characters_Info_2048;
import com.voc.genshin_helper.ui.MMXLVIII.Weapon_Info_2048;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

/**
 * Created by ankit on 27/10/17.
 */


public class BuffDBAdapter extends RecyclerView.Adapter<BuffDBAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private ArrayList<BuffDB> dbList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;


    public ArrayList<String> charChoosedNameList = new ArrayList<String>();
    public ArrayList<Integer> charChoosedRareList = new ArrayList<Integer>();
    public ArrayList<String> weaponChoosedNameList = new ArrayList<>();
    public ArrayList<Integer> weaponChoosedRareList = new ArrayList<>(); // INNER USE ONLY
    public ArrayList<String> artifactChoosedNameList = new ArrayList<>();
    public ArrayList<String> weaponChoosedFollowList = new ArrayList<>();
    public ArrayList<Integer> artifactChoosedRareList = new ArrayList<>();
    public ArrayList<String> artifactChoosedTypeList = new ArrayList<>();
    public ArrayList<String> artifactChoosedFollowList = new ArrayList<>();

    private SharedPreferences sharedPreferences;

    boolean hasAddedAcc = false;

    public BuffDBAdapter(Context context, ArrayList<BuffDB> dbList, Activity activity, SharedPreferences sharedPreferences, boolean hasAddedAcc) {
        this.context = context;
        this.activity = activity;
        this.dbList = dbList;
        this.sharedPreferences = sharedPreferences;
        this.hasAddedAcc = hasAddedAcc;

    }

    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v;
        ViewHolder evh = null;

        if(context instanceof BuffDatabaseUI){
            //Default, will edit when checked it's Account_xxx
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_buff_db_2048, parent, false);
            evh = new ViewHolder(v, (OnItemClickListener) mListener);
        }
        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        BuffDB db = dbList.get(position);

        Date date = new Date();
        ItemRss item_rss = new ItemRss();
        date.setTime(Long.parseLong(db.getUndate_unix()));
        initDBArray("\""+db.getName()+"\"");

        if(db.getName().startsWith("Account_") && position == 0){
            String[] item = db.getName().replace("Account_","").replace("Set_","").split("_");
            if((item[0].equals(sharedPreferences.getString("genshin_uid", "-1")))){
                holder.db_name.setText(item[1]);
                holder.db_subname.setText(item[0]);
                holder.db_info.setText(context.getString(R.string.from_game_display));
                holder.db_date.setText(context.getString(R.string.last_update)+" : "+(date.getYear()+1900)+"/"+(date.getMonth()+1)+"/"+date.getDate());
                holder.db_char_ll.removeAllViews();
                holder.isAccount = true;

                holder.db_subname.setVisibility(View.VISIBLE);
                holder.db_refresh.setVisibility(View.VISIBLE);
                holder.db_edit.setVisibility(View.GONE);
                holder.db_delete.setVisibility(View.GONE);
                holder.db_weapon_ll.setVisibility(View.GONE);
                holder.db_art_ll.setVisibility(View.GONE);
                holder.db_char.setVisibility(View.GONE);
                holder.db_weapon.setVisibility(View.GONE);
                holder.db_art.setVisibility(View.GONE);


                for (int x = 0 ; x < db.getChar_cnt() ; x++) {
                    View char_view = LayoutInflater.from(context).inflate(R.layout.item_img_2048, holder.db_char_ll, false);
                    ImageView item_img = char_view.findViewById(R.id.item_img);
                    final int radius = 180;
                    final int margin = 0;
                    final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                    switch (charChoosedRareList.get(x)) {
                        case 1:
                            item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);
                            break;
                        case 2:
                            item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);
                            break;
                        case 3:
                            item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);
                            break;
                        case 4:
                            item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);
                            break;
                        case 5:
                            item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);
                            break;
                        default:
                            item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);
                            break;
                    }

                    Picasso.get()
                            .load(item_rss.getCharByName(charChoosedNameList.get(x), context)[3])
                            .transform(transformation)
                            .fit()
                            .error(R.drawable.paimon_full)
                            .into(item_img);

                    // if character is exist in list
                    //tick.setVisibility(View.VISIBLE);
                    String finalName = charChoosedNameList.get(x);
                    item_img.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Characters_Info_2048 cif = new Characters_Info_2048();
                            cif.setup(finalName, context, activity);
                        }
                    });

                    holder.db_char_ll.addView(char_view);
                }
            }
        }
        else if(context instanceof BuffDatabaseUI){
            String[] item = db.getName().replace("Account_","").replace("Set_","").split("_");
            holder.db_name.setText(item[0]);
            holder.db_subname.setVisibility(View.GONE);
            holder.db_edit.setVisibility(View.VISIBLE);
            holder.db_delete.setVisibility(View.VISIBLE);
            holder.db_info.setText(
                    String.valueOf(db.getChar_cnt())+" "+context.getString(R.string.title_char)+" , "
                            +String.valueOf(db.getWeapon_cnt())+" "+ context.getString(R.string.title_weapons)+" , "
                   +String.valueOf(db.getArtifact_cnt())+" "+context.getString(R.string.title_artifacts));
            holder.db_date.setText((date.getYear()+1900)+"/"+(date.getMonth()+1)+"/"+date.getDate());
            holder.db_char_ll.removeAllViews();
            holder.db_weapon_ll.removeAllViews();
            holder.db_art_ll.removeAllViews();

            holder.set_name = "Set_"+item[0];

            for (int x = 0 ; x < db.getChar_cnt() ; x++){
                View char_view = LayoutInflater.from(context).inflate(R.layout.item_img_2048, holder.db_char_ll, false);
                ImageView item_img = char_view.findViewById(R.id.item_img);

                String name = "";

                final int radius = 180;
                final int margin = 0;
                final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                switch (charChoosedRareList.get(x)){
                    case 1 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                    case 2 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                    case 3 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                    case 4 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                    case 5 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                    default:  item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                }

                Picasso.get()
                        .load (item_rss.getCharByName(charChoosedNameList.get(x),context)[3])
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
            }

            for (int x = 0 ; x < db.getWeapon_cnt() ; x++){
                View char_view = LayoutInflater.from(context).inflate(R.layout.item_img_2048, holder.db_weapon_ll, false);
                ImageView item_img = char_view.findViewById(R.id.item_img);
                ImageView item_user_img = char_view.findViewById(R.id.item_user_img);

                String name = "";
                int rare = 1;

                final int radius = 180;
                final int margin = 0;
                final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                switch (weaponChoosedRareList.get(x)){
                    case 1 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                    case 2 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                    case 3 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                    case 4 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                    case 5 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                    default:  item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                }

                Picasso.get()
                        .load (item_rss.getWeaponByName(weaponChoosedNameList.get(x))[1])
                        .transform(transformation)
                        .fit()
                        .error (R.drawable.paimon_full)
                        .into (item_img);

                if(!weaponChoosedFollowList.get(x).equals("N/A")){
                    item_user_img.setVisibility(View.VISIBLE);
                    Picasso.get()
                            .load (item_rss.getCharByName(weaponChoosedFollowList.get(x),context)[3])
                            .transform(transformation)
                            .fit()
                            .error (R.drawable.paimon_full)
                            .into (item_user_img);
                }

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
                ImageView item_user_img = char_view.findViewById(R.id.item_user_img);

                String name = "";
                int rare = 1;

                final int radius = 180;
                final int margin = 0;
                final Transformation transformation = new RoundedCornersTransformation(radius, margin);

                switch (artifactChoosedRareList.get(x)){
                    case 1 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                    case 2 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_2s);break;
                    case 3 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_3s);break;
                    case 4 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_4s);break;
                    case 5 : item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_5s);break;
                    default:  item_img.setBackgroundResource(R.drawable.item_char_list_bg_circ_1s);break;
                }

                int tmp_artifact_type_id = 1;
                if(artifactChoosedTypeList.get(x).equals("ARTIFACT_FLOWER")){
                    tmp_artifact_type_id = 4;
                }else if(artifactChoosedTypeList.get(x).equals("ARTIFACT_PLUME")){
                    tmp_artifact_type_id = 2;
                }else if(artifactChoosedTypeList.get(x).equals("ARTIFACT_SAND")){
                    tmp_artifact_type_id = 5;
                }else if(artifactChoosedTypeList.get(x).equals("ARTIFACT_GOBLET")){
                    tmp_artifact_type_id = 1;
                }else if(artifactChoosedTypeList.get(x).equals("ARTIFACT_CIRCLET")){
                    tmp_artifact_type_id = 3;
                }

                Picasso.get()
                        .load (item_rss.getArtifactByName(item_rss.getArtifactNameByFileName(artifactChoosedNameList.get(x)))[tmp_artifact_type_id])
                        .transform(transformation)
                        .fit()
                        .error (R.drawable.paimon_full)
                        .into (item_img);

                if(!artifactChoosedFollowList.get(x).equals("N/A")){
                    item_user_img.setVisibility(View.VISIBLE);
                    Picasso.get()
                            .load (item_rss.getCharByName(artifactChoosedFollowList.get(x),context)[3])
                            .transform(transformation)
                            .fit()
                            .error (R.drawable.paimon_full)
                            .into (item_user_img);
                }

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
        public TextView db_name, db_subname, db_info, db_date;
        public ImageView db_edit, db_char, db_weapon, db_art, db_delete, db_refresh;
        public LinearLayout db_char_ll, db_weapon_ll, db_art_ll;

        public String set_name = "N/A";

        public boolean isAccount = false;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            if(context instanceof BuffDatabaseUI){
                EnkaDataCollect enkaDataCollect = new EnkaDataCollect();
                enkaDataCollect.init(context);

                db_card = itemView.findViewById(R.id.db_card);
                db_name = itemView.findViewById(R.id.db_name);
                db_subname = itemView.findViewById(R.id.db_subname);
                db_delete = itemView.findViewById(R.id.db_delete);
                db_info = itemView.findViewById(R.id.db_info);
                db_date = itemView.findViewById(R.id.db_date);
                db_edit = itemView.findViewById(R.id.db_edit);
                db_refresh = itemView.findViewById(R.id.db_refresh);
                db_char = itemView.findViewById(R.id.db_char);
                db_weapon = itemView.findViewById(R.id.db_weapon);
                db_art = itemView.findViewById(R.id.db_art);
                db_char_ll = itemView.findViewById(R.id.db_char_ll);
                db_weapon_ll = itemView.findViewById(R.id.db_weapon_ll);
                db_art_ll = itemView.findViewById(R.id.db_art_ll);

                db_card.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (context instanceof BuffDatabaseUI){
                            if(isAccount){
                                (((BuffDatabaseUI) context)).transferDataToAccountUI(enkaDataCollect);
                            }else{
                                (((BuffDatabaseUI) context)).transferDataToMainUI(set_name);
                            }
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
                                BuffDBHelper dbHelper = new BuffDBHelper(context);
                                SQLiteDatabase db = dbHelper.getWritableDatabase();
                                String oldName = db_name.getText().toString();
                                db.execSQL("DROP TABLE \""+"Set_"+oldName+"\"");
                                db.execSQL("DELETE FROM Catelogy WHERE name = \""+"Set_"+oldName+"\"");
                                if (context instanceof BuffDatabaseUI){
                                    (((BuffDatabaseUI) context)).readIndexRecord();
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
                    }
                });

                db_refresh.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        enkaDataCollect.updateEnka(context);
                        CustomToast.toast(context,v,context.getString(R.string.pls_wait));
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
                                BuffDBHelper dbHelperS = new BuffDBHelper(context);
                                SQLiteDatabase db_check = dbHelperS.getWritableDatabase();

                                if(!newName.equals("") && !newName.equals(" ") && menu_edit_tv.getText() != null) {
                                    if(tableExists(db_check, "\"Set_"+newName+"\"")){
                                        CustomToast.toast(context,activity,context.getString(R.string.name_used));
                                    }else {
                                        if(newName.contains(" ")){
                                            newName = newName.replace(" ","_");
                                        }

                                        if(!oldName.toLowerCase().equals(newName.toLowerCase())){

                                            BuffDBHelper dbHelper = new BuffDBHelper(context);
                                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                                            db.execSQL("ALTER TABLE "+"\"Set_"+oldName+ "\" RENAME TO "+"\"Set_"+newName+"\"");

                                            db.execSQL("UPDATE Catelogy SET name = \""+"Set_"+newName+"\" WHERE name = \""+"Set_"+oldName+"\";");

                                            dialog.dismiss();
                                            if (context instanceof BuffDatabaseUI){
                                                (((BuffDatabaseUI) context)).readIndexRecord();
                                            }
                                        }else {
                                            CustomToast.toast(context,activity,context.getString(R.string.name_used));
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
        SQLiteDatabase db = new BuffDBHelper(context).getReadableDatabase();
        charChoosedNameList.clear();
        charChoosedRareList.clear();
        weaponChoosedNameList.clear();
        weaponChoosedRareList.clear();
        artifactChoosedNameList.clear();
        artifactChoosedRareList.clear();
        artifactChoosedTypeList.clear();
        weaponChoosedFollowList.clear();
        artifactChoosedFollowList.clear();

        Cursor cursor = db.query(
                name,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        while(cursor.moveToNext()) {
            switch (cursor.getString(cursor.getColumnIndexOrThrow("itemType")).split("_")[0]){
                case "CHAR" : {
                    charChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("itemName")));
                    charChoosedRareList.add(cursor.getInt(cursor.getColumnIndexOrThrow("itemRare")));
                    break;
                }
                case "WEAPON" : {
                    weaponChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("itemName")));
                    weaponChoosedRareList.add(cursor.getInt(cursor.getColumnIndexOrThrow("itemRare")));
                    weaponChoosedFollowList.add(cursor.getString(cursor.getColumnIndexOrThrow("itemFollowChar")));
                    break;
                }
                case "ARTIFACT" : {
                    artifactChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("itemName")));
                    artifactChoosedRareList.add(cursor.getInt(cursor.getColumnIndexOrThrow("itemRare")));
                    artifactChoosedTypeList.add(cursor.getString(cursor.getColumnIndexOrThrow("itemType")));
                    artifactChoosedFollowList.add(cursor.getString(cursor.getColumnIndexOrThrow("itemFollowChar")));
                    break;
                }
            }
        }
        cursor.close();

    }


    public void filterList(ArrayList<BuffDB> filteredList) {
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
                "SELECT COUNT(*) FROM Catelogy WHERE name = ?",
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