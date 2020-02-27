package com.api.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.api.intercep.SecurityInterceptor;

import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "sc_nota_fiscal")
@FilterDef(name = "empresaTenant", parameters = { @ParamDef(name = "id", type = "long") })
@Filter(name = "empresaTenant", condition = "EMPRESA_TENANT_ID = :id")
@Getter
@Setter
public class NotaFiscal extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5937964774804136928L;

	@Column(name = "empresa_tenant_id")
	private Long empresaTenantId;

	@Column(name = "descricao", length = 145)
	private String descricao;

	@Column(name = "id_categoria", nullable = false)
	private Long idCategoria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria", referencedColumnName = "id", insertable = false, updatable = false)
	private Categoria categoria;

	@PrePersist
	@PreUpdate
	private void setEmpresaTenantId() {
		if (SecurityInterceptor.getEmpresaTenantId() != null) {
			this.empresaTenantId = SecurityInterceptor.getEmpresaTenantId();
		}
	}

}
