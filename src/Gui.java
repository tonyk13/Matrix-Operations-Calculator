import javax.swing.*;
import java.awt.*;

public class Gui {
    public static void main(String[] args) {
        JFrame f = new JFrame("The Matrices Project");
        f.setSize(1280, 720);
        f.setLocation(50, 0);
        final JTextArea textArea = new JTextArea(10, 40);
        f.getContentPane().add(BorderLayout.CENTER, textArea);
        final JButton button = new JButton("Click Me");
        f.getContentPane().add(BorderLayout.SOUTH, button);
        button.addActionListener(e -> textArea.append("Button was clicked\n"));

        f.setVisible(true);
    }
}
