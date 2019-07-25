package com.api.dto;

import java.io.Serializable;

/**
 * @author Edmilson
 *
 */
public class ImagemDTO implements Serializable {

	private static final long serialVersionUID = -1920000384598796574L;

	private Long id;

	private String nome;

	private String type;

	private String file;

	private Integer ordem;

	private Long idProduto;

	private Long idVariacao;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public Integer getOrdem() {
		return ordem;
	}

	public void setOrdem(Integer ordem) {
		this.ordem = ordem;
	}

	public Long getIdProduto() {
		return idProduto;
	}

	public void setIdProduto(Long idProduto) {
		this.idProduto = idProduto;
	}

	public Long getIdVariacao() {
		return idVariacao;
	}

	public void setIdVariacao(Long idVariacao) {
		this.idVariacao = idVariacao;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

}
