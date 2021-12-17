import java.awt.*;
import java.util.ArrayList;

class Snake extends Drawable {
    ArrayList<Vector2> points;
    private int length;
    private int size;

    public void draw(Graphics2D g) {
        update();
        for (Vector2 point : points) {
            g.drawOval((int) point.x, (int) point.y, size, size);
        }
        for (int i = 1; i < points.size(); i++) {
            Vector2 toHead = points.get(i).to(points.get(i - 1));
            var unitv = toHead.unitV();
            var point1 = points.get(i).add(unitv.scale((double) (size) / 2)).add(new Vector2((double) (size) / 2, (double) (size) / 2));
            var point2 = points.get(i - 1).add(unitv.scale(-(double) (size) / 2)).add(new Vector2((double) (size) / 2, (double) (size) / 2));
            g.drawLine((int) point1.x, (int) point1.y, (int) point2.x, (int) point2.y);
        }
    }

    public Snake(int len, int num) {
        points = new ArrayList<>();
        this.length = len;
        for (int i = 0; i < num; i++) {
            points.add(new Vector2(Math.random() * 10 - 5, Math.random() * 10 - 5));
        }
    }

    public void update() {
        points.set(0, new Vector2(canvas.xPos, canvas.yPos));
        for (int i = 1; i < points.size(); i++) {
            Vector2 toHead = points.get(i).to(points.get(i - 1));
            double moveFactor = toHead.len() - length;
            //find how far it is from the head point
            //hten move

            points.set(i, points.get(i).add(toHead.unitV().scale(moveFactor)));
        }
    }
}
