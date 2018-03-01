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

    @Given("^principal screen in app$")
    public void principal_screen_in_app() {
        Boolean valor = this.mainActivity.isUserInMainActivity;
        System.out.println(valor+"\n");
    }

    @When("^i tap on the option menu$")
    public void i_tap_on_the_option_menu() {
        String valor = this.mainActivity.clickOption();
        System.out.println(valor);
    }

    @Then("^option menu opens$")
    public void option_menu_opens() {
        String valor = this.mainActivity.toString();
        System.out.println(valor+"\n\n");
    }

    @When("^i tap the game button$")
    public void i_tap_the_game_button() {
        String valor = this.mainActivity.clickPlay();
        System.out.println(valor);
    }

    @Then("^appears the game screen$")
    public void appears_the_game_screen() {
        Boolean valor = this.gameActivity.isUserInGameActivity;
        System.out.println(valor+"\n\n");
    }

    @Given("^gameplay screen$")
    public void gameplay_screen() {
        String valor = this.gameActivity.toString();
        System.out.println(valor);
    }

    @When("^i tap the right button in the screen$")
    public void i_tap_the_right_button_in_the_screen() {
        Boolean valor = this.gameActivity.pressRightButton();
        System.out.println(valor);
    }

    @Then("^the spaceship moves to the left 5 units$")
    public void the_spaceship_moves_to_the_left_5_units() {
        String valor = this.gameActivity.moveSpaceToLeft();
        System.out.println(valor+"\n\n");
    }

    @When("^i tap the left button in the screen$")
    public void i_tap_the_left_button_in_the_screen() {
        Boolean valor = this.gameActivity.pressLeftButton();
        System.out.println(valor);
    }

    @Then("^the spaceship moves to the right 5 units$")
    public void the_spaceship_moves_to_the_right_5_units() {
        String valor = this.gameActivity.moveSpaceToRight();
        System.out.println(valor+"\n\n");
    }

    @When("^i tap the button in the middle of the screen$")
    public void i_tap_the_button_in_the_middle_of_the_screen() {
        Boolean valor = this.gameActivity.pressMiddleButton();
        System.out.println(valor);
    }

    @Then("^the spaceship shoots a missile that moves vertically in the screen$")
    public void the_spaceship_shoots_a_missile_that_moves_vertically_in_the_screen() {
        String valor = this.gameActivity.releaseAmmo();
        System.out.println(valor+"\n\n");
    }
}