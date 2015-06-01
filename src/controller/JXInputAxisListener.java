package controller;

import de.hardcode.jxinput.Axis;
import de.hardcode.jxinput.event.JXInputAxisEvent;
import de.hardcode.jxinput.event.JXInputAxisEventListener;
import de.hardcode.jxinput.event.JXInputEventManager;

public class JXInputAxisListener implements JXInputAxisEventListener {

		private String name = "";
		private GamePad xbox = null;
	
        public JXInputAxisListener( Axis axis , String name )
        {
            this.name = name;    
        	JXInputEventManager.addListener( this, axis, 0.75 );
        	
        }

        public void setGamePad(GamePad gp){
        	
        	this.xbox = gp;
        	
        }
        
        @Override
        public void changed(JXInputAxisEvent arg0) {
            
        	System.out.println( "Axis " + arg0.getAxis().getName() + " changed : value=" + arg0.getAxis().getValue() + ", event causing delta=" + arg0.getDelta() );
        	
        	if(arg0.getAxis().getName().equals("Y-Achse")){
            	
            	if(arg0.getAxis().getValue() < -0.5){
            		
	            	new InputHandler(this.xbox).handle(this, 1);            	
	            	
	            }else if(arg0.getAxis().getValue() > 0.5){
	            	
	            	new InputHandler(this.xbox).handle(this, -1);  
	            	
	            }else{
	            	
	            	new InputHandler(this.xbox).handle(this, 0); 
	            	
	            }
	        }

        }
        
        public String toString(){
        	
			return name;
			
        }
}