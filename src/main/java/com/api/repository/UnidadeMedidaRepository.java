package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.api.entity.UnidadeMedida;

public interface UnidadeMedidaRepository
		extends JpaRepository<UnidadeMedida, Long>, JpaSpecificationExecutor<UnidadeMedida> {

	UnidadeMedida findByDescricao(String descricao);

}
