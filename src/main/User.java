/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

/**
 *
 * @author vara
 */
public class User {

    private String name;
    private String password;
    
    //not iplemented yet
    private short accessRights;
    private String group;
    
    public User(String userName, String pass){
	name = userName;
	password = pass;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public String getPassword() {
	return password;
    }

    @Override
    public boolean equals(Object obj) {
	if(obj instanceof User){
	    User tmp = (User)obj;
	    if(name.equals(tmp.getName()))
	    {
		if(password.equals(tmp.getPassword())){
		    return true;
		}
	    }
	}
	return false;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 67 * hash + (this.name != null ? this.name.hashCode() : 0);
	hash = 67 * hash + (this.password != null ? this.password.hashCode() : 0);
	return hash;
    }
    
}
