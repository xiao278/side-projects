import org.w3c.dom.css.Rect;

import java.awt.*;

public class RectSpam extends Drawable{
    @Override
    public void draw(Graphics2D graphics) {
        for(int i = 0; i < 20; i++){
            int x = (int)(Math.random()* canvas.getWidth());
            int y = (int)(Math.random()* canvas.getHeight());
            graphics.fillRect(x,y,10,10);
        }
    }

    public static void main(String[] args) {
        TimedCanvas tc = new TimedCanvas(33);
        tc.addDrawable(new RectSpam());
    }
}
