package view;

import presenter.Presenter;

public class View {

	private Presenter presenter = null;
	
	private MainFrame fenster = null;
	
	public View() {
		
		this.getFenster();	
	}
	
	public void setPresenter(Presenter presenter){
		
		this.presenter = presenter;
	
	}

	public Presenter getPresenter(){
		
		return this.presenter;
	
	}
	
	public MainFrame getFenster() {
		
		if(this.fenster == null){
			this.fenster = new MainFrame(this);
		}
		
		return fenster;
	}
	
}
