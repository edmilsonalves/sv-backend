package com.api.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.util.ObjectUtils;

import com.api.dto.EstoqueDTO;
import com.api.entity.Estoque;
import com.api.exception.BusinessException;
import com.api.repository.EstoqueRepository;

@Service
@EnableTransactionManagement
public class EstoqueService {

	@Autowired
	private EstoqueRepository estoqueRepository;

	public EstoqueDTO findById(Long id) throws BusinessException {
		Estoque estoque = estoqueRepository.findById(id).orElse(new Estoque());
		if (ObjectUtils.isEmpty(estoque)) {
			throw new BusinessException("Estoque nao encontrado.");
		}
		EstoqueDTO estoqueDTO = new EstoqueDTO();
		BeanUtils.copyProperties(estoque, estoqueDTO);

		return estoqueDTO;
	}
	
	public EstoqueDTO findByIdProduto(Long id) throws BusinessException {
		Estoque estoque = estoqueRepository.findByIdProduto(id);
		if (ObjectUtils.isEmpty(estoque)) {
			throw new BusinessException("Estoque nao encontrado.");
		}
		EstoqueDTO estoqueDTO = new EstoqueDTO();
		BeanUtils.copyProperties(estoque, estoqueDTO);

		return estoqueDTO;
	}

	public Estoque save(Estoque estoque) throws BusinessException {
		return estoqueRepository.save(estoque);
	}

}
