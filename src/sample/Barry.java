package sample;

public class Barry extends Actor {

    private boolean isFalling;
    private boolean endGame;

    public Barry() {
        this.isFalling = false;
        endGame = false;
        setDy(-4);
    }

    public boolean getEndGame(){
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
        if (zapper != null || missile != null){
            endGame = true;
        }

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
