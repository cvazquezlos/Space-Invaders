package com.example.android.spaceinvaders.mock;

/**
 * Created by c.vazquezlos on 17/04/2017.
 */

public class MainActivityMock {

    public boolean isUserInApp;
    public boolean isUserInMainActivity;
    public boolean isOptionButtonPressed;
    public boolean isPlayButtonPressed;
    public String[] optionMenu;

    public MainActivityMock() {
        this.isUserInApp = true;
        this.isUserInMainActivity = true;
        this.isOptionButtonPressed = false;
        this.isPlayButtonPressed = false;
    }

    public String clickOption() {
        this.isOptionButtonPressed = true;
        return "Option button successfully clicked\n";
    }
    public String clickPlay() {
        this.isPlayButtonPressed = true;
        return "Play button successfully clicked\n";
    }

}
