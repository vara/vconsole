/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console;

import main.console.IOStream.IODataStreamInreface;
import main.klient.ConnectToServer;

/**
 *
 * @author vara
 */
public enum Command {
    
    
    BYE(new ActionCommand(){  
        
		@Override 
		public void exec(IODataStreamInreface c, String[] params){  
		    c.printf("Bye%n");		    
		    System.exit(0); 
		}
	}),
 
    DETAILS(new ActionCommand(){ 
 
        @Override  
        public void exec(IODataStreamInreface c, String[] params) throws Exception{  
            int detailsLevel = 1;  
            try{ 
                detailsLevel = Integer.parseInt(params[0]);
            }catch (NumberFormatException e){// ignore  
		
	    } 
            for (int i = 1; i <= detailsLevel; i++){ 
                c.printf("Detail number %1$s%n", ""+i); 
            }  
        } 
 
    }),
	    
    CONNECT(new ConnectToServer(){

	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    super.exec(c, params);
	}
    });    
      
 
    private ActionCommand action; 
 
    private Command(ActionCommand a){ 
        this.action = a;  
    } 
 
    public void exec(final IODataStreamInreface c, final String[] params, final CommandListenerException l){  
        
	try {  
            action.exec(c, params);  	    
        }catch (Exception e){ 
            l.exception(e);  
        } 
    } 
 
} 
