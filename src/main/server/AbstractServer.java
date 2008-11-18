/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.server;

/**
 *
 * @author vara
 */
public abstract class AbstractServer {

    public AbstractServer(){
    }
    
    public abstract void start()throws ExceptionInInitializerError;
    public abstract void stop();
}
