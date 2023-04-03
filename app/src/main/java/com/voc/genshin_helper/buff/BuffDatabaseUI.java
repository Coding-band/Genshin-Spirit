package com.voc.genshin_helper.buff;/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

import android.app.Activity;
import android.app.Dialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.database.DatabaseUtils;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.EditText;
import android.widget.FrameLayout;
import android.widget.ImageView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.voc.genshin_helper.R;
import com.voc.genshin_helper.buff.db.BuffDBAdapter;
import com.voc.genshin_helper.buff.db.BuffDB;
import com.voc.genshin_helper.buff.db.BuffDBContract;
import com.voc.genshin_helper.buff.db.BuffDBHelper;
import com.voc.genshin_helper.buff.obj.BuffObject;
import com.voc.genshin_helper.data.ScreenSizeUtils;
import com.voc.genshin_helper.ui.MMXLVIII.Calculator2048;
import com.voc.genshin_helper.util.BackgroundReload;
import com.voc.genshin_helper.util.CustomToast;
import com.voc.genshin_helper.util.LangUtils;

import java.util.ArrayList;

public class BuffDatabaseUI extends AppCompatActivity {

    Context context;
    Activity activity;
    SharedPreferences sharedPreferences;
    SharedPreferences.Editor editor;

    RecyclerView buff_list;
    BuffDBAdapter mAdapter;
    ArrayList<BuffObject> buffObjects = new ArrayList<>();
    ArrayList<BuffDB> buffDBList = new ArrayList<>();

    EnkaDataCollect enkaDataCollect;
    private static SQLiteDatabase db;
    BuffDBHelper dbHelper = null;
    ImageView db_add, db_back ;
    public final static int DIALOG_DISMISS = 16384;
    public final static int REFRESH_DB_LIST = 8192;

    Dialog dialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_buff_db_2048);

        context = this;
        activity = this;
        dbHelper = new BuffDBHelper(this);
        sharedPreferences = context.getSharedPreferences("user_info",MODE_PRIVATE);
        editor = sharedPreferences.edit();
        BackgroundReload.BackgroundReload(context,activity);

        /*
        initize DB page
         */
        buff_list = findViewById(R.id.buff_list);
        mAdapter = new BuffDBAdapter(this, buffDBList,activity,sharedPreferences,false);
        RecyclerView.LayoutManager mLayoutManager = new GridLayoutManager(context, 1);
        buff_list.setLayoutManager(mLayoutManager);
        buff_list.setAdapter(mAdapter);
        buff_list.removeAllViewsInLayout();

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

        addAccount();
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
                        BuffDBHelper dbHelperS = new BuffDBHelper(context);
                        SQLiteDatabase db_check = dbHelperS.getWritableDatabase();
                        String newName = db_name_et.getText().toString();
                        if(!newName.equals("") && !newName.equals(" ") && db_name_et.getText() != null) {
                            /*
                            Since new version's database has automatically add "Set_" before each dataSheet, therefore it's not longer to limit by a naming rule.

                            if(Arrays.asList(rules).contains(newName.substring(0,1))){
                                CustomToast.toast(context,activity,context.getString(R.string.name_start_with_num_err));
                            }else
                             */
                            if(tableExists(db_check, "Set_"+newName)){
                                CustomToast.toast(context,activity,context.getString(R.string.name_used));
                            }else{
                                if(newName.contains(" ")){
                                    newName = newName.replace(" ","_");
                                }
                                insertNewIndexRecord(newName, 0, 0, 0, String.valueOf(System.currentTimeMillis()), String.valueOf(System.currentTimeMillis()));
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

    public void addAccount(){
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        // Filter results WHERE "name" = 'My Title'
        String selection = BuffDBContract.DataBase.COLUMN_NAME_NAME + " like ?";
        String[] selectionArgs = { "Account_"+sharedPreferences.getString("genshin_uid","-1")+"%" };
        Cursor cursor = db.query(
                BuffDBContract.DataBase.TABLE_NAME,   // The table to query
                null,             // The array of columns to return (pass null to get all)
                selection,   // The columns for the WHERE clause
                selectionArgs,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                null               // The sort order
        );

        if(cursor.getCount() ==  0){
            insertNewAccountRecord(sharedPreferences.getString("genshin_uid","-1")+"_"+sharedPreferences.getString("genshin_username","Unknown"),
                    0,
                    0,
                    0,
                    String.valueOf(System.currentTimeMillis()),
                    String.valueOf(System.currentTimeMillis())
            );
        }


    }
    public void readIndexRecord (){
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                BaseColumns._ID,
                BuffDBContract.DataBase.COLUMN_NAME_NAME,
                BuffDBContract.DataBase.COLUMN_NAME_CHAR_CNT,
                BuffDBContract.DataBase.COLUMN_NAME_WEAPON_CNT,
                BuffDBContract.DataBase.COLUMN_NAME_ARTIFACT_CNT,
                BuffDBContract.DataBase.COLUMN_NAME_CREATE_UNIX,
                BuffDBContract.DataBase.COLUMN_NAME_UPDATE_UNIX
        };

        // Filter results WHERE "name" = 'My Title'
        String selection = BuffDBContract.DataBase.COLUMN_NAME_NAME + " = ?";
        String[] selectionArgs = { "My Title" };

        // How you want the results sorted in the resulting Cursor

        String sortOrder =
                BuffDBContract.DataBase.COLUMN_NAME_CREATE_UNIX + " DESC";

        Cursor cursor = db.query(
                BuffDBContract.DataBase.TABLE_NAME,   // The table to query
                projection,             // The array of columns to return (pass null to get all)
                null,              // The columns for the WHERE clause
                null,          // The values for the WHERE clause
                null,                   // don't group the rows
                null,                   // don't filter by row groups
                sortOrder               // The sort order
        );

        buffDBList.clear();
        while(cursor.moveToNext()) {
            BuffDB buffDB = new BuffDB();
            buffDB.setId(cursor.getInt(cursor.getColumnIndexOrThrow(BuffDBContract.DataBase._ID)));
            buffDB.setName(cursor.getString(cursor.getColumnIndexOrThrow(BuffDBContract.DataBase.COLUMN_NAME_NAME)));
            buffDB.setChar_cnt(cursor.getInt(cursor.getColumnIndexOrThrow(BuffDBContract.DataBase.COLUMN_NAME_CHAR_CNT)));
            buffDB.setWeapon_cnt(cursor.getInt(cursor.getColumnIndexOrThrow(BuffDBContract.DataBase.COLUMN_NAME_WEAPON_CNT)));
            buffDB.setArtifact_cnt(cursor.getInt(cursor.getColumnIndexOrThrow(BuffDBContract.DataBase.COLUMN_NAME_ARTIFACT_CNT)));
            buffDB.setCreate_unix(cursor.getString(cursor.getColumnIndexOrThrow(BuffDBContract.DataBase.COLUMN_NAME_CREATE_UNIX)));
            buffDB.setUndate_unix(cursor.getString(cursor.getColumnIndexOrThrow(BuffDBContract.DataBase.COLUMN_NAME_UPDATE_UNIX)));
            if(cursor.getString(cursor.getColumnIndexOrThrow(BuffDBContract.DataBase.COLUMN_NAME_NAME)).startsWith("Account_")){
                if(cursor.getString(cursor.getColumnIndexOrThrow(BuffDBContract.DataBase.COLUMN_NAME_NAME)).startsWith("Account_"+sharedPreferences.getString("genshin_uid","-1"))){
                    buffDBList.add(0,buffDB);
                }
            }else{
                buffDBList.add(buffDB);
            }
        }
        cursor.close();
        mAdapter.filterList(buffDBList);
    }

    private void insertNewIndexRecord (String name ,int char_cnt,int weapon_cnt,int artifact_cnt, String create_unix, String update_unix){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(BuffDBContract.DataBase.COLUMN_NAME_ID, getIndexDBLength());
        values.put(BuffDBContract.DataBase.COLUMN_NAME_NAME, "Set_"+name);
        values.put(BuffDBContract.DataBase.COLUMN_NAME_CHAR_CNT, char_cnt);
        values.put(BuffDBContract.DataBase.COLUMN_NAME_WEAPON_CNT, weapon_cnt);
        values.put(BuffDBContract.DataBase.COLUMN_NAME_ARTIFACT_CNT, artifact_cnt);
        values.put(BuffDBContract.DataBase.COLUMN_NAME_CREATE_UNIX, create_unix);
        values.put(BuffDBContract.DataBase.COLUMN_NAME_UPDATE_UNIX, update_unix);

        db.execSQL("CREATE TABLE IF NOT EXISTS \"" + "Set_"+ name + "\" ( " +
                "itemId"+ " INTEGER," +
                "itemName"+ " TEXT," +
                //"itemNameCode"+ " INTEGER," +
                "itemType"+ " TEXT," +
                "itemLvl"+ " INTEGER," +
                "itemASCLvl"+ " INTEGER," +
                "itemRare"+ " INTEGER," +
                "itemAffixLvl"+ " INTEGER," +
                "itemConstellationLvl"+ " INTEGER," +
                "itemEXP"+ " INTEGER," +
                "itemFollowChar"+ " TEXT," +
                "itemFollowCharId"+ " INTEGER," +
                "itemMainStatusStr"+ " TEXT," +
                "itemMainStatusValue"+ " REAL," +
                "itemSubStatus1Str"+ " TEXT," +
                "itemSubStatus1Value"+ " REAL," +
                "itemSubStatus2Str"+ " TEXT," +
                "itemSubStatus2Value"+ " INTEGER," +
                "itemSubStatus3Str"+ " TEXT," +
                "itemSubStatus3Value"+ " REAL," +
                "itemSubStatus4Str"+ " TEXT," +
                "itemSubStatus4Value"+ " REAL," +
                "charTalent1Lvl"+ " INTEGER," +
                "charTalent2Lvl"+ " INTEGER," +
                "charTalent3Lvl"+ " INTEGER," +
                "charBaseHP"+ " REAL," +
                "charHP"+ " REAL," +
                "charHPP"+ " REAL," +
                "charBaseATK"+ " REAL," +
                "charATK"+ " REAL," +
                "charATKP"+ " REAL," +
                "charBaseDEF"+ " REAL," +
                "charDEF"+ " REAL," +
                "charDEFP"+ " REAL," +
                "charSpdP"+ " REAL," +
                "charCritRate"+ " REAL," +
                "charCritDMG"+ " REAL," +
                "charEnRech"+ " REAL," +
                "charHealP"+ " REAL," +
                "charGotHealP"+ " REAL," +
                "charEleMas"+ " REAL," +
                "charPhyRes"+ " REAL," +
                "charPhyDMGP"+ " REAL," +
                "charPyroDMGP"+ " REAL," +
                "charElectroDMGP"+ " REAL," +
                "charHydroDMGP"+ " REAL," +
                "charDendroDMGP"+ " REAL," +
                "charAnemoDMGP"+ " REAL," +
                "charGeoDMGP"+ " REAL," +
                "charCryoDMGP"+ " REAL," +
                "charPyroResP"+ " REAL," +
                "charElectroResP"+ " REAL," +
                "charHydroResP"+ " REAL," +
                "charDendroResP"+ " REAL," +
                "charAnemoResP"+ " REAL," +
                "charGeoResP"+ " REAL," +
                "charCryoResP"+ " REAL," +
                "charMaxHP"+ " REAL," +
                "charMaxATK"+ " REAL," +
                "charMaxDEF"+ " REAL"+");");


        // Insert the new row, returning the primary key value of the new row
        db.insert(BuffDBContract.DataBase.TABLE_NAME, null, values);
        readIndexRecord();
    }
    private void insertNewAccountRecord (String name ,int char_cnt,int weapon_cnt,int artifact_cnt, String create_unix, String update_unix){
        // Gets the data repository in write mode
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(BuffDBContract.DataBase.COLUMN_NAME_ID, getIndexDBLength());
        values.put(BuffDBContract.DataBase.COLUMN_NAME_NAME, "Account_"+name);
        values.put(BuffDBContract.DataBase.COLUMN_NAME_CHAR_CNT, char_cnt);
        values.put(BuffDBContract.DataBase.COLUMN_NAME_WEAPON_CNT, weapon_cnt);
        values.put(BuffDBContract.DataBase.COLUMN_NAME_ARTIFACT_CNT, artifact_cnt);
        values.put(BuffDBContract.DataBase.COLUMN_NAME_CREATE_UNIX, create_unix);
        values.put(BuffDBContract.DataBase.COLUMN_NAME_UPDATE_UNIX, update_unix);

        db.execSQL("CREATE TABLE IF NOT EXISTS \""+"Account_"+ name + "\" ( " +
                "itemId"+ " INTEGER," +
                "itemName"+ " TEXT," +
                //"itemNameCode"+ " INTEGER," +
                "itemType"+ " TEXT," +
                "itemLvl"+ " INTEGER," +
                "itemASCLvl"+ " INTEGER," +
                "itemRare"+ " INTEGER," +
                "itemAffixLvl"+ " INTEGER," +
                "itemConstellationLvl"+ " INTEGER," +
                "itemEXP"+ " INTEGER," +
                "itemFollowChar"+ " TEXT," +
                "itemFollowCharId"+ " INTEGER," +
                "itemMainStatusStr"+ " TEXT," +
                "itemMainStatusValue"+ " REAL," +
                "itemSubStatus1Str"+ " TEXT," +
                "itemSubStatus1Value"+ " REAL," +
                "itemSubStatus2Str"+ " TEXT," +
                "itemSubStatus2Value"+ " INTEGER," +
                "itemSubStatus3Str"+ " TEXT," +
                "itemSubStatus3Value"+ " REAL," +
                "itemSubStatus4Str"+ " TEXT," +
                "itemSubStatus4Value"+ " REAL," +
                "charTalent1Lvl"+ " INTEGER," +
                "charTalent2Lvl"+ " INTEGER," +
                "charTalent3Lvl"+ " INTEGER," +
                "charBaseHP"+ " REAL," +
                "charHP"+ " REAL," +
                "charHPP"+ " REAL," +
                "charBaseATK"+ " REAL," +
                "charATK"+ " REAL," +
                "charATKP"+ " REAL," +
                "charBaseDEF"+ " REAL," +
                "charDEF"+ " REAL," +
                "charDEFP"+ " REAL," +
                "charSpdP"+ " REAL," +
                "charCritRate"+ " REAL," +
                "charCritDMG"+ " REAL," +
                "charEnRech"+ " REAL," +
                "charHealP"+ " REAL," +
                "charGotHealP"+ " REAL," +
                "charEleMas"+ " REAL," +
                "charPhyRes"+ " REAL," +
                "charPhyDMGP"+ " REAL," +
                "charPyroDMGP"+ " REAL," +
                "charElectroDMGP"+ " REAL," +
                "charHydroDMGP"+ " REAL," +
                "charDendroDMGP"+ " REAL," +
                "charAnemoDMGP"+ " REAL," +
                "charGeoDMGP"+ " REAL," +
                "charCryoDMGP"+ " REAL," +
                "charPyroResP"+ " REAL," +
                "charElectroResP"+ " REAL," +
                "charHydroResP"+ " REAL," +
                "charDendroResP"+ " REAL," +
                "charAnemoResP"+ " REAL," +
                "charGeoResP"+ " REAL," +
                "charCryoResP"+ " REAL," +
                "charMaxHP"+ " REAL," +
                "charMaxATK"+ " REAL," +
                "charMaxDEF"+ " REAL"+");");


        // Insert the new row, returning the primary key value of the new row
        db.insert(BuffDBContract.DataBase.TABLE_NAME, null, values);
        readIndexRecord();
    }

    public void transferDataToMainUI (String name){
        /**
         * TRANSFER DATA
         */

        dialogSetup();

        Intent intent = new Intent(context, BuffMainUI.class);
        intent.putExtra("SetName",name);

        startActivityForResult(intent,DIALOG_DISMISS);

    }

    public void transferDataToAccountUI (EnkaDataCollect enkaDataCollect){
        /**
         * TRANSFER DATA
         */

        if(!sharedPreferences.getString("genshin_uid","-1").equals("-1")){
            dialogSetup();
            Intent intent = new Intent(context, BuffAccountUI.class);
            intent.putExtra("buffObjects",enkaDataCollect.getPreLoadBuffObjectArray());

            startActivityForResult(intent,DIALOG_DISMISS);
        }else{
            CustomToast.toast(context,activity,context.getString(R.string.login_dailymemo));
        }

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
        long count = DatabaseUtils.queryNumEntries(db, BuffDBContract.DataBase.TABLE_NAME);
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
        if (db != null){
            db.close();
        }

        if (dialog != null){
            dialog.dismiss();
        }
        super.onDestroy();
    }

    // https://stackoverflow.com/questions/1601151/how-do-i-check-in-sqlite-whether-a-table-exists?rq=1
    public boolean tableExists(SQLiteDatabase db, String tableName)
    {
        if (tableName == null || db == null || !db.isOpen())
        {
            return false;
        }
        Cursor cursor = db.rawQuery(
                "SELECT COUNT(*) FROM Catelogy WHERE LOWER(name) = LOWER(?)",
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
        readIndexRecord();
        super.onRestart();
    }
}