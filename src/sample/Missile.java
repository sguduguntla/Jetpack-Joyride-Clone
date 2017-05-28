package sample;

import javafx.scene.image.Image;

public class Missile extends Actor {

    private int extendedCounter = 0;

    @Override
    public void act(long now) {

        move(getDx(), getDy());

        Barry barry = getOneIntersectingObject(Barry.class);

        if (barry != null) {
            if (barry.getPowerUp().equals("shield")) {
                extendedCounter++;
                setImage(new Image("file:img/missile_right.png", getWorld().getWidth() / 5, getWorld().getHeight() / 14, true, true, true));
                setDx(getDx() * -1);
                int randomDy = -10 + (int) (Math.random() * 20) + 1;
                setDy(randomDy);
                setRotate(randomDy);
            }
        }
    }

    public int getCounter(){
        int count = extendedCounter;
        extendedCounter = 0;
        return count;

    }

    public void resetCounter(){
        extendedCounter = 0;
    }

}
