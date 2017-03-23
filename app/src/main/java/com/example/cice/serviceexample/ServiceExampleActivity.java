package com.example.cice.serviceexample;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ServiceExampleActivity extends AppCompatActivity {

    /**
     * Si no quieres utilizar un hilo puedes utilizar un intent service
     *
     *
     * Hilo como una tarea principal y  no quieres que se bloquee la aplicacion
     *
     * Servicio algo que dura toda la aplicacion y que siempre está corriendo por detas (o mucho tiempo)
     * Funciona en una clase a parte utilizado para descaragr cosas en segundo plano, aplicaciones de mensajeria y demas
     * por ejemplo, WhatsApp tiene un servicio que continuamente está revisando servidores
     */


    /**
     * Started Services & Bound Services
     *     Started Services
     *          Lo lanza los componentes de la aplicacion (activity, broadcast reciver, etc)
     *          Corren en background hasta que termina el servicio (o son destruidos)
     *          En el mismo thread que el proceso que lo ha lanzado (local service)
     *          El sistema les da la máxima prioridad
     *
     *      Intent Services (Menos prioritarios que Started Services)
     *          Tareas en background de forma asíncrona.
     *
     *      Bound Services
     *          Este devuelve resultados
     *              Permite la interacción con el componente que lo ha lanzado
     */

    BoundService myService;
    boolean isBound = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service_example);

        Intent intent = new Intent(this, BoundService.class);
        bindService(intent, myConnection, Context.BIND_AUTO_CREATE);



    }

    /**
     * En BoundService hace falta crear una conexion
     */
    private ServiceConnection myConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
            BoundService.MyLocalBinder binder = (BoundService.MyLocalBinder) iBinder;
            myService = binder.getService();
            isBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            isBound = false;
        }
    };

    public void onClickButton(View view){

        Intent intent = new Intent(this, MyService.class);
        startService(intent);
    }

    public void showTime(View view){
        String currentTime = myService.getCurrentTime();
        TextView textView = (TextView) findViewById(R.id.text_view1);
        textView.setText(currentTime);
    }
}
