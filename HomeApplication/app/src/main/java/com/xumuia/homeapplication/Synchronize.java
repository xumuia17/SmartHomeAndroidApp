package com.xumuia.homeapplication;

import android.app.Service;
import android.content.Intent;
import android.os.Handler;
import android.os.IBinder;
import android.widget.Toast;

import java.util.Timer;
import java.util.TimerTask;

public class Synchronize extends Service {
    public Synchronize() {
    }

    @Override
    public IBinder onBind(Intent intent) {
        // TODO: Return the communication channel to the service.
        throw new UnsupportedOperationException("Not yet implemented");
    }
    @Override
    public void onCreate(){
        super.onCreate();
        Toast.makeText(this, "Служба Synchronize создана",
                Toast.LENGTH_SHORT).show();
        Timer timer = new Timer();
        final Handler handler = new Handler();
        Integer counter = 1;

        counter++;


    }
}
