import javax.swing.*;
import java.awt.event.*;
import java.util.ArrayList;

public class TimedCanvas extends Canvas{
    int interval;
    Timer timer;

    public TimedCanvas(int interval) {
        //filler super
        super(interval);
        drawables = new ArrayList<>();
        this.interval = interval;
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                mousePos = e.getPoint();
            }
        });
        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                xPos += e.getX() - mousePos.x;
                yPos += e.getY() - mousePos.y;
                mousePos = e.getPoint();
            }
        });
        SwingUtilities.invokeLater(this);
    }

    public void run(){
        super.run();
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }
}
