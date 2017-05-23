package sample;

import javafx.scene.image.Image;

public class Barry extends Actor {

    private boolean isFalling;
    private boolean endGame;
    private int score;
    private int numCoins;

    private String imag = "";

    private int dy;

    public Barry() {
        this.isFalling = true;
        endGame = false;
        score = 0;
        numCoins = 0;
        setDy(0);
    }

    public boolean getEndGame() {
        return endGame;
    }

    public void setFalling(boolean falling) {
        isFalling = falling;
    }
    public void setImag(String img) {
        imag = img;
    }

    public String getImag() {
        return imag;
    }

    public boolean getFalling() {
        return this.isFalling;
    }

    @Override
    public void act(long now) {

        Actor zapper = getOneIntersectingObject(Zapper.class);
        Actor missile = getOneIntersectingObject(Missile.class);
        Actor coin = getOneIntersectingObject(Coin.class);

        //score++;
        //System.out.println("Score: " + score);
        if (!endGame){
            score++;
        }
        //System.out.println("Score: " + score);

        Main.setScoreLabel(score);
        Main.setCoinsLabel(numCoins);

        if (zapper != null || missile != null) {
            endGame = true;
        }

        if (coin != null) {
            //getWorld().remove(coin);
            numCoins++;
            //System.out.println("Num Coins: " + numCoins);
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
