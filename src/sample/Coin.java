package sample;

import javafx.scene.image.Image;

public class Coin extends Actor {

    private int dx = -12;
    private int xPos;
    private int yPos;

    public Coin(){
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
