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

    @Override
    public void start(Stage stage) throws Exception{

        World world = new World() {
            @Override
            public void act(long now) {

            }
        };

        Barry barry = new Barry();

        barry.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                System.out.println("HIIII");

                if (e.getCode() == KeyCode.RIGHT) {
                    barry.move(3, 0);

                }

            }
        });

        barry.setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                System.out.println("WOWOWWOW");
            }
        });

        barry.setImage(new Image("file:img/barry.png"));

        world.add(barry);

        world.start();

        stage.setTitle("Group 8 Game Engine");

        Scene scene = new Scene(world, WORLD_WIDTH, WORLD_HEIGHT);

        stage.setScene(scene);

        stage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
