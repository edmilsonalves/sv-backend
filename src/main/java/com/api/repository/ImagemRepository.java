package com.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.entity.Imagem;
import com.api.entity.Produto;

public interface ImagemRepository extends JpaRepository<Imagem, Long>, JpaSpecificationExecutor<Produto> {

	List<Imagem> findByIdProduto(final Long idProduto);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Imagem imagem WHERE imagem.idVariacao = ?1 ")
	public void deleteByIdVariacao(final long idVariacao);
	
	@Modifying
	@Transactional
	@Query("DELETE FROM Imagem imagem WHERE imagem.idProduto = ?1 ")
	public void deleteByIdProduto(final long idProduto);

}
