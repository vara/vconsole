/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console.managecomands;

import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 *
 * @author vara
 */
public class StoreCommandClass {
    
    private static Map<String,ActionCommand> arrayCommands = new HashMap<String,ActionCommand>(1);
    
    public StoreCommandClass(){                
    }
    
    public ActionCommand getCommand(String name){        
        return arrayCommands.get(name);        
    }    
    public boolean isCommand(String name){
        if(name==null)
            return false;
        return arrayCommands.containsKey(name);
    }
    
    public int getSizeArrayCommand(){
        return arrayCommands.size();
    }
    
    public void setCommand(String name,ActionCommand ac)throws NullPointerException{
        if(name==null || ac==null)
            throw new NullPointerException("Command must be not null !!!");
        arrayCommands.put(name, ac);
    }
    
    public void addListCommand(Vector <AbstractCommand> vec){
        for (AbstractCommand ac : vec) {
            arrayCommands.put(ac.getCommandName(),ac);
        }
    }
}
