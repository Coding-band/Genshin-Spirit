package com.voc.genshin_helper.ui;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Shader;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.service.controls.actions.FloatAction;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.AlarmAdapter;
import com.voc.genshin_helper.data.CalculatorDB;
import com.voc.genshin_helper.data.CalculatorDBAdapter;
import com.voc.genshin_helper.data.Characters;
import com.voc.genshin_helper.data.CharactersAdapter;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.database.DataBaseContract;
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.IndexDBHelper;

import java.io.File;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/*
 * Refer from https://developer.android.com/training/data-storage/sqlite
 */

/*
 * Package com.voc.genshin_helper.ui.CalculatorDBActivity was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

public class CalculatorDBActivity extends AppCompatActivity {

    /** E.g. If I made a calculate group call "HuTao"
     *  Then there will have a database "HuTao.db" created
     *  App will read the GenshinSpiritDb.db to list database first
     *  When I choosed "HuTao", it will read "HuTao.db" and grab data
     *  These data will transfer to CalculatorUI.transfer(...)
     */

    RecyclerView mList;
    Context context;
    CalculatorDBAdapter mAdapter;
    public List<CalculatorDB> calculatorDBList = new ArrayList<>();

    private static SQLiteDatabase db;
    DataBaseHelper dbHelper = null;
    FloatingActionButton db_add_btn;

    Activity activity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cal_db);
        context = this;
        dbHelper = new DataBaseHelper(this);
        activity = this;

        BackgroundReload.BackgroundReload(context,activity);

        mList = findViewById(R.id.main_list);
        mAdapter = new CalculatorDBAdapter(this, calculatorDBList,activity);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        mList.setLayoutManager(mLayoutManager);
        mList.setAdapter(mAdapter);
        mList.removeAllViewsInLayout();

        //UI
        SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #
        boolean isColorGradient = sharedPreferences.getBoolean("theme_color_gradient",false); // Must include #
        String start_color = sharedPreferences.getString("start_color","#AEFEFF"); // Must include #
        String end_color = sharedPreferences.getString("end_color","#35858B"); // Must include #
        readIndexRecord();

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

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        if(color_hex.toUpperCase().equals("#FFFFFFFF")&&isColorGradient == false){
            window.setStatusBarColor(Color.parseColor("#000000"));
        }
        else if(isColorGradient){
            window.setStatusBarColor(Color.parseColor(end_color));
        }
        else{
            window.setStatusBarColor(Color.parseColor(color_hex));
        }

        db_add_btn = findViewById(R.id.db_add_btn);
        if(isColorGradient){
            db_add_btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(start_color)));
        }else{
            db_add_btn.setBackgroundTintList(ColorStateList.valueOf(Color.parseColor(color_hex)));
        }
        db_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_db_add, null);

                EditText db_name_et = view.findViewById(R.id.db_name_et);
                Button db_cancel = view.findViewById(R.id.db_cancel);
                Button db_ok = view.findViewById(R.id.db_ok);

                db_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                db_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String newName = db_name_et.getText().toString();
                        if(!newName.equals("") && !newName.equals(" ") && db_name_et.getText() != null) {
                            if(newName.startsWith("1")||newName.startsWith("2")||newName.startsWith("3")||newName.startsWith("4")||newName.startsWith("5")||newName.startsWith("6")||newName.startsWith("7")||newName.startsWith("8")||newName.startsWith("9")||newName.startsWith("10")){
                                Toast.makeText(context, "Number is not allow to use a first character.", Toast.LENGTH_SHORT).show();
                            }else {
                                if(newName.contains(" ")){
                                    newName = newName.replace(" ","_");
                                }
                                insertNewIndexRecord(newName, 0, 0, 0, String.valueOf(System.currentTimeMillis()));
                                dialog.dismiss();
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

    public void readIndexRecord (){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                DataBaseContract.DataBase.COLUMN_NAME_NAME,
                DataBaseContract.DataBase.COLUMN_NAME_CHAR_CNT,
                DataBaseContract.DataBase.COLUMN_NAME_WEAPON_CNT,
                DataBaseContract.DataBase.COLUMN_NAME_ARTIFACT_CNT,
                DataBaseContract.DataBase.COLUMN_NAME_CREATE_UNIX
        };

        // Filter results WHERE "name" = 'My Title'
        String selection = DataBaseContract.DataBase.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor
        String sortOrder =
                DataBaseContract.DataBase.COLUMN_NAME_NAME + " DESC";

        Cursor cursor = db.query(
                DataBaseContract.DataBase.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        calculatorDBList.clear();
        while(cursor.moveToNext()) {
            CalculatorDB calculatorDB = new CalculatorDB();
            calculatorDB.setId(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataBase._ID)));
            calculatorDB.setName(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBase.COLUMN_NAME_NAME)));
            calculatorDB.setChar_cnt(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataBase.COLUMN_NAME_CHAR_CNT)));
            calculatorDB.setWeapon_cnt(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataBase.COLUMN_NAME_WEAPON_CNT)));
            calculatorDB.setArtifact_cnt(cursor.getInt(cursor.getColumnIndexOrThrow(DataBaseContract.DataBase.COLUMN_NAME_ARTIFACT_CNT)));
            calculatorDB.setCreate_unix(cursor.getString(cursor.getColumnIndexOrThrow(DataBaseContract.DataBase.COLUMN_NAME_CREATE_UNIX)));
            calculatorDBList.add(calculatorDB);
        }
        cursor.close();
        mAdapter.filterList(calculatorDBList);
    }

    private void insertNewIndexRecord (String name ,int char_cnt,int weapon_cnt,int artifact_cnt, String create_unix){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(DataBaseContract.DataBase.COLUMN_NAME_ID, getIndexDBLength());
        values.put(DataBaseContract.DataBase.COLUMN_NAME_NAME, name);
        values.put(DataBaseContract.DataBase.COLUMN_NAME_CHAR_CNT, char_cnt);
        values.put(DataBaseContract.DataBase.COLUMN_NAME_WEAPON_CNT, weapon_cnt);
        values.put(DataBaseContract.DataBase.COLUMN_NAME_ARTIFACT_CNT, artifact_cnt);
        values.put(DataBaseContract.DataBase.COLUMN_NAME_CREATE_UNIX, create_unix);

        db.execSQL("CREATE TABLE IF NOT EXISTS " + name + "_char" + " ( " +
                "charId" + " INTEGER primary key AUTOINCREMENT,"+ // Add in 20211127
                "charName" + " TEXT," +

                "charBeforeLvl" + " INTEGER," +
                "charAfterLvl" + " INTEGER," +

                "charBeforeBreakLvl" + " INTEGER," +
                "charAfterBreakLvl" + " INTEGER," +

                "charBeforeBreakUpLvl" + " BOOLEAN," +
                "charAfterBreakUpLvl" + " BOOLEAN," +

                "charBeforeSkill1Lvl" + " INTEGER," +
                "charAfterSkill1Lvl" + " INTEGER," +

                "charBeforeSkill2Lvl" + " INTEGER," +
                "charAfterSkill2Lvl" + " INTEGER," +

                "charBeforeSkill3Lvl" + " INTEGER," +
                "charAfterSkill3Lvl" + " INTEGER," +

                "charIsCal" + " BOOLEAN" + ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + name + "_weapon" + " ( " +
                "weaponId" + " INTEGER primary key AUTOINCREMENT,"+ // Add in 20211127
                "weaponName" + " TEXT," +

                "weaponBeforeLvl" + " INTEGER," +
                "weaponAfterLvl" + " INTEGER," +

                "weaponBeforeBreakLvl" + " INTEGER," +
                "weaponAfterBreakLvl" + " INTEGER," +

                "weaponBeforeBreakUpLvl" + " BOOLEAN," +
                "weaponAfterBreakUpLvl" + " BOOLEAN," +

                "weaponFollow" + " TEXT," +
                "weaponRare" + " INTEGER," +
                "weaponIsCal" + " BOOLEAN" + ");");

        db.execSQL("CREATE TABLE IF NOT EXISTS " + name + "_artifact" + " ( " +
                "artifactId" + " INTEGER primary key AUTOINCREMENT,"+ // Add in 20211127
                "artifactName" + " TEXT," +
                "artifactType" + " TEXT," +
                "artifactBeforeLvl" + " INTEGER," +
                "artifactAfterLvl" + " INTEGER," +
                "artifactFollow" + " TEXT," +
                "artifactRare" + " INTEGER," +
                "artifactIsCal" + " BOOLEAN" + ");");

        // Insert the new row, returning the primary key value of the new row
        db.insert(DataBaseContract.DataBase.TABLE_NAME, null, values);
        readIndexRecord();
    }

    public void transferDataToUI (String name){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // INIT
        ArrayList<String> charChoosedNameList = new ArrayList<String>();
        ArrayList<Integer> charChoosedBeforeLvlList= new ArrayList<Integer>();
        ArrayList<Integer> charChoosedAfterLvlList= new ArrayList<Integer>();
        ArrayList<Integer> charChoosedBeforeBreakLvlList= new ArrayList<Integer>();
        ArrayList<Boolean> charChoosedBeforeBreakUPLvlList= new ArrayList<Boolean>();
        ArrayList<Integer> charChoosedAfterBreakLvlList= new ArrayList<Integer>();
        ArrayList<Boolean> charChoosedAfterBreakUPLvlList= new ArrayList<Boolean>();
        ArrayList<Integer> charChoosedBeforeSkill1LvlList= new ArrayList<Integer>();
        ArrayList<Integer> charChoosedAfterSkill1LvlList= new ArrayList<Integer>();
        ArrayList<Integer> charChoosedBeforeSkill2LvlList= new ArrayList<Integer>();
        ArrayList<Integer> charChoosedAfterSkill2LvlList= new ArrayList<Integer>();
        ArrayList<Integer> charChoosedBeforeSkill3LvlList= new ArrayList<Integer>();
        ArrayList<Integer> charChoosedAfterSkill3LvlList= new ArrayList<Integer>();
        ArrayList<Boolean> charChoosedIsCal= new ArrayList<Boolean>();
        ArrayList<Integer> charChoosedWeaponIdList = new ArrayList<Integer>();
        ArrayList<Integer> charChoosedFlowerIdList = new ArrayList<Integer>();
        ArrayList<Integer> charChoosedPlumeIdList = new ArrayList<Integer>();
        ArrayList<Integer> charChoosedSandIdList = new ArrayList<Integer>();
        ArrayList<Integer> charChoosedGobletIdList = new ArrayList<Integer>();
        ArrayList<Integer> charChoosedCircletIdList = new ArrayList<Integer>();

        ArrayList<String> weaponChoosedNameList = new ArrayList<>();
        ArrayList<Integer> weaponChoosedIdList = new ArrayList<>();
        ArrayList<Integer> weaponChoosedBeforeLvlList = new ArrayList<>();
        ArrayList<Integer> weaponChoosedAfterLvlList = new ArrayList<>();
        ArrayList<Integer> weaponChoosedBeforeBreakLvlList = new ArrayList<>();
        ArrayList<Boolean> weaponChoosedBeforeBreakUPLvlList = new ArrayList<>();
        ArrayList<Integer> weaponChoosedAfterBreakLvlList = new ArrayList<>();
        ArrayList<Boolean> weaponChoosedAfterBreakUPLvlList = new ArrayList<>();
        ArrayList<String> weaponChoosedFollowList = new ArrayList<>();
        ArrayList<Integer> weaponChoosedRare = new ArrayList<>();
        ArrayList<Boolean> weaponChoosedIsCal = new ArrayList<>();

        ArrayList<String> artifactChoosedNameList = new ArrayList<>();
        ArrayList<Integer> artifactChoosedIdList = new ArrayList<>();
        ArrayList<Integer> artifactChoosedBeforeLvlList = new ArrayList<>();
        ArrayList<Integer> artifactChoosedAfterLvlList = new ArrayList<>();
        ArrayList<String> artifactChoosedFollowList = new ArrayList<>();
        ArrayList<Boolean> artifactChoosedIsCal = new ArrayList<>();
        ArrayList<Integer> artifactChoosedRare = new ArrayList<>();
        ArrayList<String> artifactChoosedType = new ArrayList<>();

        /**
         * READ DATA -> Char
         */
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
            charChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("charName")));

            charChoosedBeforeLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeLvl")));
            charChoosedAfterLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterLvl")));

            charChoosedBeforeBreakLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeBreakLvl")));
            charChoosedAfterBreakLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterBreakLvl")));

            charChoosedBeforeBreakUPLvlList.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeBreakUpLvl"))));
            charChoosedAfterBreakUPLvlList.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterBreakUpLvl"))));

            charChoosedBeforeSkill1LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeSkill1Lvl")));
            charChoosedAfterSkill1LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterSkill1Lvl")));

            charChoosedBeforeSkill2LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeSkill2Lvl")));
            charChoosedAfterSkill2LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterSkill2Lvl")));

            charChoosedBeforeSkill3LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charBeforeSkill3Lvl")));
            charChoosedAfterSkill3LvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charAfterSkill3Lvl")));

            charChoosedIsCal.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("charIsCal"))));

            //charChoosedWeaponIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charChoosedWeaponIdList")));
            //charChoosedFlowerIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charChoosedFlowerIdList")));
            //charChoosedPlumeIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charChoosedPlumeIdList")));
            //charChoosedSandIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charChoosedSandIdList")));
            //charChoosedGobletIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charChoosedGobletIdList")));
            //charChoosedCircletIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("charChoosedCircletIdList")));
        }
        cursor.close();

        /**
         * READ DATA -> Weapon
         */

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
            weaponChoosedIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponId")));
            weaponChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("weaponName")));

            weaponChoosedBeforeLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponBeforeLvl")));
            weaponChoosedAfterLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponAfterLvl")));

            weaponChoosedBeforeBreakLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponBeforeBreakLvl")));
            weaponChoosedAfterBreakLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponAfterBreakLvl")));

            weaponChoosedBeforeBreakUPLvlList.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("weaponBeforeBreakUpLvl"))));
            weaponChoosedAfterBreakUPLvlList.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("weaponAfterBreakUpLvl"))));

            weaponChoosedRare.add(cursor.getInt(cursor.getColumnIndexOrThrow("weaponRare")));
            weaponChoosedFollowList.add(cursor.getString(cursor.getColumnIndexOrThrow("weaponFollow")));
            weaponChoosedIsCal.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("weaponIsCal"))));
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
            artifactChoosedIdList.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactId")));
            artifactChoosedNameList.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactName")));

            artifactChoosedBeforeLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactBeforeLvl")));
            artifactChoosedAfterLvlList.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactAfterLvl")));

            artifactChoosedFollowList.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactFollow")));
            artifactChoosedRare.add(cursor.getInt(cursor.getColumnIndexOrThrow("artifactRare")));
            artifactChoosedIsCal.add(getBooleanByInt(cursor.getInt(cursor.getColumnIndexOrThrow("artifactIsCal"))));
            artifactChoosedType.add(cursor.getString(cursor.getColumnIndexOrThrow("artifactType")));
        }
        cursor.close();

        /**
         * TRANSFER DATA
         */
        Intent intent = new Intent(context,CalculatorUI.class);
        intent.putExtra("dataSheetName",name);

        intent.putExtra("charChoosedNameList",charChoosedNameList);
        intent.putExtra("charChoosedBeforeLvlList",charChoosedBeforeLvlList);
        intent.putExtra("charChoosedAfterLvlList",charChoosedAfterLvlList);
        intent.putExtra("charChoosedBeforeBreakLvlList",charChoosedBeforeBreakLvlList);
        intent.putExtra("charChoosedBeforeBreakUPLvlList",charChoosedBeforeBreakUPLvlList);
        intent.putExtra("charChoosedAfterBreakLvlList",charChoosedAfterBreakLvlList);
        intent.putExtra("charChoosedAfterBreakUPLvlList",charChoosedAfterBreakUPLvlList);
        intent.putExtra("charChoosedBeforeSkill1LvlList",charChoosedBeforeSkill1LvlList);
        intent.putExtra("charChoosedAfterSkill1LvlList",charChoosedAfterSkill1LvlList);
        intent.putExtra("charChoosedBeforeSkill2LvlList",charChoosedBeforeSkill2LvlList);
        intent.putExtra("charChoosedAfterSkill2LvlList",charChoosedAfterSkill2LvlList);
        intent.putExtra("charChoosedBeforeSkill3LvlList",charChoosedBeforeSkill3LvlList);
        intent.putExtra("charChoosedAfterSkill3LvlList",charChoosedAfterSkill3LvlList);
        intent.putExtra("charChoosedIsCal",charChoosedIsCal);
        //intent.putExtra("charChoosedWeaponIdList",charChoosedWeaponIdList);
        //intent.putExtra("charChoosedFlowerIdList",charChoosedFlowerIdList);
        //intent.putExtra("charChoosedPlumeIdList",charChoosedPlumeIdList);
        //intent.putExtra("charChoosedSandIdList",charChoosedSandIdList);
        //intent.putExtra("charChoosedGobletIdList",charChoosedGobletIdList);
        //intent.putExtra("charChoosedCircletIdList",charChoosedCircletIdList);

        intent.putExtra("weaponChoosedNameList",weaponChoosedNameList);
        intent.putExtra("weaponChoosedIdList",weaponChoosedIdList);
        intent.putExtra("weaponChoosedBeforeLvlList",weaponChoosedBeforeLvlList);
        intent.putExtra("weaponChoosedAfterLvlList",weaponChoosedAfterLvlList);
        intent.putExtra("weaponChoosedBeforeBreakLvlList",weaponChoosedBeforeBreakLvlList);
        intent.putExtra("weaponChoosedBeforeBreakUPLvlList",weaponChoosedBeforeBreakUPLvlList);
        intent.putExtra("weaponChoosedAfterBreakLvlList",weaponChoosedAfterBreakLvlList);
        intent.putExtra("weaponChoosedAfterBreakUPLvlList",weaponChoosedAfterBreakUPLvlList);
        intent.putExtra("weaponChoosedFollowList",weaponChoosedFollowList);
        intent.putExtra("weaponChoosedRare",weaponChoosedRare);
        intent.putExtra("weaponChoosedIsCal",weaponChoosedIsCal);


        intent.putExtra("artifactChoosedNameList",artifactChoosedNameList);
        intent.putExtra("artifactChoosedIdList",artifactChoosedIdList);
        intent.putExtra("artifactChoosedBeforeLvlList",artifactChoosedBeforeLvlList);
        intent.putExtra("artifactChoosedAfterLvlList",artifactChoosedAfterLvlList);
        intent.putExtra("artifactChoosedFollowList",artifactChoosedFollowList);
        intent.putExtra("artifactChoosedIsCal",artifactChoosedIsCal);
        intent.putExtra("artifactChoosedRare",artifactChoosedRare);
        intent.putExtra("artifactChoosedType",artifactChoosedType);

        startActivity(intent);
        finish();
    }


    private long getIndexDBLength(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        long count = DatabaseUtils.queryNumEntries(db, "IndexDB");
        return count;
    }

    private boolean getBooleanByInt (int x){
        if(x == 0){
            return false;
        }else if(x == 1){
            return true;
        }else{
            return false;
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (db != null){
            db.close();
        }
    }
}