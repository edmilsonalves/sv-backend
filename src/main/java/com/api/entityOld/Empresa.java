package com.api.entityOld;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author edmilson.reis
 *
 */
@Entity
@Table(name = "empresa")
public class Empresa implements Serializable {

	private static final long serialVersionUID = -3430992971259834216L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "razao_social", length = 145)
	private String razaoSocial;

	@Column(name = "sigla", length = 5)
	private String sigla;

	@Column(name = "desc_sigla", length = 15)
	private String descSigla;

	@Column(name = "cpf_cnpj", unique = true)
	private String cpfCnpj;

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
	 * @return the razaoSocial
	 */
	public String getRazaoSocial() {
		return razaoSocial;
	}

	/**
	 * @param razaoSocial
	 *            the razaoSocial to set
	 */
	public void setRazaoSocial(String razaoSocial) {
		this.razaoSocial = razaoSocial;
	}

	/**
	 * @return the cpfCnpj
	 */
	public String getCpfCnpj() {
		return cpfCnpj;
	}

	/**
	 * @param cpfCnpj
	 *            the cpfCnpj to set
	 */
	public void setCpfCnpj(String cpfCnpj) {
		this.cpfCnpj = cpfCnpj;
	}

	/**
	 * @return the sigla
	 */
	public String getSigla() {
		return sigla;
	}

	/**
	 * @param sigla
	 *            the sigla to set
	 */
	public void setSigla(String sigla) {
		this.sigla = sigla;
	}

	/**
	 * @return the descSigla
	 */
	public String getDescSigla() {
		return descSigla;
	}

	/**
	 * @param descSigla
	 *            the descSigla to set
	 */
	public void setDescSigla(String descSigla) {
		this.descSigla = descSigla;
	}

}
