package ru.mirea.egamovrb.looper;

import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;

public class MyLooper extends Thread {
    private Handler mainHandler;
    private Handler myHandler;

    public MyLooper(Handler mainThreadHandler) {
        this.mainHandler = mainThreadHandler;
    }

    public Handler getHandler() {
        return myHandler;
    }

    @Override
    public void run() {
        Looper.prepare();
        myHandler = new Handler(Looper.myLooper()) {
            @Override
            public void handleMessage(Message msg) {
                int age = msg.getData().getInt("age");
                String job = msg.getData().getString("job");

                try {
                    Thread.sleep(age * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }

                Message resultMsg = Message.obtain();
                Bundle bundle = new Bundle();
                bundle.putString("result", "Мой возраст: " + age + ", Моя работа: " + job);
                resultMsg.setData(bundle);
                mainHandler.sendMessage(resultMsg);
            }
        };
        Looper.loop();
    }
}