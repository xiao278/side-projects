public class Projectile {
    protected Vector2 velocity;
    protected Vector2 position;
    protected double g;
    protected MyCanvas myCanvas;
    protected int size;

    protected static Vector2 pathPos;
    protected static Vector2 pathVel;

    public Projectile(Vector2 position, Vector2 velocity, int size, double g){
        this.velocity = velocity;
        this.position = position;
        this.g = g;
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

    public static void renderPath(Vector2 position, Vector2 velocity){
        pathPos = position;
        pathVel = velocity;
    }

    //relative to the shooting point
    private double getPathY(double x){
        return x*(pathVel.y/pathVel.x)-g*(x*x)/(2*(pathVel.len()*pathVel.len())*(pathVel.x*pathVel.x));
    }
}
