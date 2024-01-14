package worttrainer.model;

/**
 * Diese Klasse erstellt ein Array für WortEintrag
 *
 * @author Thomas Felber
 * @version 14.01.2024
 */
public class WortListe {
    private WortEintrag[] wEintrag = new WortEintrag[0];

    public WortListe(){

    }

    public WortListe(WortEintrag x){
        addWortEintrag(x);
    }
    /**
     * Erstellt ein neuen WortEintrag und fügt diesen zum Array hinzu
     * @param sURL übergibt die sUrl des neuen WortEintrags
     * @param word übergibt das word des neuen WortEintrags
     */
    public void addWortEintrag(String word, String sURL){
        addWortEintrag(new WortEintrag(word, sURL));
    }

    /**
     * Erweitert das Array um 1 und fügt einen WortEintrag hinzu
     * @param wEintrag WortEintrag der hinzugefügt werden soll
     */
    public void addWortEintrag(WortEintrag wEintrag){
        WortEintrag[] temp = new WortEintrag[this.wEintrag.length+1];
        int i = 0;
        for(; i < this.wEintrag.length; i++) {
            temp[i] = this.wEintrag[i];
        }
        temp[i] = wEintrag;
        this.wEintrag = temp;
    }

    /**
     * Gibt einen gewünschten WortEintrag zurück
     * @param index Der index des gewünschten Wortes
     * @return Der ermittelte WortEintrag des index
     */
    public WortEintrag giveWortEintrag(int index){
        return wEintrag[index];
    }

    /**
     * Retourniert das WortEintrag Array
     * @return das WortEintrag Array
     */
    public WortEintrag[] getwEintrag() {
        return wEintrag;
    }

    /**
     * Löscht einen gewünschten WortEintrag des Arrays
     * @param word Die Bezeichnung wessen WortEintrag gelöscht werden soll
     * @return Gibt zurück ob die Methode erfolgreich war
     */
    public boolean deleteWortEintrag(String word){
        int z1 = 0;
        if(wEintrag.length > 0){
            for(int i = 0; i < wEintrag.length; i++){
                if((wEintrag[i].getWord()).equals(word)){
                    WortEintrag[] temp = new WortEintrag[wEintrag.length-1];
                    for(int z = 0; z < wEintrag.length-1; z++){
                        z1 = z;
                        if((wEintrag[z].getWord()).equals(word)){
                            z++;

                        }
                        if(z < wEintrag.length) {
                            temp[z1] = wEintrag[z];
                        }
                    }
                    this.wEintrag = temp;
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Überschreibt toString und gibt im gewünschten Format an
     * @return die WortListe als String
     */
    @Override
    public String toString() {
        String tForm = "";
        for(int i = 0; i < wEintrag.length; i++){
            tForm += wEintrag[i].toString()+ "\n";
        }
        return tForm;
    }
}