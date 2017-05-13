package sample;

/**
 * Created by sguduguntla on 5/11/17.
 */
public class Missile extends Actor {

    int dx = -7;

    @Override
    public void act(long now) {
        move(dx, 0);
    }
}
