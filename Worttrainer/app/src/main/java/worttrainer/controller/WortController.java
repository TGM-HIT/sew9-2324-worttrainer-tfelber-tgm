package worttrainer.controller;

import worttrainer.model.*;
import worttrainer.view.*;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.MalformedURLException;
import java.sql.SQLException;
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
    private WortTrainerSpeichernImpl saveMe;

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
        view.setRandom(createRandom());
        view.newPic((liste.giveWortEintrag(view.getRandom()).getsURL()));
        // create frame
        frame = new WortFrame("WortTrainer", view);
        try {
            String input = "default.txt";
            this.saveMe = new WortTrainerSpeichernText();
            trainer = saveMe.load(input);
            view.setRichtig(trainer.getAbgefragteW());
            view.setGesamt(trainer.getGeloesteW());
            trainer.setAbgefragteW(trainer.getAbgefragteW()-1);
            trainer.setGeloesteW(trainer.getGeloesteW()-1);
        } catch (IOException exception) {
            exception.printStackTrace();
        }
    }

    public void actionPerformed(String o) {
        this.random = view.getRandom();
        if (o.equals("input")) {
            trainer.selectWortEintrag(random);
            if(!view.getTextField().isEmpty()) {
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
            }
        } else if (o.equals("reset")) {
            view.setGesamt(0);
            trainer.setGeloesteW(-1);
            view.setRichtig(0);
            trainer.setAbgefragteW(-1);
        } else if (o.equals("add")) {
            try{
                liste.addWortEintrag(
                        JOptionPane.showInputDialog(null, "Geben sie einen Namen ein"),
                        JOptionPane.showInputDialog(null, "Geben sie eine URL ein")
                );
                trainer.setwListe(liste);
            }catch (Exception e){
                e.printStackTrace();
            }
        } else if(o.equals("load")){
            try {
                String input = JOptionPane.showInputDialog(null, "Welche Datei soll geladen werden?");
                if(input.endsWith(".txt")){
                    this.saveMe = new WortTrainerSpeichernText();
                } else if(input.endsWith(".db") || input.endsWith(".sqlite3") ||input.endsWith(".sqlite") ||input.endsWith(".db3")){
                    this.saveMe = new WortTrainerSpeichernSQLite();
                } else {
                    System.err.println("Datentyp ist nicht implementiert");
                }
                trainer = saveMe.load(input);
                view.setRichtig(trainer.getAbgefragteW());
                view.setGesamt(trainer.getGeloesteW());
                trainer.setAbgefragteW(trainer.getAbgefragteW()-1);
                trainer.setGeloesteW(trainer.getGeloesteW()-1);

            } catch (IOException | ClassNotFoundException | SQLException exception ) {
                exception.printStackTrace();
            }
        } else if(o.equals("save")){
            try {
                String input = JOptionPane.showInputDialog(null, "Wie soll die Datei heißen?");
                if(input.endsWith(".txt")){
                    this.saveMe = new WortTrainerSpeichernText();
                } else if(input.endsWith(".db") || input.endsWith(".sqlite3") ||input.endsWith(".sqlite") ||input.endsWith(".db3")){
                    this.saveMe = new WortTrainerSpeichernSQLite();
                } else {
                    System.err.println("Datentyp ist nicht implementiert");
                }
                trainer.setAbgefragteW(view.getGesamt());
                trainer.setGeloesteW(view.getRichtig());
                saveMe.save(input, trainer);
                trainer.setAbgefragteW(view.getGesamt()-1);
                trainer.setGeloesteW(view.getRichtig()-1);
            } catch (IOException | ClassNotFoundException | SQLException exception ) {
                exception.printStackTrace();
            }
        }
        try {
            String input = "default.txt";
            this.saveMe = new WortTrainerSpeichernText();
            trainer.setAbgefragteW(view.getGesamt());
            trainer.setGeloesteW(view.getRichtig());
            saveMe.save(input, trainer);
            trainer.setAbgefragteW(view.getGesamt()-1);
            trainer.setGeloesteW(view.getRichtig()-1);
        } catch (IOException exception ) {
            exception.printStackTrace();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        this.actionPerformed(e.getActionCommand());
    }
    public int createRandom(){
        return rand.nextInt(liste.getwEintrag().length);
    }

    public WortView getView() {
        return view;
    }

    public void setView(WortView view) {
        this.view = view;
    }

    public WortFrame getFrame() {
        return frame;
    }

    public void setFrame(WortFrame frame) {
        this.frame = frame;
    }

    public WortEintrag getEintrag() {
        return eintrag;
    }

    public void setEintrag(WortEintrag eintrag) {
        this.eintrag = eintrag;
    }

    public WortListe getListe() {
        return liste;
    }

    public void setListe(WortListe liste) {
        this.liste = liste;
    }

    public WortTrainer getTrainer() {
        return trainer;
    }

    public void setTrainer(WortTrainer trainer) {
        this.trainer = trainer;
    }

    public WortTrainerSpeichernImpl getSaveMe() {
        return saveMe;
    }

    public void setSaveMe(WortTrainerSpeichernImpl saveMe) {
        this.saveMe = saveMe;
    }

    public int getRandom() {
        return random;
    }

    public void setRandom(int random) {
        this.random = random;
    }

    public Random getRand() {
        return rand;
    }

    public void setRand(Random rand) {
        this.rand = rand;
    }
}
