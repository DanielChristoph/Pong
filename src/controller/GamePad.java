package controller;

import presenter.Presenter;
import de.hardcode.jxinput.JXInputManager;
import de.hardcode.jxinput.directinput.DirectInputDevice;
import de.hardcode.jxinput.event.JXInputEventManager;

public class GamePad {

	private int intervall = 50;
	private DirectInputDevice xbox = null;
	
	private JXInputAxisListener axisL = null;
	private JXInputAxisListener axisR = null;
	
	private JXInputButtonListener buttonA = null;
	private JXInputButtonListener buttonB = null;
	private JXInputButtonListener buttonX = null;
	private JXInputButtonListener buttonY = null;
	private JXInputButtonListener buttonStart = null;
	
	private Presenter presenter = null;
	
	public GamePad() {

		super();
		init();
		
	}
	
	public GamePad(Presenter presenter) {

		super();
		this.presenter = presenter;
		
		init();
		
	}
	
	public void init(){
		
		try{
			
			System.load(System.getProperty("user.dir") + "\\jxinput.dll");
			
			JXInputEventManager.setTriggerIntervall( intervall );
			
			//System.out.println("Anzahl der Geräte: " + JXInputManager.getNumberOfDevices());
			
			for(int i = 0; i < JXInputManager.getNumberOfDevices(); i++){
	        	
				//System.out.println("Name: " + JXInputManager.getJXInputDevice(i).getName());
				
	            if(("Controller (XBOX 360 For Windows)").equals(JXInputManager.getJXInputDevice(i).getName())){
	            	
	                    xbox = new DirectInputDevice(i);
	                    
	            }
	            
			}
			
			if(xbox != null){
				
				//System.out.println("Unser Controller: " + getXbox().getName());
				
				getButtonA();
				getButtonB();
				getButtonX();
				getButtonY();
				getButtonStart();
				
				getAxisL();
				getAxisR();
			
			}else{
            	
            	//System.out.println("Es ist kein XBOX Controller angeschlossen. Programm wird beendet.");
            	//System.exit(1);
            	
            }
		
		}catch(Exception e){
			
			System.out.println(e.toString());
			
		}
		
	}

	/**
	 * 0 - A, 
	 * 1 - B, 
	 * 2 - X, 
	 * 3 - Y, 
	 * 4 - L1, 
	 * 5 - R1, 
	 * 6 - Select, 
	 * 7 - Start, 
	 * 8 - StickPushLeft, 
	 * 9 - StickPushRight
	 * 
	 * @return
	 */
	public DirectInputDevice getXbox() {
		
		return xbox;
		
	}
	
	public void setXbox(DirectInputDevice controller) {

		xbox = controller;
		
	}
	
	public JXInputButtonListener getButtonA() {
		
		if(buttonA == null){
			
			buttonA = new JXInputButtonListener(xbox.getButton(0), "A");
			
		}
		
		return buttonA;
	}

	public JXInputButtonListener getButtonB() {
		
		if(buttonB == null){
			
			buttonB = new JXInputButtonListener(xbox.getButton(1), "B");
			
		}
		
		return buttonB;
	}

	public JXInputButtonListener getButtonX() {
		
		if(buttonX == null){
			
			buttonX = new JXInputButtonListener(xbox.getButton(2), "X");
		
		}
		
		return buttonX;
	}

	public JXInputButtonListener getButtonY() {
		
		if(buttonY == null){
			
			buttonY = new JXInputButtonListener(xbox.getButton(3), "Y");
			
		}
		
		return buttonY;
	}
	
	public JXInputButtonListener getButtonStart() {
		
		if(buttonStart == null){
			
			buttonStart = new JXInputButtonListener(xbox.getButton(7), "Start");
			
		}
		
		return buttonStart;
	}

	public JXInputAxisListener getAxisL() {
		
		if(axisL == null){
			
			axisL = new JXInputAxisListener(xbox.getAxis(0), "links");
			axisL.setGamePad(this);
			
		}
		
		return axisL;
	}

	public JXInputAxisListener getAxisR() {
		
		if(axisR == null){
			
			axisR = new JXInputAxisListener(xbox.getAxis(1), "rechts");
			axisR.setGamePad(this);
			
		}
		
		return axisR;
	}
	
	public Presenter getPresenter(){
		
		return this.presenter;
		
	}
	
}
