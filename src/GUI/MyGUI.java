package GUI;

import javax.swing.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MyGUI extends JFrame implements ActionListener {
    public MyGUI() {
        setTitle("Trees Problems");
        setUndecorated(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(900, 600);
        setLocationRelativeTo(null);
        getContentPane().setBackground(new Color(234, 219, 200));
        setLayout(null);

        
        for (int i = 0; i < buttons.length; i++) {
            buttons[i].addActionListener(this);
            buttons[i].setFocusable(false);
            if (buttons[i] != returnButton) {
                add(buttons[i]);
            }
        }
        
        Problem1Button.setBounds(125, 500, 225, 50);
        
        Problem2Button.setBounds(525, 500, 225, 50);
        
        closeButton.setBounds(850, 0, 50, 50);
        closeButton.setBackground(new Color(255, 80, 80));
        
        returnButton.setBounds(0, 0, 50, 50);
        returnButton.setBackground(new Color(255, 80, 80));
        
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
            dispose();
            Problem1.add(closeButton);
            Problem1.add(returnButton);
            Problem1.setUndecorated(true);
            Problem1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Problem1.setSize(900, 600);
            Problem1.setLocationRelativeTo(null);
            Problem1.getContentPane().setBackground(new Color(234, 219, 200));
            Problem1.setLayout(null);
            Problem1.setVisible(true);
            
        }
        if (e.getActionCommand().equals("Problem 2")) {
            dispose();
            Problem2.add(closeButton);
            Problem2.add(returnButton);
            Problem2.setUndecorated(true);
            Problem2.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            Problem2.setSize(900, 600);
            Problem2.setLocationRelativeTo(null);
            Problem2.getContentPane().setBackground(new Color(234, 219, 200));
            Problem2.setLayout(null);
            Problem2.setVisible(true);
        }
        if (e.getActionCommand().equals("x")) {
            dispose();
            Problem1.dispose();
            Problem2.dispose();
        }
        if (e.getActionCommand().equals("<")) {
            add(closeButton);
            setVisible(true);
            Problem1.dispose();
            Problem2.dispose();
        }
    }
    
    JFrame Problem1 = new JFrame("Problem 1");
    JFrame Problem2 = new JFrame("Problem 2");
    JButton Problem1Button = new JButton("Problem 1", new ImageIcon("C:\\Users\\Muhannad\\Desktop\\tree.png"));
    JButton Problem2Button = new JButton("Problem 2", new ImageIcon("C:\\Users\\Muhannad\\Desktop\\tree.png"));
    JButton closeButton = new JButton("x");
    JButton returnButton = new JButton("<");
    JButton[] buttons = {Problem1Button, Problem2Button, closeButton, returnButton};
    JLabel headerLabel = new JLabel("Trees");
}
