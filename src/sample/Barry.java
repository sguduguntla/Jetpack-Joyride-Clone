package sample;

public class Barry extends Actor {

    private boolean isFalling;
    private boolean endGame;
    private int score;
    private int numCoins;

    public Barry() {
        this.isFalling = false;
        endGame = false;
        score = 0;
        numCoins = 0;
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

        score++;
        //System.out.println("Score: " + score);

        Main.setScoreLabel(score);

        if (zapper != null || missile != null) {
            endGame = true;
        }

        if (coin != null) {
            getWorld().remove(coin);
            numCoins++;
            System.out.println("Num Coins: " + numCoins);
            //Main.setCoinsLabel(numCoins);
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

    public void setNumCoins(int numCoins) {
        this.numCoins = numCoins;
    }

    public int getNumCoins() {
        return numCoins;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public int getScore() {
        return score;
    }

    public double getXPos() {
        return getX();
    }

    public double getYPos() {
        return getY();
    }
}
