/**
 * 
 */
package com.dawes.ridersgijon.serviceImpl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dawes.ridersgijon.model.PedidoVO;
import com.dawes.ridersgijon.model.UserVO;
import com.dawes.ridersgijon.repository.PedidoRepository;
import com.dawes.ridersgijon.service.PedidoService;

/**
 * @author CarlosM
 *
 */
@Service
public class PedidoServiceImpl implements PedidoService{
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	/**
	 * @param <S>
	 * @param entity
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#save(java.lang.Object)
	 */
	@Override
	public <S extends PedidoVO> S save(S entity) {
		return pedidoRepository.save(entity);
	}

	/**
	 * @param <S>
	 * @param entities
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#saveAll(java.lang.Iterable)
	 */
	@Override
	public <S extends PedidoVO> Iterable<S> saveAll(Iterable<S> entities) {
		return pedidoRepository.saveAll(entities);
	}

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findById(java.lang.Object)
	 */
	@Override
	public Optional<PedidoVO> findById(Integer id) {
		return pedidoRepository.findById(id);
	}

	/**
	 * @param id
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#existsById(java.lang.Object)
	 */
	@Override
	public boolean existsById(Integer id) {
		return pedidoRepository.existsById(id);
	}

	/**
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findAll()
	 */
	@Override
	public Iterable<PedidoVO> findAll() {
		return pedidoRepository.findAll();
	}

	/**
	 * @param ids
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#findAllById(java.lang.Iterable)
	 */
	@Override
	public Iterable<PedidoVO> findAllById(Iterable<Integer> ids) {
		return pedidoRepository.findAllById(ids);
	}

	/**
	 * @return
	 * @see org.springframework.data.repository.CrudRepository#count()
	 */
	@Override
	public long count() {
		return pedidoRepository.count();
	}

	/**
	 * @param id
	 * @see org.springframework.data.repository.CrudRepository#deleteById(java.lang.Object)
	 */
	@Override
	public void deleteById(Integer id) {
		pedidoRepository.deleteById(id);
	}

	/**
	 * @param entity
	 * @see org.springframework.data.repository.CrudRepository#delete(java.lang.Object)
	 */
	@Override
	public void delete(PedidoVO entity) {
		pedidoRepository.delete(entity);
	}

	/**
	 * @param entities
	 * @see org.springframework.data.repository.CrudRepository#deleteAll(java.lang.Iterable)
	 */
	@Override
	public void deleteAll(Iterable<? extends PedidoVO> entities) {
		pedidoRepository.deleteAll(entities);
	}

	/**
	 * 
	 * @see org.springframework.data.repository.CrudRepository#deleteAll()
	 */
	@Override
	public void deleteAll() {
		pedidoRepository.deleteAll();
	}
//************************************************** NO CRUDREPOSITORY*********************************
	
	/**
	 * @param user
	 * @return
	 * @see com.dawes.ridersgijon.repository.PedidoRepository#findByCliente(com.dawes.ridersgijon.model.UserVO)
	 */
	public List<PedidoVO> findByCliente(UserVO user) {
		return pedidoRepository.findByCliente(user);
	}

	/**
	 * @param user
	 * @return
	 * @see com.dawes.ridersgijon.repository.PedidoRepository#findByRider(com.dawes.ridersgijon.model.UserVO)
	 */
	public List<PedidoVO> findByRider(UserVO user) {
		return pedidoRepository.findByRider(user);
	}

	/**
	 * @param cliente
	 * @param rider
	 * @return
	 * @see com.dawes.ridersgijon.repository.PedidoRepository#findByClienteAndRider(com.dawes.ridersgijon.model.UserVO, com.dawes.ridersgijon.model.UserVO)
	 */
	public List<PedidoVO> findByClienteAndRider(UserVO cliente, UserVO rider) {
		return pedidoRepository.findByClienteAndRider(cliente, rider);
	}

	/**
	 * @param status
	 * @return
	 * @see com.dawes.ridersgijon.repository.PedidoRepository#findByStatus(int)
	 */
	public List<PedidoVO> findByStatus(int status) {
		return pedidoRepository.findByStatus(status);
	}
	
	/**
	 * 
	 * @param status
	 * @param user
	 * @return
	 */
	public List<PedidoVO> findByRiderAndStatus(UserVO rider, int status){
		return pedidoRepository.findByRiderAndStatus(rider, status);				
	}
	
}

	