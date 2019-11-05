package com.example.sqliteexample;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class SqliteExampleHelper extends SQLiteOpenHelper {
    private Context context;
    public SqliteExampleHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL("create table table_example (id integer primary key autoincrement,name varchar(20))");
        sqLiteDatabase.execSQL("create table other_table (id integer primary key autoincrement,name varchar(20))");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("drop table if exists table_example");
        sqLiteDatabase.execSQL("drop table if exists other_table");
        onCreate(sqLiteDatabase);
    }
}
