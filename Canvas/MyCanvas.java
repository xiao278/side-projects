import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class MyCanvas extends JComponent implements Runnable {

    private Graphics2D graphics2D;
    Image image;
    int xPos;
    int yPos;
    Point mousePos;
    private Point realMousePos;
    public ArrayList<Drawable> drawables;
    JFrame frame;
    Container content;
    boolean pressed;
    Timer timer;
    int interval;
    int width, height;
    public MyCanvas(int interval, int width, int height) {
        drawables = new ArrayList<>();
        this.realMousePos = new Point(getWidth()/2, getHeight()/2);
        this.interval = interval;
        this.width = width; this.height = height;
        addMouseListener(new MouseAdapter() {

            @Override
            public void mousePressed(MouseEvent e) {
                pressed = true;
                mousePos = e.getPoint();
            }

            @Override
            public void mouseReleased(MouseEvent e) {
                pressed = false;
            }
        });

        addMouseMotionListener(new MouseMotionAdapter() {
            public void mouseDragged(MouseEvent e) {
                xPos += e.getX() - mousePos.x;
                yPos += e.getY() - mousePos.y;
                mousePos = e.getPoint();
            }

            @Override
            public void mouseMoved(MouseEvent e) {
                realMousePos = e.getPoint();
                //System.out.println(e.getX() + "," + e.getY());
            }
        });
        SwingUtilities.invokeLater(this);
    }

    public MyCanvas(int interval){
        this(interval,600,400);
    }


    public void run(){
        frame = new JFrame();
        content = frame.getContentPane();

        content.add(this, BorderLayout.CENTER);
        frame.setSize(width, height);
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        timer = new Timer(interval, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                repaint();
            }
        });
        timer.start();
    }

    protected void paintComponent(Graphics g) {
        if (image == null) {
            image = createImage(getSize().width, getSize().height);

            // this lets us draw on the image (ie. the canvas)
            graphics2D = (Graphics2D) image.getGraphics();
            // gives us better rendering quality for the drawing lines
            graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                    RenderingHints.VALUE_ANTIALIAS_ON);

            // set canvas to white with default paint color
            graphics2D.setPaint(Color.white);
            graphics2D.fillRect(0, 0, getSize().width, getSize().height);
            graphics2D.setPaint(Color.black);
            repaint();
        }
        for (Drawable drawable : drawables) {
            drawable.draw(this.graphics2D);
        }
        g.drawImage(image, 0, 0, null);
        clear();
    }
    private void clear() {
        graphics2D.setPaint(Color.white);
        graphics2D.fillRect(0, 0, getSize().width, getSize().height);
        graphics2D.setPaint(Color.black);
    }

    public void addDrawable(Drawable drawable){
        drawables.add(drawable);
        drawable.setC(this);
    }

    public int getInterval() {
        return interval;
    }

    public Vector2 getMousePos(){
        return new Vector2(realMousePos.x,realMousePos.y);
    }
}
