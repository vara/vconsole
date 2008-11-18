/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.console;

import main.console.IOStream.IODataStreamInreface;

/**
 *
 * @author vara
 */
public interface ActionCommand {
    void exec(IODataStreamInreface c, String[] params) throws Exception;
}
