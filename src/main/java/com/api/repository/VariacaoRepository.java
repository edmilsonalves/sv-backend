package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.api.entity.Variacao;

public interface VariacaoRepository extends JpaRepository<Variacao, Long>, JpaSpecificationExecutor<Variacao> {

}
