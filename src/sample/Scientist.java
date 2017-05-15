package sample;

public class Scientist extends Actor {

    int dx = -3;

    @Override
    public void act(long now) {
        move(dx, 0);
    }
}
