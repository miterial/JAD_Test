package com.svetlana.jad_test;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if(!hasConnection()) {
            Toast.makeText(getApplicationContext(), "Нет подключения",Toast.LENGTH_LONG).show();
        }

        BottomNavigationView bottomNavigationView = (BottomNavigationView) findViewById(R.id.navigation);

        bottomNavigationView.setOnNavigationItemSelectedListener
                (new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        Fragment selectedFragment = null;
                        switch (item.getItemId()) {
                            case R.id.action_main:
                                selectedFragment = HomeFragment.newInstance();
                                break;
                            case R.id.action_contacts:
                                selectedFragment = ContactsFragment.newInstance();
                                break;
                        }
                        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
                        transaction.replace(R.id.frame_layout, selectedFragment);
                        transaction.commit();
                        return true;
                    }
                });

        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_layout, HomeFragment.newInstance());
        transaction.commit();
    }

    //Проверка наличия подключения к сети
    public boolean hasConnection()
    {
        ConnectivityManager cm =
                (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        return cm.getActiveNetworkInfo() != null;
    }

    public void btnSendClick(View view) {
        //Получение массива введённых значений
        EditText et = (EditText) findViewById(R.id.etRequest);
        String url = et.getText().toString();

        String[] tokens = url.split("[/]");

        String[] arr = new String[tokens.length / 2 + 1];
        Arrays.fill(arr, "");
        for (String t : tokens) {
            if (!t.equals("")) {
                int pos = -1;
                if ((pos = Arrays.asList(arr).indexOf(t)) % 2 == 0) {
                    Arrays.asList(arr).add(t);
                }
            }
        }
        Toast.makeText(getApplicationContext(), "Нажата кнопка, массив заполнен",Toast.LENGTH_LONG).show();
    }

}
