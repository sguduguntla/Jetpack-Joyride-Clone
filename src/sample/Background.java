package sample;

/**
 * Created by sguduguntla on 5/18/17.
 */
public class Background extends Actor {
    @Override
    public void act(long now) {

        move(getDx(), getDy());
        edgeLoop();
    }

    private void edgeLoop() {

        double worldWidth = getWorld().getWidth();

        System.out.println("X: " + getX());

        if (getX() <= -getWidth()) {
            setX(worldWidth);
        }

    }

}
