package com.api.entityOld;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@Entity
@Table(name = "cliente")
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
public class Cliente extends BaseEntity {

	private static final long serialVersionUID = 4238154057986740011L;

	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "endereco_cliente_id", nullable = false)
	private EnderecoCliente enderecoCliente;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "nome", nullable = false, length = 100)
	private String nome;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "telefone", length = 25)
	private String telefone;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "telefone2", length = 25)
	private String telefone2;

	@JsonInclude(Include.NON_NULL)
	@Column(name = "cpf_cnpj", length = 25)
	private String cpfCnpj;

	@JsonInclude(Include.NON_NULL)
	@JsonFormat(pattern = "dd/MM/yyyy")
	@Column(name = "aniversario", columnDefinition = "DATE")
	private Date aniversario;

	@JsonIgnore
	@OneToMany(fetch = FetchType.EAGER, mappedBy = "cliente")
	private List<Telefone> telefoneList = new ArrayList<Telefone>();

	/**
	 * @return the enderecoCliente
	 */
	public EnderecoCliente getEnderecoCliente() {
		return enderecoCliente;
	}

	/**
	 * @param enderecoCliente
	 *            the enderecoCliente to set
	 */
	public void setEnderecoCliente(EnderecoCliente enderecoCliente) {
		this.enderecoCliente = enderecoCliente;
	}

	/**
	 * @return the nome
	 */
	public String getNome() {
		return nome;
	}

	/**
	 * @param nome
	 *            the nome to set
	 */
	public void setNome(String nome) {
		this.nome = nome;
	}

	/**
	 * @return the telefone
	 */
	public String getTelefone() {
		return telefone;
	}

	/**
	 * @param telefone
	 *            the telefone to set
	 */
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	/**
	 * @return the telefone2
	 */
	public String getTelefone2() {
		return telefone2;
	}

	/**
	 * @param telefone2
	 *            the telefone2 to set
	 */
	public void setTelefone2(String telefone2) {
		this.telefone2 = telefone2;
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
	 * @return the aniversario
	 */
	public Date getAniversario() {
		return aniversario;
	}

	/**
	 * @param aniversario
	 *            the aniversario to set
	 */
	public void setAniversario(Date aniversario) {
		this.aniversario = aniversario;
	}

	/**
	 * @return the telefoneList
	 */
	public List<Telefone> getTelefoneList() {
		return telefoneList;
	}

	/**
	 * @param telefoneList
	 *            the telefoneList to set
	 */
	public void setTelefoneList(List<Telefone> telefoneList) {
		this.telefoneList = telefoneList;
	}

}
