package com.api.entityOld;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "endereco_cliente")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class EnderecoCliente implements Serializable {

	private static final long serialVersionUID = -6401010069821870914L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "numero", length = 15)
	private String numero;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "ponto_referencia", length = 145)
	private String pontoReferencia;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enderecoCliente")
	private List<Cliente> clienteList = new ArrayList<Cliente>();

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
	 * @return the endereco
	 */
	public Endereco getEndereco() {
		return endereco;
	}

	/**
	 * @param endereco
	 *            the endereco to set
	 */
	public void setEndereco(Endereco endereco) {
		this.endereco = endereco;
	}

	/**
	 * @return the numero
	 */
	public String getNumero() {
		return numero;
	}

	/**
	 * @param numero
	 *            the numero to set
	 */
	public void setNumero(String numero) {
		this.numero = numero;
	}

	/**
	 * @return the pontoReferencia
	 */
	public String getPontoReferencia() {
		return pontoReferencia;
	}

	/**
	 * @param pontoReferencia
	 *            the pontoReferencia to set
	 */
	public void setPontoReferencia(String pontoReferencia) {
		this.pontoReferencia = pontoReferencia;
	}

	/**
	 * @return the clienteList
	 */
	public List<Cliente> getClienteList() {
		return clienteList;
	}

	/**
	 * @param clienteList
	 *            the clienteList to set
	 */
	public void setClienteList(List<Cliente> clienteList) {
		this.clienteList = clienteList;
	}

}
