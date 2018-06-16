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
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import javax.xml.transform.dom.DOMLocator;

public class MainActivity extends AppCompatActivity {

    SQLiteDatabase db;
    DBOpenHelper openhelper;
    Button btn_Note;
    String tt = "love";
    String bod = "you";

    String DATABASE_TABLE = "demotable";
    String DATABASE_CREATETABLE = "create table " + DATABASE_TABLE + "(number, title, body)";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_Note = findViewById(R.id.btn_A_Note);
        btn_Note.setOnClickListener(addNote);
        openhelper = new DBOpenHelper(this);
        db = openhelper.getWritableDatabase();

    }

    private View.OnClickListener addNote = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent = new Intent();
            intent.setClass(MainActivity.this,NotepadEditor.class);
            startActivity(intent);
        }
    };

    public void onStop(){
        super.onStop();
        //db.execSQL("Drop table if exists " + DATABASE_TABLE);
        //db.execSQL(DATABASE_CREATETABLE);
        db.close();
    }

    class DBOpenHelper extends SQLiteOpenHelper{
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




