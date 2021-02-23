package Levels;

import util.GameObject;

import java.util.List;

public interface Levels {
    String getBackground();
    int getScale();
    int getXPos();
    void moveRight();
    void moveLeft();
    boolean grounded(float y);
    boolean wall(float x, float y);
    boolean levelComplete();
    List<GameObject> getOil();
}
