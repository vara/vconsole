package main.commands.time;

import java.util.Date;
import main.console.IOStream.IODataStreamInreface;
import main.console.managecomands.AbstractCommand;

/**
 * Created on 2008-11-22, 19:35:37
 * @author vara
 */
public class Time extends AbstractCommand{

    public Time(){
        super("time");
    }

    @Override
    public void exec(IODataStreamInreface c, String[] params) throws Exception {
        c.println((new Date()).toString());
    }
}
