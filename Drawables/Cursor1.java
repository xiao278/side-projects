import java.awt.*;

public class Cursor1 extends Drawable{

    final int numCircles;
    final int size = 16;
    final double spinSpeed = 5.5;
    final Vector2[] positions;
    final double radius = 35;
    private double angle;
    final private double separationAngle;
    private BouncingObject[] bouncingObjects;

    public Cursor1(int num){
        this.numCircles = num;
        positions = new Vector2[numCircles];
        this.angle = 0;
        separationAngle = 2*Math.PI/(num);
    }

    @Override
    public void draw(Graphics2D graphics) {
        update();
        if(myCanvas.pressed){
            for(int i = 0; i < numCircles; i++){
                var bo = bouncingObjects[i];
                graphics.drawOval((int)bo.getPosition().x,(int)bo.getPosition().y,size,size);
            }
        }else{
            for(int i = 0; i < numCircles; i++){
                graphics.drawOval((int)positions[i].x,(int)positions[i].y,size,size);
            }
        }
        //graphics.drawOval((int)myCanvas.getMousePos().x,(int)myCanvas.getMousePos().y,10,10);
    }

    private void update(){
        if(myCanvas.pressed){
            if(bouncingObjects == null){
                bouncingObjects = new BouncingObject[numCircles];
                for(int i = 0; i < numCircles; i++){
                    bouncingObjects[i] = new BouncingObject(size,(new Vector2(i * separationAngle + angle + Math.PI/2)).scale(radius*spinSpeed/1000.00),positions[i],0.005,myCanvas);
                }
            }
            else{
                for(int i = 0; i < bouncingObjects.length; i++){
                    bouncingObjects[i].update();
                }
            }
        }
        else{
            bouncingObjects = null;
            angle += spinSpeed * myCanvas.timer.getDelay()/1000.00;
            if(angle > Math.PI*2) angle -= Math.PI*2;
            for(int i = 0; i < numCircles; i++){
                Vector2 v = new Vector2(Math.cos(angle + i * separationAngle),Math.sin(angle + i * separationAngle));
                v = v.scale(radius);
                v = v.add(myCanvas.getMousePos());
                positions[i] = v;
            }
        }


    }

    public static void main(String[] args) {
        var c1 = new Cursor1(6);
        MyCanvas c = new MyCanvas(16);
        c.addDrawable(c1);
    }
}


