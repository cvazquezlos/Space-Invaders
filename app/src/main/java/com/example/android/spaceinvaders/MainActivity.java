package com.example.android.spaceinvaders;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void actualizaPosicionIzq(View view) {
        Button botonIzq = (Button) view;
        findViewById(R.id.nave).setX(findViewById(R.id.nave).getX() + 30);
    }

    public void actualizaPosicionDer(View view) {
        Button botonDer = (Button) view;
        findViewById(R.id.nave).setX(findViewById(R.id.nave).getX() - 30);
    }
}
