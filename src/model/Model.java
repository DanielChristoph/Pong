package model;

/**
 * Klasse Model
 * Datenbeschaffer f�r das Spiel
 * 
 * @author		C.Teipen
 * @version		22.04.2015
 */
public class Model {
	
	// Variablen f�r die beiden Spieler
	private Spieler spieler1 = null;
	private Spieler spieler2 = null;

	/**
	 * Konstruktor der Model Klasse
	 * Initialisierung der Spieler
	 */
	public Model() {

		this.spieler1 = new Spieler("Spieler 1");
		this.spieler2 = new Spieler("Spieler 2");

	}
	
	/**
	 * Gibt den Spieler zur�ck
	 * 
	 * 1 - Spieler 1
	 * 2 - Spieler 2
	 * # - NULL
	 * 
	 * @param int spieler
	 * @return Spieler
	 */
	public Spieler getSpieler(int spieler){
		
		if(spieler == 1){
			
			return this.spieler1;
			
		}else if(spieler == 1){
			
			return this.spieler2;
			
		}else{
			return null;
		}
		
	}

}
