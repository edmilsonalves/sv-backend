package com.api.entity;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import com.api.intercep.SecurityInterceptor;

@Entity
@Table(name = "sv_variacao", uniqueConstraints = @UniqueConstraint(columnNames = { "codigo", "empresa_tenant_id" }))
public class Variacao extends BaseEntity {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5937964774804136928L;

	@Column(name = "empresa_tenant_id")
	private Long empresaTenantId;

	@Column(name = "codigo", unique = true, length = 45)
	private String codigo;

	@Column(name = "id_produto", nullable = false)
	private Long idProduto;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "id_produto", referencedColumnName = "id", insertable = false, updatable = false)
	private Produto produto;

	@Column(name = "ordem", nullable = false)
	private Integer ordem;

	@Column(name = "estoque_atual", nullable = false)
	private Integer estoqueAtual;

	@Column(name = "preco_custo", precision = 10, scale = 2)
	private BigDecimal precoCusto;

	@Column(name = "preco_venda", precision = 10, scale = 2)
	private BigDecimal precoVenda;

	@Column(name = "preco_promocional", precision = 10, scale = 2)
	private BigDecimal precoPromocional;

	@Column(name = "ativo", nullable = false)
	private String ativo;

	@OneToMany(mappedBy = "variacao", fetch = FetchType.LAZY)
	private List<Imagem> listImagem;

	@OneToMany(mappedBy = "variacao", fetch = FetchType.LAZY)
	private Set<VariacaoItem> listVariacaoItem;

	@PrePersist
	@PreUpdate
	private void setEmpresaTenantId() {
		Long empresaTenantId = SecurityInterceptor.getEmpresaTenantId();
		if (empresaTenantId != null) {
			this.empresaTenantId = empresaTenantId;
		}
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

	public Produto getProduto() {
		return produto;
	}

	public void setProduto(Produto produto) {
		this.produto = produto;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
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

	public String getAtivo() {
		return ativo;
	}

	public void setAtivo(String ativo) {
		this.ativo = ativo;
	}

	public List<Imagem> getListImagem() {
		return listImagem;
	}

	public void setListImagem(List<Imagem> listImagem) {
		this.listImagem = listImagem;
	}

	public Set<VariacaoItem> getListVariacaoItem() {
		return listVariacaoItem;
	}

	public void setListVariacaoItem(Set<VariacaoItem> listVariacaoItem) {
		this.listVariacaoItem = listVariacaoItem;
	}

	public Integer getEstoqueAtual() {
		return estoqueAtual;
	}

	public void setEstoqueAtual(Integer estoqueAtual) {
		this.estoqueAtual = estoqueAtual;
	}

	public Long getEmpresaTenantId() {
		return empresaTenantId;
	}

	public void setEmpresaTenantId(Long empresaTenantId) {
		this.empresaTenantId = empresaTenantId;
	}

}
