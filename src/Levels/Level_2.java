package Levels;

import util.GameObject;

import java.util.ArrayList;

public class Level_2 extends Level implements Levels {

    public Level_2() {
        super("res/GUI/Levels/Lvl_2_grid.png", 2295, -1349);

        setOil();
        setBlockSize(12, 60, 680);
        setBase(3);
    }

    private void setOil() {
        oil = new ArrayList<>();
        String texture = "res/GUI/Levels/oil.png";

        oil.add(new GameObject(texture, 25, 25, 1000, 200));
        oil.add(new GameObject(texture, 25, 25, 1400, 200));
    }

    public boolean grounded(float y) {
        if(y >= getBase(3)) {
            return true;
        }

        if(XPos > -(26.5*BlockWidthDimension)) {
            if(y >= getBase(4)) {
                return true;
            }
        }

        if(XPos < -(3*BlockWidthDimension) && XPos > -(20*BlockWidthDimension)) {
            if(y >= getBase(5)) {
                return true;
            }
        }

        if(XPos < -(12.5*BlockWidthDimension) && XPos > -(23.5*BlockWidthDimension)) {
            if(y >= getBase(6)) {
                return true;
            }
        }

        if(XPos < -(13.5*BlockWidthDimension) && XPos > -(15.5*BlockWidthDimension)) {
            if(y >= getBase(7)) {
                return true;
            }
        }

        if(XPos < -(16.5*BlockWidthDimension) && XPos > -(22.5*BlockWidthDimension)) {
            if(y >= getBase(8)) {
                return true;
            }
        }

        if(XPos < -(27.5*BlockWidthDimension) && XPos > -(29.5*BlockWidthDimension)) {
            if(y >= getBase(4)) {
                return true;
            }
        }

        if(XPos < -(30*BlockWidthDimension) && XPos > -(34.25*BlockWidthDimension)) {
            if(y >= getBase(5)) {
                return true;
            }
        }

        if(XPos < -(30*BlockWidthDimension)) {
            if(y >= getBase(4)) {
                return true;
            }
        }

        return false;
    }

    public boolean wall(float x, float y) {
        if(y >= getBase(2.3f)) {
            return true;
        }

        if(x > -(26*BlockWidthDimension)) {
            if(y >= getBase(3.9f)) {
                return true;
            }
        }

        if(x < -(3*BlockWidthDimension) && x > -(20*BlockWidthDimension)) {
            if(y >= getBase(4.9f)) {
                return true;
            }
        }

        if(x < -(12.5*BlockWidthDimension) && x > -(23.5*BlockWidthDimension)) {
            if(y >= getBase(5.9f)) {
                return true;
            }
        }

        if(x < -(13.5*BlockWidthDimension) && x > -(15.5*BlockWidthDimension)) {
            if(y >= getBase(6.9f)) {
                return true;
            }
        }

        if(x < -(16.5*BlockWidthDimension) && x > -(22.5*BlockWidthDimension)) {
            if(y >= getBase(7.9f)) {
                return true;
            }
        }

        if(x < -(27.5*BlockWidthDimension) && x > -(29.5*BlockWidthDimension)) {
            if(y >= getBase(3.9f)) {
                return true;
            }
        }

        if(x < -(30*BlockWidthDimension) && x > -(34.25*BlockWidthDimension)) {
            if(y >= getBase(4.9f)) {
                return true;
            }
        }

        if(x < -(30*BlockWidthDimension)) {
            if(y >= getBase(3.9f)) {
                return true;
            }
        }

        return false;
    }
}
