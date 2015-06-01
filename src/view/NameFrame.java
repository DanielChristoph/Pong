package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTextField;


public class NameFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2158179084222615013L;

	private MainFrame fenster = null;
	
	private JButton start = null;
	private JLabel labelSpieler1 = null;
	private JLabel labelSpieler2 = null;
	private JTextField textFieldSpieler1 = null;
	private JTextField textFieldSpieler2 = null;

	private int HEIGHT2 = 170;
	private int WIDTH2 	= 100;
	
	public NameFrame(MainFrame fenster) {
		
		super();
		this.fenster = fenster;
		
		this.init();
	}
	
	private void init() {
		this.setSize(this.WIDTH2, this.HEIGHT2);
		this.setBackground(new java.awt.Color(255, 0, 0));
		
		this.setLayout(null);
				
		this.add(this.getLabelSpieler1());
		this.add(this.getTextFieldSpieler1());
		this.add(this.getLabelSpieler2());
		this.add(this.getTextFieldSpieler2());
		this.add(this.getStart());
	}
	
	
	public JButton getStart() {
		
		if(this.start == null) {
			this.start = new JButton();
			this.start.setText("Weiter");
			this.start.setLocation(10,105);
			this.start.setSize(80,20);
			
			this.start.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					fenster.getNameFrame().setVisible(false);
					fenster.getNameFrame().setAlwaysOnTop(false);
					fenster.initThreads();
					
					fenster.getView().getPresenter().getModel().getSpieler(1).setNickname(fenster.getNameFrame().getTextFieldSpieler1().getText());
					fenster.getView().getPresenter().getModel().getSpieler(2).setNickname(fenster.getNameFrame().getTextFieldSpieler2().getText());
					
					fenster.getLabelNameSpieler1().setText(fenster.getView().getPresenter().getModel().getSpieler(1).getNickname());
					fenster.getLabelNameSpieler2().setText(fenster.getView().getPresenter().getModel().getSpieler(2).getNickname());
				}
			});
		}
		
		return start;
	}
	
	public JLabel getLabelSpieler1(){
		if(this.labelSpieler1 == null) {
			this.labelSpieler1 = new JLabel();
			this.labelSpieler1.setText("Spieler 1:");
			this.labelSpieler1.setLocation(1, 10);
			this.labelSpieler1.setSize(100, 15);
		}
		
		return this.labelSpieler1;
	}
	
	public JLabel getLabelSpieler2(){
		if(this.labelSpieler2 == null) {
			this.labelSpieler2 = new JLabel();
			this.labelSpieler2.setText("Spieler 2:");
			this.labelSpieler2.setLocation(1, 60);
			this.labelSpieler2.setSize(100, 15);
		}
		
		return this.labelSpieler2;
	}
	
	public JTextField getTextFieldSpieler1() {
		if(this.textFieldSpieler1 == null) {
			this.textFieldSpieler1 = new JTextField();
			this.textFieldSpieler1.setLocation(1, 25);
			this.textFieldSpieler1.setSize(100, 20);
		}
		
		return this.textFieldSpieler1;
	}

	public JTextField getTextFieldSpieler2() {
		if(this.textFieldSpieler2 == null) {
			this.textFieldSpieler2 = new JTextField();
			this.textFieldSpieler2.setLocation(1,75);
			this.textFieldSpieler2.setSize(100, 20);
		}
		
		return this.textFieldSpieler2;
	}
	
	public void setStart(JButton start) {
		this.start = start;
	}
}
