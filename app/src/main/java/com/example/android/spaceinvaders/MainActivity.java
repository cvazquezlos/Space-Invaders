package com.example.android.spaceinvaders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TimerTask tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tarea = new TimerTask() {
            @Override
            public void run() {
                findViewById(R.id.municion).setY(findViewById(R.id.municion).getY() - 50);
            }
        };
    }

    public void actualizaPosicion(View v) {
        switch (v.getId()) {
            case R.id.control_derecha:
                findViewById(R.id.nave).setX(findViewById(R.id.nave).getX() - 30);
                break;
            case R.id.control_izquierda:
                findViewById(R.id.nave).setX(findViewById(R.id.nave).getX() + 30);
                break;
        }
    }

    public void dispara(View v) {
        ImageView municion = (ImageView) findViewById(R.id.municion);
        municion.setVisibility(View.VISIBLE);
        Timer timer = new Timer();
        timer.schedule(tarea, 0, 500);
    }

    private boolean llegaAlFinal(ImageView municion) {
        //return municion.getY()==;
        return true;
    }
}
