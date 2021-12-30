import java.awt.*;
import java.util.ArrayList;

public class Main extends Drawable{
    //location of mouse press
    private Vector2 pressDown;
    //a constant that determine how fast the projectile will fly
    private double force = 0.1;
    protected ArrayList<Projectile> projectiles;
    private double g = 0.4;
    private int size;

    public Main(){
        projectiles = new ArrayList<>();
        size = 10;
    }

    @Override
    public void draw(Graphics2D graphics) {
        update();
        if(pressDown != null){
            graphics.drawOval((int)pressDown.x,(int)pressDown.y,size,size);
            graphics.drawOval(myCanvas.mousePos.x,myCanvas.mousePos.y,size,size);
        }
        if(projectiles.size() > 0){
            for(int i = 0; i < projectiles.size(); i++){
                var p = projectiles.get(i);
                graphics.fillOval((int)p.getPosition().x,(int)p.getPosition().y,size/2,size/2);
            }
        }
    }

    public void update(){
        //when the user first presses
        if(pressDown == null && myCanvas.pressed){
            pressDown = myCanvas.getMousePos();
        } else if(pressDown != null && !myCanvas.pressed){
            addProjectile(new Projectile(pressDown, (new Vector2(myCanvas.mousePos.x,myCanvas.mousePos.y)).to(pressDown).scale(force),size,g));
            pressDown = null;
        }

        if(projectiles.size() > 0){
            for(int i = projectiles.size()-1; i >= 0; i--){
                var p = projectiles.get(i);
                p.update(myCanvas.timer.getDelay());
                if(p.checkDestroyed()){
                    projectiles.remove(p);
                }
            }
        }
        //System.out.println(projectiles.size());
    }

    public static void main(String[] args) {
        var main = new Main();
        var mc = new MyCanvas(10,1000,600);
        mc.addDrawable(main);
    }

    public ArrayList<Projectile> getProjectiles() {
        return projectiles;
    }

    public void addProjectile(Projectile p){
        projectiles.add(p);
        p.initialize(myCanvas);
    }
}
