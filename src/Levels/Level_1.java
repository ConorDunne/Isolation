package Levels;

import util.GameObject;

import java.util.ArrayList;

public class Level_1 extends Level implements Levels {

    public Level_1() {
        super("res/GUI/Levels/Lvl_1_grid.png", 2295, -1349);

        setOil();
        setBlockSize(12, 60, 680);
        setBase(3);
    }

    private void setOil() {
        oil = new ArrayList<>();
        String texture = "res/GUI/Levels/oil.png";

        oil.add(new GameObject(texture, 25, 25, 1250, 400));
    }

    public boolean grounded(float y) {
        if(y >= getBase(3)) {
            return true;
        }

        if(XPos > -(9.75*BlockWidthDimension)) {
            if(y >= getBase(4)) {
                return true;
            }
        }

        if(XPos < -(15.5*BlockWidthDimension) && XPos > -(31.75*BlockWidthDimension)) {
            if(y >= getBase(4)) {
                return true;
            }
        }

        if(XPos > -(3.75*BlockWidthDimension)) {
            if(y >= getBase(5)) {
                return true;
            }
        }

        if(XPos < -(21.5*BlockWidthDimension) && XPos > -(25.75*BlockWidthDimension)) {
            if(y >= getBase(5)) {
                return true;
            }
        }

        return false;
    }

    public boolean wall(float x, float y) {
        if(x > -(9.75*BlockWidthDimension)) {
            if(y >= getBase(3.9f)) {
                return true;
            }
        }

        if(x < -(15.5*BlockWidthDimension) && x > -(31.75*BlockWidthDimension)) {
            if(y >= getBase(3.9f)) {
                return true;
            }
        }

        if(x > -(3.75*BlockWidthDimension)) {
            if(y >= getBase(4.9f)) {
                return true;
            }
        }

        if(x < -(21.5*BlockWidthDimension) && x > -(25.75*BlockWidthDimension)) {
            if(y >= getBase(4.9f)) {
                return true;
            }
        }

        return false;
    }
}
