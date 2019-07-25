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
@Table(name = "sv_variacao_item")
public class VariacaoItem implements Serializable {

	private static final long serialVersionUID = 1220646324499746033L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "id_variacao", nullable = false)
	private Long idVariacao;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_variacao", referencedColumnName = "id", insertable = false, updatable = false)
	private Variacao variacao;

	@Column(name = "id_tipo_atributo", nullable = false)
	private Long idTipoAtributo;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_tipo_atributo", referencedColumnName = "id", insertable = false, updatable = false)
	private TipoAtributo tipoAtributo;

	@Column(name = "id_atributo", nullable = false)
	private Long idAtributoSelecionado;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_atributo", referencedColumnName = "id", insertable = false, updatable = false)
	private Atributo atributo;

	public VariacaoItem() {
		super();
	}

	public VariacaoItem(Long idVariacao, Long idTipoAtributo) {
		super();
		this.idVariacao = idVariacao;
		this.idTipoAtributo = idTipoAtributo;
	}

	public VariacaoItem(Long idVariacao, Long idTipoAtributo, Long idAtributoSelecionado) {
		super();
		this.idVariacao = idVariacao;
		this.idTipoAtributo = idTipoAtributo;
		this.idAtributoSelecionado = idAtributoSelecionado;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Long getIdVariacao() {
		return idVariacao;
	}

	public void setIdVariacao(Long idVariacao) {
		this.idVariacao = idVariacao;
	}

	public Variacao getVariacao() {
		return variacao;
	}

	public void setVariacao(Variacao variacao) {
		this.variacao = variacao;
	}

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

	public Long getIdAtributoSelecionado() {
		return idAtributoSelecionado;
	}

	public void setIdAtributoSelecionado(Long idAtributoSelecionado) {
		this.idAtributoSelecionado = idAtributoSelecionado;
	}

	public Atributo getAtributo() {
		return atributo;
	}

	public void setAtributo(Atributo atributo) {
		this.atributo = atributo;
	}

}
