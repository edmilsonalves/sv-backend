package com.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import com.api.entity.Produto;
import com.api.entity.TipoAtributo;

public interface TipoAtributoRepository extends JpaRepository<TipoAtributo, Long>, JpaSpecificationExecutor<Produto> {

	@Query("SELECT DISTINCT tipoAtributo "
			+ "FROM TipoAtributo tipoAtributo "
			+ "JOIN FETCH tipoAtributo.listAtributo listAtributo ")
	List<TipoAtributo> findByWithChildren();

}
