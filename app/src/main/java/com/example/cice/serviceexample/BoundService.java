package com.example.cice.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.icu.text.SimpleDateFormat;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.util.Date;
import java.util.Locale;

/**
 * Created by cice on 23/3/17.
 */

public class BoundService extends Service {

    private static final String TAG = "ServiceExample";

    private final IBinder myBinder = new MyLocalBinder();

    public BoundService(){}

    @Override

    public IBinder onBind(Intent intent) {
        Log.i(TAG, "onBind Realizado");

        return myBinder;
    }

    public String getCurrentTime(){
        Log.i(TAG, "Tiempo sacado");

        SimpleDateFormat dateFormat = new SimpleDateFormat("HH:mm:ss dd/mm/yyyy", Locale.US);
        return dateFormat.format(new Date());
    }

    public class MyLocalBinder extends Binder{

        BoundService getService(){
            return BoundService.this;
        }
    }
}
