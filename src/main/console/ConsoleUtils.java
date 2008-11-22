package main.console;

import java.util.Scanner;
import java.util.Vector;
import main.commands.cd.Cd;

/**
 * Created on 2008-11-22, 17:04:40
 * @author vara
 */
public class ConsoleUtils {
       
    public ConsoleUtils(){}
    
    public static String [] validateParameters(Scanner scanner){
               
        Vector <String> vecParams = new Vector<String>(1);		    
        while(scanner.hasNext()){	
            //find and replace this '~' char to home path 
            String param = scanner.next();            
            if(param.charAt(0)=='~')
                param=System.getProperty("user.home")+param.substring(1);

            if(param.length()>1 && (param.charAt(0)=='.' && param.charAt(1)=='/')){
                param=System.getProperty("vconsole.currentDirectory")+param.substring(1);
            }			
            if(param.length()>2 && (param.charAt(0)=='.' && param.charAt(1)=='.' && param.charAt(2)=='/')){
                param = Cd.getUpperNoodDir(System.getProperty("vconsole.currentDirectory"))+param.substring(2);
            }
            vecParams.add(param);
        }
        return vectorToArray(vecParams);
    }
    public static String[] vectorToArray(Vector<String> vec){        
        return (String [])vec.toArray(new String [vec.size()]);
    }
}
