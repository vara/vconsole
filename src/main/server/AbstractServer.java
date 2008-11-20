/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.server;

import main.console.AbstractConsole;

/**
 *
 * @author vara
 */
public abstract class AbstractServer {

    
    public static String WAIT_FOR_CLIENT =AbstractConsole.PROMPT+"Wait for client ...%n";
    public static String ERROR_SOCKET = AbstractConsole.PROMPT+"Socket server died %s";
    
    private int port = 9999;
    private String type = "TLS";
    
    public AbstractServer(){
    }
    
    public abstract void start();
    public abstract void stop();    
    public abstract void newListener();
    
    public void setType(String type){
	this.type = type;
    }
    public String getType(){
	return type;
    }
    public void setPort(int port){
	this.port = port;
    }
    public int getPort(){
	return port;
    }
    
}
