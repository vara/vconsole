package main.commands.bye;

import main.console.IOStream.IODataStreamInreface;
import main.console.managecomands.AbstractCommand;

/**
 * Created on 2008-11-22, 19:31:50
 * @author vara
 */
public class Bye extends AbstractCommand{

    public Bye(){
        super("bye");
    }

    @Override
    public void exec(IODataStreamInreface c, String[] params) throws Exception {
        c.printf("Bye%n");		    
	System.exit(0);
    }
}
