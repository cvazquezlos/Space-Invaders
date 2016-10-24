package com.example.android.spaceinvaders;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;

public class GameActivity extends AppCompatActivity {

    ImageView municion;
    ImageView nave;
    ImageView fondoJuego;
    ImageView enemigo;
    Button botonDisparo;
    RelativeLayout terrenoJuego;
    int rotacion = 0;
    Handler manejaDisparo = new Handler();
    Handler manejaEnemigo = new Handler();
    final int movimiento = 30;
    final int movimientoEnemigo = 20;
    boolean inicioAFin = false;
    int ladeadoIzq, ladeadoDer, frontal, disparo;
    int ladeadoIzqEnemigo, ladeadoDerEnemigo, frontalEnemigo, disparoEnemigo, idEnemigo;
    MediaPlayer sonidoDisparoNave;
    int puntuacion = 0;
    String titulo;
    MediaPlayer musicaFondo;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        titulo = "Space Invaders | Puntuación: ";
        setTitle(titulo + puntuacion);
        setContentView(R.layout.game_activity);
        municion = (ImageView) findViewById(R.id.municion);
        nave = (ImageView) findViewById(R.id.nave);
        enemigo = (ImageView) findViewById(R.id.enemigo);
        fondoJuego = (ImageView) findViewById(R.id.fondo_juego);
        botonDisparo = (Button) findViewById(R.id.disparo);
        terrenoJuego = (RelativeLayout) findViewById(R.id.activity_main);
        Intent i = getIntent();
        if (i != null) {
            String data = i.getStringExtra("arg");
            introduceCambios(data);
            musicaFondo = MediaPlayer.create(this, R.raw.musicafondo);
            musicaFondo.start();
        }
        manejaEnemigo.postDelayed(accionMovimiento, 0);
    }

    private void introduceCambios(String data) {
        String[] info = data.split(" ");
        int idFondo = getResources().getIdentifier(info[0], "drawable", getPackageName());
        fondoJuego.setImageResource(idFondo);
        int idNave = getResources().getIdentifier(info[1], "drawable", getPackageName());
        cambiosMovilidad(idNave);
        nave.setImageResource(frontal);
        int idEnemigo = getResources().getIdentifier(info[2], "drawable", getPackageName());
        cambiosMovilidadEnemigo(idEnemigo);
        System.out.println(idEnemigo);
        enemigo.setImageResource(frontalEnemigo);
        this.idEnemigo = idEnemigo;
    }

    public void actualizaPosicion(View v) {
        switch (v.getId()) {
            case (R.id.control_derecha):
                if (!seSale("der", "CU")) {
                    nave.setImageResource(ladeadoIzq);
                    nave.setX(nave.getX() - movimiento);
                }
                break;
            case R.id.control_izquierda:
                if (!seSale("izq", "CU")) {
                    nave.setImageResource(ladeadoDer);
                    nave.setX(nave.getX() + movimiento);
                }
                break;
        }
    }

    private void cambiosMovilidadEnemigo(int idEnemigo) {
        switch (idEnemigo) {
            case 2130837601:
                frontalEnemigo = R.drawable.enemigodiseno21;
                break;
            case 2130837598:
                frontalEnemigo = R.drawable.enemigodiseno11;
                ladeadoIzqEnemigo = R.drawable.enemigodiseno12;
                ladeadoDerEnemigo = R.drawable.enemigodiseno13;
                break;
        }
    }

    private void cambiosMovilidad(int idNave) {
        switch (idNave) {
            case 2130837589:
                frontal = R.drawable.diseno11;
                ladeadoDer = R.drawable.diseno13;
                ladeadoIzq = R.drawable.diseno12;
                disparo = R.drawable.municion;
                sonidoDisparoNave = MediaPlayer.create(this, R.raw.disparonavezanahoria);
                break;
            case 2130837592:
                frontal = R.drawable.diseno21;
                ladeadoDer = R.drawable.diseno23;
                ladeadoIzq = R.drawable.diseno22;
                disparo = R.drawable.municion1;
                sonidoDisparoNave = MediaPlayer.create(this, R.raw.disparodragon);
                break;
            case 2130837595:
                frontal = R.drawable.diseno31;
                ladeadoDer = R.drawable.diseno33;
                ladeadoIzq = R.drawable.diseno32;
                disparo = R.drawable.municion2;
                sonidoDisparoNave = MediaPlayer.create(this, R.raw.disparonavenormal);
                break;
        }
    }

    public void dispara(View v) {
        sonidoDisparoNave.start();
        nave.setImageResource(frontal);
        municion.setImageResource(disparo);
        municion.setX(nave.getX() + (((nave.getWidth()) / 2) - 5));
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
                resetBala();
            }
            manejaDisparo.postDelayed(this, 80);
            if (colisionaConEnemigo()) {
                enemigo.setY(0);
                enemigo.setX((terrenoJuego.getWidth() / 2) - (enemigo.getWidth() / 2));
                puntuacion += 20;
                setTitle(titulo + puntuacion);
                resetBala();
            }
        }
    };

    Runnable accionMovimiento = new Runnable() {
        @Override
        public void run() {
            if (inicioAFin) {
                if (idEnemigo == 2130837601) {
                    rotacion += 20;
                    enemigo.setRotation(rotacion);
                } else
                    enemigo.setImageResource(ladeadoIzqEnemigo);
                enemigo.setX(enemigo.getX() + movimientoEnemigo);
            } else {
                if (idEnemigo == 2130837601) {
                    rotacion -= 20;
                    enemigo.setRotation(rotacion);
                } else
                    enemigo.setImageResource(ladeadoDerEnemigo);
                enemigo.setX(enemigo.getX() - movimientoEnemigo);
            }
            if (seSale("izq", "IA") || seSale("der", "IA")) {
                rotacion = 0;
                enemigo.setY(enemigo.getY() + 40);
                inicioAFin = !inicioAFin;
            }
            manejaEnemigo.postDelayed(this, 80);
        }
    };

    private boolean llegaAlFinal() {
        return (municion.getY() <= 20);
    }

    private boolean seSale(String direccion, String jugador) {
        switch (direccion) {
            case "izq":
                switch (jugador) {
                    case "CU":
                        return (nave.getX() + movimiento + nave.getWidth()) > findViewById(R.id.activity_main).getWidth();
                    case "IA":
                        return (enemigo.getX() + movimiento + enemigo.getWidth()) > findViewById(R.id.activity_main).getWidth();
                }
            case "der":
                switch (jugador) {
                    case "CU":
                        return (nave.getX() - movimiento) < 0;
                    case "IA":
                        return (enemigo.getX() - movimiento) < 0;
                }
        }
        return true;
    }

    private boolean colisionaConEnemigo() {
        return estaEnRegionX() && estaEnRegionY();
    }

    private boolean estaEnRegionX() {
        return (municion.getX() > enemigo.getX()) && (municion.getX() < (enemigo.getX() + enemigo.getWidth()));
    }

    private boolean estaEnRegionY() {
        return (municion.getY() > enemigo.getY() && (municion.getY() < (enemigo.getY() + enemigo.getHeight())));
    }

    private void resetBala() {
        municion.setVisibility(View.INVISIBLE);
        manejaDisparo.removeCallbacks(accionDisparo);
        botonDisparo.setEnabled(true);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (musicaFondo != null) {
            musicaFondo.pause();
            if (isFinishing()) {
                musicaFondo.stop();
                musicaFondo.release();
            }
        }
    }

}
