package worttrainer.view;

import worttrainer.controller.WortController;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Lorem ipsum
 *
 * @author Thomas Felber
 * @version 14.01.2024
 */
public class WortView extends JPanel {
    private WortController controller;
    private JLabel[] label = new JLabel[6];
    private JButton[] button = new JButton[7];
    private JTextField textField = new JTextField();
    private int richtig = 0;
    private int gesamt = 0;

    public WortView(WortController controller) throws IOException {
        this.controller = controller;

        //Layouts
        BorderLayout border = new BorderLayout();
        this.setLayout(border);
        GridLayout grid1 = new GridLayout(2, 1);
        GridLayout grid2 = new GridLayout(3, 3);


        //------------------------------------------------
        JPanel panel1 = new JPanel(grid1);
        panel1.add(label[0] = new JLabel("Welches Wort wird unten dargestellt?"));
        panel1.add(textField);
        textField.setActionCommand("input");
        textField.addActionListener(this.controller);
        this.add(panel1, BorderLayout.PAGE_START);

        //------------------------------------------------

        label[1] = new JLabel();
        this.add(label[1]);

        //------------------------------------------------
        JPanel panel2 = new JPanel(grid2);
        panel2.add(label[2] = new JLabel("Richtige Wörter:"));
        panel2.add(label[3] = new JLabel(""+richtig));
        panel2.add(button[1] = new JButton("Zurücksetzen"));
        button[1].setActionCommand("reset");
        button[1].addActionListener(this.controller);
        panel2.add(label[4] = new JLabel("Anzahl Wörter:"));
        panel2.add(label[5] = new JLabel(""+gesamt));
        panel2.add(button[2] = new JButton("Wort hinzufügen"));
        button[2].setActionCommand("add");
        button[2].addActionListener(this.controller);
        panel2.add(button[3] = new JButton("Liste Laden"));
        button[3].setActionCommand("load");
        button[3].addActionListener(this.controller);
        panel2.add(button[4] = new JButton("Liste Speichern"));
        button[4].setActionCommand("save");
        button[4].addActionListener(this.controller);
        this.add(panel2, BorderLayout.PAGE_END);
    }

    public void correct(int richtig, int gesamt){
        this.richtig = richtig+1;
        label[3].setText(""+this.richtig);
        this.gesamt = gesamt+1;
        label[5].setText(""+this.gesamt);
    }
    public void incorrect(int richtig, int gesamt){
        this.richtig = richtig;
        label[3].setText(""+this.richtig);
        this.gesamt = gesamt+1;
        label[5].setText(""+this.gesamt);
    }

    public void newPic(String url) throws MalformedURLException {
        ImageIcon icon = new ImageIcon(new URL(url));
        Image image = icon.getImage();
        image = image.getScaledInstance(500, 281, Image.SCALE_SMOOTH);
        label[1].setIcon(new ImageIcon(image));
        this.add(label[1]);
    }

    //SETTER-METHODEN

    public void setGesamt(int gesamt) {
        this.gesamt = gesamt;
        label[3].setText(""+gesamt);
    }

    public void setRichtig(int richtig) {
        this.richtig = richtig;
        label[5].setText(""+richtig);
    }

    //GETTER-METHODEN

    /**
     * Gibt den Wert des Textfields aus
     * @return der eingegebene Wert ins Textfield
     */
    public String getTextField() {
        return (this.textField).getText();
    }

    public int getGesamt() {
        return Integer.parseInt(label[5].getText());
    }

    public int getRichtig() {
        return Integer.parseInt(label[3].getText());
    }
}
