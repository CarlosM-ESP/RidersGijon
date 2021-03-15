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
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name="user")
public class UserVO implements UserDetails{
	
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
	
	
	
	
	
	
	//Requerido para poder recoger los roles del usuario
	//Se utiliza el fetch EAGER para que los incluya automáticamente al generar un objeto UserVO
	@OneToMany(mappedBy="user", fetch = FetchType.EAGER)
    List<UserRolVO> roles;
	
	
	//Devuelve una lista de los privilegios que tiene uhn usuario
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {		
        List<GrantedAuthority> privilegios = new ArrayList<>();
        for (UserRolVO userRolVO:roles)
           privilegios.add(new SimpleGrantedAuthority(userRolVO.getRol().getNombre()));
           return privilegios;
	}
	
	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public boolean isAccountNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isAccountNonLocked() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isCredentialsNonExpired() {
		// TODO Auto-generated method stub
		return true;
	}
	@Override
	public boolean isEnabled() {
		// TODO Auto-generated method stub
		return true;
	}

	@Override
	public String toString() {
		return "UserVO [id_user=" + id_user + ", user_type=" + user_type + ", nombre=" + nombre + ", apellido1="
				+ apellido1 + ", apellido2=" + apellido2 + ", nif=" + nif + ", direccion=" + direccion + ", telefono="
				+ telefono + ", nick=" + nick + ", email=" + email + ", password=" + password + ", matricula="
				+ matricula + ", vehiculo=" + vehiculo + ", isActive=" + isActive + "]";
	}

	/**
	 * @return the isActive
	 */
	public boolean getIsActive() {
		return isActive;
	}

	/**
	 * @param isActive the isActive to set
	 */
	public void setIsActive(boolean isActive) {
		this.isActive = isActive;
	}
	
}
