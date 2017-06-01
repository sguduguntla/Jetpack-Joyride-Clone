package sample;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

public abstract class Actor extends ImageView {

    private double dx;
    private double dy;

    public Actor() {
        dx = 0;
        dy = 0;
    }

    public void setDx(double dx) {
        this.dx = dx;
    }

    public void setDy(double dy) {
        this.dy = dy;
    }

    public double getDx() {
        return dx;
    }

    public double getDy() {
        return dy;
    }

    public abstract void act(long now);

    public double getHeight() {
        return getImage().getHeight();
    }

    public double getWidth() {
        return getImage().getWidth();
    }

    public World getWorld() {
        return (World) getParent();
    }

    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public void setLocation(double x, double y) {
        setX(x);
        setY(y);
    }

    public <A extends Actor> List<A> getIntersectingObjects(Class<A> cls) {
        List<A> intersectingActors = new ArrayList<A>();

        for (Actor actor : getWorld().getObjects(cls)) {

            if (!actor.equals(this) && cls.isInstance(actor) && actor.intersects(this.getBoundsInLocal())) {
                intersectingActors.add((A) actor);
            }

        }

        return intersectingActors;
    }

    public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {

        List<A> intersectingObjects = getIntersectingObjects(cls);

        return !intersectingObjects.isEmpty() ? intersectingObjects.get(0) : null;

    }

}