/**
 * 
 */
package edu.bu.cs633.grader.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * Simple POJO for JPA of a User
 * 
 * @author donlanp
 * 
 */
@Entity
@Table(name = "user")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int userId;

	private String username;
	private String firstName;
	private String lastName;

	private String password;

	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Admin> admin;

	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Teacher> teacher;

	@OneToMany(mappedBy="user", fetch=FetchType.EAGER)
	private List<Student> student;

	/**
	 * Default constructor
	 */
	public User() {
		admin = new ArrayList<Admin>();
		teacher = new ArrayList<Teacher>();
		student = new ArrayList<Student>();
	}

	public boolean isAdmin() {
		return null != admin && !admin.isEmpty();
	}

	public boolean isTeacher() {
		return null != teacher && !teacher.isEmpty();
	}

	public boolean isStudent() {
		return null != student && !student.isEmpty();
	}

	public String toString() {
		return username + " -- A: " + isAdmin() + " | T: " + isTeacher()
				+ " | S: " + isStudent();
	}

	// Getters & Setters

	/**
	 * @return the userId
	 */
	public int getUserId() {
		return userId;
	}

	/**
	 * @param userId
	 *            the userId to set
	 */
	public void setUserId(int userId) {
		this.userId = userId;
	}

	/**
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * @param username
	 *            the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the firstName
	 */
	public String getFirstName() {
		return firstName;
	}

	/**
	 * @param firstName
	 *            the firstName to set
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * @return the lastName
	 */
	public String getLastName() {
		return lastName;
	}

	/**
	 * @param lastName
	 *            the lastName to set
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password
	 *            the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the admin
	 */
	public List<Admin> getAdmin() {
		return admin;
	}

	/**
	 * @param admin
	 *            the admin to set
	 */
	public void setAdmin(List<Admin> admin) {
		this.admin = admin;
	}

	/**
	 * @return the teacher
	 */
	public List<Teacher> getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher
	 *            the teacher to set
	 */
	public void setTeacher(List<Teacher> teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the student
	 */
	public List<Student> getStudent() {
		return student;
	}

	/**
	 * @param student
	 *            the student to set
	 */
	public void setStudent(List<Student> student) {
		this.student = student;
	}

}
