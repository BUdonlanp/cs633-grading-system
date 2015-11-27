package edu.bu.cs633.grader.entity;

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
 * Represents a teacher created assignment
 * 
 * @author donlanp
 * 
 */
@Entity
@Table(name = "assignment")
public class Assignment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int assignmentId;

	@ManyToOne
	@JoinColumn(name = "course_semester_id")
	private CourseSemester courseSemester;

	private String assignmentName;
	private int assignmentTotalPoints;

	@OneToMany(mappedBy = "assignment", fetch=FetchType.EAGER)
	private List<Grade> grades;

	/**
	 * @return the assignmentId
	 */
	public int getAssignmentId() {
		return assignmentId;
	}

	/**
	 * @param assignmentId
	 *            the assignmentId to set
	 */
	public void setAssignmentId(int assignmentId) {
		this.assignmentId = assignmentId;
	}

	/**
	 * @return the courseSemester
	 */
	public CourseSemester getCourseSemester() {
		return courseSemester;
	}

	/**
	 * @param courseSemester
	 *            the courseSemester to set
	 */
	public void setCourseSemester(CourseSemester courseSemester) {
		this.courseSemester = courseSemester;
	}

	/**
	 * @return the assignmentName
	 */
	public String getAssignmentName() {
		return assignmentName;
	}

	/**
	 * @param assignmentName
	 *            the assignmentName to set
	 */
	public void setAssignmentName(String assignmentName) {
		this.assignmentName = assignmentName;
	}

	/**
	 * @return the assignmentTotalPoints
	 */
	public int getAssignmentTotalPoints() {
		return assignmentTotalPoints;
	}

	/**
	 * @param assignmentTotalPoints
	 *            the assignmentTotalPoints to set
	 */
	public void setAssignmentTotalPoints(int assignmentTotalPoints) {
		this.assignmentTotalPoints = assignmentTotalPoints;
	}

	/**
	 * @return the grades
	 */
	public List<Grade> getGrades() {
		return grades;
	}

	/**
	 * @param grades
	 *            the grades to set
	 */
	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}

}
