import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyGraphics extends JFrame implements MouseListener, MouseMotionListener {

    int greenX = 100, greenY = 100;
    int redX = 100, redY = 100;
    int oldGreenX, oldGreenY;
    int size = 50;
    boolean moving = false;

    Socket sock;
    BufferedReader in;
    PrintWriter out;

    public MyGraphics(String title) throws IOException {
        super(title);
        this.addMouseListener(this);
        this.addMouseMotionListener(this);

        sock = new Socket("localhost", 2000);
        in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
        out = new PrintWriter(sock.getOutputStream(), true);
        this.MyFunc();

        out.println(greenX + "," + greenY);
    }

    private void MyFunc() {
        Thread redThread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        String[] arrCoordinates = in.readLine().split(",", 2);
                        redX = Integer.parseInt(arrCoordinates[0]);
                        redY = Integer.parseInt(arrCoordinates[1]);

                        System.out.println(redX + "," + redY);
                        repaint();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        });

        redThread.start();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        g.setColor(Color.red);
        g.fillRect(redX, redY, size, size);

        g.setColor(Color.green);
        g.fillRect(greenX, greenY, size, size);
    }

    @Override
    public void mousePressed(MouseEvent e) {
        int newX = e.getX();
        int newY = e.getY();
        if (newX > greenX && newX < greenX + size && newY > greenY && newY < greenY + size) {
            moving = true;
            oldGreenX = newX;
            oldGreenY = newY;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
        int newX = e.getX();
        int newY = e.getY();

        if (moving) {
            greenX += newX - oldGreenX;
            greenY += newY - oldGreenY;
            oldGreenX = newX;
            oldGreenY = newY;
            out.println(greenX + "," + greenY);

            repaint();
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        moving = false;
    }

    @Override
    public void mouseClicked(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    @Override
    public void mouseMoved(MouseEvent e) {

    }
}
