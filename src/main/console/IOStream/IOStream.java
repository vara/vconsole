/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console.IOStream;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;

/**
 *
 * @author vara
 */
public class IOStream extends AbstractIODataStream{
    public static String message = "";    
    private PrintWriter writer;    
    private BufferedReader reader;
    
    public IOStream(InputStream is,OutputStream os){
	super(is, os);
	
	writer = new PrintWriter(getOutputStream(), true);	
	reader = new BufferedReader(getInputStream());
    }
    
    public Object printf(String format, Object... args) {	
	writer.format(message+format, args);
	//System.out.println("Wysłano [printf] "+format);
	return this;
    }

    public String readLine(String format, Object... args) {
	String retStr = null;
	try{
	    if(args.length!=0){
		
		printf(format, args);
	    }
	    retStr = reader.readLine();	  
	}catch(Exception ex){System.out.println(""+ex);}	    
	
	return retStr;
    }

    public String readPassword(String fmt, Object... args) {
	throw new UnsupportedOperationException("Not supported yet.");
    }

    public void println(String str) {
	writer.println(message+str);
	//System.out.println("wysłano [println] "+str);
    }

    @Override
    public boolean closeInputStream() {
	try {
	    reader.close();
	} catch (IOException ex) {}
	return true;
    }

    @Override
    public boolean closeOutputStream() {
	
	writer.close();
	return true;
    }
    
    @Override
    public void closeIOStreams(){
	closeInputStream();
	closeOutputStream();
    }
}
