package sample;

import javafx.scene.image.Image;

public class Background extends Actor {


    public Background() {

        setImage(new Image("file:img/background.png", Main.WORLD_WIDTH, Main.WORLD_HEIGHT, false, true, true));

    }

    @Override
    public void act(long now) {

        move(getDx(), getDy());
        edgeLoop();

    }

    private void edgeLoop() {

        if (getX() <= -Main.WORLD_WIDTH - 10) {
            setX(Main.WORLD_WIDTH - 10);
        }



    }

}
