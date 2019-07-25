package com.api.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Edmilson
 *
 */
public class TipoAtributoDTO implements Serializable {

	private static final long serialVersionUID = -9197411344670674925L;

	private Long id;
	private String descricao;
	private Long idAtributoSelecionado;
	private List<OptionDTO> listAtributo;

	public TipoAtributoDTO() {
		super();
	}

	public TipoAtributoDTO(Long id) {
		super();
		this.id = id;
	}

	public Long getId() {
		return id;
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

	public Long getIdAtributoSelecionado() {
		return idAtributoSelecionado;
	}

	public void setIdAtributoSelecionado(Long idAtributoSelecionado) {
		this.idAtributoSelecionado = idAtributoSelecionado;
	}

	public List<OptionDTO> getListAtributo() {
		if (this.listAtributo == null) {
			this.listAtributo = new ArrayList<>(0);
		}
		return listAtributo;
	}

	public void setListAtributo(List<OptionDTO> listAtributo) {
		this.listAtributo = listAtributo;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
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
		TipoAtributoDTO other = (TipoAtributoDTO) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}

}
