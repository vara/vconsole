package main.commands.details;

import java.util.Iterator;
import java.util.Map;
import java.util.Properties;
import main.console.IOStream.IODataStreamInreface;
import main.console.managecomands.AbstractCommand;

/**
 * Created on 2008-11-22, 19:33:50
 * @author vara
 */
public class Details extends AbstractCommand{

    public Details(){
        super("details");
    }

    @Override
    public void exec(IODataStreamInreface c, String[] params) throws Exception {
        // get the environment variables
        c.println("Environment Variables");
        Map envMap = System.getenv();	    
        Iterator iter = envMap.keySet().iterator();
        while(iter.hasNext()){
            Object key = iter.next();
            Object val = envMap.get(key);
            c.println(key+" = "+val);
        }
        // get the system properties
        Properties prop = System.getProperties();
        c.println("System Properties");
        for (Object key2 : prop.keySet()) {
          c.println(key2 + " = " +  prop.get(key2));
        }
    }
}
