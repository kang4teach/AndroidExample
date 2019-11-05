package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button insertBtn,updateBtn,deleteBtn,queryBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textView);
        editText = findViewById(R.id.editText);
        insertBtn = findViewById(R.id.insertBtn);
        updateBtn = findViewById(R.id.updateBtn);
        deleteBtn = findViewById(R.id.deleteBtn);
        queryBtn = findViewById(R.id.queryBtn);
        insertBtn.setOnClickListener((view)->{insert();});
        deleteBtn.setOnClickListener((view)->{delete();});
    }

    private void insert(){
        SqliteExampleHelper helper = new SqliteExampleHelper(this,"database.db",null,2);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name",editText.getText().toString());
        sqLiteDatabase.insert("table_example",null,contentValues);
        sqLiteDatabase.close();
    }

    private void delete(){
        SqliteExampleHelper helper = new SqliteExampleHelper(this,"database.db",null,2);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        //delete from table_example  where name='Name';
        sqLiteDatabase.delete("table_example","name=?",new String[]{"Name"});
        sqLiteDatabase.close();
    }

}
