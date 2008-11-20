/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.net.ssl;

import javax.net.ssl.HandshakeCompletedListener;

/**
 *
 * @author vara
 */

/*
    sequence SSL handshake process
 
	client                  Server
   
   Client Hello        -->   
                       <-- Server Hello
                       <-- Server Certificate (optional)
                       <-- Server Key Exchange (optional)
                       <-- Certificate Request (optional)
   Certificate         -->
   Client Key Exchange -->
   Certificate Verify  -->
   Change Cipher Spec  -->
   Finished            -->
                       <-- Change Cipher Spec
                       <-- Finished
 */
public abstract class  MyHandshakeListener implements HandshakeCompletedListener {  
}
