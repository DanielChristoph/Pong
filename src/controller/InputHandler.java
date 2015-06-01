package controller;

import commands.Command;
import commands.DefaultCommand;
import commands.BewegungCommand;

public class InputHandler {
	
	private Command buttonA_;
	private Command buttonB_;
	private Command buttonX_;
	private Command buttonY_;
	private Command buttonStart_;
	
	private Command axisL_;
	private Command axisR_;
	
	private GamePad xbox = null;
	
	public InputHandler() {
		super();
	}
	
	public InputHandler(GamePad xbox) {
		super();
		this.xbox = xbox;
	}
	
	public void handle(JXInputButtonListener pressedButton){
		
		switch(pressedButton.toString()){
		
			case "A": 	getButtonA_().execute();
						break;
						
			case "B": 	getButtonB_().execute();
						break;
			
			case "X": 	getButtonX_().execute();
						break;
			
			case "Y": 	getButtonY_().execute();
						break;
			
			case "Start": 	getButtonStart_().execute();
							break;
	
		}
		
	}
	
	public void handle(JXInputAxisListener jxInputAxisListener, int richtung) {
		
		switch(jxInputAxisListener.toString()){
		
			case "links": 	((BewegungCommand)getAxisL_()).setRichtung(richtung);
							getAxisL_().execute();
							break;
						
			case "rechts": 	((BewegungCommand)getAxisR_()).setRichtung(richtung);
							getAxisR_().execute();
							break;
	
		}
		
	}

	public Command getAxisL_() {
		
		if(axisL_ == null){
			
			axisL_ = new BewegungCommand(this.xbox);
			
		}
		
		return axisL_;
	}
	
	public Command getAxisR_() {
		
		if(axisR_ == null){
			
			axisR_ = new BewegungCommand(this.xbox);
			
		}
		
		return axisR_;
	}
	
	public Command getButtonA_() {
		
		if(buttonA_ == null){
			
			buttonA_ = new DefaultCommand();
			
		}
		
		return buttonA_;
	}

	public Command getButtonB_() {

		if(buttonB_ == null){
			
			buttonB_ = new DefaultCommand();
			
		}
		
		return buttonB_;
	}

	public Command getButtonX_() {

		if(buttonX_ == null){
			
			buttonX_ = new DefaultCommand();
			
		}
		
		return buttonX_;
	}

	public Command getButtonY_() {

		if(buttonY_ == null){
			
			buttonY_ = new DefaultCommand();
			
		}
		
		return buttonY_;
	}

	public Command getButtonStart_() {

		if(buttonStart_ == null){
			
			buttonStart_ = new DefaultCommand();
			
		}
		
		return buttonStart_;
	}
	
	
	
}
