package sample;

import javafx.scene.image.Image;

/**
 * Created by sguduguntla on 5/11/17.
 */
public class Zapper extends Actor {

    int dx = 0;
    @Override
    public void act(long now) {
        move(dx, 0);
    }

    public void setLocation(int x, int y){
        setX(x);
        setY(y);
    }

    public void setDx(int d){
        dx = d;
    }
}
