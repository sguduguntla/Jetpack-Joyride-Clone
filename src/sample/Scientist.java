package sample;

import javafx.scene.image.Image;

public class Scientist extends Actor {

    int dx = -3;
    private int xPos;
    private int yPos;

    public Scientist() {
        setImage(new Image("file:img/scientist.png"));
        xPos = 2000;
        yPos = 2000;
        dx = 0;
    }

    public Scientist(int xPos, int yPos) {
        this.xPos = xPos;
        this.yPos = yPos;
    }

    @Override
    public void act(long now) {
        Actor barry = getOneIntersectingObject(Barry.class);

        if (barry != null){
            setLocation(getX() - getWorld().getWidth() / 2, getY());
        }
        move(dx, 0);
    }

    public void setDx(int d) {
        dx = d;
    }
}
