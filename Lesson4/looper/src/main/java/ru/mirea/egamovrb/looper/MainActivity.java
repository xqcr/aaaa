package ru.mirea.egamovrb.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import ru.mirea.egamovrb.looper.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ActivityMainBinding binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        Handler mainThreadHandler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                Log.d(MainActivity.class.getSimpleName(), "Task execute. This is result: " + msg.getData().getString("result"));
            }
        };
        MyLooper myLooper = new MyLooper(mainThreadHandler);
        myLooper.start();

        binding.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ageStr = binding.editTextAge.getText().toString();
                String job = binding.editTextJob.getText().toString();

                try {
                    int age = Integer.parseInt(ageStr);
                    Message msg = Message.obtain();
                    Bundle bundle = new Bundle();
                    bundle.putInt("age", age);
                    bundle.putString("job", job);
                    msg.setData(bundle);
                    myLooper.getHandler().sendMessage(msg);
                }catch (NumberFormatException e){
                    Log.e(MainActivity.class.getSimpleName(), "Ошибка" + ageStr);
                }
            }
        });
    }
}