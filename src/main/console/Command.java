/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console;

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
            // get the environment variables
	    c.println("Environment Variables");
	    Map envMap = System.getenv();	    
	    Iterator iter = envMap.keySet().iterator();
	    while(iter.hasNext()){
		Object key = iter.next();
		Object val = envMap.get(key);
		c.println(key+" = "+val);
	    }
	    // get the system properties
	    Properties prop = System.getProperties();
	    c.println("System Properties");
	    for (Object key2 : prop.keySet()) {
	      c.println(key2 + " = " +  prop.get(key2));
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
	 
	@Override
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
    DIRE(new ActionCommand() {
	
	@Override
	public void exec(IODataStreamInreface c, String[] params) throws Exception {
	    
	    boolean isPath = false;
	    for (String path : params) {
		if((new File(path)).exists())
		    isPath = true;
	    }
	    int lenghtAdd = 1;
	    if(!isPath)
		lenghtAdd++;
	    
	    //if in params didn't have path to dir or file then 
	    //we must add current dir to end array params.
	    //invokeExec - whithout any path to dir., metod return the values 
	    //from dir. where this app was invoked
	    
 	    String [] params2 = new String [params.length+lenghtAdd];
	    params2[0]= "dir";
	    for (int i = 1; i < params.length+1; i++) {
		params2[i] = params[i-1];	    
	    }
	    if(!isPath)
		params2[params.length+1] = System.getProperty("vconsole.currentDirectory");
	    
	    (new Exec()).invokeExec(c, params2);
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
	    c.println(System.getProperty("vconsole.currentDirectory"));
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
