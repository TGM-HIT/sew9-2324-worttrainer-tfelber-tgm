package worttrainer.model;

import javax.swing.*;
import java.io.*;
import java.util.Scanner;

/**
 * Diese Klasse dient zum speichern und laden der Klasse WortTrainer
 * @author Thomas Felber
 * @version 14.01.2024
 */
public class WortTrainerSpeichernText implements WortTrainerSpeichernImpl {


    /**
     * Speichern Methode für WortTrainer in einer txt Datei
     * @param name Wie das File heißen soll
     * @param wTrainer Der WortTrainer der gespeichert werden soll
     */
    public void save(String name, WortTrainer wTrainer) throws IOException {
        File file = new File(name);
        PrintWriter outStream = null;
        String save = "";
        try {
            outStream = new PrintWriter(file);
            for (int i = 0; i < ((wTrainer.getwListe().getwEintrag()).length); ++i) {
                save += wTrainer.getWortEintrag(i).getWord()+"\n"+wTrainer.getWortEintrag(i).getsURL()+"\n\n";
            }
            save += wTrainer.getAbgefragteW()+"\n"+wTrainer.getGeloesteW();

        } catch ( IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        outStream.print(save);
        if (!save.equals(""))
            outStream.close();
    }

    /**a
     * Lädt einen Worttrainer aus einer txt Datei
     * @param name Gibt den Namen der txt Datei an
     * @return Gibt den geladenen WortTrainer zurück
     */
    public WortTrainer load(String name) throws IOException, NumberFormatException {
        WortListe liste = new WortListe();
        int richtig = 0;
        int abgefragt = 0;
        try (Scanner scanner = new Scanner(new BufferedReader(new FileReader(name)))) {
            while (scanner.hasNextLine()) {
                if(scanner.hasNextInt()) {
                    abgefragt = scanner.nextInt();
                    richtig = scanner.nextInt();
                }else {
                    String word = scanner.next();
                    String sUrl = scanner.next();
                    liste.addWortEintrag(new WortEintrag(word, sUrl));
                }
            }
        } catch (IOException | NumberFormatException e) {
            JOptionPane.showMessageDialog(null, e.getMessage());
        }
        WortTrainer trainer = new WortTrainer(liste);
        trainer.setAbgefragteW(abgefragt);
        trainer.setGeloesteW(richtig);
        return trainer;
    }
}

