package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@Entity
public class User implements UserDetails {
	@GeneratedValue
	@Id private long id;
	
	@NonNull
	private String username, password;
	
	@ElementCollection
	@MapKeyClass(Product.class)
	Map<Long, Integer> cart = new HashMap<>();
	
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
