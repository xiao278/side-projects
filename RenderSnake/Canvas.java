import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;
import java.util.ArrayList;

public class Canvas extends JComponent implements Runnable {

    private Graphics2D graphics2D;
    Image image;
    int xPos;
    int yPos;
    Point mousePos;
    public ArrayList<Drawable> drawables;

    public Canvas() {
        drawables = new ArrayList<>();
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
                repaint();
            }
        });
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Canvas());
    }

    public void run(){
        JFrame frame = new JFrame();
        Container content = frame.getContentPane();

        content.add(this, BorderLayout.CENTER);
        frame.setSize(600, 400);
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.setVisible(true);
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
}
