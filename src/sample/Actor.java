package sample;

import javafx.event.EventHandler;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

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

    public Actor(Image image) {
        super(image);
        setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
            }
        });
    }

    abstract void act(long now);

    public double getHeight(){
        return getFitHeight();
    }

    public double getWidth(){
        return getFitWidth();
    }

    public World getWorld(){
        return (getParent() instanceof World) ? (World) getParent() : null;
    }

    public <A extends Actor> java.util.List<A> getIntersectingObjects(java.lang.Class<A> cls){
//        List<A> l = new ArrayList<A>();
//        for (Actor actor : getWorld().getObjects(cls)){
//            if (!actor.equals(this) && actor.getClass().isInstance(cls) && actor.intersects(this.getBoundsInLocal())){
//                l.add((A) actor);
//            }
//        }
//        return l;

        List<A> intersectors = new ArrayList<>();
        List<A> siblingNodes = getWorld().getObjects(cls);
        for (A actor : siblingNodes) {
            if (actor.intersects(getBoundsInLocal()) && actor != this) {
                intersectors.add(actor);
            }

        }

        return intersectors;
    }

    public <A extends Actor> A getOneIntersectingObject(java.lang.Class<A> cls){
        return getIntersectingObjects(cls).size() == 0 ? null : getIntersectingObjects(cls).get(0);
    }

    public void move(double dx, double dy){
        setX(getX() + dx);
        setY(getY() + dy);
    }

//    private int dx;
//    private int dy;
//
//    public Actor() {
//        dx = 0;
//        dy = 0;
//    }
//
//    public Actor(Image image) {
//        super(image);
//        setOnKeyPressed(new EventHandler<KeyEvent>() {
//            @Override
//            public void handle(KeyEvent event) {
//            }
//        });
//    }
//
//    public abstract void act(long now);
//
//    public double getHeight() {
//        return getFitHeight();
//    }
//
//    public double getWidth() {
//        return getFitWidth();
//    }
//
//    public void setDx(int dx) {
//        this.dx = dx;
//    }
//
//    public void setDy(int dy) {
//        this.dy = dy;
//    }
//
//    public int getDy() {
//        return dy;
//    }
//
//    public int getDx() {
//        return dx;
//    }
//
//    public World getWorld() {
//        if (this.getClass().isInstance(World.class)) {
//            return ((World) getParent());
//        } else {
//            return null;
//        }
//    }
//
//    public void move(double dx, double dy) {
//        setX(getX() + dx);
//        setY(getY() + dy);
//    }
//
//    public <A extends Actor> List<A> getIntersectingObjects(Class<A> cls) {
//        List<A> intersectingActors =  new ArrayList<A>();
//
//        for (Actor actor : getWorld().getObjects(cls)) {
//
//            if (!actor.equals(this) && cls.isInstance(actor) && actor.intersects(this.getBoundsInLocal())) {
//                intersectingActors.add((A) actor);
//            }
//
//        }
//
//        return intersectingActors;
//    }
//
//    public <A extends Actor> A getOneIntersectingObject(Class<A> cls) {
//
//        List<A> intersectingObjects = getIntersectingObjects(cls);
//
//        return !intersectingObjects.isEmpty() ? intersectingObjects.get(0): null;
//
//    }
}
