package main.commands.dir;

import java.io.*;
import java.util.*;
import main.console.ActionCommand;
import main.console.IOStream.IODataStreamInreface;

import main.console.IOStream.JavaConsole;

public class Dir implements ActionCommand {
    
    // Arg given on the command line
    private String parameters[];

    // The directory we are in;
    private File pwd;

    public static void main( String args[] ){
	
	Dir d = new Dir();
	d.displayContentDir(new JavaConsole(), args );
	
    }
    
    public void displayContentDir(IODataStreamInreface iostream,String [] args){
	
	this.parameters = args;
	String currdir = System.getProperty("vconsole.currentDirectory","/");
	pwd = new File(currdir);
	String [] list;
	if(args.length==0){
	    list = pwd.list();
	}else{
	    list = pwd.list(new DirFilter(args[0]));
	}
	Arrays.sort(list, new AlphabeticComparator());
	for (String str : list) {
	    String [] drwx = {"-","-","-","-"};
	    File f = new File(str);	    
	    
	    if(f.isDirectory())
		drwx[0] = "d";
	    if(f.canRead())
		drwx[1] = "r";
	    if(f.canWrite())
		drwx[2] = "w";
	    if(f.canExecute())
		drwx[3] = "x";
	
	    //iostream.printf(f.getPath());
	    iostream.printf("%s%s%s%s ",(Object [])drwx);
	    iostream.println(str);
	}
    }


    public void exec(IODataStreamInreface c, String[] params) throws Exception {
	displayContentDir(c, params);
    }
    
    public class AlphabeticComparator implements Comparator{
	public int compare(Object o1, Object o2) {
	    String s1 = (String)o1;
	    String s2 = (String)o2;
	    return s1.toLowerCase().compareTo( s2.toLowerCase());
	}
    }
}