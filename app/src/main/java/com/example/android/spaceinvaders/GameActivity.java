package com.example.android.spaceinvaders;

import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class GameActivity extends AppCompatActivity {

    ImageView municion, nave, fondoJuego, enemigo, asteroide1, asteroide2;
    Button botonDisparo;
    int rotacion = 0;
    int iteracion = 0;
    RelativeLayout activity_main, tablero_enemigo, tablero_aliado;
    Handler manejaDisparo = new Handler(), manejaEnemigo = new Handler(), manejaDisparoEnemigo = new Handler();
    final int movimiento = 30;
    final int movimientoEnemigo = 5;
    boolean inicioAFin = false;
    int ladeadoIzq, ladeadoDer, frontal, disparo;
    int ladeadoIzqEnemigo, ladeadoDerEnemigo, frontalEnemigo, disparoEnemigo, idEnemigo;
    TextView puntosVida;
    MediaPlayer sonidoDisparoNave;
    int puntuacion = 0;
    MediaPlayer musicaFondo;
    Boolean sonido, accionEnemigo;
    int puntosSaludJugador = 6;
    int saludObstaculo1 = 3, saludObstaculo2 = 3;
    int[] navesId, columnasCaptadas;
    LinearLayout matriz;

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().hide();
        setContentView(R.layout.game_activity);
        municion = (ImageView) findViewById(R.id.municion);
        nave = (ImageView) findViewById(R.id.nave);
        enemigo = (ImageView) findViewById(R.id.enemigo1);
        fondoJuego = (ImageView) findViewById(R.id.fondo_juego);
        asteroide1 = (ImageView) findViewById(R.id.asteroide_1);
        asteroide2 = (ImageView) findViewById(R.id.asteroide_2);
        botonDisparo = (Button) findViewById(R.id.disparo);
        activity_main = (RelativeLayout) findViewById(R.id.activity_main);
        tablero_enemigo = (RelativeLayout) findViewById(R.id.tablero_enemigo);
        tablero_aliado = (RelativeLayout) findViewById(R.id.tablero_aliado);
        puntosVida = (TextView) findViewById(R.id.ptos_vida);
        Intent i = getIntent();
        if (i != null) {
            String data = i.getStringExtra("arg");
            introduceCambios(data);
            musicaFondo = MediaPlayer.create(this, R.raw.musicafondo);
            accionEnemigo = true;
            if (sonido)
                musicaFondo.start();
            if (iteracion == 0) {
                lanzaEnemigos();
                manejaEnemigo.postDelayed(accionMovimiento, 10);
                manejaDisparoEnemigo.postDelayed(accionDisparoEnemigo, 10);
            }
        }
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
        sonido = Boolean.valueOf(info[3]);
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
            if (llegaAlFinal()) {
                resetBala();
            }
            manejaDisparo.postDelayed(this, 10);
            if (colisionaConEnte()) {
                //detectaCambiosEnAnchura();
                puntuacion += 20;
                actualizaPuntosVida();
                resetBala();
            } else if (colisionaConAsteroide(asteroide1) || colisionaConAsteroide(asteroide2)) {
                if (colisionaConAsteroide(asteroide1))
                    actualizaRecurso(asteroide1, saludObstaculo1 -= 1);
                else
                    actualizaRecurso(asteroide2, saludObstaculo2 -= 1);
            }
        }
    };

    Runnable accionMovimiento = new Runnable() {
        @Override
        public void run() {
            iteracion++;
            if (inicioAFin) {
                if (idEnemigo == 2130837601) {
                    rotacion += 20;
                    enemigo.setRotation(rotacion);
                } else
                    enemigo.setImageResource(ladeadoIzqEnemigo);
                matriz.setX(matriz.getX() + movimientoEnemigo);
            } else {
                if (idEnemigo == 2130837601) {
                    rotacion -= 20;
                    enemigo.setRotation(rotacion);
                } else
                    enemigo.setImageResource(ladeadoDerEnemigo);
                matriz.setX(matriz.getX() - movimientoEnemigo);
            }
            if (seSale("izq", "IA") || seSale("der", "IA")) {
                rotacion = 0;
                matriz.setY(matriz.getY() + 70);
                inicioAFin = !inicioAFin;
            }
            if (invadeMitad()) {
                reseteaMatriz();
                puntosSaludJugador--;
                actualizaSalud();
                actualizaPuntosVida();
            }
            if (accionEnemigo)
                manejaEnemigo.postDelayed(this, 10);
        }
    };

    Runnable accionDisparoEnemigo = new Runnable() {
        @Override
        public void run() {
            ArrayList<Integer> posiciones = enemigosQueDisparan();
            for (int i=0; i<posiciones.size(); i++){
                // Creación de ImageViews que serán las balas de cada enemigo.
            }
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

    private boolean invadeMitad() {
        return ((matriz.getY() + matriz.getHeight()) >= tablero_enemigo.getHeight());
    }

    private boolean colisionaConEnte() {
        ImageView[] navesEnemigasEnPosX = new ImageView[2];
        for (int i = 0; i < 3; i++) {
            if (estaEnRegionXRelativa((findViewById(navesId[i])))) {
                navesEnemigasEnPosX[0] = (ImageView) findViewById(navesId[i + 3]);
                navesEnemigasEnPosX[1] = (ImageView) findViewById(navesId[i]);
                break;
            }
        }
        if (navesEnemigasEnPosX[0] == null) {
            return false;
        } else if ((navesEnemigasEnPosX[0].getVisibility() != View.INVISIBLE) && (estaEnRegionYRelativa(navesEnemigasEnPosX[0]))) {
            navesEnemigasEnPosX[0].setVisibility(View.INVISIBLE);
            return true;
        } else if ((navesEnemigasEnPosX[1].getVisibility() != View.INVISIBLE) && (estaEnRegionYRelativa(navesEnemigasEnPosX[1]))) {
            navesEnemigasEnPosX[1].setVisibility(View.INVISIBLE);
            return true;
        }
        return false;
    }

    private boolean estaEnRegionXRelativa(View view) {
        int[] location = getViewLocations(view);
        int[] location1 = getViewLocations(municion);
        return (location1[0] > location[0]) && (location1[0] < (location[0] + view.getWidth()));
    }

    private boolean estaEnRegionYRelativa(View view) {
        int[] location = getViewLocations(view);
        int[] location1 = getViewLocations(municion);
        return (location1[1] > location[1]) && (location1[1] < (location[1] + view.getHeight()));
    }

    private boolean colisionaConAsteroide(ImageView view) {
        return estaEnRegionXRelativa(view) && estaEnRegionYRelativa(view);
    }

    private void actualizaRecurso(ImageView view, int salud) {
        if (salud >= 0)
            switch (salud) {
                case 2:
                    view.setImageResource(R.drawable.zobjectasteroiefase2);
                    resetBala();
                    break;
                case 1:
                    view.setImageResource(R.drawable.zobjectasteroiefase3);
                    resetBala();
                    break;
                case 0:
                    view.setVisibility(View.GONE);
                    break;
            }
    }

    private void resetBala() {
        municion.setVisibility(View.INVISIBLE);
        manejaDisparo.removeCallbacks(accionDisparo);
        botonDisparo.setEnabled(true);
    }

    private void reseteaMatriz() {
        matriz.setY(0);
        matriz.setX((tablero_enemigo.getWidth() / 2) - (matriz.getWidth() / 2));
    }

    private void actualizaPuntosVida() {
        try {
            puntosVida.setText(Integer.toString(puntuacion));
        } catch (Exception e) {
        }
    }

    private void actualizaSalud() {
        int idAEsconder = 2131492962 - puntosSaludJugador;
        findViewById(idAEsconder).setVisibility(View.INVISIBLE);
        if (puntosSaludJugador == 0) {
            accionEnemigo = false;
            manejaEnemigo.removeCallbacks(accionMovimiento);
            botonDisparo.setEnabled(false);
            findViewById(R.id.control_derecha).setEnabled(false);
            findViewById(R.id.control_izquierda).setEnabled(false);
            ((TextView) (findViewById(R.id.puntuacion_final))).setText("Puntuación: " + puntuacion);
            findViewById(R.id.pantalla_game_over).setVisibility(View.VISIBLE);
        }
    }

    public void volverMenuPrincipal(View v) {
        this.finish();
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

    private void lanzaEnemigos() {
        columnasCaptadas=new int[2];
        navesId = new int[]{R.id.enemigo1, R.id.enemigo2, R.id.enemigo3, R.id.enemigo4, R.id.enemigo5, R.id.enemigo6};
        matriz = (LinearLayout) findViewById(R.id.matriz_enemigos);
    }

    private int[] getViewLocations(View view) {
        int[] locations = new int[2];
        view.getLocationOnScreen(locations);
        return locations;
    }

    private ArrayList<Integer> enemigosQueDisparan(){
        ArrayList<Integer> posiciones = new ArrayList<Integer>();
        for (int i=0; i<3; i++){
            if (findViewById(navesId[i]).getVisibility()!=View.INVISIBLE){
                posiciones.add(i);
            } else if (findViewById(navesId[i+3]).getVisibility()!=View.INVISIBLE){
                posiciones.add(i+3);
            }
        }
        return posiciones;
    }

    // QUEDA ARREGLAR ESTOS DOS MÉTODOS
    private void detectaCambiosEnAnchura(){
        int i = esColumnaVacia();
        if (i==1 && (findViewById(navesId[1]).getVisibility()!=View.INVISIBLE && findViewById(navesId[4]).getVisibility()!=View.INVISIBLE)){
            matriz.setLayoutParams(new RelativeLayout.LayoutParams((findViewById(navesId[0]).getWidth())*2, matriz.getHeight()));
        } else if (i==2) {
            matriz.setLayoutParams(new RelativeLayout.LayoutParams((findViewById(navesId[0]).getWidth()), matriz.getHeight()));
        }
    }

    private int esColumnaVacia(){
        int j=0;
        for (int i=0; i<3; i++){
            if (findViewById(navesId[i]).getVisibility()==View.INVISIBLE && findViewById(navesId[i+3]).getVisibility()==View.INVISIBLE)
                j++;
        }
        return j;
    }
}
