package com.api.dto;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class ProdutoDTO {

	private Long id;

	private String codigo;

	private String nome;

	private String descricao;

	private int status;
	private String statusDescricao;

	private BigDecimal precoCusto;

	private BigDecimal precoVenda;

	private BigDecimal precoPromocional;

	private int tipo;
	private String tipoDescricao;

	private Boolean movimentaEstoque;

	private Boolean possuiVariacao;

	private BigDecimal peso;

	private BigDecimal largura;

	private BigDecimal altura;

	private BigDecimal cumprimento;

	private BigDecimal comissao;

	private Long idCategoria;

	private Long idUnidadeMedida;

	// Estoque
	private Integer estoqueAtual;
	private Integer estoqueMinimo;

//	private List<OptionDTO> listCategoria;
//	private List<OptionDTO> listUnidadeMedida;

	private String imagemDestaque;
	private List<ImagemDTO> listImagem;
	private List<VariacaoDTO> listVariacao;
	private AbaManipuladaDTO abaManipulada;

	public ProdutoDTO() {
		super();
	}

	public ProdutoDTO(Long id, String codigo, String nome) {
		super();
		this.id = id;
		this.codigo = codigo;
		this.nome = nome;
	}

	public String getImagemDestaque() {
		return imagemDestaque;
	}

	public void setImagemDestaque(String imagemDestaque) {
		this.imagemDestaque = imagemDestaque;
	}

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

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public int getStatus() {
		return status;
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

	public int getTipo() {
		return tipo;
	}

	public void setTipo(int tipo) {
		this.tipo = tipo;
	}

	public Boolean getMovimentaEstoque() {
		return movimentaEstoque;
	}

	public void setMovimentaEstoque(Boolean movimentaEstoque) {
		this.movimentaEstoque = movimentaEstoque;
	}

	public Boolean getPossuiVariacao() {
		return possuiVariacao;
	}

	public void setPossuiVariacao(Boolean possuiVariacao) {
		this.possuiVariacao = possuiVariacao;
	}

	public BigDecimal getPeso() {
		return peso;
	}

	public void setPeso(BigDecimal peso) {
		this.peso = peso;
	}

	public BigDecimal getLargura() {
		return largura;
	}

	public void setLargura(BigDecimal largura) {
		this.largura = largura;
	}

	public BigDecimal getAltura() {
		return altura;
	}

	public void setAltura(BigDecimal altura) {
		this.altura = altura;
	}

	public BigDecimal getCumprimento() {
		return cumprimento;
	}

	public void setCumprimento(BigDecimal cumprimento) {
		this.cumprimento = cumprimento;
	}

	public BigDecimal getComissao() {
		return comissao;
	}

	public void setComissao(BigDecimal comissao) {
		this.comissao = comissao;
	}

	public Long getIdCategoria() {
		return idCategoria;
	}

	public void setIdCategoria(Long idCategoria) {
		this.idCategoria = idCategoria;
	}

	public List<ImagemDTO> getListImagem() {
		if (this.listImagem == null) {
			this.listImagem = new ArrayList<>(0);
		}
		return listImagem;
	}

	public void setListImagem(List<ImagemDTO> listImagem) {
		this.listImagem = listImagem;
	}

	public Integer getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(Integer estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public Integer getEstoqueMinimo() {
		return estoqueMinimo;
	}

	public void setEstoqueMinimo(Integer estoqueMinimo) {
		this.estoqueMinimo = estoqueMinimo;
	}

	public Long getIdUnidadeMedida() {
		return idUnidadeMedida;
	}

	public void setIdUnidadeMedida(Long idUnidadeMedida) {
		this.idUnidadeMedida = idUnidadeMedida;
	}

	public String getTipoDescricao() {
		return tipoDescricao;
	}

	public void setTipoDescricao(String tipoDescricao) {
		this.tipoDescricao = tipoDescricao;
	}

//	public List<OptionDTO> getListCategoria() {
//		if (this.listCategoria == null) {
//			this.listCategoria = new ArrayList<>(0);
//		}
//		return this.listCategoria;
//	}
//
//	public void setListCategoria(List<OptionDTO> listCategoria) {
//		this.listCategoria = listCategoria;
//	}
//
//	public List<OptionDTO> getListUnidadeMedida() {
//		if (this.listUnidadeMedida == null) {
//			this.listUnidadeMedida = new ArrayList<>(0);
//		}
//		return this.listUnidadeMedida;
//	}
//
//	public void setListUnidadeMedida(List<OptionDTO> listUnidadeMedida) {
//		this.listUnidadeMedida = listUnidadeMedida;
//	}

	public BigDecimal getPrecoPromocional() {
		return precoPromocional;
	}

	public void setPrecoPromocional(BigDecimal precoPromocional) {
		this.precoPromocional = precoPromocional;
	}

	public String getStatusDescricao() {
		return statusDescricao;
	}

	public void setStatusDescricao(String statusDescricao) {
		this.statusDescricao = statusDescricao;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public List<VariacaoDTO> getListVariacao() {
		if (this.listVariacao == null) {
			this.listVariacao = new ArrayList<>(0);
		}
		return listVariacao;
	}

	public void setListVariacao(List<VariacaoDTO> listVariacao) {
		this.listVariacao = listVariacao;
	}

	public AbaManipuladaDTO getAbaManipulada() {
		return abaManipulada;
	}

	public void setAbaManipulada(AbaManipuladaDTO abaManipulada) {
		this.abaManipulada = abaManipulada;
	}

}
