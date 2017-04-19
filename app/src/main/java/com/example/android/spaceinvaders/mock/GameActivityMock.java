package com.example.android.spaceinvaders.mock;

/**
 * Created by c.vazquezlos on 17/04/2017.
 */

public class GameActivityMock {

    public boolean isUserInGameActivity;
    public boolean isRightButtonClicked, isLeftButtonClicked, isMiddleButtonClicked;
    public int heigh, weight;
    public int[] space = {100, 50};
    public int[] ammo = {-1};

    public GameActivityMock() {
        this.heigh = 100;
        this.weight = 100;
        this.isUserInGameActivity = true;
        this.isRightButtonClicked = false;
        this.isLeftButtonClicked = false;
    }

    @Override
    public String toString() {
        return "Heigh: " + this.heigh + "\nWeight: " + this.weight + "\nShip position: " + this.toStringg();
    }

    public boolean pressRightButton() {
        this.isRightButtonClicked = true;
        return this.isRightButtonClicked;
    }

    public String moveSpaceToLeft() {
        this.space[1] = 45;
        return "Ship has been moved to " + this.toStringg() + " coordinates.";
    }

    public boolean pressLeftButton() {
        this.isLeftButtonClicked = true;
        return this.isLeftButtonClicked;
    }

    public String moveSpaceToRight() {
        this.space[1] = 55;
        return "Space has been moved to " + this.toStringg() + " coordinates.";
    }

    public boolean pressMiddleButton() {
        this.isMiddleButtonClicked = true;
        return this.isMiddleButtonClicked;
    }

    public String releaseAmmo() {
        this.ammo[0] = 100;
        return "Space is shooting ammo!.";
    }

    private String toStringg() {
        return this.space[0] + "," + this.space[1];
    }

}
