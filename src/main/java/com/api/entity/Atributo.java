package com.api.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "sv_atributo")
public class Atributo implements Serializable {

	private static final long serialVersionUID = 1220646324499746033L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "descricao", nullable = false, length = 45)
	private String descricao;

	@Column(name = "id_tipo_atributo", nullable = false)
	private Long idTipoAtributo;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_atributo", referencedColumnName = "id", insertable = false, updatable = false)
	private TipoAtributo tipoAtributo;

	public Long getIdTipoAtributo() {
		return idTipoAtributo;
	}

	public void setIdTipoAtributo(Long idTipoAtributo) {
		this.idTipoAtributo = idTipoAtributo;
	}

	public TipoAtributo getTipoAtributo() {
		return tipoAtributo;
	}

	public void setTipoAtributo(TipoAtributo tipoAtributo) {
		this.tipoAtributo = tipoAtributo;
	}

	/**
	 * @return the descricao
	 */
	public String getDescricao() {
		return descricao;
	}

	/**
	 * @param descricao
	 *            the descricao to set
	 */
	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
