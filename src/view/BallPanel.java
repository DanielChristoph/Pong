package view;

import java.awt.Color;
import java.util.Random;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasse BallPanel - Ball der im Fenster dargestellt wird
 * 
 * @author		C.Teipen
 * @version		22.04.2015
 */
public class BallPanel extends JPanel implements Runnable{

	private static final long serialVersionUID = -4071397231696900457L;

	// Fenster in dem der Balken angezeigt wird
	private FensterFrame fenster = null;
	
	// Farbe des Balls
	private Color farbe = Color.BLACK;
	
	// Hintergundbild für den Ball
	private JLabel bgpic = null;
	
	// Da quadratisch nur eine Längenangabe
	private int groesse = 30;
	
	// Pause die nach jedem Schritt vom Ball gemacht wird
	private int pause = 25;
	
	// Pixel die sich der Ball pro Schritt bewegt
	private int schrittweite = 5;
	
	// Was auch immer - Abstand von Oben
	private int wai = 29;
	
	private int lastDirection = 4;
	
	/**
	 * Konstruktor des Balls mit Übergabe des Fensters in dem er dargestellt wird
	 * 
	 * @param fenster
	 */
	public BallPanel(FensterFrame fenster) {
		
		super();
		
		this.fenster = fenster;
		this.setBackground(farbe);
		
		this.init();
		
	}
	
	/**
	 * Inistialisiert den Ball
	 */
	public void init(){
		this.setSize(groesse, groesse);
		
		Random rand = new Random();
		this.setLocation((this.fenster.getWidth() / 2) - (this.getWidth() / 2), 
				rand.nextInt(this.fenster.getHeight() - this.getHeight()) + this.getHeight());
		
		ImageIcon bg = new ImageIcon("ball.png");
		this.bgpic = new JLabel(bg);
		this.bgpic.setBounds(0, 0, this.getWidth(), this.getHeight());
		this.add(bgpic);
		
		this.bgpic.setBackground(this.farbe);
		
	}
	
	/**
	 * Bestimmt die Richtung in die der Ball bewegt werden soll
	 * 
	 * 0 - Oben links
	 * 1 - Oben rechts
	 * 2 - Unten links
	 * 3 - Unten rechts
	 * 
	 * @param richtung
	 */
	public void move(int richtung){
		if(!this.fenster.isPausiert()) {
			if(this.lastDirection != 4) {
				richtung = lastDirection;
				lastDirection = 4;
			}
			
			try {
				Thread.sleep(pause);
			} catch (InterruptedException e) {
				System.out.println("Ball sleep fail");
			}
			
			switch(richtung){
			
				case 0:	moveOL();
						break;
						
				case 1:	moveOR();
						break;
	
				case 2:	moveUL();	
						break;
						
				case 3:	moveUR();
						break;
						
				default: break;
				
			}
		} else {
			this.lastDirection = richtung;
		}
	}
	
	/**
	 * Bewegt den Ball nach oben links
	 */
	public void moveOL(){
		
		if(this.getX() - (this.fenster.BalkenPanelAbstand + this.fenster.getSpieler(1).getWidth()) >= schrittweite && this.getY() >= schrittweite){
			
			this.setLocation(this.getX() - schrittweite, this.getY() - schrittweite);

		}
		
		if(this.getX() - (this.fenster.BalkenPanelAbstand + this.fenster.getSpieler(1).getWidth()) < schrittweite){
			
			if(balkenBeruehrt(1))
			this.move(1);
			
		}else if(this.getY() < schrittweite){
			
			this.move(2);
			
		}else{
			
			this.move(0);
			
		}
	}
	
	/**
	 * Bewegt den Ball nach oben rechts
	 */
	public void moveOR(){

		if(this.getX() + this.groesse + schrittweite <= this.fenster.getWidth() - (this.fenster.BalkenPanelAbstand + this.fenster.getSpieler(2).getWidth())
				&& this.getY() >= schrittweite){
			
			this.setLocation(this.getX() + schrittweite, this.getY() - schrittweite);
			
		}
		
		if(this.getX() + this.groesse + schrittweite > this.fenster.getWidth() - (this.fenster.BalkenPanelAbstand + this.fenster.getSpieler(2).getWidth())){
			
			if(balkenBeruehrt(2))
			this.move(0);
			
		}else if(this.getY() < schrittweite){
			
			this.move(3);
			
		}else{
			
			this.move(1);
			
		}
	}
	
	/**
	 * Bewegt den Ball nach unten links
	 */
	public void moveUL(){
		
		if(this.getX() - (this.fenster.BalkenPanelAbstand + this.fenster.getSpieler(1).getWidth()) >= schrittweite
				&& this.getY() + this.groesse + schrittweite <= this.fenster.getHeight() - wai){
			
			this.setLocation(this.getX() - schrittweite, this.getY() + schrittweite);			
			
		}
		
		if(this.getX() - (this.fenster.BalkenPanelAbstand + this.fenster.getSpieler(1).getWidth()) < schrittweite){
			
			if(balkenBeruehrt(1))
			this.move(3);
			
		}else if(this.getY() + this.groesse + schrittweite > this.fenster.getHeight() - wai){
			
			this.move(0);
			
		}else{
			
			this.move(2);
			
		}

	}
	
	/**
	 * Bewegt den Ball nach unten rechts
	 */
	public void moveUR(){
		
		if(this.getX() + this.groesse + schrittweite <= this.fenster.getWidth() - (this.fenster.BalkenPanelAbstand + this.fenster.getSpieler(2).getWidth())
				&& this.getY() + this.groesse + schrittweite <= this.fenster.getHeight() -wai){
			
			this.setLocation(this.getX() + schrittweite, this.getY() + schrittweite);
			
		}
		
		if(this.getX() + this.groesse + schrittweite > this.fenster.getWidth() - (this.fenster.BalkenPanelAbstand + this.fenster.getSpieler(2).getWidth())){
			
			if(balkenBeruehrt(2))		
			this.move(2);
			
		}else if(this.getY() + this.groesse + schrittweite > this.fenster.getHeight() - wai){
			
			this.move(1);
			
		}else{
			
			this.move(3);
			
		}
	}

	/**
	 * Überprüft ob der Ball einen Spielerbalken berührt hat, oder ob die Spielrunde vorbei ist
	 * Wenn die Spielrunde vorbei ist bekommt der Spieler der gewonnen hat einen Punkt
	 * 
	 * @param	spieler - Balken der berührt worden sein soll
	 * @return	Hat der Ball den Balken berührt?
	 */
	private boolean balkenBeruehrt(int spieler){
		
		if(spieler == 1 || spieler == 2){
			
			if(this.getY() >= this.fenster.getSpieler(spieler).getY() - this.getHeight() - 1
					&& this.getY() + this.getHeight() <= this.fenster.getSpieler(spieler).getY() + this.fenster.getSpieler(spieler).getHeight() + this.getHeight() - 1){
				
				return true;
				
			}else{
				
				if(spieler == 1){
					spieler = 2;
				}else{
					spieler = 1;
				}
				
				this.fenster.getView().getPresenter().sieg(spieler);
				this.fenster.getErgebnis().getErg(spieler).setText(this.fenster.getView().getPresenter().getPunkte(spieler) + "");
				
				this.fenster.ballThread.interrupt();
				this.fenster.setPausiertDurchPunkt(true);
				
				return false;
				
			}
			
		}
		
		return false;
		
	}
	
	/**
	 * Bewegt den Ball permanent auf der Oberfläche bis der Thread gestoppt wurde
	 * Startrichtung wird zufällig gewählt
	 */
	@Override
	public void run() {
		
		Random rand = new Random();
		
		this.move(rand.nextInt(3));
		
	}
}
