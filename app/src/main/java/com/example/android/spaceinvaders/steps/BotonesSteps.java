package com.example.android.spaceinvaders.steps;

/**
 * Created by c.vazquezlos on 17/04/2017.
 */

import com.example.android.spaceinvaders.mock.GameActivityMock;
import com.example.android.spaceinvaders.mock.MainActivityMock;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.junit.Assert.assertTrue;

@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BotonesSteps {

    private MainActivityMock mainActivity;
    private GameActivityMock gameActivity;

    @Test
    public void test1_mostrar_menu_opciones() {
        this.mainActivity = new MainActivityMock();
        Given:
        assertTrue(this.mainActivity.isUserInMainActivity);
        When:
        this.mainActivity.clickOption();
        Then:
        assertTrue(this.mainActivity.isOptionButtonPressed);
    }

    @Test
    public void test2_iniciar_el_juego() {
        this.mainActivity = new MainActivityMock();
        this.gameActivity = new GameActivityMock();
        Given:
        assertTrue(this.mainActivity.isUserInMainActivity);
        When:
        this.mainActivity.clickPlay();
        Then:
        assertTrue(this.gameActivity.toString(), true);
    }

    @Test
    public void test3_desplazar_la_nave_hacia_la_izquierda() {
        this.gameActivity = new GameActivityMock();
        Given:
        assertTrue(this.gameActivity.isUserInGameActivity);
        When:
        assertTrue(this.gameActivity.pressRightButton());
        Then:
        assertTrue(this.gameActivity.moveSpaceToLeft(), true);
    }

    @Test
    public void test4_desplazar_la_nave_hacia_la_derecha() {
        this.gameActivity = new GameActivityMock();
        Given:
        assertTrue(this.gameActivity.isUserInGameActivity);
        When:
        assertTrue(this.gameActivity.pressLeftButton());
        Then:
        assertTrue(this.gameActivity.moveSpaceToRight(), true);
    }

    @Test
    public void test5_disparar_proyectil() {
        this.gameActivity = new GameActivityMock();
        Given:
        assertTrue(this.gameActivity.isUserInGameActivity);
        When:
        assertTrue(this.gameActivity.pressMiddleButton());
        Then:
        assertTrue(this.gameActivity.releaseAmmo(), true);
    }
}
