package worttrainer.view;

import javax.swing.*;

/**
 * Erstellt das Frame
 *
 * @author Thomas Felber
 * @version 14.01.2024
 */
public class WortFrame extends JFrame{
    public WortFrame(String title, JPanel panel) {
        super(title);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.add(panel);
        setSize(500,400);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}
