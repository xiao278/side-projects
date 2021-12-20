import java.awt.*;

class MovingCircle extends Drawable {
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawOval(myCanvas.xPos, myCanvas.yPos, 10, 10);
    }
}
