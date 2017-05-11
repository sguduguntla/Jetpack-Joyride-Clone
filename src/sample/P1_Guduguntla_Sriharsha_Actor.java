package sample;

import javafx.scene.image.ImageView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sguduguntla on 3/30/17.
 */
public abstract class P1_Guduguntla_Sriharsha_Actor extends ImageView {

    public P1_Guduguntla_Sriharsha_Actor() {

    }

    public abstract void act(long now);

    public double getHeight() {
        return getFitHeight();
    }

    public double getWidth() {
        return getFitWidth();
    }

    public P1_Guduguntla_Sriharsha_World getWorld() {
        if (this.getClass().isInstance(P1_Guduguntla_Sriharsha_World.class)) {
            return ((P1_Guduguntla_Sriharsha_World) getParent());
        } else {
            return null;
        }
    }

    public void move(double dx, double dy) {
        setX(getX() + dx);
        setY(getY() + dy);
    }

    public <A extends P1_Guduguntla_Sriharsha_Actor> List<A> getIntersectingObjects(Class<A> cls) {
        List<A> intersectingActors =  new ArrayList<A>();

        for (P1_Guduguntla_Sriharsha_Actor actor : getWorld().getObjects(cls)) {

            if (!actor.equals(this) && cls.isInstance(actor) && actor.intersects(this.getBoundsInLocal())) {
                intersectingActors.add((A) actor);
            }

        }

        return intersectingActors;
    }

    public <A extends P1_Guduguntla_Sriharsha_Actor> A getOneIntersectingObject(Class<A> cls) {

        List<A> intersectingObjects = getIntersectingObjects(cls);

        return !intersectingObjects.isEmpty() ? intersectingObjects.get(0): null;

    }




}
