package com.voc.genshin_helper.util;/*
 * Package com.voc.genshin_helper.util.IndexDBHelper was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class IndexDBHelper extends SQLiteOpenHelper {

    private final static int _DBVersion = 1; //<-- 版本
    private final static String _DBName = "GenshinSpiritDb.db";  //<-- db name
    private final static String _TableName = "DataBaseIndex"; //<-- table name

    public IndexDBHelper(Context context) {
        super(context, _DBName, null, _DBVersion);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // TODO Auto-generated method stub
        final String SQL = "CREATE TABLE IF NOT EXISTS " + _TableName + " ( " +
                "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                "name" + " TEXT," +
                "char_cnt" + " INTEGER," +
                "weapon_cnt" + " INTEGER," +
                "artifact_cnt" + " INTEGER," +
                "create_unix" + " NUMERIC"+
                ");";
        db.execSQL(SQL);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        final String SQL = "DROP TABLE " + _TableName;
        db.execSQL(SQL);
    }
}
