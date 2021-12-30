public class ProjectileReflect extends Projectile{
    public ProjectileReflect(Vector2 position, Vector2 velocity,int size, double g){
        super(position,velocity,size,g);
    }

    @Override
    public void update(int dt) {
        super.update(dt);
        if(onRightBound()){
            velocity = new Vector2(-Math.abs(velocity.x),velocity.y);
        }
        else if(onLeftBound()){
            velocity = new Vector2(Math.abs(velocity.x),velocity.y);
        }
    }

    @Override
    public boolean checkDestroyed() {
        return pastBottomBound();
    }
}
