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
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
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
	static UserVO user0, user1, user2, user3, user4, user5, user6, user7, user8, user9, user10, user11, user12, user13, user14, user15, user16; 
	static UserVO user20, user21, user22, user23, user24, user25, user26, user27, user28, user29, user30, user31, user32, user33, user34, user35, user36;
	static UserVO user40, user41, user42, user43, user44, user45, user46, user47, user48, user49, user50, user51, user52, user53, user54, user55, user56;
	
	static RolVO rol1;
	static RolVO rol2;
	static RolVO rol3;	

	// Añadimos las propiedades necesarias a los usuarios de prueba
	@BeforeAll
	public static void preset() {		
//	ADMINs
		user0 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "1234", "1234", encode("1234"), "", "", true,null );
		user1 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM1", "carlosm1@carlosm.com", encode("1234"), "", "", true,null );
		user2 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM2", "carlosm2@carlosm.com", encode("1234"), "", "", true,null );
		user3 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM3", "carlosm3@carlosm.com", encode("1234"), "", "", true,null );
		user4 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM4", "carlosm4@carlosm.com", encode("1234"), "", "", true,null );
		user5 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM5", "carlosm5@carlosm.com", encode("1234"), "", "", true,null );
		user6 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM6", "carlosm6@carlosm.com", encode("1234"), "", "", true,null );
		user7 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM7", "carlosm7@carlosm.com", encode("1234"), "", "", true,null );
		user8 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM8", "carlosm8@carlosm.com", encode("1234"), "", "", true,null );
		user9 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM9", "carlosm9@carlosm.com", encode("1234"), "", "", true,null );
		user10 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM10", "carlosm10@carlosm.com", encode("1234"), "", "", true,null );		
		user11 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM11", "carlosm11@carlosm.com", encode("1234"), "", "", true,null );
		user12 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM12", "carlosm12@carlosm.com", encode("1234"), "", "", true,null );
		user13 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM13", "carlosm13@carlosm.com", encode("1234"), "", "", true,null );
		user14 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM14", "carlosm14@carlosm.com", encode("1234"), "", "", true,null );
		user15 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM15", "carlosm15@carlosm.com", encode("1234"), "", "", true,null );
		user16 = new UserVO(0, "ADMIN", "Carlos", "Menendez", "Martinez", "09444888N", "C/ Luna, nº 23, 3º izda",
				"666555444", "CarlosM16", "carlosm16@carlosm.com", encode("1234"), "", "", true,null );
		
//		CLIENTES
		user20 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "4567", "4567", encode("4567"), "", "", true, null);
		user21 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM1", "luism1@luism.com", encode("1234"), "", "", true, null);
		user22 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM2", "luism2@luism.com", encode("1234"), "", "", true, null);
		user23 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM3", "luism3@luism.com", encode("1234"), "", "", true, null);
		user24 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM4", "luism4@luism.com", encode("1234"), "", "", true, null);
		user25 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM5", "luism5@luism.com", encode("1234"), "", "", true, null);
		user26 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM6", "luism6@luism.com", encode("1234"), "", "", true, null);
		user27 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM7", "luism7@luism.com", encode("1234"), "", "", true, null);
		user28 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM8", "luism8@luism.com", encode("1234"), "", "", true, null);
		user29 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM9", "luism9@luism.com", encode("1234"), "", "", true, null);
		user30 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM10", "luism10@luism.com", encode("1234"), "", "", true, null);
		user31 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM11", "luism11@luism.com", encode("1234"), "", "", true, null);
		user32 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM12", "luism12@luism.com", encode("1234"), "", "", true, null);
		user33 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM13", "luism13@luism.com", encode("1234"), "", "", true, null);
		user34 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM14", "luism14@luism.com", encode("1234"), "", "", true, null);
		user35 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM15", "luism15@luism.com", encode("1234"), "", "", true, null);
		user36 = new UserVO(0, "CLIENT", "Luis", "Suarez", "Alonso", "09555888N", "C/ Pelota, nº 12 3º izda",
				"777555444", "JuanM16", "luism16@luism.com", encode("1234"), "", "", true, null);
		
//		RIDERs		
		user40 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"8900", "8900", encode("8900"), "1234DDR", "COCHE", true, null);
		user41 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM1", "pedrom1@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user42 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM2", "pedrom2@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user43 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM3", "pedrom@3pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user44 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM4", "pedrom4@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user45 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM5", "pedrom5@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user46 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM6", "pedrom6@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user47 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM7", "pedrom7@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user48 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM8", "pedrom8@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user49 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM9", "pedrom9@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user50 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM10", "pedrom10@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user51 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM11", "pedrom11@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user52 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM12", "pedrom12@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user53 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM13", "pedrom13@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user54 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM14", "pedrom14@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user55 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM15", "pedrom15@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		user56 = new UserVO(0, "RIDER", "Pedro", "Pérez", "Caso", "10555888N", "C/ Alegría Ñoña, nº 1 1º", "111222333",
				"PedroM16", "pedrom16@pedrom.com", encode("1234"), "1234DDR", "COCHE", true, null);
		
		rol1 = new RolVO(0, "ROLE_ADMIN"); 
		rol2 = new RolVO(0, "ROLE_CLIENT");
		rol3 = new RolVO(0, "ROLE_RIDER");
		
		
	}
	// Comprobamos la inserción de usuarios en la BBDD. Campo email unique
		@Test
		@Order(1)
		@DisplayName("InsertUsers")
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
			//AÑADIR DATOS PRUEBA A APLICACION
			try {
				//Guardar Todos los Usuarios
				userService.save(user0);
				userService.save(user4);userService.save(user5);userService.save(user6);userService.save(user7);userService.save(user8);userService.save(user9);userService.save(user10);
				userService.save(user11);userService.save(user12);userService.save(user13);userService.save(user14);userService.save(user15);userService.save(user16);
				userService.save(user20);userService.save(user21);userService.save(user22);userService.save(user23);userService.save(user24);userService.save(user25);
				userService.save(user26);userService.save(user27);userService.save(user28);userService.save(user29);userService.save(user30);userService.save(user31);
				userService.save(user32);userService.save(user33);userService.save(user34);userService.save(user35);userService.save(user36);
				userService.save(user40);userService.save(user41);userService.save(user42);userService.save(user43);userService.save(user44);userService.save(user45);
				userService.save(user46);userService.save(user47);userService.save(user48);userService.save(user49);userService.save(user50);userService.save(user51);
				userService.save(user52);userService.save(user53);userService.save(user54);userService.save(user55);userService.save(user56);				
				
			} catch (DataIntegrityViolationException e) {
				System.out.println("El usuario ya existe en la BBDD.");
				System.out.println(e.getCause());
			}
		}
	
	@Test
	@Order(2)
	@DisplayName("InsertRoles")
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
	@DisplayName("InsertUserRols")
	@Disabled("Problemas para generar el jar con Maven install")
	public void t3_InsertUserRolSuccess() {
		
		//Roles Admin
		UserRolVO userRol1 = new UserRolVO(0, userService.findById(1).get(),rolService.findById(1).get());
		UserRolVO userRol2 = new UserRolVO(0, userService.findById(2).get(),rolService.findById(1).get());
		UserRolVO userRol3 = new UserRolVO(0, userService.findById(3).get(),rolService.findById(1).get());
		UserRolVO userRol4 = new UserRolVO(0, userService.findById(4).get(),rolService.findById(1).get());
		UserRolVO userRol5 = new UserRolVO(0, userService.findById(5).get(),rolService.findById(1).get());
		UserRolVO userRol6 = new UserRolVO(0, userService.findById(6).get(),rolService.findById(1).get());
		UserRolVO userRol7 = new UserRolVO(0, userService.findById(7).get(),rolService.findById(1).get());
		UserRolVO userRol8 = new UserRolVO(0, userService.findById(8).get(),rolService.findById(1).get());
		UserRolVO userRol9 = new UserRolVO(0, userService.findById(9).get(),rolService.findById(1).get());
		UserRolVO userRol10 = new UserRolVO(0, userService.findById(10).get(),rolService.findById(1).get());
		UserRolVO userRol11 = new UserRolVO(0, userService.findById(11).get(),rolService.findById(1).get());
		UserRolVO userRol12 = new UserRolVO(0, userService.findById(12).get(),rolService.findById(1).get());
		UserRolVO userRol13 = new UserRolVO(0, userService.findById(13).get(),rolService.findById(1).get());
		UserRolVO userRol14 = new UserRolVO(0, userService.findById(14).get(),rolService.findById(1).get());
		UserRolVO userRol15 = new UserRolVO(0, userService.findById(15).get(),rolService.findById(1).get());
		UserRolVO userRol16 = new UserRolVO(0, userService.findById(16).get(),rolService.findById(1).get());
		UserRolVO userRol17 = new UserRolVO(0, userService.findById(17).get(),rolService.findById(1).get());
		//Roles Cliente
		UserRolVO userRol20 = new UserRolVO(0, userService.findById(18).get(),rolService.findById(2).get());
		UserRolVO userRol21 = new UserRolVO(0, userService.findById(19).get(),rolService.findById(2).get());
		UserRolVO userRol22 = new UserRolVO(0, userService.findById(20).get(),rolService.findById(2).get());
		UserRolVO userRol23 = new UserRolVO(0, userService.findById(21).get(),rolService.findById(2).get());
		UserRolVO userRol24 = new UserRolVO(0, userService.findById(22).get(),rolService.findById(2).get());
		UserRolVO userRol25 = new UserRolVO(0, userService.findById(23).get(),rolService.findById(2).get());
		UserRolVO userRol26 = new UserRolVO(0, userService.findById(24).get(),rolService.findById(2).get());
		UserRolVO userRol27 = new UserRolVO(0, userService.findById(25).get(),rolService.findById(2).get());
		UserRolVO userRol28 = new UserRolVO(0, userService.findById(26).get(),rolService.findById(2).get());
		UserRolVO userRol29 = new UserRolVO(0, userService.findById(27).get(),rolService.findById(2).get());
		UserRolVO userRol30 = new UserRolVO(0, userService.findById(28).get(),rolService.findById(2).get());
		UserRolVO userRol31 = new UserRolVO(0, userService.findById(29).get(),rolService.findById(2).get());
		UserRolVO userRol32 = new UserRolVO(0, userService.findById(30).get(),rolService.findById(2).get());
		UserRolVO userRol33 = new UserRolVO(0, userService.findById(31).get(),rolService.findById(2).get());
		UserRolVO userRol34 = new UserRolVO(0, userService.findById(32).get(),rolService.findById(2).get());
		UserRolVO userRol35 = new UserRolVO(0, userService.findById(33).get(),rolService.findById(2).get());
		UserRolVO userRol36 = new UserRolVO(0, userService.findById(34).get(),rolService.findById(2).get());
		
		//Roles Rider
		UserRolVO userRol40 = new UserRolVO(0, userService.findById(35).get(),rolService.findById(3).get());
		UserRolVO userRol41 = new UserRolVO(0, userService.findById(36).get(),rolService.findById(3).get());
		UserRolVO userRol42 = new UserRolVO(0, userService.findById(37).get(),rolService.findById(3).get());
		UserRolVO userRol43 = new UserRolVO(0, userService.findById(38).get(),rolService.findById(3).get());
		UserRolVO userRol44 = new UserRolVO(0, userService.findById(39).get(),rolService.findById(3).get());
		UserRolVO userRol45 = new UserRolVO(0, userService.findById(40).get(),rolService.findById(3).get());
		UserRolVO userRol46 = new UserRolVO(0, userService.findById(41).get(),rolService.findById(3).get());
		UserRolVO userRol47 = new UserRolVO(0, userService.findById(42).get(),rolService.findById(3).get());
		UserRolVO userRol48 = new UserRolVO(0, userService.findById(43).get(),rolService.findById(3).get());
		UserRolVO userRol49 = new UserRolVO(0, userService.findById(44).get(),rolService.findById(3).get());
		UserRolVO userRol50 = new UserRolVO(0, userService.findById(45).get(),rolService.findById(3).get());
		UserRolVO userRol51 = new UserRolVO(0, userService.findById(46).get(),rolService.findById(3).get());
		UserRolVO userRol52 = new UserRolVO(0, userService.findById(47).get(),rolService.findById(3).get());
		UserRolVO userRol53 = new UserRolVO(0, userService.findById(48).get(),rolService.findById(3).get());
		UserRolVO userRol54 = new UserRolVO(0, userService.findById(49).get(),rolService.findById(3).get());
		UserRolVO userRol55 = new UserRolVO(0, userService.findById(50).get(),rolService.findById(3).get());
		UserRolVO userRol56 = new UserRolVO(0, userService.findById(51).get(),rolService.findById(3).get());
		
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
		
		try {
			userRolService.save(userRol4);userRolService.save(userRol5);userRolService.save(userRol6);userRolService.save(userRol7);userRolService.save(userRol8);userRolService.save(userRol9);
			userRolService.save(userRol10);userRolService.save(userRol11);userRolService.save(userRol12);userRolService.save(userRol13);userRolService.save(userRol14);userRolService.save(userRol15);
			userRolService.save(userRol16);userRolService.save(userRol17);
			userRolService.save(userRol20);userRolService.save(userRol21);userRolService.save(userRol22);userRolService.save(userRol23);userRolService.save(userRol24);userRolService.save(userRol25);
			userRolService.save(userRol26);userRolService.save(userRol27);userRolService.save(userRol28);userRolService.save(userRol29);userRolService.save(userRol30);userRolService.save(userRol30);
			userRolService.save(userRol31);userRolService.save(userRol32);userRolService.save(userRol33);userRolService.save(userRol34);userRolService.save(userRol35);userRolService.save(userRol36);
			userRolService.save(userRol40);userRolService.save(userRol41);userRolService.save(userRol42);userRolService.save(userRol43);userRolService.save(userRol44);userRolService.save(userRol45);
			userRolService.save(userRol46);userRolService.save(userRol47);userRolService.save(userRol48);userRolService.save(userRol49);userRolService.save(userRol50);userRolService.save(userRol51);
			userRolService.save(userRol52);userRolService.save(userRol53);userRolService.save(userRol54);userRolService.save(userRol55);userRolService.save(userRol56);
			
		} catch (DataIntegrityViolationException e) {
			System.out.println("El userRol ya existe en la BBDD.");
			System.out.println(e.getCause());
		}		
	}
	  
	@Test
	@Order(12)
	@DisplayName("InsertPedidos")
	@Disabled("Problemas para generar el jar con Maven install")
	public void t12_insertPedidoSuccess() {
		PedidoVO pedido1 = new PedidoVO(0, LocalDate.of(2021, 01, 11), "Pedro Perez", "C/ Muñeca cañí, nº 3",
					"985445588", "Ana Menéndez", "C/ Uría, nº 13, 4ºF", "984779988", 0, "Prueba Pedido 1",
					LocalDate.of(2021, 01, 12), userService.findById(1).get(), userService.findById(2).get());		
		PedidoVO pedido2 = new PedidoVO(0, LocalDate.of(2021, 01, 12), "Pablo Suarez", "C/ Pelota, nº 13", "985555588",
				"Juan Muñoz", "C/ Main, nº 23, 5ºA", "984669988", 1, "Prueba Pedido 2", LocalDate.of(2021, 01, 13),
				userService.findById(2).get(), userService.findById(3).get());		
		PedidoVO pedido3 = new PedidoVO(0, LocalDate.of(2020, 01, 14), "Clara Alonso", "C/ Principal, nº 21",
				"985665588", "Eva Pisto", "C/ Principado, nº 8, 1ºA", "984559988", 2, "Prueba Pedido 3",
				LocalDate.of(2021, 01, 14), userService.findById(3).get(), userService.findById(1).get());	
		
		PedidoVO pedido4 = new PedidoVO(0, LocalDate.of(2020, 01, 14), "Clara Alonso1", "C/ Principal, nº 21","985665588", "Eva Pisto", "C/ Principado, nº 8, 1ºA", "984559988", 2, "Prueba Pedido 3",
				LocalDate.of(2021, 01, 14), userService.findById(18).get(), userService.findById(35).get());
		PedidoVO pedido5 = new PedidoVO(0, LocalDate.of(2020, 01, 14), "Clara Alonso2", "C/ Principal, nº 21","985665588", "Eva Pisto", "C/ Principado, nº 8, 1ºA", "984559988", 2, "Prueba Pedido 3",
				LocalDate.of(2021, 01, 14), userService.findById(18).get(), userService.findById(35).get());
		PedidoVO pedido6 = new PedidoVO(0, LocalDate.of(2020, 01, 14), "Clara Alonso3", "C/ Principal, nº 21","985665588", "Eva Pisto", "C/ Principado, nº 8, 1ºA", "984559988", 2, "Prueba Pedido 3",
				LocalDate.of(2021, 01, 14), userService.findById(18).get(), userService.findById(35).get());
		PedidoVO pedido7 = new PedidoVO(0, LocalDate.of(2020, 01, 14), "Clara Alonso4", "C/ Principal, nº 21","985665588", "Eva Pisto", "C/ Principado, nº 8, 1ºA", "984559988", 2, "Prueba Pedido 3",
				LocalDate.of(2021, 01, 14), userService.findById(18).get(), userService.findById(35).get());
		PedidoVO pedido8 = new PedidoVO(0, LocalDate.of(2020, 01, 14), "Clara Alonso5", "C/ Principal, nº 21","985665588", "Eva Pisto", "C/ Principado, nº 8, 1ºA", "984559988", 2, "Prueba Pedido 3",
				LocalDate.of(2021, 01, 14), userService.findById(18).get(), userService.findById(35).get());
		PedidoVO pedido9 = new PedidoVO(0, LocalDate.of(2020, 01, 14), "Clara Alonso6", "C/ Principal, nº 21","985665588", "Eva Pisto", "C/ Principado, nº 8, 1ºA", "984559988", 2, "Prueba Pedido 3",
				LocalDate.of(2021, 01, 14), userService.findById(18).get(), userService.findById(35).get());
		
		PedidoVO pedido10 = new PedidoVO(0, LocalDate.of(2021, 01, 12), "Pablo1", "C/ Pelota, nº 13", "985555588",
				"Juan Muñoz", "C/ Main, nº 23, 5ºA", "984669988", 1, "Prueba Pedido 2", LocalDate.of(2021, 01, 13),
				userService.findById(19).get(), userService.findById(36).get());
		PedidoVO pedido11 = new PedidoVO(0, LocalDate.of(2021, 01, 12), "Pablo2", "C/ Pelota, nº 13", "985555588",
				"Juan Muñoz", "C/ Main, nº 23, 5ºA", "984669988", 1, "Prueba Pedido 2", LocalDate.of(2021, 01, 13),
				userService.findById(19).get(), userService.findById(36).get());		
		PedidoVO pedido12 = new PedidoVO(0, LocalDate.of(2021, 01, 12), "Pablo3", "C/ Pelota, nº 13", "985555588",
				"Juan Muñoz", "C/ Main, nº 23, 5ºA", "984669988", 1, "Prueba Pedido 2", LocalDate.of(2021, 01, 13),
				userService.findById(19).get(), userService.findById(36).get());		
		PedidoVO pedido13 = new PedidoVO(0, LocalDate.of(2021, 01, 12), "Pablo4", "C/ Pelota, nº 13", "985555588",
				"Juan Muñoz", "C/ Main, nº 23, 5ºA", "984669988", 1, "Prueba Pedido 2", LocalDate.of(2021, 01, 13),
				userService.findById(19).get(), userService.findById(36).get());		
		PedidoVO pedido14 = new PedidoVO(0, LocalDate.of(2021, 01, 12), "Pablo5", "C/ Pelota, nº 13", "985555588",
				"Juan Muñoz", "C/ Main, nº 23, 5ºA", "984669988", 1, "Prueba Pedido 2", LocalDate.of(2021, 01, 13),
				userService.findById(19).get(), userService.findById(36).get());
		PedidoVO pedido15 = new PedidoVO(0, LocalDate.of(2021, 01, 12), "Pablo6", "C/ Pelota, nº 13", "985555588",
				"Juan Muñoz", "C/ Main, nº 23, 5ºA", "984669988", 1, "Prueba Pedido 2", LocalDate.of(2021, 01, 13),
				userService.findById(19).get(), userService.findById(36).get());		
		
		PedidoVO pedido16 = new PedidoVO(0, LocalDate.of(2021, 01, 11), "Pedro1", "C/ Muñeca cañí, nº 3",
				"985445588", "Ana Menéndez", "C/ Uría, nº 13, 4ºF", "984779988", 0, "Prueba Pedido 1",
				LocalDate.of(2021, 01, 12), userService.findById(20).get(), userService.findById(37).get());
		PedidoVO pedido17 = new PedidoVO(0, LocalDate.of(2021, 01, 11), "Pedro2", "C/ Muñeca cañí, nº 3",
				"985445588", "Ana Menéndez", "C/ Uría, nº 13, 4ºF", "984779988", 0, "Prueba Pedido 1",
				LocalDate.of(2021, 01, 12), userService.findById(20).get(), userService.findById(37).get());
		PedidoVO pedido18 = new PedidoVO(0, LocalDate.of(2021, 01, 11), "Pedro3", "C/ Muñeca cañí, nº 3",
				"985445588", "Ana Menéndez", "C/ Uría, nº 13, 4ºF", "984779988", 0, "Prueba Pedido 1",
				LocalDate.of(2021, 01, 12), userService.findById(20).get(), userService.findById(37).get());
		PedidoVO pedido19 = new PedidoVO(0, LocalDate.of(2021, 01, 11), "Pedro4", "C/ Muñeca cañí, nº 3",
				"985445588", "Ana Menéndez", "C/ Uría, nº 13, 4ºF", "984779988", 0, "Prueba Pedido 1",
				LocalDate.of(2021, 01, 12), userService.findById(20).get(), userService.findById(37).get());
		PedidoVO pedido20 = new PedidoVO(0, LocalDate.of(2021, 01, 11), "Pedro5", "C/ Muñeca cañí, nº 3",
				"985445588", "Ana Menéndez", "C/ Uría, nº 13, 4ºF", "984779988", 0, "Prueba Pedido 1",
				LocalDate.of(2021, 01, 12), userService.findById(20).get(), userService.findById(37).get());
		PedidoVO pedido21 = new PedidoVO(0, LocalDate.of(2021, 01, 11), "Pedro6", "C/ Muñeca cañí, nº 3",
				"985445588", "Ana Menéndez", "C/ Uría, nº 13, 4ºF", "984779988", 0, "Prueba Pedido 1",
				LocalDate.of(2021, 01, 12), userService.findById(20).get(), userService.findById(37).get());
				
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
		
		try {
			pedidoService.save(pedido4);pedidoService.save(pedido5);pedidoService.save(pedido6);pedidoService.save(pedido7);pedidoService.save(pedido8);pedidoService.save(pedido9);
			pedidoService.save(pedido10);pedidoService.save(pedido11);pedidoService.save(pedido12);pedidoService.save(pedido13);pedidoService.save(pedido14);pedidoService.save(pedido15);
			pedidoService.save(pedido16);pedidoService.save(pedido17);pedidoService.save(pedido18);pedidoService.save(pedido19);pedidoService.save(pedido20);pedidoService.save(pedido21);
			
			} catch (DataIntegrityViolationException e) {
				System.out.println("El pedido ya existe en la BBDD.");
				System.out.println(e.getCause());
			}
	}
	

	@Test
	@Order(13)
	@DisplayName("Encuentra pedidos de un cliente")
	public void t13_findPedidosByClienteSuccess() {
		List<PedidoVO> mostrar = pedidoService.findByCliente(userService.findById(1).get());
		//System.out.println("FindPedidosByCliente Success: " + mostrar);
		assertTrue(pedidoService.findByCliente(userService.findById(1).get()).size() != 0);
	}

	@Test
	@Order(14)
	@DisplayName("Encuentra pedidos de un rider")
	public void t14_findPedidosByRiderSuccess() {
		//System.out.println("FindPedidosByRider Success: " + pedidoService.findByRider(userService.findById(2).get()));
		assertTrue(pedidoService.findByRider(userService.findById(2).get()).size() == 0);
	}

	@Test
	@Order(15)
	@DisplayName("Encuentra pedidos de un cliente y un rider")
	public void t15_findPedidosByClienteAndRiderSuccess() {
//		System.out.println("FindPedidosByClienteAndRider Success: "
//				+ pedidoService.findByClienteAndRider(userService.findById(2).get(), userService.findById(3).get()));
		assertTrue(pedidoService.findByClienteAndRider(userService.findById(2).get(), userService.findById(3).get())
				.size() != 0);
	}

	@Test
	@Order(16)
	@DisplayName("Encuentra pedidos según status")
	public void t16_findPedidosByStatusSuccess() {
		//System.out.println("Pedidos Nuevos [Status=0]: " + pedidoService.findByStatus(0));
		assertFalse(pedidoService.findByStatus(0).isEmpty());
	}

	@Test
	@Order(17)
	@DisplayName("Encuentra usuarios activos")
	public void t17_findUsersByIsActiveSuccess() {
		//System.out.println("findUsersByIsActive: " + userService.findByIsActive(true));
		assertTrue(userService.findByIsActive(true).size() != 0);
	}

	@Test
	@Order(18)
	@DisplayName("Encuentra usuarios según el tipo")
	public void t18_findUsersByUser_typeSuccess() {
		//System.out.println("findUsersByUser_type: " + userService.findByUser_type("ADMIN"));
		assertTrue(userService.findByUser_type("ADMIN").size() != 0);
	}

	@Test
	@Order(19)
	@DisplayName("Encuentra lista de riders relacionados con un cliente")
	public void t19_findRidersByCliente() {
		//System.out.println(
		//		"****Lista Riders de Cliente: " + userService.findRidersByCliente(userService.findById(1).get()));
		assertTrue(userService.findRidersByCliente(userService.findById(1).get()).size() > 0);
	}

	

	@Test
	@Order(20)
	@DisplayName("Encuentra usuarios por mail y password")	
	@Disabled("Requiere la adicion de una contraseña ya encriptada que se genera una sola vez")
	public void t20_findUserByEmailAndPaswordSuccess() {
		UserVO loginUser = userService.findByEmailAndPassword("carlosm1@carlosm.com", "$2a$10$elAYxmdGTtfMCoErHpNBJ.Xr0J/ZNM2j2wqwy5UH7CoEVN8Gc1Jt6").get();
		System.out.println("Usuario válido encontrado: " + loginUser);
		assertEquals("carlosm1@carlosm.com", loginUser.getEmail());
	}
	
	

	@Test
	@Order(21)
	@DisplayName("Encuentra detalles del usuario autenticado")
	@Disabled("Requiere logue de sesión. Sin implementar este test correctamente. Más adelante")
	public void t21_findUserDetailsByEmailSuccess() {
		UserDetails loginUser = userService.loadUserByUsername("carlosm1@carlosm.com");
		System.out.println("Usuario válido encontrado: " + loginUser);
		assertEquals("carlosm1@carlosm.com", loginUser.getUsername());
	}
	
	/***************PRUEBAS ITERACIONES JSON y DEMAS. LIBRERIA GSON....SuperUtil */
	@Test
	@Order(30)
	@DisplayName("Pruebas. No impledmentado")
	public void t30_findAllPedidosSuccess() {
		Iterable<PedidoVO> pedidos = pedidoService.findAll();
		Iterable<PedidoVO> iterable = pedidos;
		
		for (PedidoVO pedido : iterable) {
	        System.out.println(pedido);
	    }
		//System.out.println(pedidos);
		//assertTrue(true);
	}
	
	
	
	
	
	
	

}