/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console;

import java.awt.event.KeyListener;

/**
 *
 * @author vara
 */
public abstract class AbstractConsole implements KeyListener{
    
    //system
    public static final String NO_CONSOLE = "Error: Console unavailable";  
    public static final String GREETINGS = "Welcome to the System. Please login.%n";  
    public static final String DENIED_ATTEMPT = "Wrong user name or password [%1$d]%n"; 
    public static final String ACCESS_DENIED = "Access denied%n"; 
    public static final String ACCESS_GRANTED = "Access granted%n";  
    public static final String UNKNOWN_COMMAND = "Unknown command [%1$s]%n"; 
    public static final String COMMAND_ERROR = "Command error [%1$s]: [%2$s]%n"; 
    public static final String TIME_FORMAT = "%1$tH:%1$tM:%1$tS"; 
    public static final String PROMPT = TIME_FORMAT + " $ "; 
    public static final String USER_PROMPT = TIME_FORMAT + " User: ";  
    public static final String PASS_PROMPT = TIME_FORMAT + " Password [%2$s]: ";
    //net
    public static final String CONNECTION_CLOSED ="Connection to [%s] closed.";
    
    public AbstractConsole(){
    }
    
    abstract public boolean login();
    abstract public void commandLoop();
}
