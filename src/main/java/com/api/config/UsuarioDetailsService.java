//package com.api.config;
//
//import java.util.ArrayList;
//import java.util.Collection;
//
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.GrantedAuthority;
//import org.springframework.security.core.authority.SimpleGrantedAuthority;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.stereotype.Component;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.api.entity.Role;
//import com.api.entity.Usuario;
//import com.api.repository.UsuarioRepository;
//import com.api.util.SUtils;
//
//@Component("usuarioDetailsService")
//public class UsuarioDetailsService implements UserDetailsService {
//
//	private final Logger log = LoggerFactory.getLogger(UsuarioDetailsService.class);
//
//	@Autowired
//	private UsuarioRepository userRepository;
//
//	@Override
//	@Transactional
//	public UserDetails loadUserByUsername(final String login) {
//
//		log.debug("Authenticating {}", login);
//		String lowercaseLogin = login.toLowerCase();
//
//		Usuario usuario;
//		if (lowercaseLogin.contains("@")) {
//			usuario = userRepository.findByEmail(lowercaseLogin);
//		} else {
//			usuario = userRepository.findByEmail(lowercaseLogin);
//		}
//
//
//
//		// if (userFromDatabase == null) {
//		// throw new UsernameNotFoundException("User " + lowercaseLogin + " was
//		// not found in the database");
//		// } else if (!userFromDatabase.isActivated()) {
//		// throw new UserNotActivatedException("User " + lowercaseLogin + " is
//		// not activated");
//		// }
//
//		Collection<GrantedAuthority> grantedAuthorities = new ArrayList<>();
//		if(!SUtils.isNullOrEmpty(usuario.getRoles())){
//			for (Role authority : usuario.getRoles()) {
//				GrantedAuthority grantedAuthority = new SimpleGrantedAuthority(authority.getNome());
//				grantedAuthorities.add(grantedAuthority);
//			}
//		}
//
//		return new User(usuario.getEmail(), usuario.getPassword(),
//				grantedAuthorities);
//
//	}
//
//}
