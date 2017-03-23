package com.example.cice.serviceexample;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by cice on 23/3/17.
 */

/**
 * Para poder lanzar un servicio hay que registrarlo en el manifes. Debe saberlo para poder lanzar la palicacion
 */
public class MyIntentService extends IntentService{

    private static final String TAG = "ServiceExample";
    @Override
    protected void onHandleIntent(Intent intent) {
        Log.i(TAG, "El Intent Service ha comenzado");
    }

    public MyIntentService(){
        super("MyIntentService");
    }
}
