package sample;

import javafx.scene.image.Image;

public class Coin extends Actor {

    public Coin() {
        setImage(new Image("file:img/coin.png"));

    }

    @Override
    public void act(long now) {

        Actor barry = getOneIntersectingObject(Barry.class);

        if (barry != null) {
            setLocation(getX() - getWorld().getWidth() / 2, getY());
        }
        move(getDx(), getDy());

    }

}
