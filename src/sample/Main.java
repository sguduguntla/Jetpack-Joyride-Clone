package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    public static double WORLD_WIDTH;
    public static double WORLD_HEIGHT;

    private boolean missileShooting = false;
    private long missileLocateTiming = 0;
    private int missileShootCounter = 1;

    private Missile missile;

    private Barry barry;
    private Zapper zapper;
    private Zapper zapper2;
    private PowerupBox powerupBox;
    private Scientist scientist;
    private CoinGroup group;

    private Timeline collisionTimeline;
    private Timeline boundaryCheckerTimeline;
    private Timeline zapperTimeline;
    private Timeline zapper2Timeline;
    private Timeline missileTimeline;
    private Timeline missileLocationTimeline;
    private Timeline coinTimeline;
    private Timeline scientistTimeLine;
    private Timeline powerupTimeline;

    private static Label scoreLabel;
    private static Label coinsLabel;

    private World world;

    private HBox scoreBox;

    String barryImg = "";

    @Override
    public void start(Stage stage) throws Exception {

        world = new World() {
            @Override
            public void act(long now) {

            }
        };

        WORLD_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
        WORLD_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

        stage.setTitle("P1 Group 8 Game Engine");

        Scene scene = new Scene(world, WORLD_WIDTH, WORLD_HEIGHT);

        scoreBox = new HBox(100);

        barry = new Barry();
        scoreLabel = new Label("Score: " + 0);
        scoreLabel.setAlignment(Pos.TOP_LEFT);
        scoreLabel.setFont(new Font("Helvetica", 50));
        scoreLabel.setTextFill(Color.WHITE);

        coinsLabel = new Label("Coins: " + 0);
        coinsLabel.setAlignment(Pos.TOP_RIGHT);
        coinsLabel.setFont(new Font("Helvetica", 50));
        coinsLabel.setTextFill(Color.WHITE);

        scoreBox.getChildren().addAll(scoreLabel, coinsLabel);
        zapper = new Zapper();
        zapper2 = new Zapper();
        powerupBox = new PowerupBox();
        missile = new Missile();
        group = new CoinGroup();
        scientist = new Scientist();

        Background background = new Background();
        Background background2 = new Background();

        background.setX(0);
        background2.setX(WORLD_WIDTH);

        background.setDx(-10);
        background2.setDx(-10);

        for (int i = 0; i < 60; i++) {
            group.add(new Coin());
        }

        barry.setImag("barry");
        barry.setImage(new Image("file:img/" + barry.getImag() + ".png"));
        powerupBox.setImage(new Image("file:img/powerup_box.png", WORLD_WIDTH / 8, WORLD_HEIGHT / 10, true, true, true));
        zapper.setImage(new Image("file:img/zapper.png", WORLD_WIDTH / 4, WORLD_HEIGHT / 4, true, true, true));
        zapper2.setImage(new Image("file:img/zapper_diagonal.png", WORLD_WIDTH / 4, WORLD_HEIGHT / 4, true, true, true));
        missile.setImage(new Image("file:img/missile_left.png", WORLD_WIDTH / 10, WORLD_HEIGHT / 20, true, true, true));

        barry.setLocation(WORLD_WIDTH / 5, WORLD_HEIGHT - barry.getHeight() * 2);
        zapper.setLocation(WORLD_WIDTH + 50, WORLD_HEIGHT + 50);
        zapper2.setLocation(WORLD_WIDTH + 50, WORLD_HEIGHT + 50);
        powerupBox.setLocation(WORLD_WIDTH + 50, WORLD_HEIGHT + 50);
        missile.setLocation(WORLD_WIDTH + 50, WORLD_HEIGHT + 50);
        scientist.setLocation(WORLD_WIDTH + 50, WORLD_HEIGHT - scientist.getHeight() * 2);

        barry.setOnKeyPressed(new EventHandler<KeyEvent>() {

            double dy = barry.getDy();

            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.SPACE) {
                    if (barry.getPowerUp().equals("shield")) {
                        barry.setImag("barry_rising_shield");
                    } else {
                        barry.setImag("barry_rising");
                    }
                    barry.setImage(new Image("file:img/" + barry.getImag() + ".png"));

                    if (barry.getFalling() && barry.getDy() != 0) {
                        barry.setDy(barry.getDy() - 4);
                    }

                    barry.setFalling(false);

                    if (barry.getDy() != dy && barry.getDy() != 0) {
                        dy = barry.getDy();
                    }

                    barry.setDy(dy);
                }
            }
        });

        barry.setOnKeyReleased(new EventHandler<KeyEvent>() {
            double dy = barry.getDy();

            @Override
            public void handle(KeyEvent event) {
                barry.setFalling(true);

                if (barry.getPowerUp().equals("shield")) {
                    barry.setImag("barry_falling_shield");
                } else {
                    barry.setImag("barry_falling");
                }

                barry.setImage(new Image("file:img/" + barry.getImag() + ".png"));
            }
        });

        world.add(background);
        world.add(background2);
        world.add(barry);
        world.add(zapper);
        world.add(zapper2);
        world.add(missile);
        world.add(powerupBox);
        world.add(scientist);

        int numCoins = (int) (Math.random() * 50) + 10;

        for (int i = 0; i < numCoins; i++) {
            world.add(group.get(i));
        }

        world.getChildren().add(scoreBox);

        collisionTimeline = new Timeline(new KeyFrame(
                Duration.millis(10),
                ae -> collisionCheck()));
        collisionTimeline.setCycleCount(Animation.INDEFINITE);
        collisionTimeline.play();

        boundaryCheckerTimeline = new Timeline(new KeyFrame(
                Duration.millis(110),
                ae -> boundarycheck()));
        boundaryCheckerTimeline.setCycleCount(Animation.INDEFINITE);
        boundaryCheckerTimeline.play();

        zapperTimeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                ae -> shootZapper()));
        zapperTimeline.setCycleCount(Animation.INDEFINITE);
        zapperTimeline.play();

        zapper2Timeline = new Timeline(new KeyFrame(
                Duration.millis(3500),
                ae -> shootZapper2()));
        zapper2Timeline.setCycleCount(Animation.INDEFINITE);
        zapper2Timeline.play();

        powerupTimeline = new Timeline(new KeyFrame(
                Duration.millis(4200),
                ae -> spawnPowerupBox()));
        powerupTimeline.setCycleCount(Animation.INDEFINITE);
        powerupTimeline.play();

        missileTimeline = new Timeline(new KeyFrame(
                Duration.millis(7000),
                ae -> shootMissile()));
        missileTimeline.setCycleCount(Animation.INDEFINITE);
        missileTimeline.play();

        missileLocationTimeline = new Timeline(new KeyFrame(
                Duration.millis(10),
                ae -> locateMissile()));
        missileLocationTimeline.setCycleCount(Animation.INDEFINITE);
        missileLocationTimeline.play();

        coinTimeline = new Timeline(new KeyFrame(
                Duration.millis(4000),
                ae -> addCoins()));
        coinTimeline.setCycleCount(Animation.INDEFINITE);
        coinTimeline.play();

        scientistTimeLine = new Timeline(new KeyFrame(Duration.millis(4000), ae -> addScientist()));
        scientistTimeLine.setCycleCount(Animation.INDEFINITE);
        scientistTimeLine.play();

        world.start();

        stage.setScene(scene);

        stage.show();
    }

    public void spawnPowerupBox() {
        if (barry.getPowerUp().equals("none")) {
            int randomY = (int) ((Math.random() * 300) + 100);

            if (powerupBox.getX() < 0) {
                powerupBox.setLocation(WORLD_WIDTH, randomY);
            }

            powerupBox.setDx(-10);
        } else {
            powerupBox.setX(WORLD_WIDTH + 50);
        }

    }


    public void collisionCheck() {
        if (barry.getEndGame()) {
            barry.setDy(0);
            missile.setDx(0);
            zapper.setDx(0);
            for (int i = 0; i < group.getNumCoins(); i++) {
                group.get(i).setDx(0);
            }
            boundaryCheckerTimeline.stop();
            coinTimeline.stop();
            scientistTimeLine.stop();
            collisionTimeline.stop();
            zapperTimeline.stop();
            zapper2Timeline.stop();
            missileTimeline.stop();
            powerupTimeline.stop();
        } else {
            PowerupBox intersectingPowerupBox = barry.getOneIntersectingObject(PowerupBox.class);

            if (intersectingPowerupBox != null) {

                intersectingPowerupBox.setX(WORLD_WIDTH + 50);
                barry.setPowerUp("shield");

                Label shieldLabel = new Label("Shield Activated");
                shieldLabel.setAlignment(Pos.TOP_CENTER);
                shieldLabel.setFont(new Font("Helvetica", 50));
                shieldLabel.setTextFill(Color.WHITE);

                scoreBox.getChildren().add(shieldLabel);

            }
        }

        if (barry.getPowerUp().equals("shield")) {
            powerupBox.setX(WORLD_WIDTH + 50);
        }
    }

    public void addCoins() {
        int randomNum = (int) (Math.random() * 60);
        int randomY = (int) (Math.random() * WORLD_HEIGHT);
        for (int i = 0; i < randomNum; i++) {
            if (group.get(i).getX() < 0) {
                group.get(i).setLocation(WORLD_WIDTH + ((group.get(i).getWidth() + 5) * i), randomY);
            }
            group.get(i).setDx(-10);

        }
    }

    public void addScientist() {

        if (scientist.getX() < 0) {
            scientist.setLocation(WORLD_WIDTH, WORLD_HEIGHT - scientist.getHeight() * 2);
        }
        scientist.setDx(-10);

    }

    public void boundarycheck() {
        if (barry.getY() > WORLD_HEIGHT - barry.getHeight() * 2) {
            barry.setDy(0);
            barry.setImage(new Image("file:img/barry.png"));
            barry.setImag("barry");
            if (barry.getPowerUp().equals("shield")) {
                if (barryImg.equals("barry") || barryImg.equals("barry_shield")) {
                    barry.setImag("barry_shield");
                    barryImg = "barry_shield2";
                } else {
                    barry.setImag("barry2_shield");
                    barryImg = "barry_shield";
                }

                barry.setImage(new Image("file:img/" + barry.getImag() + ".png"));
            } else {
                if (barryImg.equals("barry")) {
                    barryImg = "barry2";
                } else {
                    barryImg = "barry";
                }

                barry.setImag(barryImg);
                barry.setImage(new Image("file:img/" + barry.getImag() + ".png"));

            }
            barry.setLocation(barry.getX(), WORLD_HEIGHT - barry.getHeight() * 2);


        }
        if (barry.getY() <= 1) {

            barry.setY(1);

        }

        if (scientist.getImag().equals("scientist-moving")) {
            scientist.setImag("scientist-stationary");
        } else if (scientist.getImag().equals("scientist-stationary")) {
            scientist.setImag("scientist-stationary2");
        } else {
            scientist.setImag("scientist-moving");
        }

        scientist.setImage(new Image("file:img/" + scientist.getImag() + ".png"));

    }

    public void locateMissile() {
        missileLocateTiming += 10;

        if (missile.getX() < 0) {
            missileShooting = false;
            missile.setImage(new Image("file:img/missile_target.png"));
            missile.setLocation(WORLD_WIDTH - missile.getImage().getWidth(), barry.getY());
            missile.setDx(0);
        }

        if (!missileShooting) {
            if ((missileLocateTiming / missileShootCounter - 600) >= 6000 + 100 * (missileShootCounter - 1)) {
                missile.setImage(new Image("file:img/missile_coming.png"));
                missile.setLocation(WORLD_WIDTH - missile.getImage().getWidth(), barry.getY());
                missile.setDx(0);
            } else {
                missile.setImage(new Image("file:img/missile_target.png"));
                missile.setLocation(WORLD_WIDTH - missile.getImage().getWidth(), barry.getY());
                missile.setDx(0);
            }
        }
    }

    public void shootMissile() {
        missile.setDy(0);
        missile.setRotate(0);
        missileShooting = true;
        missileShootCounter++;
        missile.setImage(new Image("file:img/missile_left.png", WORLD_WIDTH / 5, WORLD_HEIGHT / 14, true, true, true));
        missile.setLocation(WORLD_WIDTH - missile.getImage().getWidth(), barry.getY());
        missile.setDx(-25);
    }

    public void shootZapper() {
        int randomY = (int) (Math.random() * WORLD_HEIGHT);
        if (zapper.getX() < 0) {
            zapper.setLocation(WORLD_WIDTH, randomY);
        }
        zapper.setDx(-10);
    }

    public void shootZapper2() {
        int randomY = (int) (Math.random() * WORLD_HEIGHT);
        if (zapper2.getX() < 0) {
            zapper2.setLocation(WORLD_WIDTH, randomY);
        }
        zapper2.setDx(-10);
    }

    public static void setScoreLabel(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public static void setCoinsLabel(int numCoins) {
        coinsLabel.setText("Coins: " + numCoins);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
