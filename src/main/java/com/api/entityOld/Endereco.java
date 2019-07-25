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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;

import com.api.util.SUtils;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "endereco")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Endereco implements Serializable {

	private static final long serialVersionUID = -3678585443063494817L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "cep", length = 9)
	private String cep;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "logradouro", length = 145)
	private String logradouro;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "tp_logradouro", length = 20)
	private String tipoLogradouro;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "bairro", length = 45)
	private String bairro;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "uf", length = 2)
	private String uf;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "desc_uf", length = 100)
	private String descricaoUf;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "cidade", length = 50)
	private String cidade;

	@JsonInclude(Include.NON_NULL)
	@Transient
	private String name;

	@JsonIgnore
	@OneToMany(fetch = FetchType.LAZY, mappedBy = "endereco")
	private List<EnderecoCliente> enderecoClienteList = new ArrayList<EnderecoCliente>();

	/**
	 * @return the cep
	 */
	public String getCep() {
		return SUtils.somenteNumeros(cep);
	}

	/**
	 * @param cep
	 *            the cep to set
	 */
	public void setCep(String cep) {
		this.cep = cep;
	}

	/**
	 * @return the logradouro
	 */
	public String getLogradouro() {
		return logradouro;
	}

	/**
	 * @param logradouro
	 *            the logradouro to set
	 */
	public void setLogradouro(String logradouro) {
		this.logradouro = logradouro;
	}

	/**
	 * @return the bairro
	 */
	public String getBairro() {
		return bairro;
	}

	/**
	 * @param bairro
	 *            the bairro to set
	 */
	public void setBairro(String bairro) {
		this.bairro = bairro;
	}

	/**
	 * @return the uf
	 */
	public String getUf() {
		return uf;
	}

	/**
	 * @param uf
	 *            the uf to set
	 */
	public void setUf(String uf) {
		this.uf = uf;
	}

	/**
	 * @return the cidade
	 */
	public String getCidade() {
		return cidade;
	}

	/**
	 * @param cidade
	 *            the cidade to set
	 */
	public void setCidade(String cidade) {
		this.cidade = cidade;
	}

	/**
	 * @return the enderecoClienteList
	 */
	public List<EnderecoCliente> getEnderecoClienteList() {
		return enderecoClienteList;
	}

	/**
	 * @param enderecoClienteList
	 *            the enderecoClienteList to set
	 */
	public void setEnderecoClienteList(List<EnderecoCliente> enderecoClienteList) {
		this.enderecoClienteList = enderecoClienteList;
	}

	/**
	 * @return the tipoLogradouro
	 */
	public String getTipoLogradouro() {
		return tipoLogradouro;
	}

	/**
	 * @param tipoLogradouro
	 *            the tipoLogradouro to set
	 */
	public void setTipoLogradouro(String tipoLogradouro) {
		this.tipoLogradouro = tipoLogradouro;
	}

	/**
	 * @return the descricaoUf
	 */
	public String getDescricaoUf() {
		return descricaoUf;
	}

	/**
	 * @param descricaoUf
	 *            the descricaoUf to set
	 */
	public void setDescricaoUf(String descricaoUf) {
		this.descricaoUf = descricaoUf;
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

	public String getName() {

		if (SUtils.isNull(this.name)) {
			if (null == tipoLogradouro) {
				tipoLogradouro = "";
			}
			if (null == logradouro) {
				logradouro = "";
			}
			if (null == bairro) {
				bairro = "";
			}
			if (null == cidade) {
				cidade = "";
			}
			if (null == uf) {
				uf = "";
			}

			return tipoLogradouro + " " + logradouro + " " + bairro + ", " + cidade + " - " + uf;
		}

		return this.name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((logradouro == null) ? 0 : logradouro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Endereco other = (Endereco) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (logradouro == null) {
			if (other.logradouro != null)
				return false;
		} else if (!logradouro.equals(other.logradouro))
			return false;
		return true;
	}

}
