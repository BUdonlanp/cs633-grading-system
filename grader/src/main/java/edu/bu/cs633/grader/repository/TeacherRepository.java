package edu.bu.cs633.grader.repository;

import org.springframework.data.repository.CrudRepository;

import edu.bu.cs633.grader.entity.Teacher;

public interface TeacherRepository extends CrudRepository<Teacher, Integer> {

}
