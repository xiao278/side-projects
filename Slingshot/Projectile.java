import java.util.ArrayList;

public class Projectile {
    protected Vector2 velocity;
    protected Vector2 position;
    protected static double g;
    protected MyCanvas myCanvas;
    protected int size;

    protected static Vector2 pathVel;

    public Projectile(Vector2 position, Vector2 velocity, int size){
        this.velocity = velocity;
        this.position = position;
        this.size = size;
    }

    public static void setG(double newg) {
        g = newg;
    }

    public Vector2 getPosition() {
        return position;
    }

    public Vector2 getVelocity() {
        return velocity;
    }

    public boolean checkDestroyed(){
        return  pastBottomBound() || pastLeftBound() || pastRightBound();
    }

    public void update(int dt){
        velocity = new Vector2(velocity.x,velocity.y + g);
        position = position.add(velocity);
    }

    public void initialize(MyCanvas mc){
        this.myCanvas = mc;
    }

    protected boolean pastRightBound(){
        return this.getPosition().x > myCanvas.getWidth();
    }

    protected boolean onRightBound(){
        return this.getPosition().x + size> myCanvas.getWidth();
    }

    protected boolean pastLeftBound(){
        return this.getPosition().x + size < 0;
    }

    protected boolean onLeftBound(){
        return this.getPosition().x < 0;
    }

    protected boolean pastBottomBound(){
        return this.getPosition().y > myCanvas.getHeight();
    }

    protected boolean onBottomBound(){
        return this.getPosition().y + size > myCanvas.getHeight();
    }

    protected boolean pastTopBound(){
        return this.getPosition().y + size < 0;
    }

    protected boolean onTopBound(){
        return this.getPosition().y < 0;
    }

    public static ArrayList<Vector2> getPath(Vector2 position, Vector2 velocity, MyCanvas mc){
        pathVel = velocity.scale(0.38);
        int spacing = 10;
        int iterations = 30;
        int velSign = (int)(pathVel.x/Math.abs(pathVel.x));
        spacing *= velSign;

        var arr = new ArrayList<Vector2>();
        int x = spacing;
        for(int i = 0; i < iterations; i++){
            var temp = new Vector2(x, getPointY(x));
            temp = temp.add(position);
            arr.add(temp);
            x += spacing;
        }

        return arr;
    }

    //relative to the shooting point
    private static double getPointY(double x){
        var grav = g*1.4;
        return x*(pathVel.y/pathVel.x)+grav*(x*x)/(2*(pathVel.len()*pathVel.len())*(pathVel.x*pathVel.x));
    }
}
