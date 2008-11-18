/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.klient;

import java.util.Date;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import main.console.AbstractConsole;
import main.console.ActionCommand;
import main.console.IOStream.IODataStreamInreface;
import main.console.IOStream.SocketAbstractIOStream;

/**
 *
 * @author vara
 */
public class ConnectToServer implements ActionCommand{
    
    private String ipServ;
    private int port;
    private SSLSocket sslsocket;
    public ConnectToServer(){
	
	setIpServ("192.168.1.101");
	setPort(9999);
    }

    public void exec(IODataStreamInreface systemStream, String[] params) throws Exception {
	
	
	SSLSocketFactory sslsocketfactory = (SSLSocketFactory) SSLSocketFactory.getDefault();
	sslsocket = (SSLSocket) sslsocketfactory.createSocket("192.168.1.101", 9999);
	
	IODataStreamInreface socketStream = new SocketAbstractIOStream(sslsocket);
	String string = null;
	read(socketStream, systemStream);
		
	while ((string = systemStream.readLine("")) != null) {
	    if(!string.equals("")){		
		if(string.equals("close")){
		    //socket.close();
		    ((SocketAbstractIOStream)socketStream).closeIOStreams();
		    System.out.println("Lalamido");
		    systemStream.printf(AbstractConsole.CONNECTION_CLOSED, ipServ);
		    
		}
		socketStream.println(string);
		
	    }else{}
	    	    
	}
    }
    
    private void read(final IODataStreamInreface socketReader,final IODataStreamInreface systemStream){
	
	Thread t = new Thread(new Runnable() {

	    public void run() {
		String string;
		
		    while((string = socketReader.readLine("")) != null) {
			
			systemStream.println(string);
			string="";
		    }
		System.out.println("Koniec Watku readLine");
	    }
	});
	t.start();
    }

    public String getIpServ() {
	return ipServ;
    }

    public void setIpServ(String ipServ) {
	this.ipServ = ipServ;
    }

    public int getPort() {
	return port;
    }

    public void setPort(int port) {
	this.port = port;
    }
}
