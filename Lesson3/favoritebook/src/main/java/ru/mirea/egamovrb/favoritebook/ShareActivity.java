package ru.mirea.egamovrb.favoritebook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ShareActivity extends AppCompatActivity {
    private EditText etUB;
    private EditText etUQ;
    private Button btn2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_share);

        etUB = findViewById(R.id.etUB);
        etUQ = findViewById(R.id.etUQ);
        btn2 = findViewById(R.id.btn2);

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendDataBack(v);
            }
        });

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            TextView tvDB = findViewById(R.id.tvDB);
            TextView tvDQ = findViewById(R.id.tvDQ);
            String book_name = extras.getString(MainActivity.BOOK_NAME_KEY);
            String quotes_name = extras.getString(MainActivity.QUOTES_KEY);

            tvDB.setText(String.format("Моя любимая книга: %s", book_name));
            tvDQ.setText(String.format("Моя любимая цитата: %s", quotes_name));
        }
    }
    public void sendDataBack(View view) {
        String userBook = etUB.getText().toString();
        String userQuote = etUQ.getText().toString();

        if (!userBook.isEmpty() && !userQuote.isEmpty()) {
            String text = "Название Вашей любимой книги: " + userBook + ". Цитата: " + userQuote;

            Intent data = new Intent();
            data.putExtra(MainActivity.USER_MESSAGE, text);
            setResult(Activity.RESULT_OK, data);
            finish();
        } else {

            if (userBook.isEmpty()) {
                etUB.setError("Введите название книги");
            }
            if (userQuote.isEmpty()) {
                etUQ.setError("Введите цитату");
            }
        }
    }
}