package presenter;

import model.Model;
import view.View;

public class Presenter {

	private View view = null;
	private Model model = null;
	
	public Presenter(View view, Model model) {
		
		super();
		
		this.setView(view);
		this.setModel(model);
		
	}
	
	public void startGame() {
		getView().getFenster().setVisible(true);
	}

	public View getView() {
		return view;
	}

	public void setView(View view) {
		this.view = view;
		this.view.setPresenter(this);
	}

	public Model getModel() {
		return model;
	}

	public void setModel(Model model) {
		this.model = model;
		this.model.setPresenter(this);
	}

	
	
}
