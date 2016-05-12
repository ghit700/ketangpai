package com.ketangpai.db;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by nan on 2016/5/3.
 */
public class SQLiteHelper extends SQLiteOpenHelper {

    private static final int version = 1;
    private static final String name = "kechengpai.db";


    public SQLiteHelper(Context context) {
        super(context, name, null, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "create table if not exists  user (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "account text," +
                "password text," +
                "type INTEGER," +
                "name text," +
                "path text)";
        db.execSQL(sql);
        sql = "create table if not exists contacts(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT," +
                "c_id integer," +
                "c_name text," +
                "account text," +
                "name text," +
                "path text)";
        db.execSQL(sql);

        sql = "create table if not exists messageinfo(" +
                "m_id integer," +
                "time integer," +
                "content text," +
                "send_name text," +
                "send_account text," +
                "send_path text," +
                "receive_name text," +
                "receive_account text)";
        db.execSQL(sql);

        sql = "create table if not exists newestmessage(" +
                "m_id integer," +
                "time integer," +
                "content text," +
                "send_name text," +
                "send_account text," +
                "send_path text," +
                "receive_name text," +
                "receive_account text," +
                "receive_paths)";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
