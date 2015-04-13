package com.justinmobile.security.userdetails;

import org.springframework.security.GrantedAuthority;
import org.springframework.security.userdetails.User;

public class UserWithSalt extends User {
	
	private static final long serialVersionUID = -3025805761554233776L;
	
	private String salt;
	

	public UserWithSalt(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, GrantedAuthority[] authorities)
			throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
	}
	
	public UserWithSalt(String username, String password, boolean enabled, boolean accountNonExpired,
			boolean credentialsNonExpired, boolean accountNonLocked, GrantedAuthority[] authorities,String salt)
			throws IllegalArgumentException {
		super(username, password, enabled, accountNonExpired, credentialsNonExpired, accountNonLocked, authorities);
		this.salt = salt;
	}
	
	public String getSalt() {
		return salt;
	}

	public void setSalt(String salt) {
		this.salt = salt;
	}


}
