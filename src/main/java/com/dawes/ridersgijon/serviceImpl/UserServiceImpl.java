/**
 * 
 */
package com.dawes.ridersgijon.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


import com.dawes.ridersgijon.model.UserVO;
import com.dawes.ridersgijon.repository.UserRepository;
import com.dawes.ridersgijon.service.UserService;

/**
 * @author CarlosM
 *
 */
@Service
public class UserServiceImpl implements UserDetailsService, UserService{
	
	@Autowired
	UserRepository userRepository;
	
	@Autowired
	PasswordEncoder encoder;
	
	//Requerido por Spring Security
	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {		
		UserVO user = userRepository.findByEmail(email).get();
	    if (user == null) {
	        throw new UsernameNotFoundException("Not found!");
	    }
	    return user;
	}


	/**
	 * @param <S>
	 * @param entity
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	@Override
	public <S extends UserVO> S save(S entity) {
		return userRepository.save(entity);
	}

	/**
	 * @param <S>
	 * @param entities
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#saveAll(java.lang.Iterable)
	 */
	@Override
	public <S extends UserVO> Iterable<S> saveAll(Iterable<S> entities) {
		return userRepository.saveAll(entities);
	}

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	@Override
	public Optional<UserVO> findById(Integer id) {
		return userRepository.findById(id);
	}

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#existsById(java.lang.Object)
	 */
	@Override
	public boolean existsById(Integer id) {
		return userRepository.existsById(id);
	}

	/**
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@Override
	public Iterable<UserVO> findAll() {
		return userRepository.findAll();
	}

	/**
	 * @param ids
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findAllById(java.lang.Iterable)
	 */
	@Override
	public Iterable<UserVO> findAllById(Iterable<Integer> ids) {
		return userRepository.findAllById(ids);
	}

	/**
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#count()
	 */
	@Override
	public long count() {
		return userRepository.count();
	}

	/**
	 * @param id
	 * @see org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)
	 */
	@Override
	public void deleteById(Integer id) {
		userRepository.deleteById(id);
	}

	/**
	 * @param entity
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
	 */
	@Override
	public void delete(UserVO entity) {
		userRepository.delete(entity);
	}

	/**
	 * @param entities
	 * @see org.springframework.data.repository.CrudRepository#deleteAll(java.lang.Iterable)
	 */
	@Override
	public void deleteAll(Iterable<? extends UserVO> entities) {
		userRepository.deleteAll(entities);
	}

	/**
	 * 
	 * @see org.springframework.data.repository.CrudRepository#deleteAll()
	 */
	@Override
	public void deleteAll() {
		userRepository.deleteAll();
	}
	//*****************************NO CRUD REPOSITORY************************************

	/**
	 * @param email
	 * @param password
	 * @return
	 * @see com.dawes.ridersgijon.repository.UserRepository#findByEmailAndPassword(java.lang.String, java.lang.String)
	 */
	public Optional<UserVO> findByEmailAndPassword(String email, String password) {
		return userRepository.findByEmailAndPassword(email, password);
	}

	/**
	 * @param activo
	 * @return
	 * @see com.dawes.ridersgijon.repository.UserRepository#findByIsActive(java.lang.Boolean)
	 */
	public List<UserVO> findByIsActive(Boolean activo) {
		return userRepository.findByIsActive(activo);
	}

	/**
	 * @param type
	 * @return
	 * @see com.dawes.ridersgijon.repository.UserRepository#findByUser_type(java.lang.String)
	 */
	public List<UserVO> findByUser_type(String type) {
		return userRepository.findByUser_type(type);
	}

	/**
	 * @param cliente
	 * @return
	 * @see com.dawes.ridersgijon.repository.UserRepository#findRidersByCliente(com.dawes.ridersgijon.model.UserVO)
	 */
	public List<UserVO> findRidersByCliente(UserVO cliente) {
		return userRepository.findRidersByCliente(cliente);
	}
	
	//Codificador de la contraseña
		public String encode(String password) {
			return encoder.encode(password);
		}
	
	
	//**********************************PRUEBAS redireccionamiento usuarios y autenticacion**************************************************
		//Verificación de autenticación. Devuleve true si existe un usuario autenticado en el contexto
		public boolean isAuthenticated() {
		    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		    if (authentication == null || AnonymousAuthenticationToken.class.isAssignableFrom(authentication.getClass())) {
		        return false;
		    }	    	    
		    return authentication.isAuthenticated();
		}
		
		//Devuelve, en formato String la colección de roles del usuario autenticado en el contexto
		public String giveMeTheRole() {
				String roles;		    
			    if (isAuthenticated()) {
			    	roles = SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString();	    
				    
			    }else {
			    	roles = "";
			    }
			    return roles;
			}
	//**********************************PRUEBAS redireccionamiento usuarios y autenticacion******
	
	
	
}
