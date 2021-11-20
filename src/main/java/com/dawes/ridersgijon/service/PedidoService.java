package com.dawes.ridersgijon.service;

import java.util.List;
import java.util.Optional;

import com.dawes.ridersgijon.model.PedidoVO;
import com.dawes.ridersgijon.model.UserVO;

public interface PedidoService {
	
	/**
	 * @param <S>
	 * @param entity
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	<S extends PedidoVO> S save(S entity);

	/**
	 * @param <S>
	 * @param entities
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#saveAll(java.lang.Iterable)
	 */
	<S extends PedidoVO> Iterable<S> saveAll(Iterable<S> entities);

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	Optional<PedidoVO> findById(Integer id);

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
	Iterable<PedidoVO> findAll();

	/**
	 * @param ids
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findAllById(java.lang.Iterable)
	 */
	Iterable<PedidoVO> findAllById(Iterable<Integer> ids);

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
	void delete(PedidoVO entity);

	/**
	 * @param entities
	 * @see org.springframework.data.repository.CrudRepository#deleteAll(java.lang.Iterable)
	 */
	void deleteAll(Iterable<? extends PedidoVO> entities);

	/**
	 * 
	 * @see org.springframework.data.repository.CrudRepository#deleteAll()
	 */
	void deleteAll();
	
	//*****************************METODOS AJENOS AL CRUD REPOSITORY**************************
	/**
	 * 	
	 * @param user
	 * @return
	 */
	List<PedidoVO> findByCliente(UserVO user);		
	/**
	 * 
	 * @param user
	 * @return
	 */
	List<PedidoVO> findByRider(UserVO user);
	
	/**
	 * 
	 * @param cliente
	 * @param rider
	 * @return
	 */
	List<PedidoVO> findByClienteAndRider(UserVO cliente, UserVO rider);
	
	/**
	 * 
	 * @param status
	 * @return
	 */
	List<PedidoVO> findByStatus(int status);
	
	/**
	 * 
	 * @param status
	 * @param user
	 * @return
	 */
	List<PedidoVO> findByRiderAndStatus(UserVO rider, int status);
	
	
}