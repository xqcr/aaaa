package ru.mirea.egamovrb.thread;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.Arrays;

import ru.mirea.egamovrb.thread.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    private ActivityMainBinding binding;
    private TextView infoTextView;
    private TextView tvRES;
    private int counter = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        tvRES = findViewById(R.id.tvRES);
        infoTextView = findViewById(R.id.textView2);
        Thread mainThread = Thread.currentThread();
        infoTextView.setText("Имя текущего потока: " + mainThread.getName());
        mainThread.setName("ГРУППА: БИСО-02-20, НОМЕР ПО СПИСКУ: 23, МОЙ ЛЮБИМЫЙ ФИЛЬМ: Властелин колец");
        infoTextView.append("\n Новое имя потока: " + mainThread.getName());
        Log.d(MainActivity.class.getSimpleName(), "Stack:	" + Arrays.toString(mainThread.getStackTrace()));

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        int numberThread = counter++;
                        Log.d("ThreadProject", String.format("Запущен поток № %d студентом группы № %s номер по списку № %d", numberThread, "БИСО-02-20", -1));
                        long endTime = System.currentTimeMillis() + 20 * 1000;
                        while (System.currentTimeMillis() < endTime) {
                            synchronized (this) {
                                try {
                                    wait(endTime - System.currentTimeMillis());
                                } catch (Exception e) {
                                    throw new RuntimeException(e);
                                }
                            }
                            Log.d("ThreadProject", "Выполнен поток №" + numberThread);
                        }
                    }
                }).start();
            }
        });
        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                calculateAveragePairs();
            }
        });
    }

    private void calculateAveragePairs() {
        String pairsText = binding.etPAR.getText().toString();
        String daysText = binding.etDAY.getText().toString();

        if (pairsText.isEmpty() || daysText.isEmpty()) {
            Toast.makeText(this, "Пожалуйста, заполните оба поля", Toast.LENGTH_SHORT).show();
            return;
        }

        try {
            int totalPairs = Integer.parseInt(pairsText);
            int totalDays = Integer.parseInt(daysText);

            if (totalDays == 0) {
                Toast.makeText(this, "Количество дней не может быть нулевым", Toast.LENGTH_SHORT).show();
                return;
            }

            double averagePairs = (double) totalPairs / totalDays;

            String result = String.format("Среднее количество пар в день: %.2f", averagePairs);
            tvRES.setText(result);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Пожалуйста, введите корректные числа", Toast.LENGTH_SHORT).show();
        }
    }
}