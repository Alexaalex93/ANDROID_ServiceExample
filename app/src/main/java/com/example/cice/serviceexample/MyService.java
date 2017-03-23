package com.example.cice.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;

public class MyService extends Service {

    /**
     * ESTO ES UN STARTED SERVICE
     */
    public MyService() {
    }

    private static final String TAG = "ServiceExample";

    @Override
    public void onCreate() {
        Log.i(TAG, "Service onCreate llamado");

    }

    /**
     *
     * @param intent
     * @param flags
     * @param startId
     * @return STICKY
     * Cuando el sistema se queda sin memoria al ponerle STICKY(Pegajoso) es que cuando recupere memoria vuelve a lanzarlo
     */
    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.i(TAG, "Service  onStartCommand llamado");

        final int currentId = startId;
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                for(int i = 0; i <3; i++){
                    long endTime = System.currentTimeMillis() + 10 * 1000;
                    while (System.currentTimeMillis() < endTime){
                        synchronized (this){
                            try {
                                wait(endTime - System.currentTimeMillis());
                            } catch (Exception e){}
                        }
                    } Log.i(TAG, "Service funcionando");
                }
                stopSelf();
            }
        };
        Thread thread = new Thread(runnable);
        thread.start();

        return Service.START_STICKY;
    }

    @Override
    public IBinder onBind(Intent intent) {
        Log.i(TAG, "Service onBind llamado");
        return null;
    }

    @Override
    public void onDestroy() {
        Log.i(TAG, "Service onDestroy llamado");
    }
}
