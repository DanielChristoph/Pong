package main;

import presenter.Presenter;
import model.Model;
import view.View;

public class Pong {

	public static void main(String args[]) {
				
		View view = new View();
		Model model = new Model();
		
		Presenter presenter = new Presenter(view, model);
		
		presenter.startGame();
		
	}
}
