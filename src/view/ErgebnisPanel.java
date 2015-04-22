package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * Klasse ErgebnisPanel - Anzeige des Ergebnisstandes auf der Oberfläche
 * 
 * @author		C.Teipen
 * @version		22.04.2015
 */
public class ErgebnisPanel extends JPanel {

	private static final long serialVersionUID = 1L;
	
	// Höhe und Breite
	private int HEIGHT 	= 50;
	private int WIDTH 	= 200;

	// Hintergrundfarbe
	private Color bgcolor = Color.BLACK;
	
	// Ergebnisse
	private JLabel[] erg = new JLabel[3];
	private JLabel doppel = null;
	
	// Font Eigenschaften
	private int fontsize = 32;
	private Color farbe = Color.WHITE;
	private String schrift = "Arial";
	
	/**
	 * Konstruktor des ErgebnisPanels
	 */
	public ErgebnisPanel() {
		
		super();
		init();

	}
	
	/**
	 * Initialisierung des Panels
	 */
	private void init(){
		
		this.setBackground(bgcolor);
		this.setSize(WIDTH, HEIGHT);
		
		this.add(getErg(1));
		this.add(getDoppel());
		this.add(getErg(2));
				
	}

	/**
	 * Gibt das Label mit dem Punktestand des Spielers zurück
	 * 
	 * @param spieler
	 * @return Label
	 */
	public JLabel getErg(int spieler) {
		
		if(this.erg[spieler] == null){
			
			this.erg[spieler] = new JLabel("0");
			this.erg[spieler].setForeground(farbe);
			this.erg[spieler].setFont(new Font(schrift, Font.BOLD, fontsize));
			
		}
		
		return this.erg[spieler];
	}

	/**
	 * Gibt das Label mit den Doppelpunkten zurück
	 * 
	 * @return Doppelpunkt Label
	 */
	public JLabel getDoppel() {
		
		if(doppel == null){
			
			doppel = new JLabel(":");
			doppel.setForeground(farbe);
			doppel.setFont(new Font(schrift, Font.BOLD, fontsize));
			
			doppel.setLocation((this.getWidth() / 2) - (doppel.getWidth() / 2), (this.getHeight() / 2) - (doppel.getHeight() / 2));
			
		}
		
		return doppel;
	}
	
	/**
	 * Resetet das Ergebnispanel
	 */
	public void reset(){
		
		this.getErg(1).setText("0");
		this.getErg(2).setText("0");
		
	}
	
}
