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

import com.dawes.ridersgijon.model.PedidoVO;
import com.dawes.ridersgijon.model.UserVO;
/**
 * @author CarlosM
 *
 */
@Repository
public interface PedidoRepository extends CrudRepository<PedidoVO, Integer>{		
	
	List<PedidoVO> findByCliente(UserVO user);		
	
	List<PedidoVO> findByRider(UserVO user);
	
	List<PedidoVO> findByClienteAndRider(UserVO cliente, UserVO rider);
	
	List<PedidoVO> findByStatus(int status);
	
	List<PedidoVO> findByRiderAndStatus(UserVO rider, int status);
	
	
}
	