package sample;

import javafx.scene.image.Image;

/**
 * Created by sguduguntla on 5/18/17.
 */
public class Background extends Actor {

    private double worldWidth;
    private double worldHeight;

    public Background(double worldWidth, double worldHeight) {
        this.worldWidth = worldWidth;
        this.worldHeight = worldHeight;

        setImage(new Image("file:img/background.png", worldWidth, worldHeight, false, true, true));

    }

    @Override
    public void act(long now) {

        move(getDx(), getDy());
        edgeLoop();

    }

    private void edgeLoop() {

        double worldWidth = getWorld().getWidth();

        System.out.println("X: " + getX());
        System.out.println("WIDTH: " + 1440);

        if (getX() <= -1440) {
            setX(worldWidth);
        }

    }

}
