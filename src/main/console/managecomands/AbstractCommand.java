/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console.managecomands;

/**
 *
 * @author vara
 */
public abstract class AbstractCommand implements ActionCommand{

    private String commandname;
    
    public AbstractCommand(String name){
        commandname = name;
    }
    
    @Override
    public String getCommandName(){
        return commandname;
    }

    @Override
    public String toString() {
        return commandname;
    }
    
}
