import java.awt.*;

public abstract class Drawable{
    protected MyCanvas myCanvas;
    public abstract void draw(Graphics2D graphics);

    public void setC(MyCanvas c) {
        this.myCanvas = c;
    }
}

