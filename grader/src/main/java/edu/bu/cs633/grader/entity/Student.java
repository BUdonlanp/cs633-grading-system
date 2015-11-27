/**
 * 
 */
package edu.bu.cs633.grader.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * @author donlanp
 *
 */
@Entity
@Table(name="student")
public class Student {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int studentId;
	
	@OneToOne
	@JoinColumn(name="user_id")
	private User user;
	
	@OneToMany(mappedBy = "student")
	private List<Enrollment> enrollments;
	
	public Student(){
		
	}
	
	public String toString(){
		return user.getLastName() + ", " + user.getFirstName();
	}

	/**
	 * @return the studentId
	 */
	public int getStudentId() {
		return studentId;
	}

	/**
	 * @param studentId the studentId to set
	 */
	public void setStudentId(int studentId) {
		this.studentId = studentId;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the enrollments
	 */
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	/**
	 * @param enrollments the enrollments to set
	 */
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}
	
}
