/**
 *
 */
package com.api.entity;

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

/**
 *
 * @author edmilson.reis
 *
 */
@SuppressWarnings("serial")
@MappedSuperclass
public class BaseEntity implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	protected Long id;

	@Column(name = "alterado_por")
	private Long alteradoPor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "alterado_em")
	private Date alteradoEm;

	@Column(name = "criado_por", nullable = false)
	private Long criadoPor;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "criado_em", nullable = false)
	private Date criadoEm;

	@PrePersist
	@PreUpdate
	private void setAuditoria() {
		if (this.id == null) {
			this.criadoEm = new Date();
			this.criadoPor = 1L;
		} else {
			this.alteradoEm = new Date();
			this.alteradoPor = 1L;
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

	public Long getAlteradoPor() {
		return alteradoPor;
	}

	public void setAlteradoPor(Long alteradoPor) {
		this.alteradoPor = alteradoPor;
	}

	public Long getCriadoPor() {
		return criadoPor;
	}

	public void setCriadoPor(Long criadoPor) {
		this.criadoPor = criadoPor;
	}

}
