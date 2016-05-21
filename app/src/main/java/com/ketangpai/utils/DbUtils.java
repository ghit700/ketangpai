package com.ketangpai.utils;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.ketangpai.db.SQLiteHelper;

/**
 * Created by nan on 2016/5/12.
 */
public class DBUtils {
    private SQLiteDatabase mReadableDatabase;
    private SQLiteDatabase mWritableDatabase;

    public DBUtils() {
        SQLiteHelper mSQLiteHelper = new SQLiteHelper(AppContextUtils.getInstance());
        mReadableDatabase = mSQLiteHelper.getReadableDatabase();
        mWritableDatabase = mSQLiteHelper.getWritableDatabase();
    }

    public void update(String sql, Object[] bindArgs) {
        mWritableDatabase.execSQL(sql, bindArgs);
    }

    public void insert(String sql, Object[] bindArgs) {
        Log.i("=====insert",bindArgs.length+"  "+sql);
        try {
            mWritableDatabase.execSQL(sql, bindArgs);
        } catch (Exception e) {
            Log.i("===update", e.getMessage());
        }
    }

    public void delete(String sql, Object[] bindArgs) {
        mWritableDatabase.execSQL(sql, bindArgs);
    }

    public Cursor select(String sql, String[] selectionArgs) {
        Cursor cursor = mReadableDatabase.rawQuery(sql, selectionArgs);
        return cursor;
    }


}
