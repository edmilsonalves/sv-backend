package com.api.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Edmilson
 *
 */
public class VariacaoDTO implements Serializable {

	private static final long serialVersionUID = 5190318305518057340L;

	private Long id;
	private String codigo;
	private Integer ordem;
	private Long idProduto;
	private BigDecimal precoCusto;
	private BigDecimal precoVenda;
	private Integer estoqueAtual;
	private BigDecimal precoPromocional;
	private List<TipoAtributoDTO> listTipoAtributo;
	private List<FileDTO> listImagem;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCodigo() {
		return codigo;
	}

	public void setCodigo(String codigo) {
		this.codigo = codigo;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public BigDecimal getPrecoCusto() {
		return precoCusto;
	}

	public void setPrecoCusto(BigDecimal precoCusto) {
		this.precoCusto = precoCusto;
	}

	public BigDecimal getPrecoVenda() {
		return precoVenda;
	}

	public void setPrecoVenda(BigDecimal precoVenda) {
		this.precoVenda = precoVenda;
	}

	public BigDecimal getPrecoPromocional() {
		return precoPromocional;
	}

	public void setPrecoPromocional(BigDecimal precoPromocional) {
		this.precoPromocional = precoPromocional;
	}

	public List<TipoAtributoDTO> getListTipoAtributo() {
		if (this.listTipoAtributo == null) {
			this.listTipoAtributo = new ArrayList<>(0);
		}
		return listTipoAtributo;
	}

	public void setListTipoAtributo(List<TipoAtributoDTO> listTipoAtributo) {
		this.listTipoAtributo = listTipoAtributo;
	}

	public List<FileDTO> getListImagem() {
		if (this.listImagem == null) {
			this.listImagem = new ArrayList<>(0);
		}
		return listImagem;
	}

	public void setListImagem(List<FileDTO> listImagem) {
		this.listImagem = listImagem;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public Integer getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(Integer estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

}
