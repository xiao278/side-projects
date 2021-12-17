import javax.swing.*;

public class rendersnake {
    public static void main(String[] args) {
        Canvas c = new Canvas();
        var s2 = new TraceFixedPoint(18,50,0.2f);
        c.addDrawable(s2);
    }
}
