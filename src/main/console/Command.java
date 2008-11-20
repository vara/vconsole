/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console;

import java.util.Date;
import main.console.IOStream.IODataStreamInreface;
import main.commands.ConnectToServer;
import main.commands.dir.Dir;
import main.commands.exec.Exec;

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
            c.println("List of params...");
	    for (String str : params) {
		c.println(str);
	    }
	    
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
    }),
    TIME(new ActionCommand() {

	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    c.println((new Date()).toString());
	}
    }),
    DIR(new Dir(){
	
	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    super.exec(c, params);
	}
    }),
    EXEC(new Exec(){
	
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
