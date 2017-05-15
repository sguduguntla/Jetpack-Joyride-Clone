package sample;

public class Barry extends Actor {

    @Override
    public void act(long now) {
        move(getDx(), getDy());
    }

    public double getXPos(){
        return getX();
    }

    public double getYPos(){
        return getY();
    }
}
