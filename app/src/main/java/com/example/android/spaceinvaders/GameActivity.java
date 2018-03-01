package com.example.android.spaceinvaders;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class GameActivity extends Activity {

    ArrayList<Integer> posiciones;
    boolean accionEnemigo, ejecucionAccionEnemigo, inicioAFin, sonido, izquierda, derecha;
    Button botonDisparo;
    Handler manejaDisparo = new Handler(), manejaEnemigo = new Handler();
    ImageView asteroide1, asteroide2, enemigo, fondoJuego, municion, nave;
    ImageView[] municionEnemiga, matrizEnemigos;
    int disparo, frontal, frontalEnemigo, idEnemigo, iteracion, ladeadoDer, ladeadoIzq, ladeadoDerEnemigo, ladeadoIzqEnemigo, movimiento, movimientoEnemigoX, movimientoEnemigoY, puntosSaludJugador = 6, puntuacion, rotacion, saludObstaculo1, saludObstaculo2;
    int[] columnasCaptadas, navesId;
    LinearLayout matriz;
    MediaPlayer musicaFondo, sonidoDisparoNave;
    RelativeLayout activity_main, tablero_aliado, tablero_enemigo;
    TextView puntosVida;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.game_activity);
        if (savedInstanceState == null) {
            activity_main = (RelativeLayout) findViewById(R.id.activity_main);
            asteroide1 = (ImageView) findViewById(R.id.asteroide_1);
            asteroide2 = (ImageView) findViewById(R.id.asteroide_2);
            botonDisparo = (Button) findViewById(R.id.disparo);
            enemigo = (ImageView) findViewById(R.id.enemigo1);
            fondoJuego = (ImageView) findViewById(R.id.fondo_juego);
            movimiento = 13;
            movimientoEnemigoX = 5;
            movimientoEnemigoY = 60;
            municion = (ImageView) findViewById(R.id.municion);
            nave = (ImageView) findViewById(R.id.nave);
            puntosSaludJugador = 6;
            puntosVida = (TextView) findViewById(R.id.ptos_vida);
            puntuacion = 0;
            rotacion = 0;
            saludObstaculo1 = 3;
            saludObstaculo2 = 3;
            tablero_enemigo = (RelativeLayout) findViewById(R.id.tablero_enemigo);
            tablero_aliado = (RelativeLayout) findViewById(R.id.tablero_aliado);
            Intent i = getIntent();
            if (i != null) {
                String data = i.getStringExtra("arg");
                introduceCambios(data);
                iteracion = 0;
                inicioAFin = false;
                izquierda = false;
                derecha = false;
                musicaFondo = MediaPlayer.create(this, R.raw.musicafondo);
                accionEnemigo = true;
                if (sonido)
                    musicaFondo.start();
                if (iteracion == 0) {
                    lanzaEnemigos();
                    ejecucionAccionEnemigo = true;
                    manejaEnemigo.postDelayed(accionMovimientoEnemigo, 10);
                    municionEnemiga = new ImageView[0];
                    posiciones = new ArrayList<>();
                }
            }

            final Button izquierdaControl = (Button) findViewById(R.id.control_derecha);
            izquierdaControl.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        izquierda = true;
                        derecha = false;
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        izquierda = false;
                    }
                    return true;
                }
            });

            final Button derechaControl = (Button) findViewById(R.id.control_izquierda);
            derechaControl.setOnTouchListener(new View.OnTouchListener() {

                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                        izquierda = false;
                        derecha = true;
                    }
                    if (event.getAction() == MotionEvent.ACTION_UP) {
                        derecha = false;
                    }
                    return true;
                }
            });
        }
    }

    private void introduceCambios(String data) {
        if (data==null) {
            int idFondo = getResources().getIdentifier("fondo3", "drawable", getPackageName());
            fondoJuego.setImageResource(idFondo);
            int idNave = getResources().getIdentifier("diseno11", "drawable", getPackageName());
            cambiosMovilidad(idNave, "aliado");
            nave.setImageResource(frontal);
            int idEnemigo = getResources().getIdentifier("enemigodiseno11", "drawable", getPackageName());
            cambiosMovilidad(idEnemigo, "enemigo");
            enemigo.setImageResource(frontalEnemigo);
            this.idEnemigo = idEnemigo;
            sonido = Boolean.valueOf(true);
        } else {
            String[] info = data.split(" ");
            int idFondo = getResources().getIdentifier(info[0], "drawable", getPackageName());
            fondoJuego.setImageResource(idFondo);
            int idNave = getResources().getIdentifier(info[1], "drawable", getPackageName());
            cambiosMovilidad(idNave, "aliado");
            nave.setImageResource(frontal);
            int idEnemigo = getResources().getIdentifier(info[2], "drawable", getPackageName());
            cambiosMovilidad(idEnemigo, "enemigo");
            enemigo.setImageResource(frontalEnemigo);
            this.idEnemigo = idEnemigo;
            sonido = Boolean.valueOf(info[3]);
        }
    }

    private void cambiosMovilidad(int id, String valor) {
        switch (valor) {
            case "aliado":
                switch (id) {
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
                break;
            case "enemigo":
                switch (id) {
                    case 2130837601:
                        frontalEnemigo = R.drawable.enemigodiseno21;
                        break;
                    case 2130837598:
                        frontalEnemigo = R.drawable.enemigodiseno11;
                        ladeadoIzqEnemigo = R.drawable.enemigodiseno12;
                        ladeadoDerEnemigo = R.drawable.enemigodiseno13;
                        break;
                }
                break;
        }
    }

    private void lanzaEnemigos() {
        columnasCaptadas = new int[2];
        navesId = new int[]{R.id.enemigo1, R.id.enemigo2, R.id.enemigo3, R.id.enemigo4, R.id.enemigo5, R.id.enemigo6};
        matrizEnemigos = new ImageView[navesId.length];
        for (int i = 0; i < matrizEnemigos.length; i++) {
            matrizEnemigos[i] = (ImageView) findViewById(navesId[i]);
            matrizEnemigos[i].setImageResource(frontalEnemigo);
        }
        matriz = (LinearLayout) findViewById(R.id.matriz_enemigos);
    }

    private boolean seSale(String direccion, String jugador) {
        switch (direccion) {
            case "izq":
                switch (jugador) {
                    case "CU":
                        return (nave.getX() + movimiento + nave.getWidth()) > tablero_aliado.getWidth();
                    case "IA":
                        return (matriz.getX() + movimiento + matriz.getWidth()) > tablero_enemigo.getWidth();
                }
            case "der":
                switch (jugador) {
                    case "CU":
                        return (nave.getX() - movimiento) < 0;
                    case "IA":
                        return (matriz.getX() - movimiento) < 0;
                }
        }
        return true;
    }

    public void dispara(View v) {
        if (sonido)
            sonidoDisparoNave.start();
        nave.setImageResource(frontal);
        municion.setImageResource(disparo);
        municion.setX(nave.getX() + (((nave.getWidth()) / 2) - 5));
        municion.setY(activity_main.getHeight() - nave.getHeight());
        municion.setVisibility(View.VISIBLE);
        botonDisparo.setEnabled(false);
        manejaDisparo.postDelayed(accionDisparo, 10);
    }

    Runnable accionDisparo = new Runnable() {
        @Override
        public void run() {
            municion.setY(municion.getY() - 15);
            if (invadeOrigen(municion)) {
                reiniciarBala();
            }
            manejaDisparo.postDelayed(this, 10);
            if (colisionaConEnte()) {
                //detectaCambiosEnAnchura();
                puntuacion += 20;
                actualizaPuntuacion();
                reiniciarBala();
            } else if (colisionaConAsteroide(asteroide1) && saludObstaculo1 != 0) {
                actualizaRecurso(asteroide1, saludObstaculo1 -= 1);
                reiniciarBala();
            } else if (colisionaConAsteroide(asteroide2) && saludObstaculo2 != 0) {
                actualizaRecurso(asteroide2, saludObstaculo2 -= 1);
                reiniciarBala();
            }
        }
    };

    private boolean invadeOrigen(View view) {
        return (view.getY() <= 20);
    }

    private void reiniciarBala() {
        municion.setVisibility(View.INVISIBLE);
        manejaDisparo.removeCallbacks(accionDisparo);
        botonDisparo.setEnabled(true);
    }

    private boolean colisionaConEnte() {
        ImageView[] navesEn = new ImageView[2];
        for (int i = 0; i < 3; i++) {
            if (estaEnRegionXRelativa(matrizEnemigos[i])) {
                navesEn[0] = matrizEnemigos[i + 3];
                navesEn[1] = matrizEnemigos[i];
                break;
            }
        }
        if (navesEn[0] == null) {
            return false;
        } else if ((navesEn[0].getVisibility() != View.INVISIBLE) && (estaEnRegionYRelativa(navesEn[0]))) {
            navesEn[0].setVisibility(View.INVISIBLE);
            return true;
        } else if ((navesEn[1].getVisibility() != View.INVISIBLE) && (estaEnRegionYRelativa(navesEn[1]))) {
            navesEn[1].setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }

    private boolean colisionaConAsteroide(ImageView view) {
        return estaEnRegionXRelativa(view) && estaEnRegionYRelativa(view);
    }

    private boolean estaEnRegionXRelativa(View view) {
        int[] l1 = getPosicionRelativa(view);
        int[] l2 = getPosicionRelativa(municion);
        return (l2[0] > l1[0]) && (l2[0] < (l1[0] + view.getWidth()));
    }

    private boolean estaEnRegionYRelativa(View view) {
        int[] l1 = getPosicionRelativa(view);
        int[] l2 = getPosicionRelativa(municion);
        return (l2[1] > l1[1]) && (l2[1] < (l1[1] + view.getHeight()));
    }

    private void actualizaPuntuacion() {
        try {
            puntosVida.setText("" + puntuacion);
        } catch (Exception e) {
            System.out.println("Error en la transformación de Integer a String.");
        }
    }

    private void actualizaSalud() {
        int idAEsconder = 2131492961 - puntosSaludJugador;
        if (puntosSaludJugador == 0) {
            accionEnemigo = false;
            manejaEnemigo.removeCallbacks(accionMovimientoEnemigo);
            botonDisparo.setEnabled(false);
            findViewById(R.id.control_derecha).setEnabled(false);
            findViewById(R.id.control_izquierda).setEnabled(false);
            ((TextView) (findViewById(R.id.puntuacion_final))).setText("Puntuación: " + puntuacion);
            findViewById(R.id.pantalla_game_over).setVisibility(View.VISIBLE);
        }
    }

    private void accionDisparoEnemigo() {
        if (ejecucionAccionEnemigo) {
            ArrayList<Integer> tiradores = enemigosQueDisparan();
            if (!posiciones.equals(tiradores)) {
                for (int i = 0; i < municionEnemiga.length; i++)
                    activity_main.removeView(municionEnemiga[i]);
                List<ImageView> list = new ArrayList<>();
                Collections.addAll(list, municionEnemiga);
                list.clear();
                municionEnemiga = list.toArray(new ImageView[tiradores.size()]);
                for (int i = 0; i < tiradores.size(); i++) {
                    ImageView image = new ImageView(GameActivity.this);
                    municionEnemiga[i] = image;
                    municionEnemiga[i].setImageResource(R.drawable.municionenemiga);
                    municionEnemiga[i].setLayoutParams(new android.view.ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    activity_main.addView(municionEnemiga[i]);
                    municionEnemiga[i].setVisibility(View.VISIBLE);
                    municionEnemiga[i].setX(getPosicionXRelative(matrizEnemigos[tiradores.get(i)]) + (matrizEnemigos[tiradores.get(i)].getWidth() / 2) - 8);
                    municionEnemiga[i].setY(getPosicionYRelative(matrizEnemigos[tiradores.get(i)]));
                }
                posiciones.clear();
                posiciones = new ArrayList<Integer>(tiradores);
            } else {
                for (int i = 0; i < posiciones.size(); i++) {
                    municionEnemiga[i].setVisibility(View.VISIBLE);
                    municionEnemiga[i].setX(getPosicionXRelative(matrizEnemigos[posiciones.get(i)]) + (matrizEnemigos[posiciones.get(i)].getWidth() / 2) - 8);
                    municionEnemiga[i].setY(getPosicionYRelative(matrizEnemigos[posiciones.get(i)]));
                }
            }
        }
    }

    private ArrayList<Integer> enemigosQueDisparan() {
        ArrayList<Integer> posiciones = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            if (matrizEnemigos[i + 3].getVisibility() != View.INVISIBLE) {
                posiciones.add(i + 3);
            } else if (matrizEnemigos[i].getVisibility() != View.INVISIBLE) {
                posiciones.add(i);
            }
        }
        return posiciones;
    }

    private int getPosicionXRelative(View view) {
        int[] location = getPosicionRelativa(view);
        return location[0];
    }

    private int getPosicionYRelative(View view) {
        int[] location = getPosicionRelativa(view);
        return location[1];
    }

    Runnable accionMovimientoEnemigo = new Runnable() {
        @Override
        public void run() {
            iteracion++;
            if (iteracion != 1)
                accionDisparoEnemigo();
            if (inicioAFin) {
                if (idEnemigo == 2130837601) {
                    rotacion += 2;
                    /*for (int i=0; i<matrizEnemigos.length; i++)
                        matrizEnemigos[i].setRotation(rotacion);*/
                } else
                    for (int i = 0; i < matrizEnemigos.length; i++)
                        matrizEnemigos[i].setImageResource(ladeadoIzqEnemigo);
                matriz.setX(matriz.getX() + movimientoEnemigoX);
            } else {
                if (idEnemigo == 2130837601) {
                    rotacion -= 2;
                    /*for (int i=0; i<matrizEnemigos.length; i++)
                        matrizEnemigos[i].setRotation(rotacion);*/
                } else
                    for (int i = 0; i < matrizEnemigos.length; i++)
                        matrizEnemigos[i].setImageResource(ladeadoDerEnemigo);
                matriz.setX(matriz.getX() - movimientoEnemigoX);
            }
            if (seSale("izq", "IA") || seSale("der", "IA")) {
                rotacion = 0;
                matriz.setY(matriz.getY() + movimientoEnemigoY);
                inicioAFin = !inicioAFin;
            }
            if (invadeMitad()) {
                movimientoEnemigoY *= -1;
            } else if (invadeOrigen(matriz)) {
                movimientoEnemigoY *= -1;
            }
            if (municionEnemiga.length > 0) {
                ejecucionAccionEnemigo = false;
                for (int i = 0; i < municionEnemiga.length; i++) {
                    municionEnemiga[i].setY(municionEnemiga[i].getY() + 15);
                    if (municionEnemiga[i].getVisibility() == View.VISIBLE) {
                        if (colisionaEnemigoCon(asteroide1, municionEnemiga[i]) && saludObstaculo1 != 0) {
                            municionEnemiga[i].setVisibility(View.INVISIBLE);
                        } else if (colisionaEnemigoCon(asteroide2, municionEnemiga[i]) && saludObstaculo2 != 0) {
                            municionEnemiga[i].setVisibility(View.INVISIBLE);
                        } else if (llegaMunicionAlFinal(municionEnemiga[i])) {
                            municionEnemiga[i].setVisibility(View.INVISIBLE);
                        } else if (colisionaEnemigoCon(nave, municionEnemiga[i])) {
                            municionEnemiga[i].setVisibility(View.INVISIBLE);
                            puntosSaludJugador--;
                            actualizaSalud();
                        }
                    }
                }
                if (municionDesaparecida(municionEnemiga.length)) {
                    ejecucionAccionEnemigo = true;
                }
            }
            if (todasNavesDestruidas()) {
                reseteaMatriz();
            }
            if (izquierda && !derecha) {
                if (!seSale("der", "CU")) {
                    nave.setImageResource(ladeadoIzq);
                    nave.setX(nave.getX() - movimiento);
                } else {
                    izquierda = false;
                }
            } else if (derecha && !izquierda) {
                if (!seSale("izq", "CU")) {
                    nave.setImageResource(ladeadoDer);
                    nave.setX(nave.getX() + movimiento);
                } else {
                    izquierda = false;
                }
            }
            if (accionEnemigo) {
                manejaEnemigo.postDelayed(this, 10);
            }
        }
    };

    private boolean colisionaEnemigoCon(ImageView v1, ImageView v2) {
        int[] l1 = getPosicionRelativa(v1);
        int[] l2 = getPosicionRelativa(v2);
        return ((((l2[0] > l1[0]) && (l2[0] < (l1[0] + v1.getWidth()))) && ((l2[1] > l1[1]) && (l2[1] < (l1[1] + v1.getHeight())))));
    }

    private int[] getPosicionRelativa(View view) {
        int[] localizacion = new int[2];
        view.getLocationOnScreen(localizacion);
        return localizacion;
    }

    private boolean llegaMunicionAlFinal(ImageView img) {
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int altura = metrics.heightPixels;
        return (img.getY() >= (altura - 50));
    }

    private boolean municionDesaparecida(int iterador) {
        int contador = 0;
        for (int i = 0; i < iterador; i++) {
            if (municionEnemiga[i].getVisibility() == View.INVISIBLE) {
                contador++;
            }
        }
        return (contador >= municionEnemiga.length);
    }

    private boolean todasNavesDestruidas() {
        int contador = 0;
        for (int i = 0; i < matrizEnemigos.length; i++) {
            if (matrizEnemigos[i].getVisibility() == View.INVISIBLE) {
                contador++;
            }
        }
        return contador == matrizEnemigos.length;
    }

    private boolean invadeMitad() {
        return ((matriz.getY() + matriz.getHeight()) >= (tablero_enemigo.getHeight() - (enemigo.getHeight() / 4)));
    }

    private void actualizaRecurso(ImageView view, int salud) {
        if (salud >= 0)
            switch (salud) {
                case 2:
                    view.setImageResource(R.drawable.zobjectasteroiefase2);
                    break;
                case 1:
                    view.setImageResource(R.drawable.zobjectasteroiefase3);
                    break;
                case 0:
                    view.setVisibility(View.GONE);
                    break;
            }
    }

    private void reseteaMatriz() {
        if (todasNavesDestruidas())
            for (int i = 0; i < matrizEnemigos.length; i++) {
                matrizEnemigos[i].setVisibility(View.VISIBLE);
            }
        matriz.setY(0);
        matriz.setX((tablero_enemigo.getWidth() / 2) - (matriz.getWidth() / 2));
    }

    public void volverMenuPrincipal(View v) {
        this.finish();
    }

    // QUEDA ARREGLAR ESTOS DOS MÉTODOS
    private void detectaCambiosEnAnchura() {
        int i = esColumnaVacia();
        if (i == 1 && (findViewById(navesId[1]).getVisibility() != View.INVISIBLE && findViewById(navesId[4]).getVisibility() != View.INVISIBLE)) {
            matriz.setLayoutParams(new RelativeLayout.LayoutParams((findViewById(navesId[0]).getWidth()) * 2, matriz.getHeight()));
        } else if (i == 2) {
            matriz.setLayoutParams(new RelativeLayout.LayoutParams((findViewById(navesId[0]).getWidth()), matriz.getHeight()));
        }
    }

    private int esColumnaVacia() {
        int j = 0;
        for (int i = 0; i < 3; i++) {
            if (findViewById(navesId[i]).getVisibility() == View.INVISIBLE && findViewById(navesId[i + 3]).getVisibility() == View.INVISIBLE)
                j++;
        }
        return j;
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
