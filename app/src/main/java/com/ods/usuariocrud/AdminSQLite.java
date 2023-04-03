package com.ods.usuariocrud;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class AdminSQLite extends SQLiteOpenHelper {
    public AdminSQLite(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table usuario(documento Integer primary key, nombre text, apellido text, email text, contrasena text )");
        db.execSQL("insert into usuario(email,contrasena) values('admin','admin')");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int i, int i1) {
        db.execSQL("drop table if exists usuario");
        db.execSQL("create table usuario(documento Integer primary key, nombre text, apellido text, email text, contrasena text )");
        db.execSQL("insert into usuario(email,contrasena) values('admin','admin')");
    }
}
