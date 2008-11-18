/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.server;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;
import main.console.IOStream.SocketAbstractIOStream;
import main.console.MyConsole;

/**
 *
 * @author vara
 */
public class Server extends AbstractServer{

    public Server(){
    }

    @Override
    public void start() throws ExceptionInInitializerError {
	 try {
            SSLServerSocketFactory sslserversocketfactory =
                    (SSLServerSocketFactory) SSLServerSocketFactory.getDefault();
            SSLServerSocket sslserversocket =
                    (SSLServerSocket) sslserversocketfactory.createServerSocket(9999);
           
	    //will add loop to many clients
	    System.out.println("Waiting for client ....");
	    SSLSocket sslsocket = (SSLSocket) sslserversocket.accept(); 
	    System.out.println("Client Connected !");
	    SocketAbstractIOStream saios = new SocketAbstractIOStream(sslsocket);
	    SocketAbstractIOStream.message = "[Server] ";
	    MyConsole mc = new MyConsole(saios);
	    
	    
        } catch (Exception exception) {
            throw new ExceptionInInitializerError(exception.getMessage());
        }
    }

    @Override
    public void stop() {
	
    }   
}
