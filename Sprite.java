import java.awt.*;

public abstract class Sprite {
    int x;
    int y;
    int w;
    int h;

    //checks for a collision between sprite and any other rectangle shape
    public boolean doesCollide(int _x, int _y, int _w, int _h)
    {
        if(x + w <= _x)
            return false;
        if(x >= _x + _w)
            return false;
        if(y + h <= _y)
            return false;
        if (y >= _y + _h)
            return false;
        return true;
    }

    public boolean isABrick() { return false; }

    public abstract void update();

    public abstract void draw(Graphics g);

    public abstract Json marshall();
}
