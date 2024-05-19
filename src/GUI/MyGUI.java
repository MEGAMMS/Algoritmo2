package GUI;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUI extends JFrame implements ActionListener {
    public MyGUI() {
        setTitle("first page");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(300, 250);
        getContentPane().setBackground(new Color(234, 219, 200));
        setLayout(null);

        JButton openSecondButton = new JButton("Problem 1");
        openSecondButton.setBounds(50, 30, 200, 50);
        openSecondButton.addActionListener(this);
        add(openSecondButton);
        // Create a new button (you can customize its label and position)
        JButton anotherButton = new JButton("Problem 2");
        anotherButton.setBounds(50, 100, 200, 50);
        anotherButton.addActionListener(this);
        add(anotherButton);

    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyGUI gui1 = new MyGUI();
            gui1.setVisible(true);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Problem 1")) {
            // Close the first GUI (this frame)
            dispose();
            if (e.getActionCommand().equals("Problem 2"))
                dispose();

            // Create the second GUI
            JFrame frame2 = new JFrame("Second page");
            frame2.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame2.setSize(300, 250);
            frame2.getContentPane().setBackground(new Color(200, 234, 219));

            // Add components to the second GUI (if needed)

            frame2.setVisible(true);
            JFrame frame3 = new JFrame("Second page");
            frame3.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
            frame3.setSize(300, 250);
            frame3.getContentPane().setBackground(new Color(200, 234, 219));

        }
    }

}
