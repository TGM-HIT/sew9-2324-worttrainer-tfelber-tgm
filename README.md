Der Worttrainer ist zurück! Programmiertechnisch solltest du damit ja kein  Problem haben, jetzt geht es um den Softwareentwicklungsprozess und die  uns zur Verfügung stehenden Werkzeuge. Hier folgt zunächst eine  Erinnerung an den Inhalt des Worttrainers, dann eine Beschreibung der zu erfüllenden Anforderungen.

Die inhaltliche Aufgabenstellung ist bewusst weniger präzise  formuliert als das "Original" aus dem dritten Jahrgang. Ihr könnt  natürlich genau wie damals vorgehen, ihr dürft aber mit eurer  dazugewonnenen Erfahrung auch Dinge anders angehen. Ihr sollt aber  sehrwohl die später angeführten Werkzeuge verwenden - was insbesondere  wegen Gradle bedeutet, dass diese Aufgabe in Java (oder Kotlin) zu  implementieren ist.

Verwende dazu diesen GitHub-Classroom-Link (mit einem leeren Repository, du musst das Gradle-Projekt selbst initialisieren): https://classroom.github.com/a/qMgMAMJh

## Inhalt der Anwendung

Für einen Rechtschreibtrainer für Wörter (Zielgruppe  Volksschulkinder) soll ein erster Entwurf der Funktionalität erstellt  werden. Die Kinder sollen dabei zu einem Bild (z.B. einem Hund) das  entsprechende Wort eintippen. Dieses Wort wird dann mit der richtigen  Schreibweise verglichen und eine entsprechende Meldung wird angezeigt.

### Model

- Paare von Wörtern mit den dazugehörigen Bildern (über eine URL  gespeichert, die auf das jeweilige Bild verweist) sind als Klasse  abgebildet.
  - Objekte dieser Klasse sind durch entsprechende Checks zu jeder Zeit in einem gültigen Zustand (z.B. bzgl. `null`-Werte oder ungültiger URLs).
- Der Rechtschreibtrainer selbst ist als Klasse abgebildet.
  - Der Rechtschreibtrainer hat eine Menge an Wort-Bild-Paaren zur Verfügung. Anfangs ist kein Paar ausgewählt.
  - Um zu trainieren kann ein Wort-Bild-Paar ausgewählt werden: entweder wird (mittels Index) ein bestimmtes Paar gewählt, oder ein zufälliges  wird ausgewählt.
  - Nachdem ein Paar ausgewählt wurde, kann die Bild-URL abgerufen  werden und das zugehörige Wort geraten werden. Bei einer falschen  Antwort bleibt das Wort-Bild-Paar ausgewählt; bei einer richtigen  Antwort ist das Paar danach nicht mehr ausgewählt und es muss vor dem  nächsten Raten ein neues Paar ausgewählt werden.
  - Der Rechtschreibtrainer führt eine Statistik darüber wie oft insgesamt/richtig/falsch geraten wurde.
  - Objekte dieser Klasse sind durch entsprechende Checks zu jeder Zeit in einem gültigen Zustand (z.B. bzgl. `null`-Werte oder Statistiken); Versuche ungültige Aktionen auszuführen werden erkannt und verhindert.

### Persistenz

Eine Worttrainer-Session (bestehend aus den zur Verfügung stehenden  Wort-Bild-Paaren, dem aktuell ausgewählten Paar (falls vorhanden) sowie  der aktuellen Statistik) soll auch gespeichert werden können. Die genaue Umsetzung ist großteils dir überlassen:

- wähle ein beliebiges Speicherformat (z.B. selbst festgelegt, Java-Serialisierung, JSON, XML, SQLite, ...)
- benutze beliebige dafür geeignete Bibliotheken, falls notwendig (z.B. [`org.json:json`](https://mvnrepository.com/artifact/org.json/json), eine von [zahlreichen](https://www.baeldung.com/java-xml) XML-Libraries, [`org.xerial:sqlite-jdbc`](https://mvnrepository.com/artifact/org.xerial/sqlite-jdbc))
- wähle zur Integration der Persistierung in deine Anwendung ein  sinnvolles Pattern, sodass die Speicherstrategie austauschbar bleibt.

### Grafische Oberfläche

Ermögliche es, den Worttrainer über eine grafische Oberfläche zu benutzen. Es ist dabei ausreichend, `JOptionPane` zu benutzen. Der Ablauf wäre dann etwa so:

- Beim Programmstart werden die persistierten Daten geladen. Falls  keine Daten persistiert sind, wird ein neuer Worttrainer mit einigen fix einprogrammierten Wortpaaren erstellt.
- Danach werden die folgenden Schritte wiederholt:
  - Ein Wortpaar wird zufällig ausgewählt.
  - Mittels `JOptionPane` wird die bisherige Statistik und  das aktuelle Bild angezeigt. Falls es nicht der erste Versuch ist, wird  auch angezeigt ob der vorherige Versuch richtig oder falsch war.  Außerdem wird hier eine Eingabemöglichkeit für das Wort gegeben.
  - Sofern die Eingabe nicht leer ist, wird die Eingabe überprüft und es geht von vorne los. Ansonsten wird die Schleife abgebrochen.
- Zum Schluss wird der aktuelle Zustand des Worttrainers persistiert.

Andere GUI-Technologien sind auch erlaubt, wenn eine zumindest gleichwertige Funktionalität gewährleistet ist.

## Anforderungen bzgl. Entwicklungsprozess

Neben der Umsetzung der geforderten Funktionalität fließt vor Allem  die Vorgangsweise bei der Entwicklung in die Beurteilung ein:

- Die gesamte Anwendung muss von Anfang an in einem Git-Repository und mit Gradle als Build-System entwickelt werden. Verwende dazu diesen  GitHub-Classroom-Link (mit einem leeren Repository, du musst das  Gradle-Projekt selbst initialisieren): 

  https://classroom.github.com/a/qMgMAMJh

  - Gib dem Repository von Anfang an zumindest ein minimales `README.md`.

- Die Reihenfolge, in der die geforderten Features umgesetzt werden,  ist dir überlassen: Du kannst zuerst das gesamte Model umsetzen, dann  Persistenz, dann GUI. Du kannst aber z.B. auch zuerst auf die  Statistiken verzichten, die GUI hinzufügen, und dann erst den Rest  ergänzen. Bei jedem Feature gilt aber: 

  der Entwurf kommt vor der Implementierung

  , und das muss auch durch die Commit History so ersichtlich sein! Die  Entwicklung für jedes Feature sollte also etwa so ablaufen:

  - Erweitere **zuerst** deinen Entwurf  (UML-Klassendiagramm) um die geplanten Klassen, Methoden, Assoziationen, ... Committe diese Änderungen, inklusive einem aktualisierten  png-Export des Diagramms!
  - Implementiere das neue Feature; mache wo sinnvoll auch zwischendurch Commits.
  - Stelle sicher, dass spätestens nach Abschluss des Features der Code **getestet** und **dokumentiert** ist - also *automatisierte* Testfälle und Javadoc-Kommentare vorhanden sind. Korrigiere ggf. das  Klassendiagramm, falls du bei der Umsetzung vom Plan abgewichen bist.

- Stelle spätestens beim Abschließen, besser aber schon während der  Entwicklung sicher, dass das README das Programm aussagekräftig  beschreibt: was kann das Programm und welche Technologien wurden  eingesetzt? Gibt es als Anwender etwas zu beachten?

### Erweiterte Anforderungen

setze zusätzlich zwei (EKü) bzw. drei (EKv) dieser Punkte um:

- Binde zumindest eine externe Bibliothek (außer JUnit) mittels Gradle ein und verwende diese sinnvoll in deinem Worttrainer.
- Verwende Github Actions, um mindestens einen Aspekt des  Software-Entwicklungsprozesses (z.B. Testausführung,  Jar-File-Erstellung, Javadoc-Generierung, ...) weiter zu automatisieren.
- Beteilige dich am Worttrainer eines Mitschülers/einer Mitschülerin:  verwende Issue Tracking und/oder einen Pull Request, um eine (nicht  triviale) Verbesserung vorzuschlagen.
  - Dieser Punkt ist erst bei erfolgreicher Erledigung des Issues/Pull Requests abgeschlossen.
  - Achte auf eine konstruktive Haltung bei der Zusammenarbeit.
- Ermögliche einem Mitschüler/einer Mitschülerin die Beteiligung an  deinem Worttrainer: behebe ein (nicht triviales) von jemand anderem als  Issue gemeldetes Problem
  - Dieser Punkt ist erst bei erfolgreicher Erledigung des Issues  abgeschlossen. Einfach nur einen fertigen Pull Request zu mergen zählt  nicht!
  - Achte auf eine konstruktive Haltung bei der Zusammenarbeit.

## Abgabe & Beurteilung

Trage zur Abgabe einen direkten Github-Link auf den letzten **Commit** in deinem Repository ein. Dieser Link müsste also so aussehen: `https://github.com/TGM-HIT/sew9-2324-worttrainer-<github-username>/commit/<commit-id>;`

Die Beurteilung erfolgt in einem Abgabegespräch.