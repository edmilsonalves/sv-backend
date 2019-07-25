package com.api.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.entity.VariacaoItem;

public interface VariacaoItemRepository
		extends JpaRepository<VariacaoItem, Long>, JpaSpecificationExecutor<VariacaoItem> {

	public VariacaoItem findByIdVariacaoAndIdTipoAtributo(final long idVariacao, final Long idTipoAtributo);

	@Modifying
	@Transactional
	@Query("DELETE FROM VariacaoItem variacaoItem WHERE variacaoItem.idVariacao = ?1 ")
	public void deleteByIdVariacao(final long idVariacao);

}
