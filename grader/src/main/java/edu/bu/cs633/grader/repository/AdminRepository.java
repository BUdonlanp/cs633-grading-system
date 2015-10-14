/**
 * 
 */
package edu.bu.cs633.grader.repository;

import org.springframework.data.repository.CrudRepository;

import edu.bu.cs633.grader.entity.Admin;

/**
 * @author donlanp
 *
 */
public interface AdminRepository extends CrudRepository<Admin, Integer> {

}
