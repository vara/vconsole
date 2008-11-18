/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console;

import main.*;
import java.awt.event.KeyEvent;
import java.util.Date;
import java.util.Scanner;
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
	if(true){
	    commandLoop();
	}
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
	    
            String commandLine = iostream.readLine(PROMPT,new Date());  	    
            Scanner scanner = new Scanner(commandLine);  
            if (scanner.hasNext()) { 
		
                final String commandName = scanner.next().toUpperCase(); 		
                try {  
                    final Command cmd = Enum.valueOf(Command.class, commandName);  
                    String param = scanner.hasNext() ? scanner.next() : null;  
                    cmd.exec(iostream, new String[]{param}, new CommandListenerException(){ 
 
                        @Override 
                        public void exception(Exception e){  
                            iostream.printf(COMMAND_ERROR, cmd, e.getMessage());  
			} 
                    });  
                }  
                catch (IllegalArgumentException e){  
                    iostream.printf(UNKNOWN_COMMAND, commandName);  
                }finally{
		    //System.out.println("close scanner");
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
