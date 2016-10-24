package com.example.android.spaceinvaders;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity {

    ImageButton opcionBoton;
    private PopupWindow popup;
    private RelativeLayout layoutPrincipal;
    String[] resultados;
    MediaPlayer sonidoMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        resultados = new String[3];
        resultados[0] = "fondo3";
        resultados[1] = "diseno11";
        resultados[2] = "enemigodiseno11";
        opcionBoton = (ImageButton) findViewById(R.id.opcion_boton);
        layoutPrincipal = (RelativeLayout) findViewById(R.id.principal_screen);
        sonidoMenu = MediaPlayer.create(this, R.raw.botonpresionado);
        opcionBoton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sonidoMenu.start();
                LayoutInflater inflater = (LayoutInflater) getBaseContext().getSystemService(LAYOUT_INFLATER_SERVICE);
                View vistaPopup = inflater.inflate(R.layout.popup_activity, null);
                popup = new PopupWindow(
                        vistaPopup,
                        RelativeLayout.LayoutParams.MATCH_PARENT,
                        RelativeLayout.LayoutParams.WRAP_CONTENT
                );
                popup.showAtLocation(layoutPrincipal, Gravity.BOTTOM, 0, 0);
                ImageButton cerrarPop = (ImageButton) vistaPopup.findViewById(R.id.volver_boton);
                cerrarPop.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        sonidoMenu.start();
                        popup.dismiss();
                        opcionBoton.setVisibility(View.VISIBLE);
                    }
                });
                opcionBoton.setVisibility(View.INVISIBLE);
            }
        });
    }

    public void iniciaJuego(View view) {
        sonidoMenu.start();
        Intent juego = new Intent(view.getContext(), GameActivity.class);
        juego.putExtra("arg", resultados[0] + " " + resultados[1] + " " + resultados[2]);
        startActivity(juego);
    }

    public void actualizaFondo(View vista) {
        switch (vista.getId()) {
            case R.id.fondo_1:
                resultados[0] = "fondo";
                break;
            case R.id.fondo_2:
                resultados[0] = "fondo1";
                break;
            case R.id.fondo_3:
                resultados[0] = "fondo3";
                break;
        }
    }

    public void actualizaNave(View vista) {
        switch (vista.getId()) {
            case R.id.nave_1:
                resultados[1] = "diseno11";
                break;
            case R.id.nave_2:
                resultados[1] = "diseno21";
                break;
            case R.id.nave_3:
                resultados[1] = "diseno31";
                break;
        }
    }

    public void actualizaEnemigo(View vista) {
        switch (vista.getId()) {
            case R.id.enemigo_1:
                resultados[2] = "enemigodiseno11";
                break;
            case R.id.enemigo_2:
                resultados[2] = "enemigodiseno21";
                break;
        }
    }

}
