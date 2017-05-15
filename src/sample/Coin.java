package sample;

import javafx.scene.image.Image;

public class Coin extends Actor {

    private int dx = -12;
    private int xPos;
    private int yPos;

    public Coin(){
        setImage(new Image("file:img/coin.png"));
        xPos = -20;
        yPos = -20;
    }

    public Coin(int x, int y){
        xPos = x;
        yPos = y;
    }

    @Override
    public void act(long now) {

    }

    public void setLocation(){
        setX(xPos);
        setY(yPos);
    }

    public void setDx(int d){
        dx = d;
    }
}
