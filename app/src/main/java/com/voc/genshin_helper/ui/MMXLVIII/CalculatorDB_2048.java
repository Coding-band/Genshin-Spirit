package com.voc.genshin_helper.ui.MMXLVIII;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.ColorStateList;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.voc.genshin_helper.R;
import com.voc.genshin_helper.data.CalculatorDB;
import com.voc.genshin_helper.data.CalculatorDBAdapter;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.database.DataBaseContract;
import com.voc.genshin_helper.database.DataBaseHelper;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.LangUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/*
 * Refer from https://developer.android.com/training/data-storage/sqlite
 */

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class CalculatorDB_2048 extends AppCompatActivity {

    RecyclerView mList;
    Context context;
    CalculatorDBAdapter mAdapter;
    public List<CalculatorDB> calculatorDBList = new ArrayList<>();

    private static SQLiteDatabase db;
    DataBaseHelper dbHelper = null;
    ImageView db_add, db_back ;

    Dialog dialog;
    SharedPreferences sharedPreferences;

    Activity activity;

    String[] rules = new String[]{
            "0","1","2","3","4","5","6","7","8","9",
            "&","*","@","\"","{","}","^",":",",","#","$","\"","!","/","<",">","-","%",".","+","?",";","'"," ","~","_","|","="};

    public final static int DIALOG_DISMISS = 16384;
    public final static int REFRESH_DB_LIST = 8192;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_cal_db_2048);
        context = this;
        dbHelper = new DataBaseHelper(this);
        activity = this;

        sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);

        BackgroundReload.BackgroundReload(context,activity);

        mList = findViewById(R.id.main_list);
        mAdapter = new CalculatorDBAdapter(this, calculatorDBList,activity,sharedPreferences);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        mList.setLayoutManager(mLayoutManager);
        mList.setAdapter(mAdapter);
        mList.removeAllViewsInLayout();


        Window dialogWindowX = activity.getWindow();
        dialogWindowX.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        // 2O48 DESIGN
        dialogWindowX.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        dialogWindowX.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        dialogWindowX.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        //UI
        SharedPreferences sharedPreferences = getSharedPreferences("user_info",MODE_PRIVATE);
        String color_hex = sharedPreferences.getString("theme_color_hex","#FF5A5A"); // Must include #
        boolean isColorGradient = sharedPreferences.getBoolean("theme_color_gradient",false); // Must include #
        String start_color = sharedPreferences.getString("start_color","#AEFEFF"); // Must include #
        String end_color = sharedPreferences.getString("end_color","#35858B"); // Must include #
        readIndexRecord();

        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(context.getColor(R.color.status_bar_2048));
        window.setNavigationBarColor(context.getColor(R.color.tab_bar_2048));

        db_add = findViewById(R.id.db_add);
        db_back = findViewById(R.id.db_back);

        db_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        db_add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Dialog dialog = new Dialog(context, R.style.NormalDialogStyle_N);
                View view = View.inflate(context, R.layout.menu_db_add_2048, null);

                EditText db_name_et = view.findViewById(R.id.header_search_et);
                FrameLayout db_cancel = view.findViewById(R.id.db_cancel);
                FrameLayout db_ok = view.findViewById(R.id.db_ok);
                ImageView reset = view.findViewById(R.id.header_search_reset);

                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        db_name_et.setText("");
                    }
                });

                db_cancel.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        dialog.dismiss();
                    }
                });

                db_ok.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        DataBaseHelper dbHelperS = new DataBaseHelper(context);
                        SQLiteDatabase db_check = dbHelperS.getWritableDatabase();
                        String newName = db_name_et.getText().toString();
                        if(!newName.equals("") && !newName.equals(" ") && db_name_et.getText() != null) {
                            if(Arrays.asList(rules).contains(newName.substring(0,1))){
                                CustomToast.toast(context,activity,context.getString(R.string.name_start_with_num_err));
                            }else if(tableExists(db_check, newName)){
                                CustomToast.toast(context,activity,context.getString(R.string.name_used));
                            }else{
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
                DataBaseContract.DataBase.COLUMN_NAME_CREATE_UNIX + " DESC";

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
        /**
         * TRANSFER DATA
         */

        dialogSetup();

        Intent intent = new Intent(context, Calculator2048.class);
        intent.putExtra("dataSheetName",name);

        startActivityForResult(intent,DIALOG_DISMISS);

    }

    private void dialogSetup() {
        dialog = new Dialog(context, R.style.NormalDialogStyle_N);
        View view = View.inflate(context, R.layout.fragment_wait_dialog, null);

        dialog.setContentView(view);
        dialog.setCanceledOnTouchOutside(true);
        //view.setMinimumHeight((int) (ScreenSizeUtils.getInstance(this).getScreenHeight()));
        Window dialogWindow = dialog.getWindow();
        WindowManager.LayoutParams lp = dialogWindow.getAttributes();
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        lp.height = WindowManager.LayoutParams.MATCH_PARENT;
        lp.gravity = Gravity.CENTER_VERTICAL;
        dialogWindow.setAttributes(lp);
        dialog.show();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);
        // check if the request code is same as what is passed  here it is 2
        if(requestCode==DIALOG_DISMISS)
        {
            if (dialog != null){
                dialog.dismiss();
            }
        }
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

    // https://stackoverflow.com/questions/1601151/how-do-i-check-in-sqlite-whether-a-table-exists?rq=1
    public boolean tableExists(SQLiteDatabase db, String tableName)
    {
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM IndexDB WHERE LOWER(name) = LOWER(?)",
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


    @Override
    protected void attachBaseContext(Context newBase) {
        SharedPreferences sharedPreferences = newBase.getSharedPreferences("user_info",MODE_PRIVATE);
        sharedPreferences.getInt("curr_lang_pos",2);
        super.attachBaseContext(LangUtils.getAttachBaseContext(newBase, sharedPreferences.getInt("curr_lang_pos",2)));
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        readIndexRecord();
    }
}