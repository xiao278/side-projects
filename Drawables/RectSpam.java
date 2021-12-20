import java.awt.*;

public class RectSpam extends Drawable{
    @Override
    public void draw(Graphics2D graphics) {
        for(int i = 0; i < 20; i++){
            int x = (int)(Math.random()* myCanvas.getWidth());
            int y = (int)(Math.random()* myCanvas.getHeight());
            graphics.fillRect(x,y,  10,10);
        }
    }

    public static void main(String[] args) {
        MyCanvas c = new MyCanvas(33);
        c.addDrawable(new RectSpam());
    }
}
