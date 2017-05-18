package sample;

public class Barry extends Actor {

    private boolean isFalling;

    public Barry() {
        this.isFalling = true;
        setDy(0);

        //setDy(-4);
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

            if (getDy() < 10) {
                setDy(getDy() + 0.3);

            }

            //System.out.println(getDy());
        } else {
            if (getDy() > -10) {
                setDy(getDy() - 0.3);

            }
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
