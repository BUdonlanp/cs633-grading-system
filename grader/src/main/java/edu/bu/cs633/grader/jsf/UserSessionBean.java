/**
 * 
 */
package edu.bu.cs633.grader.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ComponentSystemEvent;

import edu.bu.cs633.grader.entity.Assignment;
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
	
	private Assignment selectedAssignment;
	
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
	
	public void forwardToIndexForAdminPage(ComponentSystemEvent cse){
		if(isLoggedIn()){
			if(!getIsAdmin()){
				FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
						FacesContext.getCurrentInstance(), 
						null, 
						"/index.jsf?faces-redirect=true");
			}
		} else {
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), 
					null, 
					"/login.jsf?faces-redirect=true");
		}
	}
	
	public void forwardToIndexForTeacherPage(ComponentSystemEvent cse){
		if(isLoggedIn()){
			if(!getIsTeacher()){
				FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
						FacesContext.getCurrentInstance(), 
						null, 
						"/index.jsf?faces-redirect=true");
			}
		} else {
			FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
					FacesContext.getCurrentInstance(), 
					null, 
					"/login.jsf?faces-redirect=true");
		}
	}
	
	public void forwardToIndexForStudentPage(ComponentSystemEvent cse){
		if(isLoggedIn()){
			if(!getIsStudent()){
				FacesContext.getCurrentInstance().getApplication().getNavigationHandler().handleNavigation(
						FacesContext.getCurrentInstance(), 
						null, 
						"/index.jsf?faces-redirect=true");
			}
		} else {
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

	/**
	 * @return the selectedAssignment
	 */
	public Assignment getSelectedAssignment() {
		return selectedAssignment;
	}

	/**
	 * @param selectedAssignment the selectedAssignment to set
	 */
	public void setSelectedAssignment(Assignment selectedAssignment) {
		this.selectedAssignment = selectedAssignment;
	}
	
}
