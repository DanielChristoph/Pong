package view;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;


public class PauseFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2158179084222615013L;

	private MainFrame fenster = null;
	
	private JButton start = null;
	private JButton beenden = null;

	private int HEIGHT2 	= 100;
	private int WIDTH2 	= 100;
	
	public PauseFrame(MainFrame fenster) {
		
		super();
		this.fenster = fenster;
		
		this.init();
	}
	
	private void init() {
		this.setSize(this.WIDTH2, this.HEIGHT2);
		this.setBackground(new java.awt.Color(255, 0, 0));
		
		this.setLayout(new BorderLayout());
				
		this.add(BorderLayout.NORTH, this.getStart());
		this.add(BorderLayout.SOUTH, this.getBeenden());
	}
	
	
	public JButton getStart() {
		
		if(this.start == null) {
			this.start = new JButton();
			this.start.setText("Weiter");
			
			this.start.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					fenster.getPauseFrame().setVisible(false);
					fenster.getPauseFrame().setAlwaysOnTop(false);
					fenster.resumeGame();
				}
			});
		}
		
		return start;
	}

	public void setStart(JButton start) {
		this.start = start;
	}

	public JButton getBeenden() {
		
		if(this.beenden == null) {
			this.beenden = new JButton();
			this.beenden.setText("Beenden");
			
			this.beenden.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					fenster.getPauseFrame().setVisible(false);
					fenster.getPauseFrame().setAlwaysOnTop(false);
					fenster.setVisible(false);
					fenster = null;
					System.exit(10);
				}
			});
		}

		return beenden;
	}
}
