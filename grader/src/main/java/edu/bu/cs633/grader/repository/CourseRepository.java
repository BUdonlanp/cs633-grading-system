package edu.bu.cs633.grader.repository;

import org.springframework.data.repository.CrudRepository;

import edu.bu.cs633.grader.entity.Course;

/**
 * @author donlanp
 *
 */
public interface CourseRepository extends CrudRepository<Course, Integer> {

	public Course findByCourseCode(String courseCode);
	
}
