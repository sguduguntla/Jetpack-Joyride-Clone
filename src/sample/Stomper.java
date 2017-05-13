package sample;

import javafx.scene.image.Image;

/**
 * Created by sguduguntla on 5/11/17.
 */
public class Stomper extends Actor implements Powerup {

    @Override
    public void act(long now) {
        move(getDx(), getDy());
    }

    @Override
    public boolean isActivated() {
        return false;
    }

    @Override
    public void activate() {

    }

    @Override
    public double getRemainingTime() {
        return 0;
    }

    @Override
    public double getDuration() {
        return 0;
    }

    @Override
    public void setDuration() {

    }

    @Override
    public void deactivate() {

    }
}
