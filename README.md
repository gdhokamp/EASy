# Das EASy-Projekt

Diese Software realisiert ein *E*volutionary *A*lgorithm *S*ystem (*EASy*), 
so wie ich es in meiner Diplomarbeit 1992 angedacht habe.

Die Aufgabe, die hier untersucht wird ist in Jefferson's "Tracker System" 
(Artificial Life II) beschrieben.

Ziel ist es ein System zu entwickeln, dass nicht nur die Ideen nachbildet, 
sondern auch softwaretechnisch deutlich besser aufgestellt ist. Die 
Ergebnisse, die erzielt werden, sollten nicht daraus resultieren, dass man 
Programmierfehler eingebaut hat.

Im derzeitigen Stand (24.07.2023) konnte ich bereits Fehler beseitigen, die 
an der fehlenden Initialiserung von Variablen lagen. Unklar ist auch noch, 
ob die Automaten die Umwelt korrekt ablaufen und auch die korrekte Anzahl an 
Futter aufsammeln. Dies muss auf jeden Fall noch verifiziert werden.

## Eckpunkte

### Genom

Die Größe des Genoms ist auf 453 Bits festgelegt, so wie es bei Jefferson 
beschrieben ist. Eine Verlängerung oder Verkürzung des Genoms ist derzeit 
noch nicht vorgesehen, da ich hierzu erst einmal theoretische Überlegungen 
anstellen muss.

### Phänotyp

Der Phänotyp entspricht der Interpretation des Bitstrings als Automat und 
folgt dem Jefferson-Artikel. Der Automat ist als Zustanstabelle mit 64 
Einträgen realisiert. Es gibt jeweils zwei Einträge für Futter erkannt oder 
Futter nicht erkannt.

### Organismus

Der Organismus führt die Simulation auf dem Phänotyp durch. Für eine 
Simulation bekommt der Organismus 200 Schritte Zeit, wie es bei Jefferson 
beschrieben ist.

Überlegung hier: Man könnte das Alter des Organismus mitzählen

Der Organismus sollte noch mehr Kriterien erhalten. Eine eindeutige 
Identifikation wäre auch sehr schön.

### Population

Die Größe der Population wurde auf 1000 gesetzt. Es kann sein, dass im 
Jefferson-Artikel sogar noch mehr Organismen initial angelegt wurden. Nach 
jedem Simulationsschritt werden nur noch die Organismen übernommen, die mehr 
oder genauso viel Futter aufgenommen haben, wie der Durchschnitt aller 
Organismen. Damit bevorzugt man die, die zumindest schon einmal besser als 
der Durchschnitt performt haben. Hier wäre ein weiterer Ansatzpunkt, dass 
man nur Organismen selektiert, die wirklich über dem Durchschnitt liegen

Die nicht ausgewählten Organismen werden durch komplett neue Organismen 
ersetzt. Dieses Verhalten sollte geändert werden, in dem man neue Organismen 
aus zwei selektierten mittels Vererbung erzeugt. Eventuell nimmt man dann 
auch ein Schwanken der Populationsgröße in Kauf.

### Simulation

Die Länge der Simulation habe ich immer mal wieder abgewandelt. Da die 
Simulation doch recht zügig abläuft kann man sicher auch mal in Bereiche von 
10000 oder auch 100000 Simulationsschritte vorstoßen.

Ohne bereits Mutationen eingeführt zu haben, führt der Druck der Selektion 
schon dazu, dass auf die Dauer mehr Futter eingesammelt wird. 
Interessanterweise kann das aber auch immer wieder zu einem Einbruch führen. 
Bereits beim zweiten oder dritten Versuch gab es eine relativ stabile 
Population, die aber dann doch wieder abstürzte. Das ist auf jeden Fall eine 
interessante Beobachtung.

## Betrachtungen

Ich habe die Populationsgröße auf 65536 angehoben, so wie es auch bei 
Jefferson beschrieben ist. Meine Champions kommen ohne Mutation schon auf 78 
eingesammelte Futterstücke. Dies wird nur aufgrund des Selektionsdruck 
erreicht. Die von Jefferson beschriebenen 81 sind also schon fast am unteren 
Rand der Entwicklung. Man ist dann aber nicht weiter darauf eingegangen, 
warum es mit den Automaten nicht so geklappt hat und hat sich stattdessen 
auf die neuronalen Netze konzentriert.

Meine Aufgabe wird nun sein zu versuchen die gesamte Simulation etwas 
dynamischer zu gestalten. Zum einen sollte der Automat sich aufgrund des 
Genoms ändern und eventuell auch mehr oder weniger Zustände annehmen.

Ich wollte auch mit Chromosomen und Chromosomenmutationen hantieren und 
diese mal ausprobieren. Insgesamt sollte es ein ansprechenderes genetisches 
Modell geben und dies auch angewendet werden. Dabei sollte ich aber nicht 
aus den Augen verlieren, von wo ich komme und ob die Änderungen wirklich 
Verbesserungen gebracht haben. Vielleicht muss man an einigen Punkten auch 
wieder zurückkehren und Dinge noch einmal überdenken.

26.07.2023

Ich habe inzwischen das Crossover, also die Paarung von den Organismen 
eingebaut. Wie das genau funktioniert ist eigentlich egal, auf jeden Fall 
kommt durch die Paarung keine neue Struktur in den Genpool. Alles war schon 
vorhanden und wird nur neu rekombiniert.

Die Ergebnisse sind interessant, aber auch ernüchternd. Man muss allerdings 
berücksichtigen, dass ich bisher nur mit sehr einfachen Parametern die 
Simulation gestartet habe. Meist entsprach ein Experiment 100 
Simulationsschritten.

Was mich ernüchterd hat ist, dass man sehr selten einen Champion bekommt der 
80 oder mehr Futterstücke einsammelt. Einen Champion mit 78 erhält man auch 
schon, wenn man nur den Selektionsdruck aufbaut. Allerdings kann man 
sicherlich an den Selektionskriterien auch noch einmal drehen und zum 
Beispiel sagen, dass nur Organismen selektiert werden, die um 1 oder 2 höher 
als der Schnitt lagen.

Interessant ist allerdings, dass ohne Paarung der Durchschitt der 
gesammelten Futterstücke immer sehr niedrig liegt ( so um die 12-15), 
während man mit Paarung so um die 40 im Schnitt hat.

Die relativ kurze Simulationsdauer ist sicherlich auch ein Problem und ich 
habe immer noch im Hintergrund eine Idee für ein Alterskonzept. Was ich 
damit erreichen will, dass es disruptive Ereignisse gibt. Dann stirbt der 
Champion aus und eventuell bildet sich ein Neuer. 

Ich tendiere auch dazu, dass man Populationen dynamisch macht und z.B. auch 
die Nachkommensrate an den eingesammelten Futterstücken festmacht. Alles so 
Ideen, die man systematisch untersuchen kann.