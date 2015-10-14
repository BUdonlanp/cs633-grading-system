/**
 * 
 */
package edu.bu.cs633.grader.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;

import edu.bu.cs633.grader.entity.User;
import edu.bu.cs633.grader.service.UserService;

/**
 * Bean to represent the basic admin funcationalities
 * 
 * @author donlanp
 * 
 */
@ManagedBean(name="adminBean")
@RequestScoped
public class AdminBean {
	
	@Autowired
	private UserService userService;

	private String newUserFirstName;
	private String newUserLastName;
	
	private String[] roles;
	public static final String ADMIN = "admin";
	public static final String TEACHER = "teacher";
	public static final String STUDENT = "student";
	
	public void createNewUser(){
		boolean isAdmin = false;
		boolean isTeacher = false;
		boolean isStudent = false;
		
		for(String s: roles){
			if(ADMIN.equals(s)){
				isAdmin = true;
			} else if(TEACHER.equals(s)){
				isTeacher = true;
			} else if(STUDENT.equals(s)){
				isStudent = true;
			}
		}
		
		//Save the user
		User newUser = userService.createUser(newUserFirstName, newUserLastName, isAdmin, isTeacher, isStudent);
		
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Created new user: " + newUser.getUsername()));
	}

	/**
	 * @return the newUserFirstName
	 */
	public String getNewUserFirstName() {
		return newUserFirstName;
	}

	/**
	 * @param newUserFirstName
	 *            the newUserFirstName to set
	 */
	public void setNewUserFirstName(String newUserFirstName) {
		this.newUserFirstName = newUserFirstName;
	}

	/**
	 * @return the newUserLastName
	 */
	public String getNewUserLastName() {
		return newUserLastName;
	}

	/**
	 * @param newUserLastName
	 *            the newUserLastName to set
	 */
	public void setNewUserLastName(String newUserLastName) {
		this.newUserLastName = newUserLastName;
	}

	/**
	 * @return the roles
	 */
	public String[] getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(String[] roles) {
		this.roles = roles;
	}

}
