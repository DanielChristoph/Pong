package model;

public class Spieler {

	private int punkte = 0;
	
	public void sieg(){
		
		this.punkte++;
		
	}
	
	public int getPunkte() {
		
		return this.punkte;
	
	}
	
}
