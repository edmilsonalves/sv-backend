package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;

import com.api.entityOld.Endereco;
import com.api.exception.BusinessException;
import com.api.repository.EnderecoRepository;

@Service
@EnableTransactionManagement
public class EnderecoService {

	@Autowired
	private EnderecoRepository enderecoRepository;

	public Endereco buscar(Long id) throws BusinessException {
		Endereco endereco = enderecoRepository.findById(id).orElse(new Endereco());
		if (ObjectUtils.isEmpty(endereco)) {
			throw new BusinessException("Endereço nao encontrado.");
		}
		return endereco;
	}

	@Transactional(rollbackFor = { Exception.class })
	public Endereco salvar(Endereco endereco) throws BusinessException {
		Endereco myEndereco = null;
		try {
			myEndereco = enderecoRepository.save(endereco);
		} catch (DataIntegrityViolationException ex) {
			throw new BusinessException("Endereço já existe.");
		}

		return myEndereco;
	}

	public List<Endereco> listar() {
		return enderecoRepository.findAll();
	}

	public List<Endereco> buscarEndereco(String descricao) {
		Pageable limit = new PageRequest(0, 10);
		return enderecoRepository.findByLogradouroContaining(descricao, limit);
	}

	public List<Endereco> buscarEnderecoPorCep(String descricao) {
		return enderecoRepository.findByCep(descricao);
	}

}
