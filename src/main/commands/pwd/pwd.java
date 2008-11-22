package main.commands.pwd;

import main.console.IOStream.IODataStreamInreface;
import main.console.managecomands.AbstractCommand;

/**
 * Created on 2008-11-22, 19:37:24
 * @author vara
 */
public class pwd extends AbstractCommand{

    public pwd(){
        super("pwd");
    }

    @Override
    public void exec(IODataStreamInreface c, String[] params) throws Exception {
        c.println(System.getProperty("vconsole.currentDirectory"));
    }
}
