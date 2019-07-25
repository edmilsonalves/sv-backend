package com.api.dto;

public class CategoriaDTO {

	private Long id;

	private String descricao;

	public CategoriaDTO(Long id, String descricao) {
		super();
		this.id = id;
		this.descricao = descricao;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescricao() {
		return descricao;
	}

	public void setDescricao(String descricao) {
		this.descricao = descricao;
	}

	public Long getId() {
		return id;
	}

}
