package edu.bu.cs633.grader.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Represents a student's graded assignment
 * 
 * @author donlanp
 * 
 */
@Entity
@Table(name = "grade")
public class Grade {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int gradeId;

	@ManyToOne
	@JoinColumn(name = "enrollment_id")
	private Enrollment enrollment;

	@ManyToOne
	@JoinColumn(name = "assignment_id")
	private Assignment assignment;

	private int pointsGraded = 0;

	/**
	 * @return the gradeId
	 */
	public int getGradeId() {
		return gradeId;
	}

	/**
	 * @param gradeId
	 *            the gradeId to set
	 */
	public void setGradeId(int gradeId) {
		this.gradeId = gradeId;
	}

	/**
	 * @return the enrollment
	 */
	public Enrollment getEnrollment() {
		return enrollment;
	}

	/**
	 * @param enrollment
	 *            the enrollment to set
	 */
	public void setEnrollment(Enrollment enrollment) {
		this.enrollment = enrollment;
	}

	/**
	 * @return the assignment
	 */
	public Assignment getAssignment() {
		return assignment;
	}

	/**
	 * @param assignment
	 *            the assignment to set
	 */
	public void setAssignment(Assignment assignment) {
		this.assignment = assignment;
	}

	/**
	 * @return the pointsGraded
	 */
	public int getPointsGraded() {
		return pointsGraded;
	}

	/**
	 * @param pointsGraded
	 *            the pointsGraded to set
	 */
	public void setPointsGraded(int pointsGraded) {
		this.pointsGraded = pointsGraded;
	}

}
