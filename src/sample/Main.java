package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

public class Main extends Application {

    private static final double WORLD_WIDTH = 600;
    private static final double WORLD_HEIGHT = 600;

    private World root = new World() {
        @Override
        public void act(long now) {
        }
    };

    @Override
    public void start(Stage stage) {
        stage.setTitle("Jetpack Joyride");
        Scene scene = new Scene(root, 1000, 700);
        stage.setScene(scene);

//        for (int i = 0; i < scene.getHeight(); i += 30) {
//            for (int j = 0; j < scene.getWidth(); j +=30) {
//                Pellet p = new Pellet(j, i);
//                root.add(p);
//            }
//        }
//
//        Ghost p = new Ghost(scene.getWidth() / 2, scene.getHeight() / 2);
//        Ghost d = new Ghost(scene.getWidth() / 4, scene.getHeight() / 4);
//        Ghost f = new Ghost(scene.getWidth() / 3, scene.getHeight() / 3);
//        Ghost l = new Ghost(scene.getWidth() / 5, scene.getHeight() / 5);
//        Ghost m = new Ghost(scene.getWidth() / 5, scene.getHeight() / 2);
//        Ghost q = new Ghost(scene.getWidth() / 2, scene.getHeight() / 7);
//        Ghost w = new Ghost(scene.getWidth() / 6, scene.getHeight() / 4);
//        Ghost r = new Ghost(scene.getWidth() / 2, scene.getHeight() / 8);
//        root.add(q);
//        root.add(w);
//        root.add(r);
//        root.add(m);
//        root.add(p);
//        root.add(d);
//        root.add(f);
//        root.add(l);

//        root.setOnMouseClicked(new EventHandler<MouseEvent>() {
//            @Override
//            public void handle(MouseEvent event) {
//                Pellet p = new Pellet(event.getX(), event.getY());
//                root.add(p);
//                System.out.println("X: " + event.getX() + ", Y: " + event.getY());
//            }
//        });

        Actor barry = new Actor(new Image("file:img/barry.png")) {
            @Override
            public void act(long now) {
            }
        };

        barry.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                if (event.getText().equals("w")) barry.move(0, -10);
                else if (event.getText().equals("a")) barry.move(-10, 0);
                else if (event.getText().equals("s")) barry.move(0, 10);
                else if (event.getText().equals("d")) barry.move(10, 0);
            }
        });
        root.add(barry);

        root.start();

        stage.show();
    }

//    @Override
//    public void start(Stage stage) throws Exception{
//
//        World world = new World() {
//            @Override
//            public void act(long now) {
//
//            }
//        };
//
//        Barry barry = new Barry();
//
//        barry.setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent e) {
//                System.out.println("HIIII");
//
//                if (e.getCode() == KeyCode.RIGHT) {
//                    barry.move(3, 0);
//
//                }
//
//            }
//        });
//
//        barry.setOnKeyReleased(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//                System.out.println("WOWOWWOW");
//            }
//        });
//
//        barry.setImage(new Image("file:img/barry.png"));
//
//        world.add(barry);
//
//        world.start();
//
//        stage.setTitle("Group 8 Game Engine");
//
//        Scene scene = new Scene(world, WORLD_WIDTH, WORLD_HEIGHT);
//
//        stage.setScene(scene);
//
//        stage.show();
//    }


    public static void main(String[] args) {
        launch(args);
    }
}
