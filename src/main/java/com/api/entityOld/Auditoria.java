package com.api.entityOld;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

/**
 * @author edmilson.reis
 */
@Entity
@Table(name = "auditoria")
public class Auditoria implements Serializable {

	private static final long serialVersionUID = -1637714637619910445L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "usuario", length = 100)
	private String usuario;

	@Column(name = "tabela", length = 100)
	private String tabela;

	@Column(name = "id_tabela")
	private Long idTabela;

	@Column(name = "operacao")
	private String operacao;

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "criado_em", nullable = false, length = 19)
	private Date criadoEm;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "criado_por", nullable = false, updatable = false, length = 45)
	private String criadoPor;

	@Column(name = "dados_antes", length = 3000)
	private String dadosAntes;

	@Column(name = "dados_depois", length = 3000)
	private String dadosDepois;

	@Column(name = "registro_excluido", length = 3000)
	private String registroExcluido;

	/**
	 * @return the usuario
	 */
	public String getUsuario() {
		return usuario;
	}

	/**
	 * @param usuario
	 *            the usuario to set
	 */
	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	/**
	 * @return the tabela
	 */
	public String getTabela() {
		return tabela;
	}

	/**
	 * @param tabela
	 *            the tabela to set
	 */
	public void setTabela(String tabela) {
		this.tabela = tabela;
	}

	/**
	 * @return the idTabela
	 */
	public Long getIdTabela() {
		return idTabela;
	}

	/**
	 * @param idTabela
	 *            the idTabela to set
	 */
	public void setIdTabela(Long idTabela) {
		this.idTabela = idTabela;
	}

	/**
	 * @return the operacao
	 */
	public String getOperacao() {
		return operacao;
	}

	/**
	 * @param operacao
	 *            the operacao to set
	 */
	public void setOperacao(String operacao) {
		this.operacao = operacao;
	}

	/**
	 * @return the criadoEm
	 */
	public Date getCriadoEm() {
		return criadoEm;
	}

	/**
	 * @param criadoEm
	 *            the criadoEm to set
	 */
	public void setCriadoEm(Date criadoEm) {
		this.criadoEm = criadoEm;
	}

	/**
	 * @return the dadosAntes
	 */
	public String getDadosAntes() {
		return dadosAntes;
	}

	/**
	 * @param dadosAntes
	 *            the dadosAntes to set
	 */
	public void setDadosAntes(String dadosAntes) {
		this.dadosAntes = dadosAntes;
	}

	/**
	 * @return the dadosDepois
	 */
	public String getDadosDepois() {
		return dadosDepois;
	}

	/**
	 * @param dadosDepois
	 *            the dadosDepois to set
	 */
	public void setDadosDepois(String dadosDepois) {
		this.dadosDepois = dadosDepois;
	}

	/**
	 * @return the id
	 */
	public Long getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(Long id) {
		this.id = id;
	}

	/**
	 * @return the criadoPor
	 */
	public String getCriadoPor() {
		return criadoPor;
	}

	/**
	 * @param criadoPor
	 *            the criadoPor to set
	 */
	public void setCriadoPor(String criadoPor) {
		this.criadoPor = criadoPor;
	}

	/**
	 * @return the registroExcluido
	 */
	public String getRegistroExcluido() {
		return registroExcluido;
	}

	/**
	 * @param registroExcluido
	 *            the registroExcluido to set
	 */
	public void setRegistroExcluido(String registroExcluido) {
		this.registroExcluido = registroExcluido;
	}

}
