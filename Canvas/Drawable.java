import java.awt.*;
import java.util.ArrayList;
public abstract class Drawable{
    protected Canvas canvas;
    public abstract void draw(Graphics2D graphics);

    public void setC(Canvas c) {
        this.canvas = c;
    }
}

