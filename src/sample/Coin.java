package sample;

import javafx.scene.image.Image;

public class Coin extends Actor {

    private int dx = -10;
    private int xPos;
    private int yPos;

    public Coin(){
//        setImage(new Image("file:img/coin.png", 40, 40, true, true));
        setImage(new Image("file:img/coin.png"));
        xPos = 2000;
        yPos = 2000;
        dx = 0;
    }

    public Coin(int x, int y){
        xPos = x;
        yPos = y;
    }

    @Override
    public void act(long now) {

        Actor barry = getOneIntersectingObject(Barry.class);

        if (barry != null){
            setLocation(getWorld().getWidth() + 50, getWorld().getHeight() / 2);
        }
        move(dx, 0);
    }

    public void setLocation(int x, int y){
        setX(x);
        setY(y);
    }

    public void setLocation(){
        setX(xPos);
        setY(yPos);
    }

    public void setDx(int d){
        dx = d;
    }
}
