package com.api.entityOld;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "endereco_funcionario")
public class EnderecoFuncionario extends BaseEntity implements java.io.Serializable {

	private static final long serialVersionUID = 2852790731993385191L;

	@Column(name = "numero", length = 15)
	private String numero;

	@Column(name = "ponto_referencia", length = 145)
	private String pontoReferencia;

	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "endereco_id", nullable = false)
	private Endereco endereco;

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "enderecoFuncionario")
	private List<Funcionario> funcionarioList = new ArrayList<Funcionario>();

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
	 * @return the funcionarioList
	 */
	public List<Funcionario> getFuncionarioList() {
		return funcionarioList;
	}

	/**
	 * @param funcionarioList
	 *            the funcionarioList to set
	 */
	public void setFuncionarioList(List<Funcionario> funcionarioList) {
		this.funcionarioList = funcionarioList;
	}

}
