package GUI;

import javax.swing.*;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUI extends JFrame implements ActionListener {

    JFrame Problem1 = new JFrame("Problem 1");
    JFrame Problem2 = new JFrame("Problem 2");
    JFrame[] Frames = { Problem1, Problem2, this };
    JButton Problem1Button = new JButton("Problem 1", new ImageIcon("C:/Users/Muhannad/Desktop/tree.png"));
    JButton Problem2Button = new JButton("Problem 2", new ImageIcon("C:/Users/Muhannad/Desktop/tree.png"));
    JButton closeButton = new JButton("x");
    JButton returnButton = new JButton("<");
    JButton[] Buttons = { Problem1Button, Problem2Button, closeButton, returnButton };
    JLabel headerLabel = new JLabel("Trees");

    public MyGUI() {
        setTitle("Trees Problems");
        setLayout(null);
        setLocationRelativeTo(null);

        headerLabel.setBounds(400, 50, 900, 70);
        headerLabel.setFont(new Font("Arial Black", 0, 50));
        
        Problem1Button.setBounds(125, 500, 225, 50);
        
        Problem2Button.setBounds(525, 500, 225, 50);
        
        closeButton.setBounds(850, 0, 50, 50);
        closeButton.setBackground(new Color(255, 80, 80));
        
        returnButton.setBounds(0, 0, 50, 50);
        returnButton.setBackground(new Color(255, 80, 80));
        
        for (JButton button : Buttons) {
            button.addActionListener(this);
            button.setFocusable(false);
            if (button != returnButton)
                add(button);
        }
        
        for (JFrame frame :Frames) {
            frame.setLayout(null);
            frame.setUndecorated(true);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(900, 600);
            frame.getContentPane().setBackground(new Color(234, 219, 200));
            frame.add(closeButton);
            frame.add(headerLabel);
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            MyGUI gui1 = new MyGUI();
            gui1.setVisible(true);
            gui1.setLocationRelativeTo(null);
        });
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getActionCommand().equals("Problem 1")) {
            setVisible(false);
            Problem1.add(returnButton);
            Problem1.add(headerLabel);
            Problem1.setVisible(true);
            headerLabel.setText("Problem1");
            Problem1.setLocationRelativeTo(null);

        }
        if (e.getActionCommand().equals("Problem 2")) {
            setVisible(false);
            Problem2.add(returnButton);
            Problem2.add(headerLabel);
            Problem2.setVisible(true);
            headerLabel.setText("Problem2");
            Problem2.setLocationRelativeTo(null);
        }
        if (e.getActionCommand().equals("x")) {
            dispose();
            Problem1.dispose();
            Problem2.dispose();
        }
        if (e.getActionCommand().equals("<")) {
            add(headerLabel);
            headerLabel.setText("Trees");
            setVisible(true);
            Problem1.setVisible(false);
            Problem2.setVisible(false);
        }
    }

}
