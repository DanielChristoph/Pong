package presenter;

import javax.swing.JOptionPane;

import model.Model;
import view.View;
import connection.Result;
import connection.dbconnection;

/**
 * Klasse Presenter
 * 
 * @author		C.Teipen
 * @version		22.04.2015
 */
public class Presenter {

	// View und Model
	private View view = null;
	private Model model = null;
	
	/**
	 * Konstruktor des Presenters mit �bergabe der View und des Models
	 * 
	 * @param view
	 * @param model
	 */
	public Presenter(View view, Model model) {
		
		super();
		
		this.setView(view);
		this.setModel(model);
		
	}
	
	/**
	 * Startet ein Spiel
	 */
	public void startGame() {
		
		this.view.getFenster().setVisible(true);
		
	}
	
	/**
	 * Setzt die View
	 * 
	 * @param view
	 */
	public void setView(View view) {
		
		this.view = view;
		this.view.setPresenter(this);
		
	}

	/**
	 * Setzt das Model
	 * 
	 * @param model
	 */
	public void setModel(Model model) {
		
		this.model = model;
		
	}
	
	/**
	 * Gibt das Model zur�ck
	 * 
	 * @return
	 */
	public Model getModel() {
		
		return this.model;
		
	}
	
	/**
	 * Gibt die GUI zur�ck
	 * 
	 * @return
	 */
	public View getView() {
		
		return this.view;
		
	}
	
	/**
	 * F�gt einem Spieler einen Punkt hinzu f�r den Sieg
	 * 
	 * @param spieler
	 */
	public void sieg(int spieler){
		
		getModel().getSpieler(spieler).sieg();
		if (getModel().getSpieler(spieler).getPunkte() == 10) {
			JOptionPane.showMessageDialog(null, getModel().getSpieler(spieler).getNickname() + " hat gewonnen", "Gewonnen", JOptionPane.INFORMATION_MESSAGE);
			//TODO neue dbc + result Objekt, dbc.insertGameResult(result)
			dbconnection dbc = new dbconnection();
			int spieler2 = 0;
			if(spieler == 1)
				spieler2 = 2;
			else 
				spieler2 = 1;
			Result r = new Result(getModel().getSpieler(spieler).getNickname(),
					getModel().getSpieler(spieler2).getNickname(),
					getModel().getSpieler(spieler).getPunkte(),
					getModel().getSpieler(spieler2).getPunkte());
			dbc.insertGameResult(r);
			this.view.getFenster().neuesSpiel();
			this.model.reset();
			this.view.getFenster().getSpieler(1).init(1);
			this.view.getFenster().getSpieler(2).init(2);
		}
	}
	
	/**
	 * Gibt die Punkte des Spielers zur�ck
	 * 
	 * @param spieler
	 * @return punkte
	 */
	public int getPunkte(int spieler){
		
		return this.model.getSpieler(spieler).getPunkte();
		
	}
	
}
