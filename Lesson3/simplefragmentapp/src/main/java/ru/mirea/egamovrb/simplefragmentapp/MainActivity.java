package ru.mirea.egamovrb.simplefragmentapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

public class MainActivity extends AppCompatActivity {
    private Fragment fragment1, fragment2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FragmentManager fragmentManager = getSupportFragmentManager();

        Fragment existingFragment1 = fragmentManager.findFragmentById(R.id.list);
        Fragment existingFragment2 = fragmentManager.findFragmentById(R.id.viewer);

        if (existingFragment1 == null && existingFragment2 == null) {
            fragment1 = new FirstFragment();
            fragment2 = new SecondFragment();

            Button btnFirstFragment = findViewById(R.id.btnFirstFragment);
            Button btnSecondFragment = findViewById(R.id.btnSecondFragment);

            btnFirstFragment.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                            fragment1).commit();
                }
            });

            btnSecondFragment.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    fragmentManager.beginTransaction().replace(R.id.fragmentContainerView,
                            fragment2).commit();
                }
            });
        }
    }
}