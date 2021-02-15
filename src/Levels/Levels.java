package Levels;

public interface Levels {
    String getBackground();
    int getScale();
    int getXPos();
    void moveRight();
    void moveLeft();
    boolean grounded(float y);
    boolean wall(float x, float y);
}
