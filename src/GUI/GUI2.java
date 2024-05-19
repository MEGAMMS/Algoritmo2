package GUI;

import javax.swing.*;

public class GUI2 extends JFrame {
    public GUI2() {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setTitle("GUI 2");

        JLabel label = new JLabel("This is Gui 2");
        add(label);

        pack();
    }
}