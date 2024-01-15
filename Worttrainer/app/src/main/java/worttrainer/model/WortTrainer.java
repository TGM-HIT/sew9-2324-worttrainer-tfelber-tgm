package worttrainer.model;

/**
 * Diese Klasse dient zum Testen verschiedener Funktionen in WortEintrag;
 *
 * @author Thomas Felber
 * @version 11.01.2024
 */
public class WortTrainer {
    private WortListe wListe;
    int x = 0;
    int abgefragteW=-1;
    int geloesteW=-1;

    public WortTrainer(WortListe wL){
        wListe = wL;
    }
    /**
     * Diese Methode gibt dir einen zufälligen WortEintrag der Klasse
     * WortListe zurück
     * @return Ein zufälliger WortEintrag
     */
    public WortEintrag randomWortEintrag(){
        x = (int)(Math.random()*(((wListe.getwEintrag()).length)));
        return wListe.giveWortEintrag(x);
    }

    /**
     * Diese Methode wählt einen WortEintrag aus
     * @param index Der index von dem gewünschten WortEintrag
     */
    public void selectWortEintrag(int index){
        x = index;
    }

    /**
     * Gibt dir den aktuellen WortEintrag zurück
     * @return der WortEintrag der aktuell ausgewählt ist.
     */
    public WortEintrag giveCurrentWortEintrag(){
        return wListe.giveWortEintrag(x);
    }

    /**
     * Schaut ob das übergebene Wort den ausgewählten WortEintrag entspricht
     * @param word das zu überprüfende Wort
     * @return Ob es übereinstimmt
     */
    public boolean check(String word){
        if((wListe.giveWortEintrag(x).getWord()).equals(word)){
            geloesteW +=1;
            abgefragteW +=1;
        }
        else{
            abgefragteW += 1;
        }
        return (wListe.giveWortEintrag(x).getWord()).equals(word);
    }

    /**
     * Funktioniert wie check nur werden die Groß-/ Kleinschreibung ignoriert
     * @param word das zu überprüfende Wort
     * @return Ob es übereinstimmt
     */
    public boolean checkIgnoreCase(String word){
        if(((wListe.giveWortEintrag(x).getWord()).toLowerCase()).equals(word.toLowerCase())){
            geloesteW +=1;
            abgefragteW +=1;
        }
        else{
            abgefragteW += 1;
        }
        return ((wListe.giveWortEintrag(x).getWord()).toLowerCase()).equals(word.toLowerCase());

    }

    /**
     * Gibt einen WortEintrag in einem bestimmten Index zurück
     * @param index der gewünschte index
     * @return der gewünschte Worteintrag
     */
    public WortEintrag getWortEintrag(int index){
        return (wListe.getwEintrag()[index]);
    }

    //SETTER-METHODEN

    public void setAbgefragteW(int abgefragteW) {
        this.abgefragteW = abgefragteW;
    }

    public void setGeloesteW(int geloesteW) {
        this.geloesteW = geloesteW;
    }


    //GETTER-METHODEN

    public int getAbgefragteW() {
        return abgefragteW;
    }

    public int getGeloesteW() {
        return geloesteW;
    }

    public WortListe getwListe() {
        return wListe;
    }

    public void setwListe(WortListe wListe) {
        this.wListe = wListe;
    }
}
