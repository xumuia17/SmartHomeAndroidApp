package com.xumuia.homeapplication;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Intent;

import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity{

    public static String dataFromAsyncTask;
   final Handler uiHandler = new Handler();


        @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AsyncRequest asyncRequest= new AsyncRequest();
        asyncRequest.execute("http://192.168.1.34:4567/synchronize");


        /* Получаем элементы интерфейса*/
            final Button sunrise = (Button) findViewById(R.id.button4);
            final TextView txt = (TextView) findViewById(R.id.textView);
            final TextView txtSwitch = (TextView) findViewById(R.id.textView3);
            final Switch swHall = (Switch) findViewById(R.id.switch1);
            final Switch swBedroom = (Switch) findViewById(R.id.switch2);
            final Switch swKitchen = (Switch) findViewById(R.id.switch3);
            final Handler uiHandler = new Handler();



        /*Запускаем сервисы*/

        Timer timer= new Timer();
        TimerTask setSwitchers = new TimerTask() {
            @Override
            public void run() {
                uiHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("3");
                        AsyncRequest asyncRequest= new AsyncRequest();
                        asyncRequest.execute("http://192.168.1.34:4567/synchronize");
                        final String swRes1 = dataFromAsyncTask.substring(0,1);
                        final String swRes2 = dataFromAsyncTask.substring(2,3);
                        final String swRes3 = dataFromAsyncTask.substring(4,5);
                        System.out.println(dataFromAsyncTask);
                        swHall.setChecked(getBool(swRes1));
                        swKitchen.setChecked(getBool(swRes2));
                        swBedroom.setChecked(getBool(swRes3));
                    }
                });
            }
        };
        timer.schedule(setSwitchers,2000,7000);



        swKitchen.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    txtSwitch.setText("Checked");
                } else {
                    txtSwitch.setText("Unchecked");
                }
            }
        });




        View.OnClickListener oclBtnOk = new View.OnClickListener() {

            @Override
            public void onClick(View v){
                AsyncRequest asyncRequest= new AsyncRequest();
                asyncRequest.execute("http://192.168.1.34:4567/synchronize");
                String swRes1 = dataFromAsyncTask.substring(0,1);
                String swRes2 = dataFromAsyncTask.substring(2,3);
                String swRes3 = dataFromAsyncTask.substring(4,5);
                swHall.setChecked(getBool(swRes1));
                swKitchen.setChecked(getBool(swRes2));
                swBedroom.setChecked(getBool(swRes3));


            }
        };

        sunrise.setOnClickListener(oclBtnOk);
    }

    public static Boolean getBool(String str) {
        if (str.equals("1"))
        {
            return true;
        } else {
            return false;
        }

    }




}
