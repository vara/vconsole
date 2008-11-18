/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package main;

import java.util.Vector;

/**
 *
 * @author vara
 */
public class Users {
    
    private User defaultUser = new User("wara","wara");
    private Vector<User> usersList = new Vector<User>(1);
    
    private short currentUser=-1;
    
    public Users(){
	usersList.add(defaultUser);
    }
    
    public boolean checkUser(String name,String pass){
	User logUser = new User(name,pass);
	
	for (int i=0;i<usersList.size();i++) {
	    User user = usersList.get(i);
	    if(user.equals(logUser)){
		currentUser = (short) i;
		return true;
	    }
	}
	return false;
    }
    
    public User getLoggedUser(){
	
	User retUser = null;
	try{
	    retUser = usersList.elementAt(currentUser);
	}catch(IndexOutOfBoundsException e){}		
	
	return retUser;
    }
}
