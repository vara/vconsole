package main.console.managecomands;

import java.util.Vector;
import main.commands.bye.Bye;
import main.commands.cat.Cat;
import main.commands.cd.Cd;
import main.commands.connect.ConnectToServer;
import main.commands.cp.Cp;
import main.commands.details.Details;
import main.commands.dir.Dir;
import main.commands.dire.Dire;
import main.commands.exec.Exec;
import main.commands.time.Time;
import main.console.IOStream.IODataStreamInreface;

/**
 *
 * @author vara
 */
public class Command2 { 
    
    private StoreCommandClass cmdTest = null;
    
    //This variable will be set when the command was founded in StoreCommandClass
    private ActionCommand actionCommand = null;    
    
    public Command2(StoreCommandClass sc){
        cmdTest = sc;
    }
    
    public static Command2 getInstace(){
        
        StoreCommandClass scc = new StoreCommandClass();
        Vector<AbstractCommand> retStr = new Vector<AbstractCommand>(1);
        
        retStr.add(new Bye());
        retStr.add(new Details());
        retStr.add(new ConnectToServer());
        retStr.add(new Time());
        retStr.add(new Dir());
        retStr.add(new Dire());
        retStr.add(new Exec());
        retStr.add(new Cd());
        retStr.add(new Cp());
        retStr.add(new Cat());
        
        scc.addListCommand(retStr);        
        Command2 comm2 = new Command2(scc);        
        return comm2;
    }
    
    public interface  CommandListenerException {
        void exception(Exception e);      
    }
    
    public void exec(final IODataStreamInreface c, final String[] params, final CommandListenerException l){  
        
	try {  
            actionCommand.exec(c, params);  	    
        }catch (Exception e){ 
            l.exception(e);  
        } 
    }
    
    public void searchCommand(String name)throws IllegalArgumentException{
        
        if(actionCommand!=null){
            if(actionCommand.getCommandName().equals(name))
                return;
        }
        if(cmdTest!=null){            
            if((actionCommand = cmdTest.getCommand(name))==null)
                throw new IllegalArgumentException(""+name);
        }else throw new IllegalArgumentException(StoreCommandClass.class.getName()+" not Initialized !!!");
    }    
}
