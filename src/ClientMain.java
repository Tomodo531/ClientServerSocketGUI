import javax.swing.*;
import java.io.IOException;

public class ClientMain {
    public static void main(String[] args) throws IOException {
        MyGraphics mg = new MyGraphics("Client");
        mg.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mg.setBounds(300, 200, 400, 500);
        mg.setVisible(true);
    }
}
