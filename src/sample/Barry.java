package sample;

/**
 * Created by sguduguntla on 5/11/17.
 */
public class Barry extends Actor {

    @Override
    public void act(long now) {
        move(getDx(), getDy());
    }
}
