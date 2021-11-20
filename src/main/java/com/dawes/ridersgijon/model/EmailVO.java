package com.dawes.ridersgijon.model;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


//Anotaciones Lombock
//Getters, Setters, Constructores con y sin argumentos
//toString(), equals(), hascode()
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="email")
public class EmailVO {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_email;	
	@DateTimeFormat (pattern="dd/MM/yyyy")
	private LocalDate fechaEmail;
	private String name;
	private String email;
	private String subject;	
	private String message;	
	
	//Solo si se integra con la aplicacion usando datos de clientes y Rider y Admins
	//@ManyToOne	
	//private UserVO cliente;	
	//@ManyToOne	
	//private UserVO rider;
}
