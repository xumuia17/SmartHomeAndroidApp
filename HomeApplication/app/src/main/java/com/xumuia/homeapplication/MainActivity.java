package com.xumuia.homeapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    public static String dataFromAsyncTask;
    AsyncRequest asyncRequest= new AsyncRequest();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /* Получаем элементы интерфейса*/
        final Button sunrise = (Button) findViewById(R.id.button4);
        final TextView txt = (TextView) findViewById(R.id.textView);
        final TextView txtSwitch = (TextView) findViewById(R.id.textView3);
        final Switch swBathroom = (Switch) findViewById(R.id.switch1);





        /*Запускаем сервисы*/
        startService(new Intent(MainActivity.this, Synchronize.class));

        swBathroom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtSwitch.setText("Checked");
                } else {
                    txtSwitch.setText("Unchecked");
                }
            }
        });
        Timer myTimer = new Timer(); // Создаем таймер
        final Handler uiHandler = new Handler();

        myTimer.schedule(new TimerTask() { // Определяем задачу
            @Override
            public void run() {
               new AsyncRequest().execute("http://192.168.1.34:4567/synchronize");
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                       /*txt.setText(result);*/
                    }
                });
            };
        }, 0L, 15000);

        View.OnClickListener oclBtnOk = new View.OnClickListener() {

            @Override
            public void onClick(View v){
                asyncRequest.execute("http://192.168.1.34:4567/synchronize");
                txt.setText(dataFromAsyncTask);
            }
        };

        sunrise.setOnClickListener(oclBtnOk);
    }









}
