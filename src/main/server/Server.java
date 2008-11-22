/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.server;

import java.io.IOException;
import java.security.cert.X509Certificate;
import java.util.Date;
import javax.net.ssl.HandshakeCompletedEvent;
import javax.net.ssl.SSLPeerUnverifiedException;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import main.console.IOStream.JavaConsole;
import main.console.IOStream.SocketAbstractIOStream;
import main.console.MyConsole;
import main.net.ssl.MyHandshakeListener;
import main.net.ssl.SSLConnectionProperties;

/**
 *
 * @author vara
 */
public class Server extends AbstractServer implements Runnable{

    private SSLServerSocket serverSocket=null;       
    private static JavaConsole out = new JavaConsole();
    private static int ident = 1;
    private MyConsole mc ;
    
    public Server() throws Exception{
	try {

	    SSLServerSocketFactory sslServFac = 
		    SSLConnectionProperties.getServerSocketFactory(getType());	    
	    serverSocket = (SSLServerSocket) sslServFac.createServerSocket(getPort());	    
	    
	} catch (Exception ex) {throw ex;}
    }

    @Override
    public void start(){ newListener();}    
    
    @Override
    public void stop() {}

    @Override
    public void run() {
	SSLSocket sslsocket= null;	
	try {
	    out.printf(AbstractServer.WAIT_FOR_CLIENT,new Date());
	    sslsocket = (SSLSocket) serverSocket.accept();	    
	    out.println("Client Connected !");
	    sslsocket.addHandshakeCompletedListener(new CustomerCertified(ident++));
	} catch (IOException ex) {
	    out.printf(AbstractServer.ERROR_SOCKET,new Date(),ex);	    
	    ex.printStackTrace(out.getPrintStream());
	}	
	newListener();
	SocketAbstractIOStream saios = null;		
	 try {
	    saios = new SocketAbstractIOStream(sslsocket);	    
	    saios.println("Welcome to server");
	    //delete this var !!! (only for preview test)
	    SocketAbstractIOStream.message = "[Server] ";
	    System.out.println("create console");
	    mc =new MyConsole(saios);	    

	} catch (IOException ex) {
	    out.printf("IOStream Error %s", ex.getMessage());
	    out.println("Client disconnected !");
	    ex.printStackTrace(out.getPrintStream());
	}
	 
	finally{
	    try { sslsocket.close(); } catch (IOException ex) {}
	    out.println("Close client "+sslsocket);
	}		
    }
    
    @Override
    public void newListener() {
	(new Thread(this)).start();
    }

    private class CustomerCertified extends MyHandshakeListener{

	private int ident;
	public CustomerCertified(int id){
	    ident =id;
	}
        @Override
	public void handshakeCompleted(HandshakeCompletedEvent arg0) {	    
	    try {
	      X509Certificate 
		cert=(X509Certificate)arg0.getPeerCertificates()[0];
	      String peer=cert.getSubjectDN().getName();
	      out.println(ident+": Request from "+peer);
	      mc.commandLoop();
	    }
	    catch (SSLPeerUnverifiedException pue) {
		out.println(ident+": Peer unverified ");
		//pue.printStackTrace(out.getPrintStream());
		mc = null;
		try { arg0.getSocket().close();	} catch (IOException ex) {}
	    }
	}	
    }
    
}
