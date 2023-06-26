import javax.swing.*;
import java.awt.*;

public class Window {
    public Window() {
    }

    public JFrame getjFrame() {
        JFrame jFrame = new JFrame();
        jFrame.setVisible(true);
        jFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Toolkit toolkit = Toolkit.getDefaultToolkit();
        Dimension dimension = toolkit.getScreenSize();
        jFrame.setBounds(dimension.width / 2 - 300, dimension.height / 2 - 200, 600, 400);
        jFrame.setTitle("Caesar encrypt/decrypt file app");
        return jFrame;
    }
}
