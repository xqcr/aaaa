package ru.mirea.egamovrb.intentapp;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class SecondActivity extends AppCompatActivity {
    private TextView tv;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);

        tv = findViewById(R.id.tv);

        Intent intent = getIntent();
        String currentTime = intent.getStringExtra("time");
        int myNumber = intent.getIntExtra("number", 0);

        int square = myNumber * myNumber;

        String message = "КВАДРАТ ЗНАЧЕНИЯ МОЕГО НОМЕРА ПО СПИСКУ В ГРУППЕ СОСТАВЛЯЕТ ЧИСЛО " +
                square + ", а текущее время " + currentTime;

        tv.setText(message);
    }
}