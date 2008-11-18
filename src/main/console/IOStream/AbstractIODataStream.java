/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console.IOStream;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;

/**
 *
 * @author vara
 */
public abstract class AbstractIODataStream implements IODataStreamInreface{

    private InputStreamReader ins=null;
    private OutputStreamWriter outs=null;
    
    public AbstractIODataStream(InputStream is,OutputStream os){
	ins= new InputStreamReader(is);
	outs= new OutputStreamWriter(os);
    }
    protected InputStreamReader getInputStream(){
	return ins;
    }
    
    protected OutputStreamWriter getOutputStream(){
	return outs;
    }
    public abstract boolean closeInputStream();
    public abstract boolean closeOutputStream();
    public abstract void closeIOStreams();
}
