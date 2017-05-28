package sample;

/**
 * Created by sguduguntla on 5/27/17.
 */
public class PowerupBox extends Actor {

    private String powerup;

    public PowerupBox() {
        powerup = "none";

    }

    @Override
    public void act(long now) {
        move(getDx(), getDy());
    }
}
