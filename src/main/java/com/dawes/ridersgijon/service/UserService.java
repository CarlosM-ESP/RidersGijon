package com.dawes.ridersgijon.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.dawes.ridersgijon.model.UserVO;

public interface UserService {

	/**
	 * @param <S>
	 * @param entity
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	<S extends UserVO> S save(S entity);

	/**
	 * @param <S>
	 * @param entities
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#saveAll(java.lang.Iterable)
	 */
	<S extends UserVO> Iterable<S> saveAll(Iterable<S> entities);

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	Optional<UserVO> findById(Integer id);

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#existsById(java.lang.Object)
	 */
	boolean existsById(Integer id);

	/**
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	Iterable<UserVO> findAll();

	/**
	 * @param ids
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findAllById(java.lang.Iterable)
	 */
	Iterable<UserVO> findAllById(Iterable<Integer> ids);

	/**
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#count()
	 */
	long count();

	/**
	 * @param id
	 * @see org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)
	 */
	void deleteById(Integer id);

	/**
	 * @param entity
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
	 */
	void delete(UserVO entity);

	/**
	 * @param entities
	 * @see org.springframework.data.repository.CrudRepository#deleteAll(java.lang.Iterable)
	 */
	void deleteAll(Iterable<? extends UserVO> entities);

	/**
	 * 
	 * @see org.springframework.data.repository.CrudRepository#deleteAll()
	 */
	void deleteAll();
	
	//********************************************* NO CRUD REPOSITORY **************************************
	/**
	 * 
	 * @param email
	 * @param password
	 * @return
	 */
	Optional<UserVO> findByEmailAndPassword(String email, String password);
	
	/**
	 * 
	 * @param activo
	 * @return
	 */
	List<UserVO> findByIsActive(Boolean activo);
	
	/**
	 * 
	 * @param type
	 * @return
	 */
	List<UserVO> findByUser_type(String type);
	
	/**
	 * 
	 * @param cliente
	 * @return
	 */
	List<UserVO> findRidersByCliente(UserVO cliente);
	

	/**
	 * Spring Security. Requerido para definir los campos de login
	 * 
	 * @param email
	 * @return
	 * @throws UsernameNotFoundException
	 */
	UserDetails loadUserByUsername(String email) throws UsernameNotFoundException;
	
	
	/**
	 * Encoding password 
	 * @param password
	 * @return
	 */
	String encode(String password);
	
	/**
	 * Autenticacion
	 * @return
	 */
	boolean isAuthenticated();
	
	/**
	 * 
	 * @return
	 */
	UserVO findUserLogged();
	
}