package view;

import presenter.Presenter;

public class View {

	private Presenter presenter = null;
	
	private FensterFrame fenster = null;
	
	public View() {
		
		this.getFenster();		
	}
	
	public void setPresenter(Presenter presenter){
		
		this.presenter = presenter;
	
	}

	public Presenter getPresenter(){
		
		return this.presenter;
	
	}
	
	public FensterFrame getFenster() {
		
		if(this.fenster == null){
			this.fenster = new FensterFrame(this);
		}
		
		return fenster;
	}
	
}
