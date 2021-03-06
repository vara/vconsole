package main.commands.dir;

import java.io.*;
import java.util.*;
import java.util.regex.Pattern;
import main.console.managecomands.AbstractCommand;
import main.console.IOStream.IODataStreamInreface;

import main.console.IOStream.JavaConsole;

public class Dir extends AbstractCommand {
    
    // Arg given on the command line
    private String parameters[];

    // The directory we are in;
    private File pwd;

    
    public Dir(){
        super("dir");
    }
    
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
	    //list = pwd.list(new DirFilter(args[0]));
	    final String regExp = "";
	    list = pwd.list(new FilenameFilter() {		
		private Pattern pattern = Pattern.compile(regExp);		
		public boolean accept(File dir, String name) {
		    return pattern.matcher(new File(name).getName()).matches();		    
		}
	    });
	}
	Arrays.sort(list, new AlphabeticComparator());
	for (String str : list) {
	    String [] drwx = {"-","-","-","-"};
	    File f = new File(currdir+File.separator+str);	    
	    
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