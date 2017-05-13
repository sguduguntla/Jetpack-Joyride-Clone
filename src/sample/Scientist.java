package sample;

/**
 * Created by sguduguntla on 5/12/17.
 */
public class Scientist extends Actor {

    int dx = -3;

    @Override
    public void act(long now) {
        move(dx, 0);
    }
}
