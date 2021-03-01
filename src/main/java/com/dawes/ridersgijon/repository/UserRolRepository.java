/**
 * 
 */
package com.dawes.ridersgijon.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.dawes.ridersgijon.model.UserRolVO;

/**
 * @author CarlosM
 *
 */
@Repository
public interface UserRolRepository extends CrudRepository<UserRolVO, Integer>{	
	
}
	