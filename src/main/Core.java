/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import main.console.IOStream.JavaConsole;
import main.console.MyConsole;
import main.server.Server;

/**
 *
 * @author vara
 */

public class Core {

    
    public Core(boolean srv){
	
	if(!srv){
	    try{
		//MyConsole mc = new MyConsole(new SystemConsole());
		MyConsole mc = new MyConsole(new JavaConsole());

	    }catch(RuntimeException e){
		System.out.println(""+e.getMessage());
		exit(1);
	    }
	}
	else{
	    
	    Server s = new Server();
	    s.start();
	}
    }
    
    public static void main(String [] arg){
	boolean isServer = false;
	if(arg.length<1)
	{
	    System.out.println("No parameter");
	    System.exit(1);
	}
	for (String str : arg) {
	    if(str.equals("server")){
		
		System.getProperties().put("javax.net.ssl.keyStore","/home/vara/svrkeyStore");		
		isServer = true;
	    }else if(str.equals("client")) {
		System.getProperties().put("javax.net.ssl.trustStore","/home/vara/svrkeyStore");		
		isServer = false;
	    }else{
		System.out.println("bad parameters");
		System.exit(1);
	    }
	    System.getProperties().put("javax.net.ssl.keyStorePassword","wara2008");
	}
	new Core(isServer);
    }
    
    public static void exit(int status){	
	//do something before exit app 
	
	System.exit(status);
    }
}
