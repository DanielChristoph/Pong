package view;

import java.awt.Color;

import javax.swing.JPanel;

public class BalkenPanel extends JPanel implements Runnable{

	private static final long serialVersionUID = -3527165548569866458L;

	private FensterFrame fenster = null;
	
	// Höhe und Breite
	private int breite = 20;
	private int hoehe = 100;
	
	// Panel Farbe
	private Color farbe = Color.WHITE;
	
	// Panel Schrittweite
	private int defaultSchritt = 10;
	private int schrittweite = 0;
	
	// Tastenbelegung
	private int hoch = 0;
	private int runter = 0;
	
	private int wai = 29;
	
	public BalkenPanel(FensterFrame fenster, int hoch, int runter) {
		
		super();
		
		this.fenster = fenster;
		this.hoch = hoch;
		this.runter = runter;
		
		this.setBackground(this.farbe);
		this.setSize(this.breite, this.hoehe);
		
	}
	
	private void doMove(){
		
		if(this.schrittweite < 0 && this.getY() > 0)
		this.setLocation(this.getX(), this.getY() + this.schrittweite);
		
		if(this.schrittweite > 0 && this.getY() + this.getHeight() < this.fenster.getHeight() - this.wai)
		this.setLocation(this.getX(), this.getY() + this.schrittweite);
		
	}
	
	public void move(){
		
		try {
			Thread.sleep(10);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if(this.schrittweite != 0)
			this.doMove();
		
		this.move();
		
	}
	
	public void pressed(int taste){
		
		if(taste == hoch){
			this.schrittweite = -this.defaultSchritt;
		}
		
		if(taste == runter){
			this.schrittweite = this.defaultSchritt;
		}
		
	}
	
	public void released(){
		
		this.schrittweite = 0;
		
	}

	@Override
	public void run() {

		this.move();
		
	}
	
}
