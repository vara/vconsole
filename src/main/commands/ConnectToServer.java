/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.commands;

import java.util.Date;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;
import main.console.AbstractConsole;
import main.console.ActionCommand;
import main.console.IOStream.IODataStreamInreface;
import main.console.IOStream.JavaConsole;
import main.console.IOStream.SocketAbstractIOStream;
import main.net.ssl.SSLConnectionProperties;

/**
 *
 * @author vara
 */
public class ConnectToServer implements ActionCommand{
    
    private String ipServ;
    private int port;
    private SSLSocket sslsocket;
        
    public ConnectToServer(){
	SSLConnectionProperties.setPasswordKeyStore("password");
	SSLConnectionProperties.setPathToKeystore("../tmp/clientstore");
	setIpServ("192.168.1.101");
	setPort(9999);
    }
    public void init(IODataStreamInreface systemStream, String[] params) throws Exception{
	
	IODataStreamInreface socketStream=null;
	try{	    
	    //null -> default "TLS"
	    SSLSocketFactory factory = 
		    SSLConnectionProperties.getSSLSocketFactory(null);
	    SSLSocket socket = 
		    (SSLSocket)factory.createSocket(getIpServ(),getPort());
	    socket.startHandshake();
	    socketStream = new SocketAbstractIOStream(sslsocket);	
	    talkWithServer(socketStream, systemStream);	
	}catch(Exception e){
	    System.out.println("przechwicilem wyjatek jea ");throw e;}
	finally{
	    System.out.println("czyszcze ....brudy ");
	    System.out.println(""+sslsocket);
	    ((SocketAbstractIOStream)socketStream).closeIOStreams();
	}
    }
    
    public static void main(String [] str){
	ConnectToServer con = new ConnectToServer();
	try {
	    con.init(new JavaConsole(), str);
	} catch (Exception ex) {
	    System.out.println(""+ex);
	    System.exit(1);
	}
    }
    
    public void exec(IODataStreamInreface systemStream, String[] params) throws Exception {
	init(systemStream, params);
    }
    
    protected void talkWithServer(final IODataStreamInreface socketStream,
			final IODataStreamInreface systemStream){
	readFromSocketWriteToOStream(socketStream, systemStream);			
	writeToSocketReadFromIStream(socketStream,systemStream);
    }
    
    /* IMPORTANT!
     * This metod must be invoked after the call readFromSocketWriteToOStream
     * (Protected ! Invoked this method talkWithServer())
     */
    
    private void writeToSocketReadFromIStream(final IODataStreamInreface socketStream,
			final IODataStreamInreface systemStream){
	String string = null;
	while ((string = systemStream.readLine("")) != null) {
	    
	    if(!string.equals("")){		
		if(string.equals("close")){
		    //socket.close();
		    ((SocketAbstractIOStream)socketStream).closeIOStreams();
		    systemStream.printf(AbstractConsole.CONNECTION_CLOSED,ipServ);
		    break;
		}
		socketStream.println(string);
		
	    }else{systemStream.printf(AbstractConsole.PROMPT,new Date());}	    	    
	}
    }
    
    /* IMPORTANT!
     * This metod must be ivoked before the call writeToSocketReadFromIStream 
     * (Protected ! Invoked this method talkWithServer())
     */
    
    private void readFromSocketWriteToOStream(final IODataStreamInreface socketReader,
			final IODataStreamInreface systemStream){
	
	Thread t = new Thread(new Runnable() {
	    public void run() {
		String string;
		
		    while((string = socketReader.readLine("")) != null) {
			
			systemStream.println(string);
			string="";
		    }
		System.out.println("End of Thread readLine");
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
