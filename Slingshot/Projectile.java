public class Projectile {
    private Vector2 velocity;
    private Vector2 position;
    private final double g;
    public Projectile(Vector2 position, Vector2 velocity, double g){
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

    public void update(int dt){
        velocity = new Vector2(velocity.x,velocity.y + g);
        position = position.add(velocity);
    }
}
