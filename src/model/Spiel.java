package model;

public class Spiel {

	private Spieler sp1 = null;
	private Spieler sp2 = null;
	
	public Spiel() {
		
		this.sp1 = new Spieler();
		this.sp2 = new Spieler();
		
	}
	
	public Spieler getSpieler(int spieler) {
		
		if(spieler == 1)
			return this.sp1;
		else
			return this.sp2;
		
	}
	
}
