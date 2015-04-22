package view;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JLabel;
import javax.swing.JPanel;

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
	
	public ErgebnisPanel() {
		
		super();
		init();

	}
	
	private void init(){
		
		this.setBackground(bgcolor);
		this.setSize(WIDTH, HEIGHT);
		
		this.add(getErg(1));
		this.add(getDoppel());
		this.add(getErg(2));
				
	}

	public JLabel getErg(int spieler) {
		
		if(this.erg[spieler] == null){
			
			this.erg[spieler] = new JLabel("0");
			this.erg[spieler].setForeground(farbe);
			this.erg[spieler].setFont(new Font(schrift, Font.BOLD, fontsize));
			
		}
		
		return this.erg[spieler];
	}

	public JLabel getDoppel() {
		
		if(doppel == null){
			
			doppel = new JLabel(":");
			doppel.setForeground(farbe);
			doppel.setFont(new Font(schrift, Font.BOLD, fontsize));
			
			doppel.setLocation((this.getWidth() / 2) - (doppel.getWidth() / 2), (this.getHeight() / 2) - (doppel.getHeight() / 2));
			
		}
		
		return doppel;
	}
	
	
	
}
