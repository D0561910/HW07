package com.example.charlessin.sqlite;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

public class NotepadEditor extends AppCompatActivity {

    public static final String KEY_TITLE = "KEY_TITLE";
    public static final String KEY_CONTEXT = "KEY_CONTEXT";

    EditText et_input;
    EditText et_ttl;
    String ttl = "unknow";
    String cont = "unknow";
    SQLiteDatabase db;
    DBOpenHelper openhelper;
    String DATABASE_TABLE = "demotable";
    String DATABASE_CREATETABLE = "create table " + DATABASE_TABLE + "(number, title, body)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notepad_editor);

        et_input = findViewById(R.id.et_Context);
        et_ttl = findViewById(R.id.et_title);
        ttl = et_ttl.getText().toString();
        cont = et_input.getText().toString();

        openhelper = new DBOpenHelper(this);
        db = openhelper.getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put("number",1);
        cv.put("title", "title");
        cv.put("body", "body");
        db.insert(DATABASE_TABLE,null,cv);

        Cursor c = db.rawQuery("select * from " + DATABASE_TABLE,null);
        String[] names = c.getColumnNames();

        for(int i=0;i<names.length;i++){
            Log.d("Charles","ColumnNames (" + c.getColumnIndex(names[i]) + "): " + names[i]);
        }
        c.moveToFirst();
        db.execSQL("insert into " + DATABASE_TABLE + " values(3," + "\'" + ttl + "\'" + "," + "\'" + cont + "\'" + ");");

        String[] titles = new String[c.getCount()];
        for(int i=0;i<c.getCount();i++) {
            Log.d("Charles","Title" + i + ": " + c.getString(c.getColumnIndex(names[1])));
            titles[i] = c.getString(c.getColumnIndex(names[1]));
            c.moveToNext();
        }
        db.delete(DATABASE_TABLE,"title=" + "'title'",null);
        db.delete(DATABASE_TABLE,"title=" + "' '",null);
        db.delete(DATABASE_TABLE,"title=" + "'love'",null);
        db.delete(DATABASE_TABLE,"title=" + "'null'",null);


    }

    public void onPause(){
        super.onPause();
    }

    class DBOpenHelper extends SQLiteOpenHelper {
        public DBOpenHelper(Context context){
            super(context, "demo.db",null,1);
        }

        public void onCreate (SQLiteDatabase db){
            db.execSQL(DATABASE_CREATETABLE);
        }
        public void onUpgrade(SQLiteDatabase db, int oldV, int newV){

        }
    }
}


//
//openhelper = new DBOpenHelper(this);
//        db = openhelper.getWritableDatabase();
//        ContentValues cv = new ContentValues();
//        cv.put("number",1);
//        cv.put("title", tt);
//        cv.put("body", bod);
//        db.insert(DATABASE_TABLE,null,cv);
//        db.execSQL("insert into " + DATABASE_TABLE + " values(1," + "'zzz'" + "," + "'xxx!'"+");");
//        Cursor c = db.rawQuery("select * from " + DATABASE_TABLE,null);
//        String[] names = c.getColumnNames();
//
//        for(int i=0;i<names.length;i++){
//        Log.d("Charles","ColumnNames (" + c.getColumnIndex(names[i]) + "): " + names[i]);
//        }
//        c.moveToFirst();
//        for(int i=0;i<c.getCount();i++){
//        Log.d("Charles","Title" + i + ": " + c.getString(c.getColumnIndex(names[1])));
//        }
//        c.moveToNext();
//        for(int i=0;i<c.getCount();i++){
//        Log.d("Charles","body" + i + ": " + c.getString(c.getColumnIndex(names[2])));
//        }
//        c.moveToNext();
//
//        cv = new ContentValues();
//        cv.put("title", "def");
//
//        db.update(DATABASE_TABLE, cv,"title=" + "'QQQ'",null);
//
//        c = db.rawQuery("select * from " + DATABASE_TABLE,null);
//
//        c.moveToFirst();
//        for(int i=0;i<c.getCount();i++){
//        Log.d("Charles","Title(Update1)" + i + ": " + c.getString(c.getColumnIndex(names[1])));
//        c.moveToNext();
//        }
//
//        c = db.rawQuery("select * from " + DATABASE_TABLE ,null);
//
//        db.execSQL("update " + DATABASE_TABLE + " set body=" + "'aaa'" + " where title=" + "'love'" + ";");
//
//        c.moveToFirst();
//        for(int i = 0;i<c.getCount();i++){
//        Log.d("Charles","Body(Update2)" + i + ": " + c.getString(c.getColumnIndex(names[2])));
//        c.moveToNext();
//        }
//        c.close();
//        db.delete(DATABASE_TABLE,"title=" + "'love'",null);
//        db.delete(DATABASE_TABLE,"title=" + "'111'",null);
//        db.delete(DATABASE_TABLE,"body=" + "'you'",null);
//        db.delete(DATABASE_TABLE,"body=" + "'222'",null);