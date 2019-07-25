package com.api.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.api.entityOld.Endereco;


public interface EnderecoRepository extends JpaRepository<Endereco, Long>, JpaSpecificationExecutor<Endereco> {
	
	  //@Query("SELECT endereco FROM Endereco endereco where endereco.logradouro like %:descricao% ")
	  //List<Endereco> buscarEndereco(@Param("descricao") String descricao);
	 
	List<Endereco> findByLogradouroContaining(String descricao, Pageable limit);

	List<Endereco> findByCep(String descricao);
}
