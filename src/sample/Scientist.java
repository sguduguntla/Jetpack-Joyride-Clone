package sample;

import javafx.scene.image.Image;

public class Scientist extends Actor {

    private String imag = "";


    public Scientist() {
        setImage(new Image("file:img/scientist-stationary.png"));
        imag = "scientist-stationary";

    }

    public void setImag(String img) {
        imag = img;
    }

    public String getImag() {
        return imag;
    }

    @Override
    public void act(long now) {

        move(getDx(), getDy());

    }

}
