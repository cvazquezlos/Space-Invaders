package com.example.android.spaceinvaders;

import android.os.Handler;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageView municion;
    RelativeLayout rl;
    Handler manejaDisparo = new Handler();
    final int movimiento = 30;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        municion = (ImageView) findViewById(R.id.municion);
        rl = (RelativeLayout) findViewById(R.id.activity_main);
    }

    public void actualizaPosicion(View v) {
        switch (v.getId()) {
            case (R.id.control_derecha):
                if (!seSale("der")) {
                    findViewById(R.id.nave).setX(findViewById(R.id.nave).getX() - movimiento);
                }
                break;
            case R.id.control_izquierda:
                if (!seSale("izq")) {
                    findViewById(R.id.nave).setX(findViewById(R.id.nave).getX() + movimiento);
                }
                break;
        }
    }

    private boolean seSale(String direccion) {
        switch (direccion) {
            case "izq":
                return (findViewById(R.id.nave).getX() + movimiento + findViewById(R.id.nave).getWidth()) > rl.getWidth();
            case "der":
                return (findViewById(R.id.nave).getX() - movimiento) < 0;
        }
        return true;
    }

    Runnable accionDisparo = new Runnable() {
        @Override
        public void run() {
            municion.setY(municion.getY() - 50);
            if (llegaAlFinal()) {
                municion.setVisibility(View.INVISIBLE);
                manejaDisparo.removeCallbacks(accionDisparo);
                findViewById(R.id.disparo).setEnabled(true);
            }
            manejaDisparo.postDelayed(this, 80);
        }
    };

    public void dispara(View v) {
        municion.setX(findViewById(R.id.nave).getX() + (((findViewById(R.id.nave).getWidth()) / 2) - 15));
        municion.setY(findViewById(R.id.nave).getY());
        municion.setVisibility(View.VISIBLE);
        findViewById(R.id.disparo).setEnabled(false);
        manejaDisparo.postDelayed(accionDisparo, 0);
    }

    private boolean llegaAlFinal() {
        return findViewById(R.id.municion).getY() <= 50;
    }
}
