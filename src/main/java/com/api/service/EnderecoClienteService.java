package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.api.entityOld.EnderecoCliente;
import com.api.exception.BusinessException;
import com.api.repository.EnderecoClienteRepository;

@Service
@EnableTransactionManagement
public class EnderecoClienteService {

	@Autowired
	private EnderecoClienteRepository enderecoClienteRepository;

	public EnderecoCliente buscar(Long id) throws BusinessException {
		EnderecoCliente enderecoCliente = enderecoClienteRepository.findById(id).orElse(new EnderecoCliente());
		if (ObjectUtils.isEmpty(enderecoCliente)) {
			throw new BusinessException("Endereço Cliente nao encontrado.");
		}
		return enderecoCliente;
	}

	@Transactional(rollbackFor = { Exception.class })
	public EnderecoCliente salvar(EnderecoCliente enderecoCliente) throws BusinessException {
		EnderecoCliente myEnderecoCliente = null;
		try {
			myEnderecoCliente = enderecoClienteRepository.save(enderecoCliente);
		} catch (DataIntegrityViolationException ex) {
			throw new BusinessException("Endereço já existe.");
		}

		return myEnderecoCliente;
	}

	public List<EnderecoCliente> listar() {
		return enderecoClienteRepository.findAll();
	}

}
