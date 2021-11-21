/**
 * 
 */
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
@Table(name="pedido")
public class PedidoVO {	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_pedido;
	
	@DateTimeFormat (pattern="dd/MM/yyyy")
	private LocalDate fechaPedido;
	private String nombre_remitente;
	private String dir_remitente;
	private String telefono_remitente;
	private String nombre_destinatario;
	private String dir_destinatario;
	private String telefono_destinatario;
	private int status;
	private String comentarios;
	
	@DateTimeFormat (pattern="dd/MM/yyyy")
	private LocalDate fecha_entregado;
	
	@ManyToOne	
	private UserVO cliente;	
	@ManyToOne	
	private UserVO rider;
}
