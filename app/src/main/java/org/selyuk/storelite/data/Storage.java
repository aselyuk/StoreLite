package org.selyuk.storelite.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class Storage extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "storage.db";
    private static final int SCHEMA = 1;

    static final String TABLE_PRODUCTS = "Products";

    public Storage(Context context) {
        super(context, DATABASE_NAME, null, SCHEMA);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //
    }
}
