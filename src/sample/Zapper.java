package sample;

/**
 * Created by sguduguntla on 5/11/17.
 */
public class Zapper extends Actor {

    int dx = -5;
    @Override
    public void act(long now) {
        move(dx, 0);
    }
}
