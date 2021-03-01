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


import com.dawes.ridersgijon.model.UserVO;


/**
 * @author CarlosM
 *
 */
@Repository
public interface UserRepository extends CrudRepository<UserVO, Integer>{
	
	Optional<UserVO> findByEmailAndPassword(String email, String password);
	
	List<UserVO> findByIsActive(Boolean activo);
	
	@Query("select u from UserVO u where user_type = :type")
	List<UserVO> findByUser_type(String type);
		
	@Query("SELECT u FROM UserVO AS u where u IN (SELECT DISTINCT p.rider FROM PedidoVO p WHERE p.cliente = :clienteUser)")
	List<UserVO> findRidersByCliente(UserVO clienteUser);
	
	Optional<UserVO> findByEmail(String email);

}