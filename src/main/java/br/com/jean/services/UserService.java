package br.com.jean.services;

import org.springframework.security.core.context.SecurityContextHolder;

import br.com.jean.security.UserSS;

public class UserService {
	public static UserSS authenticated() {
		try {
			return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		} catch (Exception e) {
			return null;
		}
	}
}
