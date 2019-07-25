package com.api.dto;

import java.io.Serializable;

public class OptionDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2837608332883678250L;

	private Long id;

	private String descricao;

	public OptionDTO() {
		super();
	}

	public OptionDTO(Long id, String descricao) {
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
