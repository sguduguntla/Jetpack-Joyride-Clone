package sample;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sguduguntla on 3/30/17.
 */
public abstract class Actor extends ImageView {

    private int dx;
    private int dy;

    public Actor() {
        dx = 0;
        dy = 0;
    }

    public abstract void act(long now);

    public double getHeight() {
        return getFitHeight();
    }

    public double getWidth() {
        return getFitWidth();
    }

    public void setDx(int dx) {
        this.dx = dx;
    }

    public void setDy(int dy) {
        this.dy = dy;
    }

    public int getDy() {
        return dy;
    }

    public int getDx() {
        return dx;
    }

    public World getWorld() {
        if (this.getClass().isInstance(World.class)) {
            return ((World) getParent());
        } else {
            return null;
        }
    }

    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public <A extends Actor> List<A> getIntersectingObjects(Class<A> cls) {
        List<A> intersectingActors =  new ArrayList<A>();

        for (Actor actor : getWorld().getObjects(cls)) {

            if (!actor.equals(this) && cls.isInstance(actor) && actor.intersects(this.getBoundsInLocal())) {
                intersectingActors.add((A) actor);
            }

        }

        return intersectingActors;
    }

    public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {

        List<A> intersectingObjects = getIntersectingObjects(cls);

        return !intersectingObjects.isEmpty() ? intersectingObjects.get(0): null;

    }




}
