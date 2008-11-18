/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console.IOStream;

import java.io.IOException;
import javax.net.ssl.SSLSocket;

/**
 *
 * @author vara
 */
public class SocketAbstractIOStream extends IOStream{

    private SSLSocket socket;    
    
    public SocketAbstractIOStream(SSLSocket sslsocket) throws IOException{      
	super(sslsocket.getInputStream(),sslsocket.getOutputStream());
	socket = sslsocket;
    }
    @Override
    public void closeIOStreams(){
	
	if(!socket.isClosed()){
	    try {		
		socket.close();		
	    } catch (Exception ex) {}
	}
    }
}
