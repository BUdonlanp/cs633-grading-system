/**
 * 
 */
package edu.bu.cs633.grader;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

import edu.bu.cs633.grader.jsf.AdminBean;
import edu.bu.cs633.grader.jsf.CourseManagementBean;
import edu.bu.cs633.grader.jsf.IndexBean;
import edu.bu.cs633.grader.jsf.LoginBean;
import edu.bu.cs633.grader.jsf.UserSessionBean;

/**
 * @author donlanp
 *
 */
@Configuration
public class JsfConfig {

	@Bean
	@Scope(value="request")
	public LoginBean loginBean(){
		return new LoginBean();
	}
	
	@Bean
	@Scope(value="request")
	public IndexBean indexBean(){
		return new IndexBean();
	}
	
	@Bean
	@Scope(value="request")
	public AdminBean adminBean(){
		return new AdminBean();
	}
	
	@Bean
	@Scope(value="session")
	public UserSessionBean userSessionBean(){
		return new UserSessionBean();
	}
	
	@Bean
	@Scope(value="request")
	public CourseManagementBean courseManageBean(){
		return new CourseManagementBean();
	}
	
}
