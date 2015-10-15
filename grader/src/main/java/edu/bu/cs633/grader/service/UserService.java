/**
 * 
 */
package edu.bu.cs633.grader.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import edu.bu.cs633.grader.entity.Admin;
import edu.bu.cs633.grader.entity.Student;
import edu.bu.cs633.grader.entity.Teacher;
import edu.bu.cs633.grader.entity.User;
import edu.bu.cs633.grader.repository.AdminRepository;
import edu.bu.cs633.grader.repository.StudentRepository;
import edu.bu.cs633.grader.repository.TeacherRepository;
import edu.bu.cs633.grader.repository.UserRepository;

/**
 * @author donlanp
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepo;
	@Autowired
	private AdminRepository adminRepo;
	@Autowired
	private TeacherRepository teacherRepo;
	@Autowired
	private StudentRepository studentRepo;
	
	/**
	 * Obtains a user's information for logging in
	 * @param username The username of the user logging in
	 * @param password The password of the user logging in
	 * @return The user object if successful, null otherwise
	 */
	public User login(String username, String password){
		return userRepo.findByUsernameAndPassword(username, password);
	}
	
	/**
	 * Create a new user within the system
	 * The username and password for the newly created user will be lastname + firstname[0]
	 * @param firstName The first name of the user
	 * @param lastName The last name of the user
	 * @param isAdmin Whether the user should be an admin user
	 * @param isTeacher Whether the user should be a teacher user
	 * @param isStudent whether the user should be a student user
	 * @return The newly created user
	 */
	public User createUser(String firstName, String lastName, boolean isAdmin, boolean isTeacher, boolean isStudent){
		User user = new User();
		user.setFirstName(firstName);
		user.setLastName(lastName);
		
		//Create username
		String username = lastName.toLowerCase() + firstName.toLowerCase().charAt(0);
		user.setUsername(username);
		user.setPassword(username);
		
		//Save to repo
		user = userRepo.save(user);
		
		//Save roles
		if(isAdmin){
			Admin a = new Admin();
			a.setUser(user);
			adminRepo.save(a);
			user.setAdmin(a);
		}
		if(isTeacher){
			Teacher a = new Teacher();
			a.setUser(user);
			teacherRepo.save(a);
			user.setTeacher(a);
		}
		if(isStudent){
			Student a = new Student();
			a.setUser(user);
			studentRepo.save(a);
			user.setStudent(a);
		}
		
		return user;
	}
	
	/**
	 * Updates a user within the system
	 * @param user The user to update
	 * @return The newly updated user object
	 */
	public User saveUser(User user){
		
		user = userRepo.save(user);
		
		return user;
	}
	
	
	public List<User> getAllUsers(){
		Iterable<User> result = userRepo.findAll();
		
		List<User> retList = new ArrayList<User>();
		Iterator<User> iter = result.iterator();
		while(iter.hasNext()){
			retList.add(iter.next());
		}
		
		
		return retList;
	}
	
}
