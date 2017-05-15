package sample;

public class Barry extends Actor {

    private boolean isFalling;

    public Barry() {
        this.isFalling = false;

        setDy(-4);
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    public boolean getFalling() {
        return this.isFalling;
    }

    @Override
    public void act(long now) {

        if (isFalling) {
            setDy(Math.abs(getDy()) * 1.05);
        } else {
            setDy(Math.abs(getDy()) * -1.05);
        }

        move(getDx(), getDy());
    }

    public double getXPos(){
        return getX();
    }

    public double getYPos(){
        return getY();
    }
}
