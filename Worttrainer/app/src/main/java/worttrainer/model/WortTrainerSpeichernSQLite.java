package worttrainer.model;

import java.io.*;
import java.sql.*;

/**
 * Diese Klasse dient zum speichern und laden der Klasse WortTrainer
 * @author Thomas Felber
 * @version 14.01.2024
 */
public class WortTrainerSpeichernSQLite implements WortTrainerSpeichernImpl {

    private Connection database;

    public WortTrainerSpeichernSQLite() throws ClassNotFoundException, SQLException {
        Class.forName("org.sqlite.JDBC");
    }

    public Connection getDatabase() {
        return database;
    }

    @Override
    public void save(String name, WortTrainer wTrainer) throws IOException {
        // Tabelle erstellen, falls sie noch nicht existiert
        PreparedStatement statement = null;
        try {
            database = DriverManager.getConnection("jdbc:sqlite:"+name);
            statement = database.prepareStatement("CREATE TABLE IF NOT EXISTS worttrainer (id INTEGER PRIMARY KEY AUTOINCREMENT, word VARCHAR(255), url VARCHAR(255), anzahlAbgefragt INTEGER, anzahlRichtig INTEGER)");
            statement.executeUpdate();
            // Daten in die Tabelle schreiben
            statement = database.prepareStatement("INSERT INTO worttrainer (word, url, anzahlAbgefragt, anzahlRichtig) VALUES (?, ?, ?, ?)");
            for (WortEintrag eintrag : wTrainer.getwListe().getwEintrag()) {
                statement.setString(1, eintrag.getWord());
                statement.setString(2, eintrag.getsURL());
                statement.setInt(3, wTrainer.getAbgefragteW());
                statement.setInt(4, wTrainer.getGeloesteW());
                statement.executeUpdate();
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }

    @Override
    public WortTrainer load(String name) throws IOException, NumberFormatException {
        // Wortliste erstellen
        WortListe liste = new WortListe();

        // Daten aus der Tabelle lesen
        PreparedStatement statement = null;
        try {
            database = DriverManager.getConnection("jdbc:sqlite:"+name);
            statement = database.prepareStatement("SELECT word, url, anzahlAbgefragt, anzahlRichtig FROM worttrainer");
            ResultSet resultSet = statement.executeQuery();
            int ab=0, ri=0;
            while (resultSet.next()) {
                liste.addWortEintrag(new WortEintrag(resultSet.getString("word"), resultSet.getString("url")));
                ab = resultSet.getInt("anzahlAbgefragt");
                ri = resultSet.getInt("anzahlRichtig");
            }

            // Worttrainer erstellen
            WortTrainer trainer = new WortTrainer(liste);
            trainer.setAbgefragteW(ab);
            trainer.setGeloesteW(ri);
            return trainer;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

    }
}
