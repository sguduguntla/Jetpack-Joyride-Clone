package sample;

import javafx.animation.AnimationTimer;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by sguduguntla on 3/30/17.
 */
public abstract class World extends Pane {

    private AnimationTimer timer;
    private final static int TIMER_DELAY = 0;

    public void P1_Guduguntla_Sriharsha_World() {

        sceneProperty().addListener(new ChangeListener<Scene>() {

            List<Actor> actors = getObjects(Actor.class);

            @Override
            public void changed(ObservableValue<? extends Scene> observable, Scene oldValue, Scene newValue) {
                if (newValue != null) {
                    newValue.setOnKeyReleased(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent e) {
                            handle(e);

                            for (Actor actor : actors) {
                                actor.getOnKeyReleased().handle(e);
                            }
                        }
                    });

                    newValue.setOnKeyPressed(new EventHandler<KeyEvent>() {
                        @Override
                        public void handle(KeyEvent e) {
                            handle(e);

                            for (Actor actor : actors) {
                                actor.getOnKeyPressed().handle(e);
                            }
                        }
                    });
                }
            }
        });

    }

    public abstract void act(long now);

    public void add(Actor actor) {
        this.getChildren().add(actor);
        System.out.println(getChildren().size());
    }

    public void remove(Actor actor) {
        this.getChildren().remove(actor);

    }

    public void start() {

        List<Actor> actors = getObjects(Actor.class);

        timer = new AnimationTimer() {

            private long lastTime = 0;
            private int elapsedTime = 0;

            @Override
            public void handle(long now) {

                if (now - lastTime >= convertMilliToNano(TIMER_DELAY)) {
                    lastTime = now;

                    elapsedTime++;

                    act(now);

                    for (Actor actor : actors) {
                        actor.act(now);
                    }
                }

            }

            private long convertMilliToNano(long time) {
                return TIMER_DELAY * 1_000_000;
            }
        };

        timer.start();

    }

    public void stop() {

        timer.stop();

    }

    public <A extends Actor> List<A> getObjects(Class<A> cls) {
       List<A> actors = new ArrayList<A>();

        System.out.println(this.getChildren().size());

       for (Node node: this.getChildren()) {
           if (cls.isInstance(node)) {
               actors.add(cls.cast(node));
           }
       }

       return actors;
    }

}

