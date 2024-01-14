package worttrainer.controller;

import worttrainer.model.*;
import worttrainer.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Random;

/**
 * Lorem ipsum
 *
 * @author Thomas Felber
 * @version 14.01.2024
 */

public class WortController implements ActionListener {

    private WortView view;
    private WortFrame frame;
    private WortEintrag eintrag;
    private WortListe liste;
    private WortTrainer trainer;

    private int random;
    Random rand = new Random();
    public static void main(String[] args) throws IOException {
        new WortController();
    }

    public WortController() throws IOException {
        //Model
        eintrag = new WortEintrag("Hund", "https://live.staticflickr.com/3919/14172677717_32f107831e_b.jpg");
        liste = new WortListe(eintrag);
        liste.addWortEintrag("Katze", "https://www.kindernetz.de/wissen/tierlexikon/1605104091696,steckbrief-katze-102~_v-16x9@2dL_-6c42aff4e68b43c7868c3240d3ebfa29867457da.jpg");
        liste.addWortEintrag("Ente", "https://cdn.pixabay.com/photo/2021/11/02/14/18/mallard-6763331__340.jpg");
        liste.addWortEintrag("Schmetterling", "https://www.planet-wissen.de/natur/insekten_und_spinnentiere/schmetterlinge/bild-schmetterling-schwalbenschwanz-100~_v-gseagaleriexl.jpg");
        trainer = new WortTrainer(liste);
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
        this.random = view.getRandom();
        if (o.equals("input")) {
            trainer.selectWortEintrag(random);
            if (trainer.check(view.getTextField())) {
                view.correct(trainer.getGeloesteW(), trainer.getAbgefragteW());
                view.setRandom(createRandom());
                random = view.getRandom();
                trainer.selectWortEintrag(random);
                try {
                    view.newPic((liste.giveWortEintrag(random).getsURL()));
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
            } else {
                view.incorrect(trainer.getGeloesteW() + 1, trainer.getAbgefragteW());
                try {
                    view.newPic((liste.giveWortEintrag(random).getsURL()));
                } catch (MalformedURLException malformedURLException) {
                    malformedURLException.printStackTrace();
                }
            }
        } else if (o.equals("reset")) {
            view.setGesamt(0);
            trainer.setGeloesteW(-1);
            view.setRichtig(0);
            trainer.setAbgefragteW(-1);
        } else if (o.equals("add")) {
            liste.addWortEintrag(
                    JOptionPane.showInputDialog(null, "Geben sie einen Namen ein"),
                    JOptionPane.showInputDialog(null, "Geben sie eine URL ein")
            );
        }
    }
    public int createRandom(){
        return rand.nextInt(liste.getwEintrag().length);
    }
}
