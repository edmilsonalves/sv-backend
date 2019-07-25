/**
 *
 */
package com.api.entityOld;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.api.intercep.SecurityInterceptor;

/**
 *
 * @author edmilson.reis
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
@FilterDef(name = "empresaTenant", parameters = { @ParamDef(name = "id", type = "long") })
@Filter(name = "empresaTenant", condition = "EMPRESA_TENANT_ID = :id")
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Long id;

	@Column(name = "empresa_tenant_id")
	private Long empresaTenantId;

	@Column(name = "id_usuario_alteracao")
	private Long idUsuarioAlteracao;
	//
//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_usuario_alteracao", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
//	private Usuario usuarioAlteracao;
//
	@Column(name = "id_usuario_inclusao", nullable = false)
	private Long idUsuarioInclusao;
//
//	@ManyToOne(optional = false, fetch = FetchType.LAZY)
//	@JoinColumn(name = "id_usuario_inclusao", referencedColumnName = "id", nullable = false, insertable = false, updatable = false)
//	private Usuario usuarioInclusao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "criadoEm", nullable = false)
	private Date criadoEm;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "alteradoEm")
	private Date alteradoEm;
	

	public Long getIdUsuarioAlteracao() {
		return idUsuarioAlteracao;
	}

	public void setIdUsuarioAlteracao(Long idUsuarioAlteracao) {
		this.idUsuarioAlteracao = idUsuarioAlteracao;
	}

	public Long getIdUsuarioInclusao() {
		return idUsuarioInclusao;
	}

	public void setIdUsuarioInclusao(Long idUsuarioInclusao) {
		this.idUsuarioInclusao = idUsuarioInclusao;
	}


	@PrePersist
	@PreUpdate
	private void empresaTenantId() {
		Long empresaTenantId = SecurityInterceptor.getEmpresaTenantId();
		if (empresaTenantId != null) {
			this.empresaTenantId = empresaTenantId;
		}

		if (this.id == null && this.criadoEm == null) {
			this.criadoEm = new Date();
		}
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	public Date getCriadoEm() {
		return criadoEm;
	}

	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	public Date getAlteradoEm() {
		return alteradoEm;
	}

	public void setAlteradoEm(Date alteradoEm) {
		this.alteradoEm = alteradoEm;
	}

	public Long getEmpresaTenantId() {
		return empresaTenantId;
	}

	public void setEmpresaTenantId(Long empresaTenantId) {
		this.empresaTenantId = empresaTenantId;
	}

}
