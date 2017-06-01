package sample;

public class Barry extends Actor {

    private boolean isFalling;
    private boolean endGame;
    private int score;
    private int numCoins;
    private double powerUpDuration; //Milliseconds
    private String powerUp;
    private String imag = "";

    public Barry() {
        this.isFalling = true;
        endGame = false;
        score = 0;
        numCoins = 0;
        powerUp = "none";
        powerUpDuration = 0;
        setDy(0);
    }


    public boolean getEndGame() {
        return endGame;
    }

    public void setEndGame(boolean game) {
        endGame = game;
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

        if (!endGame) {
            score++;
        }

        Main.setScoreLabel(score);
        Main.setCoinsLabel(numCoins);

        if (!powerUp.equals("shield")) {
            if (powerUp.equals("stomper")) {
                if (zapper != null) {
                    zapper.setX(Main.WORLD_WIDTH + 50);
                    powerUp = "none";
                }

                if (missile != null) {
                    missile.setX(Main.WORLD_WIDTH + 50);
                    powerUp = "none";
                }


            } else {
                if (zapper != null || missile != null) {
                    endGame = true;
                }
            }

        }

        if (coin != null) {
            numCoins++;
        }

        if (isFalling) {
            if (getDy() < 10) {
                setDy(getDy() + 0.3);
            }
        } else {
            if (getDy() > -10) {
                setDy(getDy() - 0.3);

            }
        }

        move(getDx(), getDy());
    }

    public void setPowerUp(String powerUp) {
        this.powerUp = powerUp;
    }

    public String getPowerUp() {
        return powerUp;
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
