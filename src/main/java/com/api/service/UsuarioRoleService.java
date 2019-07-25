package com.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.entityOld.UsuarioRole;
import com.api.exception.BusinessException;
import com.api.repository.UsuarioRoleRepository;

@Service
@EnableTransactionManagement
public class UsuarioRoleService {

	@Autowired
	private UsuarioRoleRepository usuarioRoleRepository;

	public void salvar(UsuarioRole usuarioRole) throws BusinessException {
		try {
			usuarioRoleRepository.save(usuarioRole);
		} catch (Exception e) {
			throw new BusinessException("Ocorreu um erro na solicitação: " + e.getMessage());
		}
	}

}
