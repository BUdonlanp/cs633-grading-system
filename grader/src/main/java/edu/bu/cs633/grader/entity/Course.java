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
 * Represents a Course to take
 * 
 * @author donlanp
 * 
 */
@Entity
@Table(name = "course")
public class Course {

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int courseId;

	private String courseCode;
	private String courseName;

	@OneToMany(mappedBy = "course", fetch = FetchType.EAGER)
	private List<CourseSemester> courseSemesters;
	
	public Course(){
		courseSemesters = new ArrayList<CourseSemester>();
	}

	/**
	 * @return the courseId
	 */
	public int getCourseId() {
		return courseId;
	}

	/**
	 * @param courseId
	 *            the courseId to set
	 */
	public void setCourseId(int courseId) {
		this.courseId = courseId;
	}

	/**
	 * @return the courseCode
	 */
	public String getCourseCode() {
		return courseCode;
	}

	/**
	 * @param courseCode
	 *            the courseCode to set
	 */
	public void setCourseCode(String courseCode) {
		this.courseCode = courseCode;
	}

	/**
	 * @return the courseName
	 */
	public String getCourseName() {
		return courseName;
	}

	/**
	 * @param courseName
	 *            the courseName to set
	 */
	public void setCourseName(String courseName) {
		this.courseName = courseName;
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
