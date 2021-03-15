/**
 * 
 */
package com.dawes.ridersgijon.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.ridersgijon.model.UserRolVO;
import com.dawes.ridersgijon.model.UserVO;
import com.dawes.ridersgijon.repository.UserRolRepository;
import com.dawes.ridersgijon.service.UserRolService;

/**
 * @author CarlosM
 *
 */
@Service
public class UserRolServiceImpl implements UserRolService {
	
	@Autowired
	private UserRolRepository userRolRepository;

	/**
	 * @param <S>
	 * @param entity
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	@Override
	public <S extends UserRolVO> S save(S entity) {
		return userRolRepository.save(entity);
	}

	/**
	 * @param <S>
	 * @param entities
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#saveAll(java.lang.Iterable)
	 */
	@Override
	public <S extends UserRolVO> Iterable<S> saveAll(Iterable<S> entities) {
		return userRolRepository.saveAll(entities);
	}

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	@Override
	public Optional<UserRolVO> findById(Integer id) {
		return userRolRepository.findById(id);
	}

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#existsById(java.lang.Object)
	 */
	@Override
	public boolean existsById(Integer id) {
		return userRolRepository.existsById(id);
	}

	/**
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@Override
	public Iterable<UserRolVO> findAll() {
		return userRolRepository.findAll();
	}

	/**
	 * @param ids
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findAllById(java.lang.Iterable)
	 */
	@Override
	public Iterable<UserRolVO> findAllById(Iterable<Integer> ids) {
		return userRolRepository.findAllById(ids);
	}

	/**
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#count()
	 */
	@Override
	public long count() {
		return userRolRepository.count();
	}

	/**
	 * @param id
	 * @see org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)
	 */
	@Override
	public void deleteById(Integer id) {
		userRolRepository.deleteById(id);
	}

	/**
	 * @param entity
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
	 */
	@Override
	public void delete(UserRolVO entity) {
		userRolRepository.delete(entity);
	}

	/**
	 * @param entities
	 * @see org.springframework.data.repository.CrudRepository#deleteAll(java.lang.Iterable)
	 */
	@Override
	public void deleteAll(Iterable<? extends UserRolVO> entities) {
		userRolRepository.deleteAll(entities);
	}

	/**
	 * 
	 * @see org.springframework.data.repository.CrudRepository#deleteAll()
	 */
	@Override
	public void deleteAll() {
		userRolRepository.deleteAll();
	}

	/**
	 * @param user
	 * @return
	 * @see com.dawes.ridersgijon.repository.UserRolRepository#findByUser(com.dawes.ridersgijon.model.UserVO)
	 */
	public Optional<UserRolVO> findByUser(UserVO user) {
		return userRolRepository.findByUser(user);
	}
	
	

}
