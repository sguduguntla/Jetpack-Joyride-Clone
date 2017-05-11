/**
 * Name: Sriharsha Guduguntla (Sai)
 * Date: 04/02/17
 * Period: 1
 * Time Taken: 2 hours
 * Reflection: This lab seemed quite hard at first because I had no idea how I was
 * going to satisfy all the different instructions on the slides, however,
 * I was able to interpret the details by the end and figure out most of it.
 * Along the way, I was still confused about exactly how to the world and actor
 * classes would interact with each other because I was getting a lot of
 * null pointer exceptions as well as casting exceptions. As a result, I wasn't
 * able to get the keyboard presses and the collision detection working
 * completely. I believe that one of the issues could be that I am not adding
 * all the actors to the right worlds, but I am not completely sure. I hope
 * to get some help tomorrow, and resubmit the lab soon. Overall, it was an
 * interesting lab!
 */
package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class P1_Guduguntla_Sriharsha_Main extends Application {

    private static final double WORLD_WIDTH = 600;
    private static final double WORLD_HEIGHT = 600;

    @Override
    public void start(Stage stage) throws Exception{

        Pane root = new Pane();

        P1_Guduguntla_Sriharsha_World world = new P1_Guduguntla_Sriharsha_World() {
            @Override
            public void act(long now) {

            }
        };

        P1_Guduguntla_Sriharsha_Actor flappyBird = new P1_Guduguntla_Sriharsha_Actor() {

            int dx = 3;
            int dy = 0;

            @Override
            public void act(long now) {

                if (getX() >= WORLD_WIDTH) {
                    setX(getWidth() * -2);
                }

                move(dx, dy);

            }


        };

        flappyBird.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {

                if (e.getCode() == KeyCode.UP) {
                    System.out.println("WOW");
                }

                System.out.println("Hi");

                e.consume();

            }

        });


        P1_Guduguntla_Sriharsha_Actor pole = new P1_Guduguntla_Sriharsha_Actor() {
            @Override
            public void act(long now) {
                int dx = -1;
                int dy = 0;

                move(dx, dy);

                /*if (getOneIntersectingObject(this.getClass()) != null && getOneIntersectingObject(this.getClass()).equals(flappyBird.getClass())) {
                    System.out.println("HIIII");
                }*/

            }
        };


        flappyBird.setImage(new Image("file:flappybird.png"));
        pole.setImage(new Image("file:pole.png"));

        world.add(flappyBird);
        world.add(pole);

        world.start();

        root.getChildren().addAll(flappyBird, pole);

        stage.setTitle("Hello World");

        Scene scene = new Scene(root, WORLD_WIDTH, WORLD_HEIGHT);

        stage.setScene(scene);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
