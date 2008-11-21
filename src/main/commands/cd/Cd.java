/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.commands.cd;

import java.io.File;
import main.console.ActionCommand;
import main.console.IOStream.IODataStreamInreface;

/**
 *
 * @author vara
 */
public class Cd implements ActionCommand{

    
    
    public Cd(){
    }

    public void exec(IODataStreamInreface c, String[] params) throws Exception {
	changeDir(c, params);
    }
    
    public static String getUpperNoodDir(String currentDir){
	//back to upper nood	
	int retVal = currentDir.lastIndexOf(File.separator);
	String upperNoodDir =null;
	if(retVal>0)
	    upperNoodDir= currentDir.substring(0, retVal);
	else if(retVal==0){		    
	    upperNoodDir = File.separator;
	}
	return upperNoodDir;
    }
    
    private void changeDir(IODataStreamInreface c,String [] arg) throws Exception{
    	
	String currentDir = System.getProperty("vconsole.currentDirectory");
	File tmpFile = null;
	
	if(arg!=null && arg.length>=1){
	    if(arg[0].equals("..")){
		String upperNoodDir = Cd.getUpperNoodDir(currentDir);
		if(upperNoodDir!=null)
		    tmpFile = new File(upperNoodDir);
	    }else{
		
		String dir = arg[0];		
		
		if( (dir.charAt(0) == File.separatorChar) ||
			(dir.length()>1 && dir.charAt(1)==':'))
		    
		    tmpFile = new File(dir.trim());
		else
		    tmpFile = new File(currentDir,dir.trim());
	    }
	    
	    if(tmpFile==null && !tmpFile.exists()){
		throw new Exception("'"+tmpFile.getCanonicalPath()+"' not exists");
	    }
	    //check file. is it a directory?
	    if(tmpFile.isDirectory()){		
		System.setProperty("vconsole.currentDirectory", tmpFile.getCanonicalPath());
	    }else{
		//c.println("Bład ! "+tmpFile.getCanonicalPath()+" isn't directory");
		throw new Exception("'"+tmpFile.getCanonicalPath()+"' isn't directory");
	    }
	}	
    }
}
