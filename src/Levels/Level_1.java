package Levels;

public class Level_1 extends Level implements Levels {

    public Level_1() {
        super("res/Levels/Lvl_1_grid.png", 2295, -1349);

        setBlockSize(12, 60, 680);
        setBase(3);
    }

    public boolean grounded(float y) {
        if(y >= getBase(3)) {
            return true;
        }

        if(XPos > -(9*BlockWidthDimension)) {
            if(y >= getBase(4)) {
                return true;
            }
        }

        if(XPos < -(15.5*BlockWidthDimension) && XPos > -(31*BlockWidthDimension)) {
            if(y >= getBase(4)) {
                return true;
            }
        }

        if(XPos > -(3*BlockWidthDimension)) {
            if(y >= getBase(5)) {
                return true;
            }
        }

        if(XPos < -(21.5*BlockWidthDimension) && XPos > -(25*BlockWidthDimension)) {
            if(y >= getBase(5)) {
                return true;
            }
        }

        return false;
    }

    public boolean wall(float x, float y) {
        if(x > -(9*BlockWidthDimension)) {
            if(y >= getBase(3.9f)) {
                return true;
            }
        }

        if(x < -(15.5*BlockWidthDimension) && x > -(31*BlockWidthDimension)) {
            if(y >= getBase(3.9f)) {
                return true;
            }
        }

        if(x > -(3*BlockWidthDimension)) {
            if(y >= getBase(4.9f)) {
                return true;
            }
        }

        if(x < -(21.5*BlockWidthDimension) && x > -(25*BlockWidthDimension)) {
            if(y >= getBase(4.9f)) {
                return true;
            }
        }

        return false;
    }
}
