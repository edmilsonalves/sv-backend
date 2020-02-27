package com.api.repository;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.api.entity.ScFile;
import com.api.entity.NotaFiscal;

public interface FileRepository extends JpaRepository<ScFile, Long>, JpaSpecificationExecutor<NotaFiscal> {

	List<ScFile> findByIdPai(final Long idPai);

	@Modifying
	@Transactional
	@Query("DELETE FROM File file WHERE file.idPai = ?1 ")
	public void deleteByIdPai(final long idPai);

}
