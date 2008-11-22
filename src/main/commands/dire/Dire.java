package main.commands.dire;

import java.io.File;
import main.commands.exec.Exec;
import main.console.IOStream.IODataStreamInreface;
import main.console.managecomands.AbstractCommand;

/**
 * Created on 2008-11-22, 19:28:04
 * @author vara
 */
public class Dire extends AbstractCommand{

    public Dire(){
        super("dire");
    }

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
}
