import java.awt.*;

public abstract class Sprite {
    int x;
    int y;
    int w;
    int h;

    public abstract void update();

    public abstract void draw(Graphics g);

    public abstract Json marshall();
}
