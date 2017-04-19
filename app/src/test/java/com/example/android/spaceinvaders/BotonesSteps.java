package com.example.android.spaceinvaders;

import com.example.android.spaceinvaders.mock.GameActivityMock;
import com.example.android.spaceinvaders.mock.MainActivityMock;

import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

/**
 * Created by c.vazquezlos on 19/04/2017.
 */

public class BotonesSteps {

    private MainActivityMock mainActivity;
    private GameActivityMock gameActivity;

    @Before
    public void before_test() {
        this.mainActivity = new MainActivityMock();
        this.gameActivity = new GameActivityMock();
    }

    @Given("^la pantalla principal de la aplicación$")
    public void la_pantalla_principal_de_la_aplicación() {
        Boolean valor = this.mainActivity.isUserInMainActivity;
        System.out.println(valor+"\n");
    }

    @When("^pincho sobre el botón de opciones$")
    public void pincho_sobre_el_botón_de_opciones() throws Throwable {
        String valor = this.mainActivity.clickOption();
        System.out.println(valor);
    }

    @Then("^se abre el menú de opciones$")
    public void se_abre_el_menú_de_opciones() throws Throwable {
        String valor = this.mainActivity.toString();
        System.out.println(valor+"\n\n");
    }

    @When("^pincho sobre el botón de jugar$")
    public void pincho_sobre_el_botón_de_jugar() throws Throwable {
        String valor = this.mainActivity.clickPlay();
        System.out.println(valor);
    }

    @Then("^se cambia de pantalla a la pantalla de juego$")
    public void se_cambia_de_pantalla_a_la_pantalla_de_juego() throws Throwable {
        Boolean valor = this.gameActivity.isUserInGameActivity;
        System.out.println(valor+"\n\n");
    }

    @Given("^me encuentro en la pantalla de juego$")
    public void me_encuentro_en_la_pantalla_de_juego() throws Throwable {
        String valor = this.gameActivity.toString();
        System.out.println(valor);
    }

    @When("^pulso en el botón de la derecha de la pantalla$")
    public void pulso_en_el_botón_de_la_derecha_de_la_pantalla() throws Throwable {
        Boolean valor = this.gameActivity.pressRightButton();
        System.out.println(valor);
    }

    @Then("^la nave se desplaza hacia la izquierda en 5 unidades$")
    public void la_nave_se_desplaza_hacia_la_izquierda_en_unidades() throws Throwable {
        String valor = this.gameActivity.moveSpaceToLeft();
        System.out.println(valor+"\n\n");
    }

    @When("^pulso en el botón de la izquierda de la pantalla$")
    public void pulso_en_el_botón_de_la_izquierda_de_la_pantalla() throws Throwable {
        Boolean valor = this.gameActivity.pressLeftButton();
        System.out.println(valor);
    }

    @Then("^la nave se desplaza hacia la derecha en 5 unidades$")
    public void la_nave_se_desplaza_hacia_la_derecha_en_unidades() throws Throwable {
        String valor = this.gameActivity.moveSpaceToRight();
        System.out.println(valor+"\n\n");
    }

    @When("^pulso en el botón del centro de la pantalla$")
    public void pulso_en_el_botón_del_centro_de_la_pantalla() throws Throwable {
        Boolean valor = this.gameActivity.pressMiddleButton();
        System.out.println(valor);
    }

    @Then("^la nave dispara un proyectil que se desplaza verticalmente$")
    public void la_nave_dispara_un_proyectil_que_se_desplaza_verticalmente() throws Throwable {
        String valor = this.gameActivity.releaseAmmo();
        System.out.println(valor+"\n\n");
    }
}