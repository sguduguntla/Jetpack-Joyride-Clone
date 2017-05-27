package sample;

import javafx.scene.image.Image;

public class Scientist extends Actor {

    int dx;
    private int xPos;
    private int yPos;
    private String imag = "";


    public Scientist() {
        setImage(new Image("file:img/scientist-stationary.png"));
        imag = "scientist-stationary";
        xPos = 2000;
        yPos = 2000;
        dx = -7;
    }

    public void setImag(String img) {
        imag = img;
    }

    public String getImag() {
        return imag;
    }

    @Override
    public void act(long now) {

        move(dx, 0);

//        if (imag.equals("scientist-moving")) {
//            imag = "scientist-stationary";
//        } else if (imag.equals("scientist-stationary")) {
//            imag = "scientist-stationary2";
//        } else {
//            imag = "scientist-moving";
//        }
//
//        setImage(new Image("file:img/" + imag + ".png"));

    }

    public void setDx(int d) {
        dx = d;
    }
}
