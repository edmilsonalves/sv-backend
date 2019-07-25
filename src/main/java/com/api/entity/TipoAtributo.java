package com.api.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Filter;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

@Entity
@Table(name = "sv_tipo_atributo")
@FilterDef(name = "empresaTenant", parameters = { @ParamDef(name = "id", type = "long") })
@Filter(name = "empresaTenant", condition = "EMPRESA_TENANT_ID = :id")
public class TipoAtributo implements Serializable {

	private static final long serialVersionUID = 1220646324499746033L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id", unique = true, nullable = false)
	private Long id;

	@Column(name = "empresa_tenant_id", nullable = false)
	private Long empresaTenantId;

	@Column(name = "descricao", nullable = false, length = 45)
	private String descricao;

	@OneToMany(mappedBy = "tipoAtributo", fetch = FetchType.LAZY)
	private List<Atributo> listAtributo;

	public List<Atributo> getListAtributo() {
		return listAtributo;
	}

	public void setListAtributo(List<Atributo> listAtributo) {
		this.listAtributo = listAtributo;
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

	public Long getEmpresaTenantId() {
		return empresaTenantId;
	}

	public void setEmpresaTenantId(Long empresaTenantId) {
		this.empresaTenantId = empresaTenantId;
	}

}
