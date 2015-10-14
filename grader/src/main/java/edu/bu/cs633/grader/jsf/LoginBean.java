/**
 * 
 */
package edu.bu.cs633.grader.jsf;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import org.springframework.beans.factory.annotation.Autowired;

import edu.bu.cs633.grader.entity.User;
import edu.bu.cs633.grader.service.UserService;

/**
 * This bean represents the JSF Login Page
 * @author donlanp
 *
 */
@ManagedBean(name="loginBean")
@RequestScoped
public class LoginBean {
	
	private String username;
	private String password;
	
	@Autowired
	private UserService userService;
	
	@Autowired
	private UserSessionBean userSessionBean;
	
	public String login(){
		String ret = null;
		User user = userService.login(getUsername(), getPassword());
		if(user != null){
			userSessionBean.setCurrentUser(user);
			ret = "index.jsf?faces-redirect=true";
		}
		return ret;
	}
	
	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
