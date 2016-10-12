package com.example.android.spaceinvaders;

import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView municion;
    ImageView nave;
    Button botonDisparo;
    Handler manejaDisparo = new Handler();
    final int movimiento = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        municion = (ImageView) findViewById(R.id.municion);
        nave = (ImageView) findViewById(R.id.nave);
        botonDisparo = (Button) findViewById(R.id.disparo);
    }

    public void actualizaPosicion(View v) {
        switch (v.getId()) {
            case (R.id.control_derecha):
                if (!seSale("der")) {
                    nave.setImageResource(R.drawable.diseno12);
                    nave.setX(nave.getX() - movimiento);
                }
                break;
            case R.id.control_izquierda:
                if (!seSale("izq")) {
                    nave.setImageResource(R.drawable.diseno13);
                    nave.setX(nave.getX() + movimiento);
                }
                break;
        }
    }

    public void dispara(View v) {
        nave.setImageResource(R.drawable.diseno11);
        municion.setX(nave.getX() + (((nave.getWidth()) / 2)-5));
        municion.setY(nave.getY());
        municion.setVisibility(View.VISIBLE);
        botonDisparo.setEnabled(false);
        manejaDisparo.postDelayed(accionDisparo, 0);
    }

    Runnable accionDisparo = new Runnable() {
        @Override
        public void run() {
            municion.setY(municion.getY() - 50);
            if (llegaAlFinal()) {
                municion.setVisibility(View.INVISIBLE);
                manejaDisparo.removeCallbacks(accionDisparo);
                botonDisparo.setEnabled(true);
            }
            manejaDisparo.postDelayed(this, 80);
        }
    };

    private boolean llegaAlFinal() {
        return municion.getY() <= 20;
    }

    private boolean seSale(String direccion) {
        switch (direccion) {
            case "izq":
                return (nave.getX() + movimiento + nave.getWidth()) > findViewById(R.id.activity_main).getWidth();
            case "der":
                return (nave.getX() - movimiento) < 0;
        }
        return true;
    }
}
