package powerups;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class PowerUp extends JPanel{

	private static final long serialVersionUID = 3406990362210626777L;
	
	private int hoehe = 10;
	private int breite = 10;
	
	private JLabel label = null;
	private ImageIcon bild = null;
	private String bildpfad = "";
	
	public void init() {
		
		this.setSize(breite, hoehe);
		this.add(this.getLabel());
		
	}
	
	public JLabel getLabel() {
		
		if(this.label == null){
			
			this.label = new JLabel(this.getBild());
			this.label.setSize(this.breite, this.hoehe);
			this.label.setBackground(null);
			
		}
		
		return this.label;
		
	}

	public ImageIcon getBild(){

		if(this.bild == null){
			
			this.bild = new ImageIcon(this.getBildpfad());
			
		}
		
		return this.bild;
	}

	public String getBildpfad(){
		
		return this.bildpfad;
	
	}
	
	public void setBildpfad(String pfad){
		
		this.bildpfad = pfad;
	
	}
}
