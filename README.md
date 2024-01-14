# 9a.1: Worttrainer Reloaded

## Inhalt der Anwendung

Für einen Rechtschreibtrainer für Wörter (Zielgruppe Volksschulkinder) soll ein erster Entwurf der  Funktionalität erstellt werden. Die Kinder sollen dabei zu einem Bild  (z.B. einem Hund) das entsprechende Wort eintippen. Dieses Wort wird  dann mit der richtigen Schreibweise verglichen und eine entsprechende  Meldung wird angezeigt.

## Entwicklungsumgebung

- **Sprache**: Java
- **Build-Tool**: Gradle (Kotlin)



## Aufgaben 

### Kernfunktionen:

- **Bild-Wort-Paar**: Darstellung durch Klassen, die Wort-Bild-Paare speichern.
- **Gültigkeitsprüfung von URLs**: Sichergestellt durch Checks gegen null-Werte oder ungültige URLs.
- **Auswahlmechanismus**: Zufällige oder gezielte Auswahl von Wort-Bild-Paaren.
- **Trainingssession**: Nutzer tippt das Wort zum Bild. Feedback bei richtiger oder falscher Antwort.
- **Statistik**: Aufzeichnung der Gesamtanzahl sowie der Anzahl richtiger und falscher Antworten.

### 

### Persistenz:

- **Speicherformat**: Text-Files und SQLite
- **Bibliotheken**: org.xerial:sqlite-jdbc:3.44.1.0

### 

### Grafische Oberfläche:

- **Basis**: Nutzung von JOptionPane.
- Ablauf
  - Neuer Worttrainers wird erstellt -> wenn gewünscht kann auch ein anderer geladen werten
  - Auswahl und Anzeige eines Wortpaares, inklusive Statistik
  - Eingabe des Wortes und Prüfung der Richtigkeit
  - Persistierung des aktuellen Zustandes mittels Button-Klicks

## 

## Abschluss

Das Projekt dient der Vertiefung in  Softwareentwicklungsprozesse und der praktischen Anwendung von Java  unter Verwendung von Gradle. Es bietet die Möglichkeit, das Gelernte aus früheren Jahren zu integrieren und zu erweitern.