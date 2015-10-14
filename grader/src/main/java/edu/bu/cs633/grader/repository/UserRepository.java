/**
 * 
 */
package edu.bu.cs633.grader.repository;

import org.springframework.data.repository.CrudRepository;

import edu.bu.cs633.grader.entity.User;

/**
 * @author donlanp
 *
 */
public interface UserRepository extends CrudRepository<User, Integer> {

	public User findByUsernameAndPassword(String username, String password);
	
}
