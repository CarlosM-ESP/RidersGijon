/**
 * 
 */
package com.dawes.ridersgijon.model;

import javax.persistence.Column;

/**
 * @author CarlosM
 *
 */

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class UserVO {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id_user;
	private String user_type;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private String nif;
	private String direccion;
	private String telefono;
	private String nick;
	
	@Column(unique=true)
	private String email;
	
	private String password;
	private String matricula;
	private String vehiculo;
	private boolean isActive;
	

	
}
