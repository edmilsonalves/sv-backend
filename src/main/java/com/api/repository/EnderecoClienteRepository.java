package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.api.entityOld.EnderecoCliente;

public interface EnderecoClienteRepository
		extends JpaRepository<EnderecoCliente, Long>, JpaSpecificationExecutor<EnderecoCliente> {

}
