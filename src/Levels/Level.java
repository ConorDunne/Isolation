package Levels;

public class Level {
    private String background;
    private int Scale;
    private int RightBound;

    protected int XPos;
    protected float base;

    protected int BlocksTall;
    protected int BlocksWide;
    protected float BlockHeightDimension;
    protected float BlockWidthDimension;

    public Level() {}

    public Level(String background, int Scale, int RightBound) {
        this.background = background;
        this.Scale = Scale;
        this.RightBound = RightBound;

        this.XPos = -20;
    }

    protected void setBlockSize(int BlocksTall, int BlocksWide, int Height) {
        this.BlocksTall = BlocksTall;
        this.BlocksWide = BlocksWide;
        this.BlockHeightDimension = Height/BlocksTall;

        this.BlockWidthDimension = 38.8f;
    }

    protected void setBase(int Blocks) {
        this.base = ((BlocksTall-Blocks)* BlockHeightDimension)-(BlockHeightDimension *0.6f);
    }

    public String getBackground() {
        return background;
    }

    public int getScale() {
        return Scale;
    }

    public int getXPos() {
        return XPos;
    }

    protected float getBase(float blocks) {
        return ((BlocksTall-blocks) * BlockHeightDimension) - (BlockHeightDimension * 0.6f);
    }

    public void moveRight() {
        if(XPos > RightBound)
            XPos--;
    }

    public void moveLeft() {
        if(XPos < 0)
            XPos++;
    }
}
