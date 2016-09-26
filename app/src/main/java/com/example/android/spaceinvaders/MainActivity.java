package com.example.android.spaceinvaders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity {

    TimerTask tarea;
    Timer timer;
    float posicionInicialMunicion;
    ImageView municion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        municion = (ImageView) findViewById(R.id.municion);
        posicionInicialMunicion = municion.getY();
    }

    public void actualizaPosicion(View v) {
        switch (v.getId()) {
            case (R.id.control_derecha):
                findViewById(R.id.nave).setX(findViewById(R.id.nave).getX() - 30);
                break;
            case R.id.control_izquierda:
                findViewById(R.id.nave).setX(findViewById(R.id.nave).getX() + 30);
                break;
        }
    }

    public void dispara(View v) {
        ImageView ammo = (ImageView) findViewById(R.id.municion);
        if (ammo.getY() >= posicionInicialMunicion) {
            System.out.println(findViewById(R.id.municion).getY() + "\n\n\n");
            ammo.setVisibility(View.VISIBLE);
            timer = new Timer();
            tarea = new TimerTask() {
                @Override
                public void run() {
                    if (!llegaAlFinal()) {
                        municion.setY(municion.getY() - 50);
                        System.out.println(municion.getY() + "\n");
                    } else
                        restauraPorDefecto();

                }
            };
        }
        timer.schedule(tarea, 0, 500);
    }

    private boolean llegaAlFinal() {
        return findViewById(R.id.municion).getY() <= 50;
    }

    private void restauraPorDefecto() {
        timer.cancel();
        tarea.cancel();
        municion.setX(findViewById(R.id.nave).getX() + (findViewById(R.id.nave).getWidth()) / 2);
        municion.setY(1122);
    }
}
