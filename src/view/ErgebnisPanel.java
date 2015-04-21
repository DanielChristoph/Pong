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
	private JLabel erg1 = null;
	private JLabel doppel = null;
	private JLabel erg2 = null;
	
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
		
		this.add(getErg1());
		this.add(getDoppel());
		this.add(getErg2());
				
	}

	public JLabel getErg1() {
		
		if(erg1 == null){
			
			erg1 = new JLabel("0");
			erg1.setForeground(farbe);
			erg1.setFont(new Font(schrift, Font.BOLD, fontsize));
			
		}
		
		return erg1;
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

	public JLabel getErg2() {
		
		if(erg2 == null){
			
			erg2 = new JLabel("0");
			erg2.setForeground(farbe);
			erg2.setFont(new Font(schrift, Font.BOLD, fontsize));
			
		}
		
		return erg2;
	}
	
	
	
}
