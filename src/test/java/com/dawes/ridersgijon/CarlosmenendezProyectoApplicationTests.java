package com.dawes.ridersgijon;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.time.LocalDate;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.dawes.ridersgijon.model.PedidoVO;
import com.dawes.ridersgijon.model.RolVO;
import com.dawes.ridersgijon.model.UserRolVO;
import com.dawes.ridersgijon.model.UserVO;
import com.dawes.ridersgijon.service.PedidoService;
import com.dawes.ridersgijon.service.RolService;
import com.dawes.ridersgijon.service.UserRolService;
import com.dawes.ridersgijon.service.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarlosmenendezProyectoApplicationTests {

	// Vamos a inyectar los Service de cada entidad para poder usarlos en los test
	@Autowired
	UserService userService;
	@Autowired
	PedidoService pedidoService;
	@Autowired
	RolService rolService;
	
	@Autowired
	UserRolService userRolService;
	
	
    public static BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }    
    public static String encode(String password) {
        return passwordEncoder().encode(password);
    }


	// Vamos a instanciar usuarios de prueba
	static UserVO user1;
	static UserVO user2;
	static UserVO user3;
	static RolVO rol1;
	static RolVO rol2;
	static RolVO rol3;	

	// Añadimos las propiedades necesarias a los usuarios de prueba
	@BeforeAll
	public static void preset() {		
		user1 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM", "carlosm@carlosm.com", encode("1234"), "", "", true,null );
		user2 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM", "luism@luism.com", encode("1234"), "", "", true, null);
		user3 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM", "pedrom@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		
		rol1 = new RolVO(0, "ROLE_ADMIN"); 
		rol2 = new RolVO(0, "ROLE_CLIENT");
		rol3 = new RolVO(0, "ROLE_RIDER");
		
		
	}
	// Comprobamos la inserción de usuarios en la BBDD. Campo email unique
		@Test
		@Order(1)
		public void t1_insertUserSuccess() {
			try {
				userService.save(user1);
			} catch (DataIntegrityViolationException e) {
				System.out.println("El usuario " + user1.getNombre() + " ya existe en la BBDD.");
				System.out.println(e.getCause());
			}
			try {
				userService.save(user2);
			} catch (DataIntegrityViolationException e) {
				System.out.println("El usuario " + user2.getNombre() + " ya existe en la BBDD.");
				System.out.println(e.getCause());
			}
			try {
				userService.save(user3);
			} catch (DataIntegrityViolationException e) {
				System.out.println("El usuario " + user3.getNombre() + " ya existe en la BBDD.");
				System.out.println(e.getCause());
			}
			assertNotNull(userService.findById(1));
		}
	
	@Test
	@Order(2)
	public void t2_InsertRolSuccess() {
		try {
			rolService.save(rol1);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El rol " + rol1.getNombre() + " ya existe en la BBDD.");
			System.out.println(e.getCause());
		}		
		try {
			rolService.save(rol2);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El rol " + rol2.getNombre() + " ya existe en la BBDD.");
			System.out.println(e.getCause());
		}		
		try {
			rolService.save(rol3);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El rol " + rol3.getNombre() + " ya existe en la BBDD.");
			System.out.println(e.getCause());
		}
		assertNotNull(rolService.findById(1));
	}
	
	@Test
	@Order(3)
	public void t3_InsertUserRolSuccess() {
		UserRolVO userRol1 = new UserRolVO(0, userService.findById(1).get(),rolService.findById(1).get());
		UserRolVO userRol2 = new UserRolVO(0, userService.findById(2).get(),rolService.findById(2).get());
		UserRolVO userRol3 = new UserRolVO(0, userService.findById(3).get(),rolService.findById(3).get());
		
		try {
			userRolService.save(userRol1);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El userRol " + userRol1.getIduserrol() + " ya existe en la BBDD.");
			System.out.println(e.getCause());
		}
		try {
			userRolService.save(userRol2);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El userRol " + userRol2.getIduserrol() + " ya existe en la BBDD.");
			System.out.println(e.getCause());
		}
		try {
			userRolService.save(userRol3);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El userRol " + userRol3.getIduserrol() + " ya existe en la BBDD.");
			System.out.println(e.getCause());
		}		
	}
	
	@Test
	@Order(12)
	public void t12_insertPedidoSuccess() {
		PedidoVO pedido1 = new PedidoVO(0, LocalDate.of(2021, 01, 11), "Pedro Perez", "C/ Muñeca cañí, nº 3",
					"985445588", "Ana Menéndez", "C/ Uría, nº 13, 4ºF", "984779988", 0, "Prueba Pedido 1",
					LocalDate.of(2021, 01, 12), userService.findById(1).get(), userService.findById(2).get());		
		PedidoVO pedido2 = new PedidoVO(0, LocalDate.of(2021, 01, 12), "Pablo Suarez", "C/ Pelota, nº 13", "985555588",
				"Juan Muñoz", "C/ Main, nº 23, 5ºA", "984669988", 1, "Prueba Pedido 2", LocalDate.of(2021, 01, 13),
				userService.findById(2).get(), userService.findById(3).get());		
		PedidoVO pedido3 = new PedidoVO(0, LocalDate.of(2021, 01, 13), "Clara Alonso", "C/ Principal, nº 21",
				"985665588", "Eva Pisto", "C/ Principado, nº 8, 1ºA", "984559988", 2, "Prueba Pedido 3",
				LocalDate.of(2021, 01, 14), userService.findById(3).get(), userService.findById(1).get());	
		
		try {
		pedidoService.save(pedido1);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El pedido " + pedido1.getId_pedido() + " ya existe en la BBDD.");
			System.out.println(e.getCause());
		}		
		try {
		pedidoService.save(pedido2);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El pedido " + pedido2.getId_pedido() + " ya existe en la BBDD.");
			System.out.println(e.getCause());
		}		
		try {
		pedidoService.save(pedido3);
		} catch (DataIntegrityViolationException e) {
			System.out.println("El pedido " + pedido3.getId_pedido() + " ya existe en la BBDD.");
			System.out.println(e.getCause());
		}
		assertNotNull(pedidoService.findById(1));
	}

	@Test
	@Order(13)
	public void t13_findPedidosByClienteSuccess() {
		List<PedidoVO> mostrar = pedidoService.findByCliente(userService.findById(1).get());
		System.out.println("FindPedidosByCliente Success: " + mostrar);
		assertTrue(pedidoService.findByCliente(userService.findById(1).get()).size() != 0);
	}

	@Test
	@Order(14)
	public void t14_findPedidosByRiderSuccess() {
		System.out.println("FindPedidosByRider Success: " + pedidoService.findByRider(userService.findById(2).get()));
		assertTrue(pedidoService.findByRider(userService.findById(2).get()).size() != 0);
	}

	@Test
	@Order(15)
	public void t15_findPedidosByClienteAndRiderSuccess() {
		System.out.println("FindPedidosByClienteAndRider Success: "
				+ pedidoService.findByClienteAndRider(userService.findById(2).get(), userService.findById(3).get()));
		assertTrue(pedidoService.findByClienteAndRider(userService.findById(2).get(), userService.findById(3).get())
				.size() != 0);
	}

	@Test
	@Order(16)
	public void t16_findPedidosByStatusSuccess() {
		System.out.println("Pedidos Nuevos [Status=0]: " + pedidoService.findByStatus(0));
		assertFalse(pedidoService.findByStatus(0).isEmpty());
	}

	@Test
	@Order(17)
	public void t17_findUsersByIsActiveSuccess() {
		System.out.println("findUsersByIsActive: " + userService.findByIsActive(true));
		assertTrue(userService.findByIsActive(true).size() != 0);
	}

	@Test
	@Order(18)
	public void t18_findUsersByUser_typeSuccess() {
		System.out.println("findUsersByUser_type: " + userService.findByUser_type("ADMIN"));
		assertTrue(userService.findByUser_type("ADMIN").size() != 0);
	}

	@Test
	@Order(19)
	public void t19_findRidersByCliente() {
		System.out.println(
				"****Lista Riders de Cliente: " + userService.findRidersByCliente(userService.findById(1).get()));
		assertTrue(userService.findRidersByCliente(userService.findById(1).get()).size() > 0);
	}

	@Test
	@Order(20)
	public void t20_findUserByEmailAndPaswordSuccess() {
		UserVO loginUser = userService.findByEmailAndPassword("carlosm@carlosm.com", "$2a$10$iixa48z.h72HuvjzMZHXeOCKxzvr02uvSsviN.qfIjr70dMrroMku").get();
		System.out.println("Usuario válido encontrado: " + loginUser);
		assertEquals("carlosm@carlosm.com", loginUser.getEmail());
	}
	
	
	//Autenticacion UserDetails
	@Test
	@Order(21)
	public void t21_findUserDetailsByEmailSuccess() {
		UserDetails loginUser = userService.loadUserByUsername("carlosm@carlosm.com");
		System.out.println("Usuario válido encontrado: " + loginUser);
		assertEquals("$2a$10$iixa48z.h72HuvjzMZHXeOCKxzvr02uvSsviN.qfIjr70dMrroMku", loginUser.getPassword());
	}
	
	/***************PRUEBAS ITERACIONES JSON y DEMAS LIBRERIA GSON....SuperUtil */
	@Test
	@Order(30)
	public void t30_findAllPedidosSuccess() {
		Iterable<PedidoVO> pedidos = pedidoService.findAll();
		Iterable<PedidoVO> iterable = pedidos;
		
		for (PedidoVO pedido : iterable) {
	        System.out.println(pedido);
	    }
		System.out.println(pedidos);
		assertTrue(true);
	}
	
	
	
	
	
	
	

}