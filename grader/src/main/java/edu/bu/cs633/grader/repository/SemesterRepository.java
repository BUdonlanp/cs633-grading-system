package edu.bu.cs633.grader.repository;

import org.springframework.data.repository.CrudRepository;

import edu.bu.cs633.grader.entity.Semester;

/**
 * 
 * @author donlanp
 *
 */
public interface SemesterRepository extends CrudRepository<Semester, Integer> {

	public Semester findByYearAndSemesterName(int year, String semesterName);
	
}
