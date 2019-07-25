package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.api.entityOld.Empresa;

public interface EmpresaRepository
		extends JpaRepository<Empresa, Long>, JpaSpecificationExecutor<Empresa> {

}
