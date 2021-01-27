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
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.event.annotation.BeforeTestClass;

import com.dawes.ridersgijon.model.PedidoVO;
import com.dawes.ridersgijon.model.UserVO;
import com.dawes.ridersgijon.service.PedidoService;
import com.dawes.ridersgijon.service.UserService;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class CarlosmenendezProyectoApplicationTests {

	// Vamos a inyectar los Service de cada entidad para poder usarlos en los test
	@Autowired
	UserService userService;
	@Autowired
	PedidoService pedidoService;

	// Vamos a instanciar usuarios de prueba
	static UserVO user1;
	static UserVO user2;
	static UserVO user3;

	// Añadimos las propiedades necesarias a los usuarios de prueba
	@BeforeAll
	public static void preset() {
		user1 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM", "carlosm@carlosm.com", "1234", "", "", true);
		user2 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM", "luism@luism.com", "1234", "", "", true);
		user3 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM", "pedrom@pedrom.com", "1234", "1234DDR", "COCHE", true);
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
	public void t2_insertPedidoSuccess() {
		PedidoVO pedido1 = new PedidoVO(0, LocalDate.of(2021, 01, 11), "Pedro Perez", "C/ Muñeca cañí, nº 3",
				"985445588", "Ana Menéndez", "C/ Uría, nº 13, 4ºF", "984779988", 0, "Prueba Pedido 1",
				LocalDate.of(2021, 01, 12), userService.findById(1).get(), userService.findById(2).get());
		PedidoVO pedido2 = new PedidoVO(0, LocalDate.of(2021, 01, 12), "Pablo Suarez", "C/ Pelota, nº 13", "985555588",
				"Juan Muñoz", "C/ Main, nº 23, 5ºA", "984669988", 1, "Prueba Pedido 2", LocalDate.of(2021, 01, 13),
				userService.findById(2).get(), userService.findById(3).get());
		PedidoVO pedido3 = new PedidoVO(0, LocalDate.of(2021, 01, 13), "Clara Alonso", "C/ Principal, nº 21",
				"985665588", "Eva Pisto", "C/ Principado, nº 8, 1ºA", "984559988", 2, "Prueba Pedido 3",
				LocalDate.of(2021, 01, 14), userService.findById(3).get(), userService.findById(1).get());

		pedidoService.save(pedido1);
		pedidoService.save(pedido2);
		pedidoService.save(pedido3);

		assertNotNull(pedidoService.findById(1));
	}

	@Test
	@Order(3)
	public void t3_findPedidosByClienteSuccess() {
		System.out
				.println("FindPedidosByCliente Success: " + pedidoService.findByCliente(userService.findById(1).get()));
		assertTrue(pedidoService.findByCliente(userService.findById(1).get()).size() != 0);
	}

	@Test
	@Order(4)
	public void t4_findPedidosByRiderSuccess() {
		System.out.println("FindPedidosByRider Success: " + pedidoService.findByRider(userService.findById(2).get()));
		assertTrue(pedidoService.findByRider(userService.findById(2).get()).size() != 0);
	}

	@Test
	@Order(5)
	public void t5_findPedidosByClienteAndRiderSuccess() {
		System.out.println("FindPedidosByClienteAndRider Success: "
				+ pedidoService.findByClienteAndRider(userService.findById(2).get(), userService.findById(3).get()));
		assertTrue(pedidoService.findByClienteAndRider(userService.findById(2).get(), userService.findById(3).get())
				.size() != 0);
	}

	@Test
	@Order(6)
	public void t6_findPedidosByStatusSuccess() {
		System.out.println("Pedidos Nuevos [Status=0]: " + pedidoService.findByStatus(0));
		assertFalse(pedidoService.findByStatus(0).isEmpty());
	}

	@Test
	@Order(7)
	public void t7_findUsersByIsActiveSuccess() {
		System.out.println("findUsersByIsActive: " + userService.findByIsActive(true));
		assertTrue(userService.findByIsActive(true).size() != 0);
	}

	@Test
	@Order(8)
	public void t8_findUsersByUser_typeSuccess() {
		System.out.println("findUsersByUser_type: " + userService.findByUser_type("ADMIN"));
		assertTrue(userService.findByUser_type("ADMIN").size() != 0);
	}

	@Test
	@Order(9)
	public void t9_findRidersByCliente() {
		System.out.println(
				"****Lista Riders de Cliente: " + userService.findRidersByCliente(userService.findById(1).get()));
		assertTrue(userService.findRidersByCliente(userService.findById(1).get()).size() > 0);
	}

	@Test
	@Order(10)
	public void t10_findUserByEmailAndPaswordSuccess() {
		UserVO loginUser = userService.findByEmailAndPassword("carlosm@carlosm.com", "1234").get();
		System.out.println("Usuario válido encontrado: " + loginUser);
		assertEquals("carlosm@carlosm.com", loginUser.getEmail());
	}

}