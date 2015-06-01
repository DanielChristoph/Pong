package main;

import presenter.Presenter;
import model.Model;
import view.View;
import controller.*;

/**
 * Main Klasse des Spiels
 * 
 * @author		C.Teipen
 * @version		22.04.2015
 */
public class Pong {

	/**
	 * Main-Methode
	 * Initialisierung und Verkn�pfung von View, Model & Presenter
	 * 
	 * @param args
	 */
	public static void main(String args[]) {
				
		View view = new View();
		Model model = new Model();
		
		Presenter presenter = new Presenter(view, model);
		
		GamePad controller = new GamePad(presenter);
		
		presenter.startGame();
		
	}
}
