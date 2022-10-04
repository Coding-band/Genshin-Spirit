package com.voc.genshin_helper.ui.SipTik;

import static android.content.Context.MODE_PRIVATE;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
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
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Transformation;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.CalculatorDB;
import com.voc.genshin_helper.data.ItemRss;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.ui.CalculatorDBActivity;
import com.voc.genshin_helper.ui.MMXLVIII.Artifact_Info_2048;
import com.voc.genshin_helper.ui.MMXLVIII.CalculatorDB_2048;
import com.voc.genshin_helper.ui.MMXLVIII.Characters_Info_2048;
import com.voc.genshin_helper.ui.MMXLVIII.Weapon_Info_2048;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.FileLoader;
import com.voc.genshin_helper.util.RoundedCornersTransformation;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
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


public class CalculatorItemAdapter extends RecyclerView.Adapter<CalculatorItemAdapter.ViewHolder> {

    private Context context;
    private Activity activity;
    private List<CalculatorItem> dbList;
    private AdapterView.OnItemClickListener mListener;
    private WebView webView ;


    String[] rules = new String[]{
            "0","1","2","3","4","5","6","7","8","9",
            "&","*","@","\"","{","}","^",":",",","#","$","\"","!","/","<",">","-","%",".","+","?",";","'"," ","~","_","|","="};

    public CalculatorItemAdapter(Context context, List<CalculatorItem> dbList, Activity activity) {
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

        if(context instanceof CalculatorDB_SipTik){
            v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_calculator_db_siptik, parent, false);
            evh = new ViewHolder(v, (OnItemClickListener) mListener);
        }

        return evh;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        CalculatorItem db = dbList.get(position);
        ItemRss itemRss = new ItemRss();

        holder.id = db.getId();
        holder.type = db.getType();
        holder.name = db.getName();
        holder.beforeLvl = db.getBeforeLvl();
        holder.afterLvl = db.getAfterLvl();
        holder.beforeBreakLvl = db.getBeforeBreakLvl();
        holder.afterBreakLvl = db.getAfterBreakLvl();
        holder.beforeBreakUpLvl = db.isBeforeBreakUpLvl();
        holder.afterBreakUpLvl = db.isAfterBreakUpLvl();
        holder.beforeSkill1Lvl = db.getBeforeSkill1Lvl();
        holder.afterSkill1Lvl = db.getAfterSkill1Lvl();
        holder.beforeSkill2Lvl = db.getBeforeSkill2Lvl();
        holder.afterSkill2Lvl = db.getAfterSkill2Lvl();
        holder.beforeSkill3Lvl = db.getBeforeSkill3Lvl();
        holder.afterSkill3Lvl = db.getAfterSkill3Lvl();
        holder.follow = db.getFollow();
        holder.rare = db.getRare();
        holder.isCal = db.isCal();

        CalculatorExtendSipTik ces = new CalculatorExtendSipTik();
        ces.setPreview(true);
        ces.setup(context,activity,holder.itemViewX);
        ces.cal_setup(holder.id,holder.type,holder.name,holder.beforeLvl,holder.afterLvl,holder.beforeBreakLvl,holder.afterBreakLvl,holder.beforeBreakUpLvl,holder.afterBreakUpLvl,holder.beforeSkill1Lvl,holder.afterSkill1Lvl,holder.beforeSkill2Lvl,holder.afterSkill2Lvl,holder.beforeSkill3Lvl,holder.afterSkill3Lvl,holder.follow,holder.rare,holder.isCal);



        if(context instanceof CalculatorDB_SipTik){
            ItemRss item_rss = new ItemRss();

            holder.db_id.setText(String.valueOf(db.getId()));
            holder.db_curr_tv.setText("Lv"+String.valueOf(db.getBeforeLvl()));
            holder.db_aim_tv.setText("Lv"+String.valueOf(db.getAfterLvl()));

            if (db.type.equals("CHAR")){
                holder.db_name.setText(item_rss.getCharByName(db.getName(),context)[1]);
                holder.db_talent_ll.setVisibility(View.VISIBLE);
                holder.db_talent_tv.setText(String.valueOf(db.getAfterSkill1Lvl())+"/"+String.valueOf(db.getAfterSkill2Lvl())+"/"+String.valueOf(db.getAfterSkill3Lvl()));
            }else{
                holder.db_name.setText(item_rss.getWeaponByName(db.getName(),context)[0]);
                holder.db_talent_ll.setVisibility(View.GONE);
            }


            //RoundedCornersTransformation roundedCornersTransformation = new RoundedCornersTransformation(360, 0, RoundedCornersTransformation.CornerType.ALL);
            Transformation roundedCornersTransformation = new RoundedCornersTransformation(64, 0);

            if (db.getType().equals("CHAR")){
                Picasso.get().load(FileLoader.loadIMG(item_rss.getCharByName(db.getName(),context)[3],context)).fit().transform(roundedCornersTransformation).into(holder.db_ico);
            }else {
                Picasso.get().load(FileLoader.loadIMG(item_rss.getWeaponByName(db.getName(),context)[1],context)).fit().transform(roundedCornersTransformation).into(holder.db_ico);
            }

            switch (db.getRare()){
                case 1 : holder.db_ico.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
                case 2 : holder.db_ico.setBackgroundResource(R.drawable.bg_rare2_char_siptik);break;
                case 3 : holder.db_ico.setBackgroundResource(R.drawable.bg_rare3_char_siptik);break;
                case 4 : holder.db_ico.setBackgroundResource(R.drawable.bg_rare4_char_siptik);break;
                case 5 : holder.db_ico.setBackgroundResource(R.drawable.bg_rare5_char_siptik);break;
                default:  holder.db_ico.setBackgroundResource(R.drawable.bg_rare1_char_siptik);break;
            }

        }
    }

    @Override
    public int getItemCount() {
        return dbList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public TextView db_name , db_id , db_curr_tv , db_aim_tv , db_talent_tv , db_press_detail;
        public ImageView db_ico, db_delete;
        public LinearLayout db_material_ll ;
        public ConstraintLayout db_talent_ll;
        public View itemViewX;

        int id ;
        String type ;
        String name ;
        int beforeLvl ;
        int afterLvl ;
        int beforeBreakLvl ;
        int afterBreakLvl ;
        boolean beforeBreakUpLvl ;
        boolean afterBreakUpLvl ;
        int beforeSkill1Lvl ;
        int afterSkill1Lvl ;
        int beforeSkill2Lvl ;
        int afterSkill2Lvl ;
        int beforeSkill3Lvl ;
        int afterSkill3Lvl ;
        String follow ;
        int rare ;
        boolean isCal ;

        public ViewHolder(View itemView, final OnItemClickListener listener) {
            super(itemView);

            if(context instanceof CalculatorDB_SipTik){
                db_id = itemView.findViewById(R.id.db_id);
                db_name = itemView.findViewById(R.id.db_name);
                db_curr_tv = itemView.findViewById(R.id.db_curr_tv);
                db_aim_tv = itemView.findViewById(R.id.db_aim_tv);
                db_talent_tv = itemView.findViewById(R.id.db_talent_tv);
                db_press_detail = itemView.findViewById(R.id.db_press_detail);
                db_ico = itemView.findViewById(R.id.db_ico);
                db_delete = itemView.findViewById(R.id.db_delete);
                db_material_ll = itemView.findViewById(R.id.db_material_ll);
                db_talent_ll = itemView.findViewById(R.id.talent_ll);
                itemViewX = itemView;

                db_press_detail.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        if (context instanceof CalculatorDB_SipTik){
                            (((CalculatorDB_SipTik) context)).displayDataToUI(id,type,name,beforeLvl,afterLvl,beforeBreakLvl,afterBreakLvl,beforeBreakUpLvl,afterBreakUpLvl,beforeSkill1Lvl,afterSkill1Lvl,beforeSkill2Lvl,afterSkill2Lvl,beforeSkill3Lvl,afterSkill3Lvl,follow,rare,isCal);
                        }
                    }
                });

                db_delete.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                            DataBaseHelper_SipTik dbHelper = new DataBaseHelper_SipTik(context);
                            SQLiteDatabase db = dbHelper.getWritableDatabase();
                            String oldName = db_name.getText().toString();
                            long oldID = Long.valueOf(db_id.getText().toString());
                            db.execSQL("DELETE FROM SipTik WHERE _id = "+oldID);
                            if (context instanceof CalculatorDB_SipTik){
                                (((CalculatorDB_SipTik) context)).readIndexRecord();
                            }
                        }
                });
            }

        }
    }

    public void filterList(List<CalculatorItem> filteredList) {
        dbList = filteredList;
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