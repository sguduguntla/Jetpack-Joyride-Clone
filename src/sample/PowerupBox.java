package sample;

public class PowerupBox extends Actor {

    @Override
    public void act(long now) {
        move(getDx(), getDy());
    }
}
