import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Driver extends JFrame {
    Perceptron p;
    final static int WIDTH = 800;
    final static int HEIGHT = 800;
    Point[] points;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Driver demo = new Driver();
            demo.setVisible(true);
        });
    }

    public Driver() {
        points = new Point[100];
        p = new Perceptron();
        for (int i = 0; i < points.length; i++) {
            points[i] = new Point();
        }
        setTitle("Neural Network");
        setSize(WIDTH, HEIGHT);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                for (Point pt: points) {
                    float[] inputs = {pt.x, pt.y};
                    int target = pt.label;
                    p.train(inputs, target);
                    repaint();
                }
            }
            @Override public void mousePressed(MouseEvent e) {}
            @Override public void mouseReleased(MouseEvent e) {}
            @Override public void mouseEntered(MouseEvent e) {}
            @Override public void mouseExited(MouseEvent e) {}
        });
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        Graphics2D g2d = (Graphics2D) g;
        g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2d.drawLine(0, 0, WIDTH, HEIGHT);
        for (Point pt: points) {
            g2d.setColor((pt.label == 1) ? Color.gray : Color.black);
            g2d.fillOval((int)pt.x, (int) pt.y, 32, 32);
            float[] inputs = {pt.x, pt.y};
            int target = pt.label;

            int guess = p.guess(inputs);
            g2d.setColor((guess == target) ? Color.green : Color.red);
            g2d.fillOval((int)pt.x + 8, (int) pt.y + 8, 16, 16);
        }
    }

    private static class Point {
        float x, y;
        int label;

        Point() {
            this.x = (float) (Math.random() * WIDTH);
            this.y = (float) (Math.random() * HEIGHT);
            this.label = (x > y) ? 1 : -1;
        }
    }
}
