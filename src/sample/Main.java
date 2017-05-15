package sample;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.util.Duration;

public class Main extends Application {

    private double WORLD_WIDTH = Screen.getPrimary().getVisualBounds().getWidth();
    private double WORLD_HEIGHT = Screen.getPrimary().getVisualBounds().getHeight();

    Scientist scientist;
    Missile missile;
    Barry barry;
    Zapper zapper;
    CoinGroup group;
    Label scoreLabel;
    int score = 0;
//    int tempDy = 0;

    @Override
    public void start(Stage stage) throws Exception {

        World world = new World() {
            @Override
            public void act(long now) {

            }
        };

        stage.setTitle("P1 Group 8 Game Engine");

        Scene scene = new Scene(world, WORLD_WIDTH, WORLD_HEIGHT);

//        Rectangle2D primaryScreenBounds = Screen.getPrimary().getVisualBounds();
//
//        //set Stage boundaries to visible bounds of the main screen
//        stage.setX(primaryScreenBounds.getMinX());
//        stage.setY(primaryScreenBounds.getMinY());
//        stage.setWidth(primaryScreenBounds.getWidth());
//        stage.setHeight(primaryScreenBounds.getHeight());

//        WORLD_WIDTH = primaryScreenBounds.getWidth();
//        WORLD_HEIGHT = primaryScreenBounds.getHeight();

        scoreLabel = new Label("Score: " + score);
        barry = new Barry();
        zapper = new Zapper();
        missile = new Missile();
        group = new CoinGroup();
        for (int i = 0; i < 60; i++) {
            group.add(new Coin());
        }
        barry.setLocation(80, WORLD_HEIGHT - 82);
        zapper.setLocation(WORLD_WIDTH + 50, WORLD_HEIGHT + 50);
        missile.setLocation(WORLD_WIDTH + 50, WORLD_HEIGHT + 50);


        barry.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                if (e.getCode() == KeyCode.W) {
                    if (barry.getY() < 0){
                        barry.setDy(0);
                    } else if (barry.getY() > WORLD_HEIGHT - 82){
                        barry.setDy(0);
                    } else {
//                        barry.setDy(-(5 + tempDy));
//                        tempDy += 2;
                        barry.setDy(-7);
                    }
                    barry.setDx(0);
                }
            }
        });

        barry.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                barry.setDx(0);
                barry.setDy(7);
                //tempDy = 0;
            }
        });

        barry.setImage(new Image("file:img/barry.png"));
        zapper.setImage(new Image("file:img/zapper.png"));
        missile.setImage(new Image("file:img/missile.png"));

        world.add(barry);
        world.add(zapper);
        world.add(missile);
        for (int i = 0; i < 60; i++) {
            world.add(group.get(i));
        }

        Timeline boundaryCheckerTimeline = new Timeline(new KeyFrame(
                Duration.millis(10),
                ae -> boundarycheck()));
        boundaryCheckerTimeline.setCycleCount(Animation.INDEFINITE);
        boundaryCheckerTimeline.play();

        Timeline zapperTimeline = new Timeline(new KeyFrame(
                Duration.millis(2000),
                ae -> shootZapper()));
        zapperTimeline.setCycleCount(Animation.INDEFINITE);
        zapperTimeline.play();

        Timeline missileTimeline = new Timeline(new KeyFrame(
                Duration.millis(3000),
                ae -> shootMissile()));
        missileTimeline.setCycleCount(Animation.INDEFINITE);
        missileTimeline.play();

        Timeline coinTimeline = new Timeline(new KeyFrame(
                Duration.millis(4000),
                ae -> addCoins()));
        coinTimeline.setCycleCount(Animation.INDEFINITE);
        coinTimeline.play();

        world.start();

        stage.setScene(scene);

        stage.show();
    }

    public void addCoins(){
        int randomNum = (int)(Math.random() * 60);
        int randomY = (int) (Math.random() * WORLD_HEIGHT);
        for (int i = 0; i < randomNum; i++) {
            group.get(i).setLocation(WORLD_WIDTH + (30 * i), randomY);
            group.get(i).setDx(-12);
        }
    }

    public void boundarycheck(){
        if (barry.getY() > WORLD_HEIGHT - 82) {
            barry.setDy(0);
            barry.setLocation(barry.getX(), WORLD_HEIGHT - 82);
        }
        if (barry.getY() < 0) {
            //barry.setDy(7);
            barry.setLocation(barry.getX(), 1);
        }
    }

    public void shootMissile(){
        int randomY = (int) (Math.random() * WORLD_HEIGHT);
        missile.setLocation(WORLD_WIDTH, randomY);
        missile.setDx(-20);
    }

    public void shootZapper(){
        int randomY = (int) (Math.random() * WORLD_HEIGHT);
        zapper.setLocation(WORLD_WIDTH, randomY);
        zapper.setDx(-12);
    }

    public static void main(String[] args) {
        launch(args);
    }
}
