package sample;

public class Barry extends Actor {

    private boolean isFalling;
    private boolean endGame;

    public Barry() {
        this.isFalling = false;
        endGame = false;
        setDy(-0.3);
    }

    public boolean getEndGame() {
        return endGame;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }

    public boolean getFalling() {
        return this.isFalling;
    }

    @Override
    public void act(long now) {
        Actor zapper = getOneIntersectingObject(Zapper.class);
        Actor missile = getOneIntersectingObject(Missile.class);
        Actor coin = getOneIntersectingObject(Coin.class);

        if (zapper != null || missile != null) {
            endGame = true;
        }


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

    public double getXPos() {
        return getX();
    }

    public double getYPos() {
        return getY();
    }
}
