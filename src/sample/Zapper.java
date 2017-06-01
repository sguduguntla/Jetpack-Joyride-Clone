package sample;

public class Zapper extends Actor {

    @Override
    public void act(long now) {

        move(getDx(), getDy());

    }



}
