package com.api.dto;

import java.io.Serializable;

/**
 * @author Edmilson
 *
 */
public class EstoqueDTO implements Serializable {

	private static final long serialVersionUID = -1570044535511126149L;

	private Long id;

	private Integer minimo;

	private Integer atual;

	public Integer getMinimo() {
		return minimo;
	}

	public void setMinimo(Integer minimo) {
		this.minimo = minimo;
	}

	public Integer getAtual() {
		return atual;
	}

	public void setAtual(Integer atual) {
		this.atual = atual;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

}
