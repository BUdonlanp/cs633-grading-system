/**
 * 
 */
package edu.bu.cs633.grader.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import edu.bu.cs633.grader.entity.User;

/**
 * General Bean to hold information about the user currently logged into the system
 * @author donlanp
 *
 */
@ManagedBean
@SessionScoped
public class UserSessionBean {

	private User currentUser;
	
	//Session checks for redirects
	
	/**
	 * Checks to see if the user is logged in, if not -- forwards to the login page
	 * @param cse
	 */
	public void forwardToLogin(ComponentSystemEvent cse){
		if(!isLoggedIn()){
			System.out.println("User is not logged in...");
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), 
					null, 
					"/login.jsf?faces-redirect=true");
		}
	}
	
	//Easy accessors
	
	/**
	 * Easy boolean check to see if the user is logged in
	 * @return
	 */
	public boolean isLoggedIn(){
		return null != currentUser;
	}
	
	public boolean getIsAdmin(){
		return currentUser.isAdmin();
	}
	
	public boolean getIsTeacher(){
		return currentUser.isTeacher();
	}
	
	public boolean getIsStudent(){
		return currentUser.isStudent();
	}
	
	//Gettes/Setters

	/**
	 * @return the currentUser
	 */
	public User getCurrentUser() {
		return currentUser;
	}

	/**
	 * @param currentUser the currentUser to set
	 */
	public void setCurrentUser(User currentUser) {
		this.currentUser = currentUser;
	}
	
}
