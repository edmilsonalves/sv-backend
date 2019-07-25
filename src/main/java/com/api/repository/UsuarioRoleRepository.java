package com.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import com.api.entityOld.UsuarioRole;

public interface UsuarioRoleRepository extends JpaRepository<UsuarioRole, Long>, JpaSpecificationExecutor<UsuarioRole> {

}
