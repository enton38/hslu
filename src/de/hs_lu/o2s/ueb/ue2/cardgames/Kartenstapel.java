package de.hs_lu.o2s.ueb.ue2.cardgames;

public class Kartenstapel {

	/**
	 * Array vom Typ Spielkarte, welches alle Karten enth�lt die auf dem Stapel
	 * liegen verweist also auf Karten, die wiederum auf werte und farben Index: 0:
	 * unterste Karte; kartenA.length-1; oberste Karte
	 */
	private Spielkarte[] kartenA;

	/**
	 * Konstruktor f�r leeren Kartenstapel
	 */
	public Kartenstapel() {
		kartenA = new Spielkarte[0];
	}

	/**
	 * Konstruktor f�r Kartenstapel mit genau einer Karte
	 */
	public Kartenstapel(Spielkarte karte) {
		kartenA = new Spielkarte[1];
		kartenA[0] = karte;
	}

	/**
	 * Konstruktor zur Generierung eines Kartenstapel aus dem gegebenen Spiel
	 */
	public Kartenstapel(Kartenspiel spiel) {
		kartenA = new Spielkarte[spiel.getKartenAnzahl()];

		// kartenAA referenziert auf die im Spiel angelegten einzelnen Karten
		Spielkarte[][] kartenAA = spiel.getKartenAA();

		int index = 0;
		// mit kartenAA.length bekommen wird die L�nge des �u�eren Arrays
		for (int i = 0; i < kartenAA.length; i++) {
			// mit kartenAA[0].length holen wir uns die L�nge des inneren Arrays
			for (int j = 0; j < kartenAA[0].length; j++) {
				// hier wollen wir in dem Array kartenA die jeweilige Spielkarte abspeichern
				kartenA[index] = kartenAA[i][j];
				index++;
			}
		}
	}

	/**
	 * legt die �bergebene Karte auf den Kartenstapel
	 */
	public void push(Spielkarte newCard) {
		// ein um 1 gr��eres Array anlegen
		Spielkarte[] kartenNeu = new Spielkarte[kartenA.length + 1];

		// die bisherigen Karten in das neue Array kopieren
		System.arraycopy(kartenA, 0, kartenNeu, 0, kartenA.length);

		// die neue Karte auf den Stapel legen ("oben drauf")
		kartenNeu[kartenA.length] = newCard;

		kartenA = kartenNeu;

	}

	/**
	 * Hebt eine Karte oben vom Stapel ab
	 * 
	 * @return
	 */
	public Spielkarte pop() {
		// ein um 1 kleineres Array anlegen
		if (this.empty()) {
			System.out.println("Kartenstapel leer");
			return null;
		}
		Spielkarte[] kartenNeu = new Spielkarte[kartenA.length - 1];
		Spielkarte letzteA = kartenA[kartenA.length - 1];

		// ins neue Array kopieren bis auf die letzte Karte
		System.arraycopy(kartenA, 0, kartenNeu, 0, kartenA.length - 1);
		kartenA = kartenNeu;
		return letzteA;

	}

	/**
	 * Liefert oberste Karte vom Stapel ohne abzuheben
	 * 
	 * @return
	 */
	public Spielkarte top() {
		// gleiche wie pop, blo� ohne Array Verk�rzung
		Spielkarte letzteA = kartenA[kartenA.length - 1];
		return letzteA;
	}

	/**
	 * true, falls Stapel keine Karte enth�lt, andernfalls false
	 * 
	 * @return
	 */
	public boolean empty() {
		boolean empty = true;

		if (kartenA.length == 0) {
			return empty;
		}

		return false;
	}

	/**
	 * Ein weiteres Spiel auf den Stapel legen
	 * 
	 * @param
	 */
	public void addKartenspiel(Kartenspiel kartenspiel) {
		Spielkarte [] kartenNeu;
		int stapelgroesse = this.kartenA.length + kartenspiel.getKartenAnzahl();
		kartenNeu = new Spielkarte [stapelgroesse];
		
		System.arraycopy(this.kartenA, 0, kartenNeu, 0, this.kartenA.length);
		Kartenstapel s = new Kartenstapel(kartenspiel);
		
		System.arraycopy(s.kartenA, 0, kartenNeu, this.kartenA.length, s.kartenA.length);
		
		this.kartenA = kartenNeu;
		
		
	}
	
	

	/**
	 * Einen weiteren Stapel auf den Stapel legen
	 * 
	 * @param kartenspiel
	 */
	public void addKartenstapel(Kartenstapel kartenstapel) {

		int laenge = this.getKartenAnzahl()+kartenstapel.getKartenAnzahl();
		Spielkarte[] kartenNeu = new Spielkarte[laenge];
		
		
		System.arraycopy(this.kartenA, 0, kartenNeu, 0, this.kartenA.length);
		System.arraycopy(kartenstapel.kartenA, 0, kartenNeu, this.kartenA.length, kartenstapel.getKartenAnzahl());
		
		this.kartenA = kartenNeu;
		
		
	}

	/**
	 * Anzahl an Karten im Stapel zur�ckgeben
	 * 
	 * @return Kartenanzahl
	 */
	public int getKartenAnzahl() {
		return kartenA.length;
	}

	/**
	 * Summe der Punktewerte aller Karten zur�ckgeben
	 * 
	 * @return
	 */
	public int getPunktwert() {
		int gesamtPunktwert = 0;

		// von jeder Spielkarte den Punktwert auslesen und zum gesamtPuktwert addieren
		for (int f = 0; f < kartenA.length; f++) {
			gesamtPunktwert += kartenA[f].getPunktwert();
		}
		return gesamtPunktwert;
	}

	/**
	 * entfernt letzte Karte aus Stapel und gibt diese zur�ck
	 * 
	 * @return
	 */
	public Spielkarte last() {
		if (this.empty()) {
			System.out.println("Kartenstapel leer");
			return null;
		}
		Spielkarte[] kartenNeu = new Spielkarte[kartenA.length - 1];

		Spielkarte untersteKarte = kartenA[0];

		System.arraycopy(kartenA, 1, kartenNeu, 0, kartenA.length - 1);

		return untersteKarte;

	}

	/**
	 * gibt zuf�llige Karte zur�ck ohne diese zu entfernen
	 * 
	 * @return
	 */
	public Spielkarte popRandomCard() {
		// Erzeugen einer zuf�lligen Zahl, die in random gespeichert wird
		double random = Math.random();
		// Berechnung, welche Karte gezeigt werden soll.
		int karte = (int) (kartenA.length * random);
		// Auslesen und zur�ckgeben der zuf�lligen Karte
		Spielkarte[] kartenNeu = new Spielkarte[kartenA.length - 1];
		for (int i = 0; i < kartenNeu.length; i++) {
			int counter = 0;
			if (counter == karte) {
				counter++;
				kartenNeu[i] = kartenA[counter];

			} else {
				kartenNeu[i] = kartenA[counter];
			}
			counter++;

		}
		Spielkarte kartenReturn = new Spielkarte();
		kartenReturn = kartenA[karte];
		kartenA = kartenNeu;
		return kartenReturn;

	}

	/**
	 * alle Karten im Kartenstapel zur�ckgeben
	 */
	public String toString() {
		String myString = "Kartenstapel mit " + this.getKartenAnzahl() + " Karten, Punktwert " + this.getPunktwert()
				+ " und folgenden Karten:\n";

		for (int f = 0; f < kartenA.length - 1; f++) {
			myString += kartenA[f].toStringKurz() + ",\n";
		}
		myString += kartenA[kartenA.length - 1].toStringKurz();
		return myString;
	}
}
