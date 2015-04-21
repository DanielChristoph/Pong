package view;

import java.awt.Color;
import java.util.Random;

import javax.swing.JPanel;

public class BallPanel extends JPanel implements Runnable{

	private static final long serialVersionUID = -4071397231696900457L;

	private FensterFrame fenster = null;
	
	private Color farbe = Color.WHITE;
	private int groesse = 20;
	private int pause = 25;
	private int schrittweite = 5;
	
	private int wai = 29;
	
	public BallPanel(FensterFrame fenster) {
		super();
		this.fenster = fenster;
		this.setBackground(farbe);
		this.setSize(groesse, groesse);
	}
	
	public void init(){
		
		Random rand = new Random();
		this.setLocation((this.fenster.getWidth() / 2) - (this.getWidth() / 2), rand.nextInt(this.fenster.getHeight() - this.getHeight()) + this.getHeight());
		
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
		
		try {
			Thread.sleep(pause);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
		
	}
	
	/**
	 * Bewegt den Ball nach oben links
	 */
	public void moveOL(){
		
		if(this.getX() - this.fenster.BalkenPanelAbstand >= schrittweite && this.getY() >= schrittweite){
			
			this.setLocation(this.getX() - schrittweite, this.getY() - schrittweite);

		}
		
		if(this.getX() - this.fenster.BalkenPanelAbstand < schrittweite){
			
			if(isTouched(1))
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

		if(this.getX() + this.groesse + schrittweite <= this.fenster.getWidth() - this.fenster.BalkenPanelAbstand
				&& this.getY() >= schrittweite){
			
			this.setLocation(this.getX() + schrittweite, this.getY() - schrittweite);
			
		}
		
		if(this.getX() + this.groesse + schrittweite > this.fenster.getWidth() - this.fenster.BalkenPanelAbstand){
			
			if(isTouched(2))
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
		
		if(this.getX() - this.fenster.BalkenPanelAbstand >= schrittweite
				&& this.getY() + this.groesse + schrittweite <= this.fenster.getHeight() - wai){
			
			this.setLocation(this.getX() - schrittweite, this.getY() + schrittweite);			
			
		}
		
		if(this.getX() - this.fenster.BalkenPanelAbstand < schrittweite){
			
			if(isTouched(1))
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
		
		if(this.getX() + this.groesse + schrittweite <= this.fenster.getWidth() - this.fenster.BalkenPanelAbstand
				&& this.getY() + this.groesse + schrittweite <= this.fenster.getHeight() -wai){
			
			this.setLocation(this.getX() + schrittweite, this.getY() + schrittweite);
			
		}
		
		if(this.getX() + this.groesse + schrittweite > this.fenster.getWidth() - this.fenster.BalkenPanelAbstand){
			
			if(isTouched(2))			
			this.move(2);
			
		}else if(this.getY() + this.groesse + schrittweite > this.fenster.getHeight() - wai){
			
			this.move(1);
			
		}else{
			
			this.move(3);
			
		}
	}

	private boolean isTouched(int spieler){
		
		if(spieler == 1){
			
			if(this.getY() >= this.fenster.getSpieler1().getY() 
					&& this.getY() + this.getHeight() <= this.fenster.getSpieler1().getY() + this.fenster.getSpieler1().getHeight()){
				
				return true;
				
			}else{
				
				this.fenster.getView().getPresenter().getModel().getSpiel().getSpieler(2).sieg();
				this.fenster.getErgebnis().getErg2().setText(this.fenster.getView().getPresenter().getModel().getSpiel().getSpieler(2).getPunkte() + "");
				this.fenster.ballThread.interrupt();
				return false;
				
			}
			
		}else{
			
			if(this.getY() >= this.fenster.getSpieler2().getY() 
					&& this.getY() + this.getHeight() <= this.fenster.getSpieler2().getY() + this.fenster.getSpieler2().getHeight()){
				
				return true;
			
			}else{
				
				this.fenster.getView().getPresenter().getModel().getSpiel().getSpieler(1).sieg();
				this.fenster.getErgebnis().getErg1().setText(this.fenster.getView().getPresenter().getModel().getSpiel().getSpieler(1).getPunkte() + "");
				this.fenster.ballThread.interrupt();
				return false;
			
			}
		}
		
	}
	
	@Override
	public void run() {

		Random rand = new Random();
		this.move(rand.nextInt(3));
		
	}
}
