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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author donlanp
 * 
 */
@Entity
@Table(name = "teacher")
public class Teacher {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int teacherId;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user;

	@OneToMany(mappedBy = "teacher", fetch = FetchType.EAGER)
	private List<CourseSemester> courseSemesters;

	public Teacher() {
		courseSemesters = new ArrayList<CourseSemester>();
	}

	/**
	 * @return the teacherId
	 */
	public int getTeacherId() {
		return teacherId;
	}

	/**
	 * @param teacherId
	 *            the teacherId to set
	 */
	public void setTeacherId(int teacherId) {
		this.teacherId = teacherId;
	}

	/**
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * @param user
	 *            the user to set
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * @return the courseSemesters
	 */
	public List<CourseSemester> getCourseSemesters() {
		return courseSemesters;
	}

	/**
	 * @param courseSemesters
	 *            the courseSemesters to set
	 */
	public void setCourseSemesters(List<CourseSemester> courseSemesters) {
		this.courseSemesters = courseSemesters;
	}

}
