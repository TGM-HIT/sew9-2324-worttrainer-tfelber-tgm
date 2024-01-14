package worttrainer.model;

/**
 * Erstellt ein Objekt welches eine URL und ein
 * bestimmtes word speichert. Diese Klasse ermöglicht
 * ebenfalls das checken der URL und des Attribut word.
 * Außerdem überschreibt sie toString.
 * @author Thomas Felber
 * @version 14.01.2024
 */

public class WortEintrag {
    private String sURL;
    private String word;

    /**
     * Der Konstruktor fügt den Attributen die gewünschten Werte zu
     * @param word Der gewünschte Wert für das Attribut word
     * @param sURL Der gewünschte Wert für das Attribut sURL
     */
    public WortEintrag(String word, String sURL){
        setsURL(sURL);
        setWord(word);
    }
    /**
     * Diese Methode schaut ob die übergebene url das richtige Format hat
     * @return Das Ergebnis des Tests
     */
    public boolean checkUrl(String sURL){
        int sZaehler = 0;
        String http = "";
        for(int i = 0;i < sURL.length(); i++){
            if(sURL.charAt(i) == '/' &&  sZaehler < 2) {
                sZaehler++;
            }
            if(sZaehler == 2){
                for(int x = 0; x <= i; x++){
                    http += sURL.charAt(x);
                }
            }
            if(http.equals("http://") || http.equals("https://")){
                return true;
            }
        }
        return false;
    }

    /**
     * Überschreibt die Methode toString so dass folgendes Format
     * zurückgegeben wird: "word; sURL"
     * @return Das Objekt als String
     */
    @Override
    public String toString() {
        return getWord()+"; "+getsURL();
    }

    //------------------------------------------------------------------------------------

    //Getter-Methoden der Attribute
    public String getsURL() {
        return sURL;
    }
    public String getWord() {
        return word;
    }

    //------------------------------------------------------------------------------------

    //Setter-Methoden der Attribute

    /**
     * Diese Methode checkt ob die URL das gewünschte Format hat
     * Ebenso setzt sie einen Wert für das Attribut
     * @param sURL Der gewünschte Wert für das Attribut sURL
     */
    public void setsURL(String sURL) {
        if(checkUrl(sURL)){
            this.sURL = sURL;
        }
        else{
            throw new IllegalArgumentException("FormatException for sURL");
        }
    }
    /**
     * Diese Methode checkt ob das Word das gewünschte Format hat
     * Ebenso setzt sie einen Wert für das Attribut
     * @param word Der gewünschte Wert für das Attribut word
     */
    public void setWord(String word) {
        if(word.length() >= 2){
            this.word = word;
        }
        else{
            throw new IllegalArgumentException("FormatException for word");
        }
    }
}
