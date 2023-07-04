import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

public class Window extends JFrame implements ActionListener {
    JButton buttonSource;

    Window(){
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,500);
        //pack();
        setVisible(true);

        buttonSource = new JButton("Исходный файл");
        add(buttonSource);
        buttonSource.addActionListener(this);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == buttonSource){
            JFileChooser jFileChooser = new JFileChooser();
            jFileChooser.setCurrentDirectory(new File("."));
            int response = jFileChooser.showOpenDialog(null);
            if (response == JFileChooser.APPROVE_OPTION) {
                Main.decryptedFile = jFileChooser.getSelectedFile(); //.getAbsolutePath();
            }
        }
    }
}
