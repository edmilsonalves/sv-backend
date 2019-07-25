package com.api.entity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import com.api.enums.StatusProdutoEnum;
import com.api.enums.TipoProdutoEnum;
import com.api.intercep.SecurityInterceptor;

@Entity
@Table(name = "sv_produto", uniqueConstraints = @UniqueConstraint(columnNames = { "codigo", "empresa_tenant_id" }))
@FilterDef(name = "empresaTenant", parameters = { @ParamDef(name = "id", type = "long") })
@Filter(name = "empresaTenant", condition = "EMPRESA_TENANT_ID = :id")
public class Produto extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5937964774804136928L;

	@Column(name = "empresa_tenant_id")
	private Long empresaTenantId;

	@Column(name = "codigo", unique = true, length = 45)
	private String codigo;

	@Column(name = "nome", length = 45)
	private String nome;

	@Column(name = "descricao", length = 145)
	private String descricao;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "status", nullable = false)
	private StatusProdutoEnum status;

	@Column(name = "preco_custo", precision = 10, scale = 2)
	private BigDecimal precoCusto;

	@Column(name = "preco_venda", precision = 10, scale = 2)
	private BigDecimal precoVenda;

	@Column(name = "preco_promocional", precision = 10, scale = 2)
	private BigDecimal precoPromocional;

	@Enumerated(EnumType.ORDINAL)
	@Column(name = "tipo", nullable = false)
	private TipoProdutoEnum tipo;

	@Column(name = "movimenta_estoque", nullable = false)
	private Boolean movimentaEstoque;

	@Column(name = "possui_variacao", nullable = false)
	private Boolean possuiVariacao;

	@Column(name = "peso", precision = 10, scale = 2)
	private BigDecimal peso;

	@Column(name = "largura", precision = 10, scale = 2)
	private BigDecimal largura;

	@Column(name = "altura", precision = 10, scale = 2)
	private BigDecimal altura;

	@Column(name = "comprimento", precision = 10, scale = 2)
	private BigDecimal comprimento;

	@Column(name = "comissao", precision = 10, scale = 2)
	private BigDecimal comissao;

	@Column(name = "id_categoria", nullable = false)
	private Long idCategoria;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "id_categoria", referencedColumnName = "id", insertable = false, updatable = false)
	private Categoria categoria;

	@Column(name = "id_unidade_medida", nullable = false)
	private Long idUnidadeMedida;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_unidade_medida", referencedColumnName = "id", insertable = false, updatable = false)
	private UnidadeMedida unidadeMedida;

	@OneToOne(mappedBy = "produto")
	private Estoque estoque;

	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private List<Imagem> listImagem;

	@OneToMany(mappedBy = "produto", fetch = FetchType.LAZY)
	private Set<Variacao> listVariacao;

	@PrePersist
	@PreUpdate
	private void setEmpresaTenantId() {
		Long empresaTenantId = SecurityInterceptor.getEmpresaTenantId();
		if (empresaTenantId != null) {
			this.empresaTenantId = empresaTenantId;
		}
	}

	public Set<Variacao> getListVariacao() {
		return listVariacao;
	}

	public void setListVariacao(Set<Variacao> listVariacao) {
		this.listVariacao = listVariacao;
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

	public StatusProdutoEnum getStatus() {
		return status;
	}

	public void setStatus(StatusProdutoEnum status) {
		this.status = status;
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

	public TipoProdutoEnum getTipo() {
		return tipo;
	}

	public void setTipo(TipoProdutoEnum tipo) {
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

	public BigDecimal getComprimento() {
		return comprimento;
	}

	public void setComprimento(BigDecimal comprimento) {
		this.comprimento = comprimento;
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

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Long getIdUnidadeMedida() {
		return idUnidadeMedida;
	}

	public void setIdUnidadeMedida(Long idUnidadeMedida) {
		this.idUnidadeMedida = idUnidadeMedida;
	}

	public UnidadeMedida getUnidadeMedida() {
		return unidadeMedida;
	}

	public void setUnidadeMedida(UnidadeMedida unidadeMedida) {
		this.unidadeMedida = unidadeMedida;
	}

	public List<Imagem> getListImagem() {
		if (this.listImagem == null) {
			this.listImagem = new ArrayList<>(0);
		}
		return listImagem;
	}

	public void setListImagem(List<Imagem> listImagem) {
		this.listImagem = listImagem;
	}

	public Estoque getEstoque() {
		return estoque;
	}

	public void setEstoque(Estoque estoque) {
		this.estoque = estoque;
	}

	public Long getEmpresaTenantId() {
		return empresaTenantId;
	}

	public void setEmpresaTenantId(Long empresaTenantId) {
		this.empresaTenantId = empresaTenantId;
	}

}
