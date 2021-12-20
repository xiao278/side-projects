import java.awt.*;

public class BouncingObject {
    private int size;
    private Vector2 position;
    private Vector2 velocity;
    private double drag;
    MyCanvas myCanvas;
    public BouncingObject(int size, Vector2 velocity, Vector2 position, double drag, MyCanvas myCanvas){
        this.size = size;
        this.drag = drag;
        this.velocity = velocity;
        this.position = position;
        this.myCanvas = myCanvas;
    }
    public void update(){
        if(position.x < 0 || position.x + size > myCanvas.getWidth()){
            velocity = new Vector2(-velocity.x, velocity.y);
        }
        if(position.y < 0 || position.y + size > myCanvas.getHeight()){
            velocity = new Vector2(velocity.x, -velocity.y);
        }
        velocity = velocity.scale(1-drag);
        position = position.add(velocity.scale(myCanvas.getInterval()));
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public Vector2 getPosition() {
        return position;
    }

    public int getSize() {
        return size;
    }

    public double getDrag() {
        return drag;
    }

    public static void main(String[] args) {

    }
}
