/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.commands.exec;

import java.io.InputStream;
import java.io.InputStreamReader;
import main.console.ActionCommand;
import main.console.IOStream.AbstractIODataStream;
import main.console.IOStream.IODataStreamInreface;
import main.console.IOStream.IOStream;
import main.console.IOStream.JavaConsole;

/**
 *
 * @author vara
 */
public class Exec implements ActionCommand,Runnable{

    public Exec(){	
    }
    
    public static void main(String args[]){
	try {
	    String [] myarg = {"ls","-l"};
	    (new Exec()).invokeExec(new JavaConsole(),myarg);
	} catch (Exception ex) {
	    System.out.println(""+ex);
	}
    }
    
    public void invokeExec(IODataStreamInreface c,String [] args) throws Exception{
        
	try{
	    Process proc = null;
	    String str = System.getProperty("os.name");
	    
	    if(str.equals("Linux")){
		
		c.printf("Execing %s%n",(Object[])args);
		Runtime rt= Runtime.getRuntime();
		proc = rt.exec(args);
		
	    }else if(str.equals("Windows NT")){
		
		String[] cmd = new String[3];
		cmd[0] = "cmd.exe" ;
		cmd[1] = "/C" ;
		cmd[2] = args[0];
		c.println("Execing "+cmd[0]+" "+cmd[1]+" "+cmd[2]);
		Runtime rt= Runtime.getRuntime();
		proc = rt.exec(cmd);
	    }                  
                                   
	    InputStream inputErr = proc.getErrorStream();
	    
	    IOStream iostream = new IOStream(new InputStreamReader(proc.getInputStream()),
			    ((AbstractIODataStream)c).getOutputStreamWriter());
	    
	    String retStr=null;
	    while((retStr = iostream.readLine(""))!=null){
		iostream.println(retStr);
	    }
	    
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);        
        
	} catch (Throwable t){ throw new Exception(t.getMessage());}
    }

    public void exec(IODataStreamInreface c, String[] params) throws Exception {
	invokeExec(c, params);
    }

    public void run() {
    }
}
