/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console.IOStream;

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

    public Object printf(String format, Object... args) {
	return iostream.printf(format, args);
    }

    public String readLine(String format, Object... args) {
	return iostream.readLine(format, args);
    }

    public String readPassword(String fmt, Object... args) {
	return new String(iostream.readPassword(fmt, args));
    }

    public void println(String str) {
	println(str);
    }
}
