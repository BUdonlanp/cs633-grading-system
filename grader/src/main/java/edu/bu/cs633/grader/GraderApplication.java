/**
 * 
 */
package edu.bu.cs633.grader;

import java.util.List;

import javax.faces.webapp.FacesServlet;
import javax.servlet.ServletContext;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.context.embedded.ServletListenerRegistrationBean;
import org.springframework.boot.context.embedded.ServletRegistrationBean;
import org.springframework.boot.context.web.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.context.ServletContextAware;

import com.sun.faces.config.ConfigureListener;

import edu.bu.cs633.grader.entity.User;
import edu.bu.cs633.grader.service.UserService;

/**
 * Spring Boot Application starter
 * @author donlanp
 *
 */
@SpringBootApplication
public class GraderApplication extends SpringBootServletInitializer implements ServletContextAware{

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(GraderApplication.class);
	}
	
	@Override
    protected SpringApplicationBuilder configure(
                 SpringApplicationBuilder application) {
        return application.sources(GraderApplication.class);
    }
	
	@Bean
    public ServletRegistrationBean servletRegistrationBean() {
        FacesServlet servlet = new FacesServlet();
        ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(servlet, "*.jsf");
        servletRegistrationBean.setLoadOnStartup(1);
		return servletRegistrationBean;
    }
	
	@Bean
    public ServletListenerRegistrationBean<ConfigureListener> jsfConfigureListener() {
        return new ServletListenerRegistrationBean<ConfigureListener>(new ConfigureListener());
    }
	
	@Override
	public void setServletContext(ServletContext context) {
		context.setInitParameter("com.sun.faces.forceLoadConfiguration", Boolean.TRUE.toString()); 
	}
	
	@Bean
	public CommandLineRunner demo(final UserService service){
		return new CommandLineRunner(){

			@Override
			public void run(String... args) throws Exception {
				List<User> users = service.getAllUsers();
				
				for(User u: users){
					System.out.println(u);
				}
			}
			
		};
	}


}
