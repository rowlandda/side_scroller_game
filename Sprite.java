import java.awt.*;

public abstract class Sprite {
    int x;
    int y;
    int w;
    int h;
    int prevY;
    int prevX;
    String name;
    boolean left = false;

    //generic empty constructor without params so I can make whatever type of constructor I want
    public Sprite() {;}

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

    public boolean isAMario() { return false; }

    public boolean isACoinblock() { return false; }

    public abstract void update();

    public abstract void draw(Graphics g);

    public Sprite(Json ob)
    {
        name = ob.getString("name");
        x = (int)ob.getLong("x");
        y = (int)ob.getLong("y");
        w = (int)ob.getLong("w");
        h = (int)ob.getLong("h");
    }

    public Json marshall()
    {
        Json ob = Json.newObject();
        ob.add("name", name);
        ob.add("x", x);
        ob.add("y", y);
        ob.add("w", w);
        ob.add("h", h);
        return ob;
    }
    //so mario can override it to jump
    public void jump() {;}
}
