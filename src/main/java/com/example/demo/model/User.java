package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@RequiredArgsConstructor
@Entity
public class User implements UserDetails {
	@GeneratedValue
	@Id private long id;
	
	@NonNull
	private String username, password;

	// TODO: Maybe don't do this until we're making the CartController
	@ElementCollection
	Map<Product, Integer> cart = new HashMap<>();
	
	// TODO: Don't do this until we are working on auth. Just put this in the md as a copy/paste.
	// UserDetails requires these, they are technically getters (is-ers?) overridden by Lombok.
	// @Transient Makes it so these aren't persisted in the database, as they are hard coded.
	@Transient
	private boolean accountNonExpired = true;
	@Transient
	private boolean accountNonLocked = true;
	@Transient
	private boolean credentialsNonExpired = true;
	@Transient
	private boolean enabled = true;
	@Transient
	private Collection<GrantedAuthority> authorities = null;

}
