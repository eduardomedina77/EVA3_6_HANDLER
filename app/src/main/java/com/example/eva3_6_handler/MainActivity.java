package com.example.eva3_6_handler;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView txtVwMensa;
    Handler miHandler = new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            //AQUI PODEMOS MODIFICAR LA UI
            int Valor_i = (int)msg.obj;
            txtVwMensa.append("Hilo:" + msg.what + "valor i = " + Valor_i + "\n");
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtVwMensa = findViewById(R.id.txtVsMensa);

        Thread tHilo = new Thread(){
            @Override
            public void run() {
                super.run();
                int i=0;
                while (true){
                    try {
                        Thread.sleep(1000);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    Message msg= miHandler.obtainMessage(1000, i);
                    miHandler.sendMessage(msg);
                    Log.wtf("MiRunnable", i + "");
                    i++;
                }
            }
        };
        tHilo.start();
    }
}