package com.voc.genshin_helper.database;/*
 * Package com.voc.genshin_helper.database.DataBaseContract was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

import android.provider.BaseColumns;

/*
 * Package com.voc.genshin_helper.database.DataBaseContract was
 * Created by Voc-夜芷冰 , Programmer of Xectorda
 * Copyright © 2021 Xectorda 版權所有
 */

public final class DataBaseContract {
    // To prevent someone from accidentally instantiating the contract class,
    // make the constructor private.
    private DataBaseContract() {}

    /* Inner class that defines the table contents */
    public static class DataBase implements BaseColumns {
        public static final String TABLE_NAME = "IndexDB";
        public static final String COLUMN_NAME_ID = "_id";
        public static final String COLUMN_NAME_NAME = "name";
        public static final String COLUMN_NAME_CHAR_CNT = "char_cnt";
        public static final String COLUMN_NAME_WEAPON_CNT = "weapon_cnt";
        public static final String COLUMN_NAME_ARTIFACT_CNT = "artifact_cnt";
        public static final String COLUMN_NAME_CREATE_UNIX = "create_unix";

    }
}
