import java.awt.*;

public class BouncingDemo extends Drawable {
    int size;
    Vector2 position;
    Vector2 velocity;
    public BouncingDemo(int size){
        this.size = size;
        this.velocity = new Vector2(Math.random()*Math.PI*2);
        this.velocity.scale(2);
    }
    @Override
    public void draw(Graphics2D graphics) {
        update();
        graphics.drawOval((int)position.x,(int)position.y,size,size);
    }
    private void update(){
        if(position.x < 0 || position.x + size > myCanvas.getWidth()){
            velocity = new Vector2(-velocity.x, velocity.y);
        }
        if(position.y < 0 || position.y + size > myCanvas.getHeight()){
            velocity = new Vector2(velocity.x, -velocity.y);
        }
        position = position.add(velocity.scale(myCanvas.getInterval()));
    }

    @Override
    public void setC(MyCanvas c) {
        super.setC(c);
        this.position = new Vector2(myCanvas.getWidth()/2,myCanvas.getHeight()/2);
    }

    public static void main(String[] args) {
        var c = new MyCanvas(10);
        var bd = new BouncingDemo(25);
        c.addDrawable(bd);
    }
}
