package com.dawes.ridersgijon.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

	@Data
	@NoArgsConstructor
	@AllArgsConstructor
	@Entity
	@Table(name="userrol")
public class UserRolVO {

	    @Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    @Column(unique=true)
	    private int iduserrol;
	    
	    @ManyToOne
	    @JoinColumn(name="id_user")
	    private UserVO user;
	    
	    @ManyToOne
	    @JoinColumn(name="idrol")
	    private RolVO rol;
	}

