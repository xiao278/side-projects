import java.awt.*;
import java.util.ArrayList;
public abstract class Drawable{
    protected Canvas canvas;
    public abstract void draw(Graphics2D graphics);

    public void setC(Canvas c) {
        this.canvas = c;
    }
}

class MovingCircle extends Drawable{
    public void draw(Graphics2D graphics2D){
        graphics2D.drawOval(canvas.xPos,canvas.yPos,10,10);
    }
}

class Snake extends Drawable{
    ArrayList<Vector2> points;
    private int length;
    private int size;
    public void draw(Graphics2D g){
        update();
        for(Vector2 point: points){
            g.drawOval((int)point.x, (int)point.y,size,size);
        }
        for(int i = 1; i < points.size(); i++){
            Vector2 toHead = points.get(i).to(points.get(i-1));
            var unitv = toHead.unitV();
            var point1 = points.get(i).add(unitv.scale((double)(size)/2)).add(new Vector2((double)(size)/2,(double)(size)/2));
            var point2 = points.get(i-1).add(unitv.scale(-(double)(size)/2)).add(new Vector2((double)(size)/2,(double)(size)/2));
            g.drawLine((int)point1.x,(int)point1.y,(int)point2.x,(int)point2.y);
        }
    }
    public Snake(int len, int num){
        points = new ArrayList<>();
        this.length = len;
        for(int i = 0; i < num; i++){
            points.add(new Vector2(Math.random()*10-5,Math.random()*10-5));
        }
    }
    public void update(){
        points.set(0, new Vector2(canvas.xPos, canvas.yPos));
        for(int i = 1; i < points.size(); i++){
            Vector2 toHead = points.get(i).to(points.get(i-1));
            double moveFactor = toHead.len() - length;
            //find how far it is from the head point
            //hten move

            points.set(i,points.get(i).add(toHead.unitV().scale(moveFactor)));
        }
    }
}

class TraceFixedPoint extends Drawable{
    ArrayList<Vector2> segments;
    ArrayList<Vector2> followPoints; //size one less than points, holds target position
    private float radius;
    private int length; //segment length
    private int num; //number of segments
    private Vector2 lastPos;
    private double size = 5;
    public TraceFixedPoint(int len, int num, float radius){
        this.length = len;
        this.num = num;
        segments = new ArrayList<>();
        followPoints = new ArrayList<>();
        this.radius = radius;
    }
    public void draw(Graphics2D g){
        update();
        for(Vector2 point: segments){
            g.drawOval((int)point.x, (int)point.y,5,5);
        }
        for(int i = 1; i < segments.size(); i++){
            Vector2 toHead = segments.get(i).to(segments.get(i-1));
            var unitv = toHead.unitV();
            var point1 = segments.get(i).add(unitv.scale((double)(size)/2)).add(new Vector2((double)(size)/2,(double)(size)/2));
            var point2 = segments.get(i-1).add(unitv.scale(-(double)(size)/2)).add(new Vector2((double)(size)/2,(double)(size)/2));
            g.drawLine((int)point1.x,(int)point1.y,(int)point2.x,(int)point2.y);
        }
    }
    public void update(){
        Vector2 newPos = new Vector2(canvas.xPos, canvas.yPos);
        Vector2 dPos = lastPos.to(newPos);
        double dPosLen = dPos.len();
        //capping how muc the head will move in one update
        if(dPosLen > length/2.5){
            segments.set(0, segments.get(0).add(dPos.scale(1/2.5)));
        }
        else{
            segments.set(0, segments.get(0).add(dPos));
        }
        //segment[i+1] should head to followpoints[i]
        for(int i = 0; i < followPoints.size(); i++) {
            Vector2 toTarget = segments.get(i+1).to(followPoints.get(i));
            //within readius, find new target
            if (toTarget.len() < radius){
                followPoints.set(i,segments.get(i));
            }
            //outside radius, go towards target
            else {
//                segments.set(i+1,segments.get(i+1).add(toTarget.unitV().scale(moveLength)));
                double dtfp = toTarget.len(); //distance to follow point
                Vector2 fptns = followPoints.get(i).to(segments.get(i)); //follow point to next segment
                double dtns = dtfp + fptns.len(); //distance to next segment
                double moveFactor;
                if(dPosLen > dtfp || dtns > length * 10){
                    followPoints.set(i,segments.get(i));
                    toTarget = segments.get(i+1).to(followPoints.get(i));
                    moveFactor = toTarget.len() - length;
                }
                else{
                    moveFactor = dtns - length;
                }
                segments.set(i+1,segments.get(i+1).add(toTarget.unitV().scale(moveFactor)));
            }
        }
        lastPos = newPos;
    }

    @Override
    public void setC(Canvas c) {
        super.setC(c);
        lastPos = new Vector2(canvas.xPos,canvas.yPos);
        for(int i = 0; i < num; i++){
            segments.add(new Vector2(Math.random()*10-5 + canvas.getWidth()/2.0,Math.random()*10-5 + canvas.getHeight()/2.0));
        }
        for(int i = 0; i < segments.size()-1; i++){
            followPoints.add(segments.get(i));
        }
    }
}