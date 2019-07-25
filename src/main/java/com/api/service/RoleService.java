package com.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.entityOld.Role;
import com.api.exception.BusinessException;
import com.api.repository.RoleRepository;

@Service
@EnableTransactionManagement
public class RoleService {

	@Autowired
	private RoleRepository roleRepository;

	public Role findById(Long id) throws BusinessException {
		return roleRepository.findById(id).orElse(null);
	}

}
