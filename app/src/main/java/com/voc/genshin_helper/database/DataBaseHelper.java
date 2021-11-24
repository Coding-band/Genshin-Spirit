package com.voc.genshin_helper.database;/*
 * Package com.voc.genshin_helper.database.DataBaseHelper was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/*
 * Package com.voc.genshin_helper.database.DataBaseHelper was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

public class DataBaseHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "GenshinSpirit.db";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + DataBaseContract.DataBase.TABLE_NAME + " ( " +
            "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "name" + " TEXT," +
            "char_cnt" + " INTEGER," +
            "weapon_cnt" + " INTEGER," +
            "artifact_cnt" + " INTEGER," +
            "create_unix" + " TEXT"+
            ");";

    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + DataBaseContract.DataBase.TABLE_NAME;

    public DataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    public void onCreate(SQLiteDatabase db) {
        db.disableWriteAheadLogging();
        db.execSQL(SQL_CREATE_ENTRIES);
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        db.execSQL(SQL_DELETE_ENTRIES);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }
}