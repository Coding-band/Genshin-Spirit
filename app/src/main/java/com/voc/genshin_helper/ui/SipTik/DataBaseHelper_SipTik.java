package com.voc.genshin_helper.ui.SipTik;/*
 * Package com.voc.genshin_helper.database.DataBaseHelper was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.voc.genshin_helper.database.DataBaseContract;

/*
 * Project Genshin Spirit (原神小幫手) was
 * Created & Develop by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2023 Xectorda 版權所有
 */

public class DataBaseHelper_SipTik extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;

    /**
     * VERSION　2 : Changed at 20211127 0653 +8
     */

    public static final String DATABASE_NAME = "GenshinSpirit_SipTik.db";
    public static final String TABLE_NAME = "SipTik";
    private static final String SQL_CREATE_ENTRIES = "CREATE TABLE IF NOT EXISTS " + TABLE_NAME + " ( " +
            "_id" + " INTEGER PRIMARY KEY AUTOINCREMENT," +
            "type"	+	" TEXT," +
            "name"	+	" TEXT," +
            "beforeLvl"	+	" INTEGER," +
            "afterLvl"	+	" INTEGER," +
            "beforeBreakLvl"	+	" INTEGER," +
            "afterBreakLvl"	+	" INTEGER," +
            "beforeBreakUpLvl"	+	" BOOLEAN," +
            "afterBreakUpLvl"	+	" BOOLEAN," +
            "beforeSkill1Lvl"	+	" INTEGER," +
            "afterSkill1Lvl"	+	" INTEGER," +
            "beforeSkill2Lvl"	+	" INTEGER," +
            "afterSkill2Lvl"	+	" INTEGER," +
            "beforeSkill3Lvl"	+	" INTEGER," +
            "afterSkill3Lvl"	+	" INTEGER," +
            "follow"	+	" TEXT," +
            "rare"	+	" INTEGER," +
            "isCal"	+	" BOOLEAN" +
            ");";



    private static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + TABLE_NAME;

    public DataBaseHelper_SipTik(Context context) {
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