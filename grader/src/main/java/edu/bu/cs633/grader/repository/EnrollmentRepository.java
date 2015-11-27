package edu.bu.cs633.grader.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.bu.cs633.grader.entity.Enrollment;
import edu.bu.cs633.grader.entity.Student;

/**
 * Used for our database interactions for Enrollment objects
 * @author donlanp
 * 
 */
public interface EnrollmentRepository extends
		CrudRepository<Enrollment, Integer> {

	public List<Enrollment> findByStudent(Student student);
	
}
