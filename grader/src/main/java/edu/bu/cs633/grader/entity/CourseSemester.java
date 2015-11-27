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
 * Represents an instance of a course that students can enroll in
 * 
 * @author donlanp
 * 
 */
@Entity
@Table(name = "course_semester")
public class CourseSemester {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int courseSemesterId;

	@ManyToOne
	@JoinColumn(name = "course_id")
	private Course course;

	@ManyToOne
	@JoinColumn(name = "semester_id")
	private Semester semester;

	@ManyToOne
	@JoinColumn(name = "teacher_id")
	private Teacher teacher;

	@OneToMany(mappedBy = "courseSemester", fetch = FetchType.EAGER)
	private List<Enrollment> enrollments;

	@OneToMany(mappedBy = "courseSemester", fetch = FetchType.EAGER)
	private List<Assignment> assignments;

	public String toString() {
		return semester.toString() + " : " + course.getCourseCode() + " : "
				+ teacher.toString();
	}

	/**
	 * @return the courseSemesterId
	 */
	public int getCourseSemesterId() {
		return courseSemesterId;
	}

	/**
	 * @param courseSemesterId
	 *            the courseSemesterId to set
	 */
	public void setCourseSemesterId(int courseSemesterId) {
		this.courseSemesterId = courseSemesterId;
	}

	/**
	 * @return the course
	 */
	public Course getCourse() {
		return course;
	}

	/**
	 * @param course
	 *            the course to set
	 */
	public void setCourse(Course course) {
		this.course = course;
	}

	/**
	 * @return the semester
	 */
	public Semester getSemester() {
		return semester;
	}

	/**
	 * @param semester
	 *            the semester to set
	 */
	public void setSemester(Semester semester) {
		this.semester = semester;
	}

	/**
	 * @return the teacher
	 */
	public Teacher getTeacher() {
		return teacher;
	}

	/**
	 * @param teacher
	 *            the teacher to set
	 */
	public void setTeacher(Teacher teacher) {
		this.teacher = teacher;
	}

	/**
	 * @return the enrollments
	 */
	public List<Enrollment> getEnrollments() {
		return enrollments;
	}

	/**
	 * @param enrollments
	 *            the enrollments to set
	 */
	public void setEnrollments(List<Enrollment> enrollments) {
		this.enrollments = enrollments;
	}

	/**
	 * @return the assignments
	 */
	public List<Assignment> getAssignments() {
		return assignments;
	}

	/**
	 * @param assignments
	 *            the assignments to set
	 */
	public void setAssignments(List<Assignment> assignments) {
		this.assignments = assignments;
	}

}
