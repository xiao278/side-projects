import java.awt.*;

class MovingCircle extends Drawable {
    public void draw(Graphics2D graphics2D) {
        graphics2D.drawOval(canvas.xPos, canvas.yPos, 10, 10);
    }
}
