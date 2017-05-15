package sample;

public class DashForward extends Actor implements Powerup {

    private int distance;

    public DashForward(int distance) {
        this.distance = distance;
    }

    @Override
    public void act(long now) {

    }

    public void setDistance(int distance) {
        this.distance = distance;
    }

    public int getDistance() {
        return distance;
    }

    @Override
    public boolean isActivated() {
        return false;
    }

    @Override
    public void activate() {

    }

    @Override
    public double getRemainingTime() {
        return 0;
    }

    @Override
    public double getDuration() {
        return 0;
    }

    @Override
    public void setDuration() {

    }

    @Override
    public void deactivate() {

    }
}
