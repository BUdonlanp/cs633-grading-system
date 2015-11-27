package edu.bu.cs633.grader.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import edu.bu.cs633.grader.entity.Enrollment;
import edu.bu.cs633.grader.entity.Grade;

public interface GradeRepository extends CrudRepository<Grade, Integer>{

	public List<Grade> findByEnrollment(Enrollment e);

}
