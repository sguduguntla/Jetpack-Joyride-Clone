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
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private double WORLD_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    private double WORLD_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    private Scientist scientist;
    private Missile missile;
    private Barry barry;
    private Zapper zapper;
    private CoinGroup group;

    private Timeline collisionTimeline;

    private Timeline boundaryCheckerTimeline;

    private Timeline zapperTimeline;

    private Timeline missileTimeline;

    private Timeline coinTimeline;
    private static Label scoreLabel;
    private static Label coinsLabel;

    @Override
    public void start(Stage stage) throws Exception {

        World world = new World() {
            @Override
            public void act(long now) {

            }
        };

        stage.setTitle("P1 Group 8 Game Engine");

        Scene scene = new Scene(world, WORLD_WIDTH, WORLD_HEIGHT);

        barry = new Barry();
        scoreLabel = new Label("Score: " + 0);
        scoreLabel.setAlignment(Pos.TOP_CENTER);
        scoreLabel.setFont(new Font("Helvetica", 50));
        scoreLabel.setTextFill(Color.WHITE);

        coinsLabel = new Label("Num Coins: " + 0);
        coinsLabel.setAlignment(Pos.TOP_RIGHT);
        coinsLabel.setFont(new Font("Helvetica", 50));
        coinsLabel.setTextFill(Color.WHITE);
        zapper = new Zapper();
        missile = new Missile();
        group = new CoinGroup();

        for (int i = 0; i < 60; i++) {
            group.add(new Coin());
        }

        barry.setImage(new Image("file:img/barry.png", 100, 100, true, true));
        zapper.setImage(new Image("file:img/zapper.png", 200, 200, true, true));
        missile.setImage(new Image("file:img/missile.png", 150, 150, true, true));

        barry.setLocation(WORLD_WIDTH / 5, WORLD_HEIGHT - barry.getHeight());
        zapper.setLocation(WORLD_WIDTH + 50, WORLD_HEIGHT + 50);
        missile.setLocation(WORLD_WIDTH + 50, WORLD_HEIGHT + 50);

        barry.setOnKeyPressed(new EventHandler<KeyEvent>() {

            double dy = barry.getDy();

            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.W) {
                    if (barry.getFalling() && barry.getDy() != 0) {
                        barry.setDy(barry.getDy() - 4);
                    }
                    //This is Andy! :)
                    barry.setFalling(false);
                    if (barry.getY() < 0) {
                        //dy = 0;
                    } else if (barry.getY() > WORLD_HEIGHT - barry.getHeight() * 2) {
                        //dy = 0;
                    } else {

                    }

                    if (barry.getDy() != dy && barry.getDy() != 0) {
                        dy = barry.getDy();
                    }

                    System.out.println(dy);

                    barry.setDy(dy);
                }
            }
        });


        barry.setOnKeyReleased(new EventHandler<KeyEvent>() {
            double dy = barry.getDy();

            @Override
            public void handle(KeyEvent event) {
                barry.setFalling(true);
            }
        });

        world.add(barry);
        world.add(zapper);
        world.add(missile);

        int numCoins = (int) (Math.random() * 50) + 10;

        for (int i = 0; i < numCoins; i++) {
            world.add(group.get(i));
        }

        world.getChildren().addAll(scoreLabel);
        world.getChildren().addAll(coinsLabel);

        collisionTimeline = new Timeline(new KeyFrame(
                Duration.millis(10),
                ae -> collisionCheck()));
        collisionTimeline.setCycleCount(Animation.INDEFINITE);
        collisionTimeline.play();

        boundaryCheckerTimeline = new Timeline(new KeyFrame(
                Duration.millis(10),
                ae -> boundarycheck()));
        boundaryCheckerTimeline.setCycleCount(Animation.INDEFINITE);
        boundaryCheckerTimeline.play();

        zapperTimeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                ae -> shootZapper()));
        zapperTimeline.setCycleCount(Animation.INDEFINITE);
        zapperTimeline.play();

        missileTimeline = new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> shootMissile()));
        missileTimeline.setCycleCount(Animation.INDEFINITE);
        missileTimeline.play();

        coinTimeline = new Timeline(new KeyFrame(
                Duration.millis(4000),
                ae -> addCoins()));
        coinTimeline.setCycleCount(Animation.INDEFINITE);
        coinTimeline.play();

        world.start();

        stage.setScene(scene);

        stage.show();
    }

    public void collisionCheck() {
        if (barry.getEndGame()) {
            barry.setDy(0);
            missile.setDx(0);
            //missile.setLocation(WORLD_WIDTH + 50, 0);
            zapper.setDx(0);
            for (int i = 0; i < group.getNumCoins(); i++) {
                group.get(i).setDx(0);
            }
            boundaryCheckerTimeline.stop();
            coinTimeline.stop();
            collisionTimeline.stop();
            zapperTimeline.stop();
            missileTimeline.stop();
        }
    }

    public void addCoins() {
        int randomNum = (int) (Math.random() * 60);
        int randomY = (int) (Math.random() * WORLD_HEIGHT);
        for (int i = 0; i < randomNum; i++) {
            if (group.get(i).getX() < 0) {

                group.get(i).setLocation(WORLD_WIDTH + ((group.get(i).getWidth() + 5) * i), randomY);
            }
            group.get(i).setDx(-6);

        }
    }

    public void boundarycheck() {
        if (barry.getY() > WORLD_HEIGHT - barry.getHeight() * 2) {
            barry.setDy(0);
            barry.setLocation(barry.getX(), WORLD_HEIGHT - barry.getHeight() * 2);
        }
        if (barry.getY() < 0) {
            barry.setLocation(barry.getX(), 1);
        }
    }

    public void shootMissile() {
        int randomY = (int) (Math.random() * WORLD_HEIGHT);
        if (missile.getX() < 0) {
            missile.setLocation(WORLD_WIDTH, randomY);
        }
        missile.setDx(-10);
    }

    public void shootZapper() {
        int randomY = (int) (Math.random() * WORLD_HEIGHT);
        if (zapper.getX() < 0) {
            zapper.setLocation(WORLD_WIDTH, randomY);
        }
        zapper.setDx(-10);
    }

    public static void setScoreLabel(int score) {
        scoreLabel.setText("Score: " + score);
    }

    public static void setCoinsLabel(int numCoins) {
        coinsLabel.setText("Num Coins: " + numCoins);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
