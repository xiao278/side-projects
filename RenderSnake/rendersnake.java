public class rendersnake {
    public static void main(String[] args) {
        MyCanvas c = new MyCanvas(20);
        var s2 = new TraceFixedPoint(18,50,0.2f);
        c.addDrawable(s2);
    }
}
