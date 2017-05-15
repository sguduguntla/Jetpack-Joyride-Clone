package sample;

public interface Powerup {

    boolean isActivated();

    void activate();

    double getRemainingTime();

    double getDuration();

    void setDuration();

    void deactivate();

}
