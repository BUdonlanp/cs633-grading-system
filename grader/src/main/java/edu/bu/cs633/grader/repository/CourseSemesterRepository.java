package edu.bu.cs633.grader.repository;

import org.springframework.data.repository.CrudRepository;

import edu.bu.cs633.grader.entity.Course;
import edu.bu.cs633.grader.entity.CourseSemester;
import edu.bu.cs633.grader.entity.Semester;
import edu.bu.cs633.grader.entity.Teacher;

/**
 * @author donlanp
 *
 */
public interface CourseSemesterRepository extends CrudRepository<CourseSemester, Integer> {

	public CourseSemester findByTeacherAndCourseAndSemester(Teacher teacher, Course course, Semester semester);
	
}
