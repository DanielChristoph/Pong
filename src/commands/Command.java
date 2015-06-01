package commands;

import view.MainFrame;

public abstract class Command {
	
	private MainFrame view = null;
	
	public Command() {

		super();
		
	}
	
	public abstract void execute();
	
	public void setView(MainFrame view){
		
		this.view = view;
		
	}
	
	public MainFrame getView(){
		
		return this.view;
		
	}
	
}