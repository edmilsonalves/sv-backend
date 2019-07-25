package com.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.entityOld.Role;
import com.api.entityOld.Usuario;
import com.api.entityOld.UsuarioRole;
import com.api.entityOld.UsuarioRoleId;
import com.api.exception.BusinessException;
import com.api.repository.RoleRepository;
import com.api.repository.UsuarioRepository;
import com.api.repository.UsuarioRoleRepository;
import com.api.util.SUtils;

@Service
@EnableTransactionManagement
public class UsuarioService {

	private static final Logger logger = LoggerFactory.getLogger(UsuarioService.class);

	@Autowired
	private UsuarioRepository usuarioRepository;

	// @Autowired
	// private AuthenticationManager authenticationManager;

	@Autowired
	private UsuarioRoleRepository usuarioRoleRepository;

	@Autowired
	private RoleRepository roleRepository;

	public Usuario salvar(Usuario usuario) throws BusinessException {
		Usuario existeUsuario = usuarioRepository.findByEmail(usuario.getEmail());

		if (!SUtils.isNull(existeUsuario)) {
			throw new BusinessException("Já existe um usuario utilizando esse email");
		}

		return usuarioRepository.save(usuario);
	}

	public void autologin(Usuario usuario) {

		UsuarioRole usuarioRole = new UsuarioRole();
		UsuarioRoleId pk = new UsuarioRoleId();
		pk.setUsuarioId(usuario.getId());
		pk.setRoleId(1L);
		usuarioRole.setId(pk);

		usuarioRoleRepository.save(usuarioRole);
		List<Role> roles = new ArrayList<Role>();
		roles.add(roleRepository.findByNome("ADMIN"));
		usuario.setRoles(roles);

//		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
//		for (Role role : usuario.getRoles()) {
//			grantedAuthorities.add(new SimpleGrantedAuthority(role.getNome()));
//		}
//
//		UserDetails userDetails = new User(usuario.getEmail(), usuario.getPassword(), grantedAuthorities);
//
//		UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
//				userDetails, usuario.getPassword(), userDetails.getAuthorities());
//
//		// authenticationManager.authenticate(usernamePasswordAuthenticationToken);
//
//		if (usernamePasswordAuthenticationToken.isAuthenticated()) {
//			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
//			logger.debug(String.format("Auto login realizado com sucesso! ", usuario.getNome()));
//		}
	}

	public Usuario findById(Long id) throws BusinessException {
		return usuarioRepository.findById(id).orElse(null);
	}

	public Usuario recuperarAcesso(String email) throws BusinessException {

		Usuario usuario = usuarioRepository.findByEmail(email);

		if (SUtils.isNull(usuario)) {
			throw new BusinessException("Não existe um usuário com esse email, por favor verifique!");
		} else {
			usuario.setPassword(gerarSenha());
			return usuarioRepository.save(usuario);
		}
	}

	private String gerarSenha() {
		UUID uuid = UUID.randomUUID();
		return uuid.toString().substring(0, 8);
	}

}
