package presenter;

import model.Model;
import view.View;

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
	 * Konstruktor des Presenters mit Übergabe der View und des Models
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
	 * Gibt das Model zurück
	 * 
	 * @return
	 */
	private Model getModel() {
		
		return this.model;
		
	}
	
	/**
	 * Fügt einem Spieler einen Punkt hinzu für den Sieg
	 * 
	 * @param spieler
	 */
	public void sieg(int spieler){
		
		getModel().getSpieler(spieler).sieg();
		
	}
	
	/**
	 * Gibt die Punkte des Spielers zurück
	 * 
	 * @param spieler
	 * @return punkte
	 */
	public int getPunkte(int spieler){
		
		return this.model.getSpieler(spieler).getPunkte();
		
	}
	
}
