package model;

import javax.swing.JOptionPane;

/**
 * Klasse Spieler
 * 
 * @author 		C.Teipen
 * @version		22.04.2015
 */
public class Spieler {

	// Siege die der Spiele erreicht hat
	private int punkte = 0;
	
	// Nickname des Spielers
	private String nickname = "";
	
	/**
	 * Konstruktor des Spielers
	 * 
	 * @param nickname - Name des Spielers
	 */
	public Spieler(String nickname) {
		
		this.setNickname(nickname);
		
	}
	
	/**
	 * Bei Aufruf wird die Punktanzahl um 1 hochgezählt
	 */
	public void sieg(){
		this.punkte++;		
	}
	
	/**
	 * Gibt die Anzahl der Punkte zurück
	 * 
	 * @return punkte
	 */
	public int getPunkte() {
		
		return this.punkte;
	
	}
	
	/**
	 * Setzt die Anzahl der Punkte
	 * 
	 * @return punkte
	 */
	public void setPunkte(int punkte) {
		
		this.punkte = punkte;
	
	}

	/**
	 * Gibt den Nicknamen des Spielers zurück
	 * 
	 * @return nickname
	 */
	public String getNickname() {
		
		return nickname;
		
	}

	/**
	 * Setzt den Nicknamen des Spielers
	 * 
	 * @param nickname
	 */
	public void setNickname(String nickname) {
		
		this.nickname = nickname;
		
	}
	
}
