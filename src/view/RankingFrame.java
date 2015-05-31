package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

import connection.dbconnection;

public class RankingFrame extends JFrame {
	/**
	 * 
	 */
	private static final long serialVersionUID = 2158179084222615013L;

	private MainFrame fenster = null;

	private JButton zurueck = null;
	private JLabel labelRanking = null;

	private int HEIGHT2 = 300;
	private int WIDTH2 = 200;

	public RankingFrame(MainFrame fenster) {

		super();
		this.fenster = fenster;

		this.init();
	}

	private void init() {
		this.setSize(this.WIDTH2, this.HEIGHT2);
		this.setBackground(new java.awt.Color(255, 0, 0));

		this.setLayout(null);

		this.add(this.getRanking());
		this.add(this.getZurueck());
	}
	
	public JLabel getRanking(){
		if(this.labelRanking == null) {
			this.labelRanking = new JLabel();
			this.labelRanking.setText("");
			this.labelRanking.setLocation(1, 1);
			this.labelRanking.setSize(200, 200);
			this.labelRanking.setHorizontalAlignment(JLabel.CENTER);
			//this.labelRanking.setBorder(BorderFactory.createEmptyBorder(10, 0, 0, 0));
		}
		dbconnection dbc = new dbconnection();
		ResultSet rs = dbc.getTop();
		try {
			labelRanking.setText("<html>Rangliste <br>");
			int rank = 1;
			while(rs.next()){
				labelRanking.setText(labelRanking.getText() + "<br>" + rank + ".\t" + rs.getString("Spieler").toString() + ":\t" + rs.getInt("Punktedifferenz"));
				rank++;
			}	
			labelRanking.setText(labelRanking.getText() + "</pre></html>");
		} catch (SQLException e) {
			e.printStackTrace();
		}
		dbc.closeConn();
		return this.labelRanking;
	}
	
	public JButton getZurueck() {
		
		if(this.zurueck == null) {
			this.zurueck = new JButton();
			this.zurueck.setText("Zurück");
			this.zurueck.setLocation(50,230);
			this.zurueck.setSize(80,20);
			
			this.zurueck.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
					fenster.getRankingFrame().setVisible(false);
					fenster.getRankingFrame().setAlwaysOnTop(false);
					
					fenster.getMenue().setVisible(true);
					fenster.getMenue().setAlwaysOnTop(true);
				}
			});
		}
		
		return zurueck;
	}
}
