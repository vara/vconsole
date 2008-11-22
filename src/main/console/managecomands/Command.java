/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console.managecomands;

import java.io.File;
import java.util.Date;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import main.commands.cat.Cat;
import main.commands.cd.Cd;
import main.console.IOStream.IODataStreamInreface;
import main.commands.connect.ConnectToServer;
import main.commands.cp.Cp;
import main.commands.dir.Dir;
import main.commands.exec.Exec;
import main.console.managecomands.Command2.CommandListenerException;

/**
 *
 * @author vara
 */
public enum Command {
    
    
    BYE(new ActionCommand(){  
        
	    @Override 
	    public void exec(IODataStreamInreface c, String[] params){  
		 
	    }

        @Override
        public String getCommandName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
	}),
 
    DETAILS(new ActionCommand(){ 
 
        @Override  
        public void exec(IODataStreamInreface c, String[] params) throws Exception{  
              
        }

        @Override
        public String getCommandName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
 
    }),
	    
    CONNECT(new ConnectToServer(){

	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    super.exec(c, params);
	}
    }),
    TIME(new ActionCommand() {
	 
	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    
	}

        @Override
        public String getCommandName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }),
    DIR(new Dir(){
	
	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    super.exec(c, params);
	}
    }),
    DIRE(new ActionCommand() {
	
	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    
	    
	}

        @Override
        public String getCommandName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }),
    EXEC(new Exec(){
	
	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    super.exec(c, params);
	}
    }),
    CD(new Cd(){	
	
	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    super.exec(c, params);
	}
    }),
    PWD(new ActionCommand() {
	
	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    
	}

        @Override
        public String getCommandName() {
            throw new UnsupportedOperationException("Not supported yet.");
        }
    }),
    CP(new Cp(){

	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    super.exec(c, params);
	}
    }),CAT(new Cat(){
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
