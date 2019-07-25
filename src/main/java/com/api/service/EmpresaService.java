package com.api.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.api.entityOld.Empresa;
import com.api.exception.BusinessException;
import com.api.repository.EmpresaRepository;

@Service
@EnableTransactionManagement
public class EmpresaService {

	@Autowired
	private EmpresaRepository empresaRepository;

	public Empresa salvar(Empresa empresa) throws BusinessException {
		Empresa myEmpresa = null;
		try {
			myEmpresa = empresaRepository.save(empresa);
		} catch (Exception e) {
			throw new BusinessException("Ocorreu um erro na solicitação: " + e.getMessage());
		}

		return myEmpresa;
	}

	public List<Empresa> listar() {
		return empresaRepository.findAll();
	}

	public Empresa findById(Long id) throws BusinessException {
		return empresaRepository.findById(id).orElse(new Empresa());
	}

}
