package com.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.api.entity.Produto;
import com.api.enums.StatusProdutoEnum;

public interface ProdutoRepository extends JpaRepository<Produto, Long>, JpaSpecificationExecutor<Produto> {

	@Query("SELECT produto "
			+ "FROM Produto produto "
			+ "LEFT JOIN FETCH produto.estoque estoque "
			+ "LEFT JOIN FETCH produto.listImagem listImagem " 
			+ "LEFT JOIN FETCH produto.listVariacao listVariacao " 
			+ "WHERE produto.id = ?1 ")
	Produto findByIdWithChildren(final Long id);

	List<Produto> findByNomeStartingWith(String nome);

	Page<Produto> findByNomeContainingOrCodigoContaining(Pageable pageable, String nome, String codigo);

	List<Produto> findByNomeContainingOrCodigoContaining(String nome, String codigo);

	@Query("FROM Produto prod WHERE (prod.nome LIKE %:nome% OR prod.codigo LIKE %:codigo%) AND prod.status = :status")
	List<Produto> findByNomeContainingOrCodigoContainingAndStatus(@Param("nome") String nome,
			@Param("codigo") String codigo, @Param("status") StatusProdutoEnum status);

	@Query("SELECT DISTINCT prod FROM Produto prod "
			+ "LEFT JOIN FETCH prod.estoque estoque " 
			+ "LEFT JOIN FETCH prod.listVariacao listVariacao " 
			+ "LEFT JOIN FETCH prod.listImagem listImagem " 
			+ "WHERE (:query IS NULL OR (prod.nome LIKE lower(concat('%', :query, '%')) OR prod.codigo LIKE lower(concat('%', :query, '%')))) "
			+ "AND (:status IS NULL OR :status = prod.status) ")
	List<Produto> listByFilter(@Param("query") String query, @Param("status") StatusProdutoEnum status);

//	@Query("SELECT new com.api.dto.ProdutoDTO(prod.id, prod.codigo, prod.nome) FROM Produto prod "
//			+ "LEFT JOIN FETCH prod.estoque estoque " 
//			+ "LEFT JOIN FETCH prod.listImagem listImagem " 
//			+ "WHERE (:query IS NULL OR (prod.nome LIKE lower(concat('%', :query, '%')) OR prod.codigo LIKE lower(concat('%', :query, '%')))) "
//			+ "AND (:status IS NULL OR :status = prod.status) ")
//	List<ProdutoDTO> listDtoByFilter(@Param("query") String query, @Param("status") StatusProdutoEnum status);
	
	List<Produto> findByStatus(StatusProdutoEnum status);

	List<Produto> findByIdCategoria(final Long idCategoria);

	Produto findByCodigo(String codigo);

}
