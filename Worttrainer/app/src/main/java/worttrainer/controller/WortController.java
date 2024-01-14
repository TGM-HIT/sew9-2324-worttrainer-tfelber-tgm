package worttrainer.controller;

import worttrainer.view.WortFrame;
import worttrainer.view.WortView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

/**
 * Lorem ipsum
 *
 * @author Thomas Felber
 * @version 14.01.2024
 */

public class WortController implements ActionListener {

    private WortView view;
    private WortFrame frame;

    public static void main(String[] args) throws IOException {
        new WortController();
    }

    public WortController() throws IOException {
        //View
        view = new WortView(this);
        // Test random pic
        view.newPic("https://live.staticflickr.com/3919/14172677717_32f107831e_b.jpg");
        // create frame
        frame = new WortFrame("WortTrainer", view);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getActionCommand();
        System.out.println(o+"");
    }
}
