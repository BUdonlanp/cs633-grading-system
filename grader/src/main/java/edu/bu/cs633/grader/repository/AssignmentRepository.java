package edu.bu.cs633.grader.repository;

import org.springframework.data.repository.CrudRepository;

import edu.bu.cs633.grader.entity.Assignment;

public interface AssignmentRepository extends CrudRepository<Assignment, Integer> {

}
