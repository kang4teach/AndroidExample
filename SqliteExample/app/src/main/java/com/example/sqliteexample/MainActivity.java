package com.example.sqliteexample;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.icu.text.UnicodeSetSpanner;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textView;
    EditText editText;
    Button insertBtn, updateBtn, deleteBtn, queryBtn;

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
        insertBtn.setOnClickListener((view) -> {
            insert();
        });
        deleteBtn.setOnClickListener((view) -> {
            delete();
        });
        updateBtn.setOnClickListener((view) -> {
            update();
        });
        queryBtn.setOnClickListener((view) -> {
            query(view);
        });
    }

    private void insert() {
        SqliteExampleHelper helper = new SqliteExampleHelper(this, "database.db", null, 2);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", editText.getText().toString());
        sqLiteDatabase.insert("table_example", null, contentValues);
        sqLiteDatabase.close();
    }

    private void delete() {
        SqliteExampleHelper helper = new SqliteExampleHelper(this, "database.db", null, 2);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        //delete from table_example  where name='Name';
        sqLiteDatabase.delete("table_example", "name=?", new String[]{"Name"});
        sqLiteDatabase.close();
    }

    private void update() {
        int updateId = 4;
        SqliteExampleHelper helper = new SqliteExampleHelper(this, "database.db", null, 2);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", editText.getText().toString());
        // update () values() where ()
        sqLiteDatabase.update("table_example", contentValues, "id=?", new String[]{"4"});
        sqLiteDatabase.close();
    }

    private void query(View view) {
        SqliteExampleHelper helper = new SqliteExampleHelper(this, "database.db", null, 2);
        SQLiteDatabase sqLiteDatabase = helper.getWritableDatabase();
        // select () from () where () group by () having () order by ()  *limit()
        Cursor cursor = sqLiteDatabase.query(
                "table_example",
                new String[]{"id", "name"},
                null,
                null,
                null,
                null,
                null);
        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String name = cursor.getString(1);
                String showString = "id:" + String.valueOf(id) + " name: " + name;
                Toast.makeText(view.getContext(),
                        showString,
                        Toast.LENGTH_SHORT
                ).show();
                Log.d("CURSOR", showString);
            } while (cursor.moveToNext());
        }
        sqLiteDatabase.close();
    }

}
