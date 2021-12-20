public class Vector2 {
    public double x;
    public double y;
    Vector2(double x, double y){
        this.x = x;
        this.y = y;
    }
    Vector2(double angle){
        this.x = Math.cos(angle);
        this.y = Math.sin(angle);
    }
    Vector2(){
        this.x = 0;
        this.y = 0;
    }
    public double len(){
        return Math.sqrt(x*x + y*y);
    }
    public Vector2 unitV(){
        double len = len();
        return new Vector2(x/len,y/len);
    }

    //this to input vecotr
    public Vector2 to(Vector2 v){
        return new Vector2(v.x - x, v.y - y);
    }

    public Vector2 from(Vector2 v){
        return new Vector2(x - v.x, y - v.y);
    }

    public void set(double x, double y){
        this.x = x;
        this.y = y;
    }

    public Vector2 add(Vector2 v){
        return new Vector2(v.x + x, v.y + y);
    }

    public Vector2 scale(double scalar){
        return new Vector2(x*scalar, y*scalar);
    }
    public String toString(){
        return "(" + this.x + ", " + this.y + ")";
    }
}
