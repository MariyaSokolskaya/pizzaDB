package com.example.pizzadb;

import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class DBInfoActivity extends AppCompatActivity {
    PizzaDBHelper pizzaDBHelper;
    SQLiteDatabase sdb;

    ListView listView;
    Cursor cursor;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dbinfo);

        listView = findViewById(R.id.db_info_list);

        pizzaDBHelper = new PizzaDBHelper(this);
        sdb = pizzaDBHelper.getWritableDatabase();

        String query = "SELECT * FROM " + PizzaDBHelper.TABLE_NAME + ";";
        cursor = sdb.rawQuery(query, null);
        //Вариант 1 - нам надо вытаскивать из курсора инфу по одной ячейке -> HashMap -> список данных
        // и передать в SimpleAdapter
        //Вариант 2 - отобразить содержимое cursor как есть. -> использовать SimpleCursorAdapter

        String [] from = {PizzaDBHelper.COLUMN_DATA, PizzaDBHelper.COLUMN_MONEY,
                        PizzaDBHelper.COLUMN_NAMES, PizzaDBHelper.COLUMN_CLIENT};
        int [] to = {R.id.data_info, R.id.money_info, R.id.names_info, R.id.client_info};
        SimpleCursorAdapter cursorAdapter = new SimpleCursorAdapter(this, R.layout.list_item,
                cursor, from, to, CursorAdapter.FLAG_REGISTER_CONTENT_OBSERVER);
        listView.setAdapter(cursorAdapter);
        //Пример: select * from table WHERE money > 800 OR money < 200
    }

    @Override
    protected void onStart() {
        super.onStart();
        if(!sdb.isOpen())
            sdb = pizzaDBHelper.getWritableDatabase();
    }

    @Override
    protected void onStop() {
        super.onStop();
        cursor.close();
        sdb.close();
    }
}