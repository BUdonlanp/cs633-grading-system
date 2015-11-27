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
 * Represents the enrollment of a student in a courses
 * 
 * @author donlanp
 * 
 */
@Entity
@Table(name = "enrollment")
public class Enrollment {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int enrollmentId;

	@ManyToOne
	@JoinColumn(name = "course_semester_id")
	private CourseSemester courseSemester;

	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student student;
	
	@OneToMany(mappedBy = "enrollment", fetch=FetchType.EAGER)
	private List<Grade> grades;

	/**
	 * @return the enrollmentId
	 */
	public int getEnrollmentId() {
		return enrollmentId;
	}

	/**
	 * @param enrollmentId
	 *            the enrollmentId to set
	 */
	public void setEnrollmentId(int enrollmentId) {
		this.enrollmentId = enrollmentId;
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
	 * @return the student
	 */
	public Student getStudent() {
		return student;
	}

	/**
	 * @param student
	 *            the student to set
	 */
	public void setStudent(Student student) {
		this.student = student;
	}

	/**
	 * @return the grades
	 */
	public List<Grade> getGrades() {
		return grades;
	}

	/**
	 * @param grades the grades to set
	 */
	public void setGrades(List<Grade> grades) {
		this.grades = grades;
	}
}
