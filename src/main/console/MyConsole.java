/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console;

import main.console.managecomands.AbstractConsole;
import main.console.managecomands.Command2;
import main.*;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Scanner;
import main.console.IOStream.IODataStreamInreface;
import main.console.managecomands.Command2.CommandListenerException;


/**
 *
 * @author vara
 */
public class MyConsole extends AbstractConsole{
            
    private IODataStreamInreface iostream;    
    private Users userList = new Users();
    
    private static Command2 cmd = Command2.getInstace();
    
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
		
                final String commandName = scanner.next().toLowerCase();
		try {  
                    cmd.searchCommand(commandName);
                    cmd.exec(iostream,ConsoleUtils.validateParameters(scanner),
			    new CommandListenerException(){ 
 
                        @Override 
                        public void exception(Exception e){  
                            iostream.printf(COMMAND_ERROR, cmd, e.getMessage());  
			} 
                    });  
                }  
                catch (IllegalArgumentException e){  
                    iostream.printf(UNKNOWN_COMMAND, commandName+" "+e.getMessage());  
                }finally{		    
		    scanner.close(); 
		}
            }
        }
    }

    @Override
    public void keyTyped(KeyEvent e) {
	System.out.println(""+e);
    }

    @Override
    public void keyPressed(KeyEvent e) {
	System.out.println(""+e);
    }

    @Override
    public void keyReleased(KeyEvent e) {
	System.out.println(""+e);
    }    
}
