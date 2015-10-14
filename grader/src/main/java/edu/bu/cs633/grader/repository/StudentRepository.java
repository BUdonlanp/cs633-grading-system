/**
 * 
 */
package edu.bu.cs633.grader.repository;

import org.springframework.data.repository.CrudRepository;

import edu.bu.cs633.grader.entity.Student;

/**
 * @author donlanp
 *
 */
public interface StudentRepository extends CrudRepository<Student, Integer> {

}
