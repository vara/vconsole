/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console;

import main.*;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Scanner;
import java.util.Vector;
import main.commands.cd.Cd;
import main.console.IOStream.IODataStreamInreface;


/**
 *
 * @author vara
 */
public class MyConsole extends AbstractConsole{
            
    private IODataStreamInreface iostream;    
    private Users userList = new Users();
    
    public MyConsole(IODataStreamInreface iostream){
	this.iostream = iostream;
	
	String execDir = System.getProperty("user.home");
	System.getProperties().setProperty("vconsole.currentDirectory", execDir);
    }
    public String getPrompt(){	
	
	StringBuffer strBuff= new StringBuffer(AbstractConsole.PROMPT);
	String userHome = System.getProperty("user.home");
	String currDir = System.getProperty("vconsole.currentDirectory",userHome);
	
	if(currDir.startsWith(userHome)){
	    currDir="~"+currDir.substring(userHome.length(),currDir.length());
	}
	return strBuff.insert(strBuff.length()-2, currDir).toString();
    }
    
    @Override
    public boolean login(){
	iostream.printf(GREETINGS); 
        boolean accessGranted = false;  
        int attempts = 0;  
        while (!accessGranted && attempts < 3){	 
	    
	    String name = iostream.readLine(USER_PROMPT, new Date());	    
	    String passdata = iostream.readPassword(PASS_PROMPT, new Date(), name);
	  
	       if (userList.checkUser(name,passdata)){

		attempts = 0;
		accessGranted = true;
		break;
	    }
	    //wait! 2sec
	    try {Thread.sleep(2000); } catch (InterruptedException ex) {}
	    iostream.printf(DENIED_ATTEMPT, ++attempts);
        }  
        if (! accessGranted){  
            iostream.printf(ACCESS_DENIED);  
            return false; 
        }  
        iostream.printf(ACCESS_GRANTED);  
        return true;
    }

    @Override
    public void commandLoop() {
	while (true){  	    
	    
            String commandLine = iostream.readLine(getPrompt(),new Date());
            Scanner scanner = new Scanner(commandLine);  
            if (scanner.hasNext()) { 
		
                final String commandName = scanner.next().toUpperCase();
		try {  
                    final Command cmd = Enum.valueOf(Command.class, commandName);
		    
		    Vector <String> vecParams = new Vector<String>(1);		    
		    while(scanner.hasNext()){	
			//find and replace this '~' char to home path 
			String param = scanner.next();
			if(param.charAt(0)=='~')
			    param=System.getProperty("user.home")+param.substring(1);
			
			if(param.length()>1 && (param.charAt(0)=='.' && param.charAt(1)=='/')){
			    param=System.getProperty("vconsole.currentDirectory")+param.substring(1);
			}			
			if(param.length()>2 && (param.charAt(0)=='.' && param.charAt(1)=='.' && param.charAt(2)=='/')){
			    param = Cd.getUpperNoodDir(System.getProperty("vconsole.currentDirectory"))+param.substring(2);
			}
			vecParams.add(param);
		    }
		    
                    cmd.exec(iostream,(String[]) vecParams.toArray(new String[vecParams.size()]),
			    new CommandListenerException(){ 
 
                        @Override 
                        public void exception(Exception e){  
                            iostream.printf(COMMAND_ERROR, cmd, e.getMessage());  
			} 
                    });  
                }  
                catch (IllegalArgumentException e){  
                    iostream.printf(UNKNOWN_COMMAND, commandName);  
                }finally{		    
		    scanner.close(); 
		}
            }
        }
    }

    public void keyTyped(KeyEvent e) {
	System.out.println(""+e);
    }

    public void keyPressed(KeyEvent e) {
	System.out.println(""+e);
    }

    public void keyReleased(KeyEvent e) {
	System.out.println(""+e);
    }
}
