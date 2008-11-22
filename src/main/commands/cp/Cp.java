/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.commands.cp;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import main.console.managecomands.AbstractCommand;
import main.console.IOStream.IODataStreamInreface;

/**
 *
 * @author vara
 */
public class Cp extends AbstractCommand{

    public static final int OVERWRITE_ALWAYS = 1;
    public static final int OVERWRITE_NEVER = 2;
    public static final int OVERWRITE_ASK = 3;
    
    private int bufferSize = 4 * 1024;
    private boolean showTime = true;
    private boolean copyOriginalTimestamp = true;
    private int override = OVERWRITE_ASK;
    
    private String srcFileName = "";
    
    private IODataStreamInreface iostream;    
    
    public Cp(){
        super("cp");
    }

    public void exec(IODataStreamInreface c, String[] params) throws Exception {
	
	if(params.length==2){	
	    setIOstream(c);	    	
	    File srcf = checkPathToFile(checkAbsolutePath(params[0]),true);	    
	    File destf = checkPathToFile(checkAbsolutePath(params[1]), false);
	    if(!doCopy(destf)){
		return;
	    }
	    copyFile(srcf, destf);
	    
	    if (copyOriginalTimestamp) {
		if (!destf.setLastModified(srcf.lastModified())) {
			iostream.println("Error: Could not set " +
					"timestamp of copied file.");
		}
	    }
	}else throw new Exception("cp [path to sorce file] " +
				  "[path to destination file]");	
    }
    
    public void setIOstream(IODataStreamInreface io){
	iostream = io;
    }
    
    public void copyFile(File fsource , File ftarget) throws Exception{
	
	
	FileInputStream fin = new FileInputStream(fsource);
	FileOutputStream fout = new FileOutputStream(ftarget);	
	long millis = System.currentTimeMillis();		
	byte[] buffer = new byte[bufferSize];
	int bytesRead;
	while ((bytesRead = fin.read(buffer)) != -1) {
		fout.write(buffer, 0, bytesRead);
	}
	fout.close();
	fin.close();
	
	if (showTime) {
	    millis = System.currentTimeMillis() - millis;
	    iostream.println("Second(s): " + (millis/1000L));
	}	
    }
    
    public static String checkAbsolutePath(String fileName){
	
	    int isPath = fileName.indexOf(File.separator);
	    if(isPath==-1)
		fileName = System.getProperty("vconsole.currentDirectory")+File.separator+fileName;
	    return fileName;
    }
    
    public File checkPathToFile(String path,boolean source)throws Exception{
	
	File file = new File(path);		
	if(source){	    
	    if(!file.isFile() && !file.canRead())
		throw new Exception("Not a readable file: " + file.getName());
	    srcFileName = file.getName();
	    iostream.printf("source %s\n", file.getAbsolutePath());
	}else{
	    if(!file.isDirectory()){
		//maybe file ???
		String isDir = file.getAbsolutePath().replaceAll(file.getName(),"");		
		file = new File(isDir,file.getName());
		if(file.isDirectory())
		    throw new Exception("Not a directory: " + file.getName());
	    }
	    else file = new File(file, srcFileName);
	    iostream.printf("destination %s\n", file.getAbsolutePath());
	}
	return file;
    }
    
    public boolean doCopy(File file) {
	boolean exists = file.exists();
	if (override == OVERWRITE_ALWAYS || !exists) {
		return true;
	} else
	if (override == OVERWRITE_NEVER) {
		return false;
	} else
	if (override == OVERWRITE_ASK) {
		return iostream.readLine("File exists. " +
			"Overwrite (y/n)?").equals("y");
	} else {
		throw new InternalError("Program error. Invalid " +
			"value for override: " + override);
	}
    }
}
