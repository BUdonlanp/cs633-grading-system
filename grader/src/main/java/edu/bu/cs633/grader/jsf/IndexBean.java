/**
 * 
 */
package edu.bu.cs633.grader.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;

import edu.bu.cs633.grader.entity.User;
import edu.bu.cs633.grader.service.UserService;

/**
 * This bean represents the JSF Index Page
 * 
 * @author donlanp
 * 
 */
@ManagedBean(name = "indexBean")
@RequestScoped
public class IndexBean {

	private String oldPassword;
	private String newPasswordOne;
	private String newPasswordTwo;
	
	@Autowired
	private UserSessionBean userSessionBean;
	
	@Autowired
	private UserService userService;
	
	public void updatePassword(){
		//Check if the password is the current user's password
		if(userSessionBean.getCurrentUser().getPassword().equals(oldPassword)){
			//Check if the two passwords match
			if(!StringUtils.isEmpty(newPasswordOne) && newPasswordOne.equals(newPasswordTwo)){
				User user = userSessionBean.getCurrentUser();
				user.setPassword(newPasswordOne);
				user = userService.saveUser(user);
				userSessionBean.setCurrentUser(user);
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Password updated!"));
			} else {
				FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
						"Passwords do not match!", "Please make sure the two passwords match!"));
				
			}
		} else {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, 
					"Incorrect password!", "Please enter the correct current password!"));
		}
	}

	/**
	 * @return the oldPassword
	 */
	public String getOldPassword() {
		return oldPassword;
	}

	/**
	 * @param oldPassword
	 *            the oldPassword to set
	 */
	public void setOldPassword(String oldPassword) {
		this.oldPassword = oldPassword;
	}

	/**
	 * @return the newPasswordOne
	 */
	public String getNewPasswordOne() {
		return newPasswordOne;
	}

	/**
	 * @param newPasswordOne
	 *            the newPasswordOne to set
	 */
	public void setNewPasswordOne(String newPasswordOne) {
		this.newPasswordOne = newPasswordOne;
	}

	/**
	 * @return the newPasswordTwo
	 */
	public String getNewPasswordTwo() {
		return newPasswordTwo;
	}

	/**
	 * @param newPasswordTwo
	 *            the newPasswordTwo to set
	 */
	public void setNewPasswordTwo(String newPasswordTwo) {
		this.newPasswordTwo = newPasswordTwo;
	}

}
