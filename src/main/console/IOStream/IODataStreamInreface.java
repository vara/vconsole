/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console.IOStream;

/**
 *
 * @author vara
 */
public interface IODataStreamInreface{
    
    Object printf(String format, Object ... args);
    String readLine(String format, Object ... args);
    String readPassword(String fmt, Object ... args);
    void println(String str);
}
