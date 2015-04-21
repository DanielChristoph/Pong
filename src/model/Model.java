package model;

import presenter.Presenter;

public class Model {

	private Presenter presenter = null;
	private Spiel spiel = null;

	public Model() {

		this.spiel = new Spiel();

	}

	public Spiel getSpiel() {
		
		return this.spiel;
		
	}

	public Presenter getPresenter() {

		return this.presenter;

	}
	
	public void setPresenter(Presenter presenter) {

		this.presenter = presenter;

	}

}
