package com.example.pizzadb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    PizzaDBHelper pizzaDBHelper;
    SQLiteDatabase sdb;
    EditText data, money, names, client;
    Button insertButton, showButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        pizzaDBHelper = new PizzaDBHelper(this);
        sdb = pizzaDBHelper.getWritableDatabase();

        data = findViewById(R.id.data);
        money = findViewById(R.id.money);
        names = findViewById(R.id.names);
        client = findViewById(R.id.client);
        insertButton = findViewById(R.id.insert_button);
        showButton = findViewById(R.id.show_button);

        insertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ContentValues values = new ContentValues();
                values.put(PizzaDBHelper.COLUMN_DATA, data.getText().toString());
                values.put(PizzaDBHelper.COLUMN_MONEY, Double.parseDouble(money.getText().toString()));
                values.put(PizzaDBHelper.COLUMN_NAMES, names.getText().toString());
                values.put(PizzaDBHelper.COLUMN_CLIENT, client.getText().toString());
                sdb.insert(PizzaDBHelper.TABLE_NAME, null, values);
                Toast.makeText(getApplicationContext(), "Информация сохранена",
                        Toast.LENGTH_SHORT).show();
            }
        });
        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, DBInfoActivity.class);
                startActivity(intent);
            }
        });
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
        sdb.close();
    }
}