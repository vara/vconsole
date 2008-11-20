/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main.commands.dir;

import java.io.File;
import java.io.FilenameFilter;
import java.util.regex.Pattern;

/**
 *
 * @author vara
 */
public class DirFilter implements FilenameFilter{

    private Pattern pattern;
    
    public DirFilter(String regexp){
	pattern = Pattern.compile(regexp);
    }

    public boolean accept(File dir, String name) {	
	return pattern.matcher(new File(name).getName()).matches();
    }
}
