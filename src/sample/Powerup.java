package sample;

/**
 * Created by sguduguntla on 5/11/17.
 */
public interface Powerup {

    boolean isActivated();

    void activate();

    double getRemainingTime();

    double getDuration();

    void setDuration();

    void deactivate();

}
