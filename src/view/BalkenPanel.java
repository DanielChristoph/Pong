package view;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasse BalkenPanel - Spielbalken der Oberfl�che
 * 
 * @author		C.Teipen
 * @version		22.04.2015
 */
public class BalkenPanel extends JPanel implements Runnable{

	private static final long serialVersionUID = -3527165548569866458L;

	// Fenster in dem der Balken angezeigt wird
	private MainFrame fenster = null;
	
	// H�he und Breite
	private int breite = 20;
	private int hoehe = 100;
	
	// Panel Farbe
	private Color farbe = Color.BLACK;
	
	// Label mit Hintergrundbild
	private JLabel bgpic = null;
	
	private String name = null;
	
	private int defaultSchritt = 25;
	// Schrittweite und sein Standardwert
	private int schrittweite = 0;
	
	// Tastenbelegung
	private int hoch = 0;
	private int runter = 0;
	
	// Was auch immer - Abstand von Oben
	private int wai = 29;
	
	/**
	 * Konstruktor
	 * Initialisierung des Fensters
	 * Tasten zum bewegen (hoch, runter)
	 * 
	 * @param fenster
	 * @param hoch
	 * @param runter
	 */
	public BalkenPanel(MainFrame fenster, int hoch, int runter, int spieler) {
		
		super();
		
		this.fenster = fenster;
		this.hoch = hoch;
		this.runter = runter;
		
		this.init(spieler);
		
	}
	
	/**
	 * Initialisierung der Farbe und Gr��e
	 */
	public void init(int spieler){
		this.setBackground(this.farbe);
		this.setSize(this.breite, this.hoehe);
		
		ImageIcon bg = new ImageIcon("res/balken.png");
		this.bgpic = new JLabel(bg);
		this.bgpic.setBounds(1, -(this.hoehe / 2), this.breite, this.hoehe);
		this.bgpic.setBackground(this.farbe);
		
		this.add(bgpic);
		
		if(spieler == 1){
			
			this.setLocation(this.fenster.BalkenPanelAbstand, (this.fenster.getHeight() / 2) - (this.getHeight() / 2));
		
		}else{
			
			this.setLocation(this.fenster.getWidth() - this.getWidth() - this.fenster.BalkenPanelAbstand, (this.fenster.getHeight() / 2) - (this.getHeight() / 2));
			
		}
		
	}
	
	/**
	 * Bewegt den Balken abh�ngig von der Variable <code>schrittweite</code>
	 */
	private void bewegeBalken(){
		
		// Wenn hoch und noch nicht oben angelangt
		if(this.schrittweite < 0 && this.getY() > 0)
			this.setLocation(this.getX(), this.getY() + this.schrittweite);
		
		// Wenn runter und noch nicht unten angelangt
		if(this.schrittweite > 0 && this.getY() + this.getHeight() < this.fenster.getHeight() - this.wai)
			this.setLocation(this.getX(), this.getY() + this.schrittweite);
		
	}
	
	/**
	 * Bewegt den Balken mit Delay von 10ms und nur wenn <code>schrittweite</code> ungleich 0
	 * Ruft sich danach selber auf
	 */
	public void bewegen(){
		
		if(!this.fenster.isPausiert()) {
			
			try {
			
				Thread.sleep(25);
				
				if(this.schrittweite != 0)
					this.bewegeBalken();	
				
				this.bewegen();

			} catch (InterruptedException e) {
				
				System.out.println("Balken sleep fail");
				
			} catch (Exception e) {
				
				System.out.println("Balken ultra fail: " + e.getMessage());
				
			}
			
		}
		
	}
	
	/**
	 * Die �bergebene Taste bestimmt den Wert den <code>schrittweite</code> bekommt.
	 * Dieser �berpr�ft ob <code>hoch</code> oder <code>runter</code> gedr�ckt wurde.
	 * 
	 * @param taste - gedr�ckte Taste
	 */
	public void pressed(int taste){
		
		if(taste == hoch){
			this.schrittweite = -this.defaultSchritt;
		}
		
		if(taste == runter){
			this.schrittweite = this.defaultSchritt;
		}
		
	}
	
	/**
	 * Aufruf beim loslassen der gedr�ckten Taste
	 */
	public void released(){
		
		this.schrittweite = 0;
		
	}

	/**
	 * Der Balken bewegt sich durchg�ngig. Es variiert nur die <code>schrittweite</code>.
	 */
	@Override
	public void run() {
		
		this.bewegen();
		
	}
	
	public int getHoch(){
		
		return this.hoch;
		
	}
	
	public int getRunter(){
		
		return this.runter;
		
	}
}
