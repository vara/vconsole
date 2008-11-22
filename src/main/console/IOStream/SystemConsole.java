package main.console.IOStream;

import main.console.managecomands.AbstractConsole;
import main.console.*;
import java.io.Console;

/**
 *
 * @author vara
 */
public class SystemConsole implements IODataStreamInreface{
        
    private Console iostream = null;
    
    public SystemConsole()throws RuntimeException{
	Console con = System.console();	
	if(con!=null){
	    iostream = con;
	}else throw new RuntimeException(AbstractConsole.NO_CONSOLE);
    }

    @Override
    public Object printf(String format, Object... args) {
	return iostream.printf(format, args);
    }

    @Override
    public String readLine(String format, Object... args) {
	return iostream.readLine(format, args);
    }

    @Override
    public String readPassword(String fmt, Object... args) {
	return new String(iostream.readPassword(fmt, args));
    }

    @Override
    public void println(String str) {
	println(str);
    }
}
