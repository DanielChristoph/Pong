package commands;

import controller.GamePad;

public class BewegungCommand extends Command{

	private int richtung = 0;
	private GamePad xbox = null;
	
	public BewegungCommand(GamePad xbox) {
		super();
		this.xbox = xbox;
	}
	
	public int getRichtung() {
		return richtung;
	}

	public void setRichtung(int richtung) {
		this.richtung = richtung;
	}
	
	@Override
	public void execute() {
		
		if(this.richtung > 0){
			
			//System.out.println("Hoch");
			this.xbox.getPresenter().getView().getFenster().getSpieler(1).pressed(this.xbox.getPresenter().getView().getFenster().getSpieler(1).getHoch());
			
		}else if(this.richtung < 0){
			
			//System.out.println("Runter");
			this.xbox.getPresenter().getView().getFenster().getSpieler(1).pressed(this.xbox.getPresenter().getView().getFenster().getSpieler(1).getRunter());
			
		}else{
			
			this.xbox.getPresenter().getView().getFenster().getSpieler(1).released();
			
		}
		
	}

}
