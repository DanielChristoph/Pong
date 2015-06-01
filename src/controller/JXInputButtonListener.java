package controller;

import de.hardcode.jxinput.Button;
import de.hardcode.jxinput.event.JXInputButtonEvent;
import de.hardcode.jxinput.event.JXInputButtonEventListener;
import de.hardcode.jxinput.event.JXInputEventManager;

public class JXInputButtonListener implements JXInputButtonEventListener {

		private String name = "";
	
        public JXInputButtonListener(Button button, String name)
        {
            this.name = name;    
        	JXInputEventManager.addListener( this, button );
        }

        
        @Override
        public void changed(JXInputButtonEvent arg0) {
        	
        		if(arg0.getButton().getState()){
        			
        			new InputHandler().handle(this);
        			
        		}

        }
        
        public String toString(){
        	
			return name;
			
        }

}