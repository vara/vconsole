/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.commands.cat;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import main.commands.cp.Cp;
import main.console.ActionCommand;
import main.console.IOStream.IODataStreamInreface;

/**
 *
 * @author vara
 */
public class Cat implements ActionCommand{

    private IODataStreamInreface iostream;
    private boolean dispNumLine=true;
    private boolean dispCharEndLine = true;
    
    private char charEnd = '$';
    
    public Cat(){
    }

    public void setIOstream(IODataStreamInreface io){
	iostream = io;
    }
    
    public void exec(IODataStreamInreface c, String[] params) throws Exception {
	setIOstream(c);
	if(params.length>0){
	    String fileName = params[0];
	    File file = new File(Cp.checkAbsolutePath(fileName));
	    if(!file.isFile())
		throw new Exception("This "+file.getAbsolutePath()+" is not file !!!");
	    displayFile(file);
	}
    }
    
    public void displayFile(File path)throws Exception{
	
	FileInputStream fin = new FileInputStream(path);
	BufferedReader br = new BufferedReader(new InputStreamReader(fin));
	String str=null;
	
	int numLine = 1;
	while((str = br.readLine())!=null){
	    iostream.printf("%d %s\n",numLine,str);	    
	    numLine++;
	}
    }
}
