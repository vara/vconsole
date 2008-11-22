/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.commands.exec;

import main.console.managecomands.AbstractCommand;
import main.console.IOStream.IODataStreamInreface;
import main.console.IOStream.IOStream;
import main.console.IOStream.JavaConsole;

/**
 *
 * @author vara
 */
public class Exec extends AbstractCommand{

    public Exec(){
        super("exec");
    }
    
    public static void main(String args[]){
	try {
	    String [] myarg = {"ls","-l"};
	    Exec ex = new Exec();
	    ex.invokeExec(new JavaConsole(),myarg);
	    ex.invokeExec(new JavaConsole(),new String [] {"pwd"});
	    ex.invokeExec(new JavaConsole(),new String [] {"cd .."});
	    ex.invokeExec(new JavaConsole(),new String [] {"pwd"});
	    
	} catch (Exception ex) {
	    System.out.println(""+ex);
	}
    }
    
    public void invokeExec(IODataStreamInreface c,String [] args) throws Exception{
        
	try{
	    Process proc = null;
	    String str = System.getProperty("os.name");
	    
	    if(str.equals("Linux")){
		
		//c.printf("Execing %s%n",(Object[])args);
		Runtime rt= Runtime.getRuntime();
		proc = rt.exec(args);
		
	    }else if(str.equals("Windows XP")){
		
		String[] cmd = new String[args.length+2];
		cmd[0] = "cmd.exe" ;
		cmd[1] = "/C" ;
		//String viewParams = "Execing "+cmd[0]+" "+cmd[1]+" ";
		for(int i=2;i<cmd.length;i++){
		    cmd[i] = args[i-2];
		    //viewParams+=cmd[i]+" ";
		}
		//c.println(viewParams);
		Runtime rt= Runtime.getRuntime();
		proc = rt.exec(cmd);
	    }
	    
	    IOStream iostreamErr = new IOStream(proc.getErrorStream(),proc.getOutputStream());
	    IOStream iostream = new IOStream(proc.getInputStream(),proc.getOutputStream());
	    (new Streamer(iostreamErr, c, "[Error]%s%n")).start();
	    (new Streamer(iostream, c, "%s%n")).start();
	    	    
            int exitVal = proc.waitFor();
            System.out.println("ExitValue: " + exitVal);        
        
	} catch (Throwable t){ throw new Exception(t.getMessage());}
    }

    public void exec(IODataStreamInreface c, String[] params) throws Exception {
	invokeExec(c, params);
    }

    private class Streamer extends Thread{
	
	private final IODataStreamInreface ioProcess;
	private final IODataStreamInreface ioConsol;
	private String type;
	
	public Streamer(IODataStreamInreface ioProcess,IODataStreamInreface ioConsol,String type){
	    this.ioConsol = ioConsol;
	    this.ioProcess = ioProcess;
	    this.type = type;
	}
	@Override
	public void run(){
	    
	    String retStr=null;
	    while((retStr = ioProcess.readLine(""))!=null){		
		ioConsol.printf(type,retStr);
	    }
	}
    }
}
